## 动态切换数据源作业说明

### 环境准备
1. ORM框架使用Mybatis
2. 通过Mybatis Generator生成Mapper和实体类
3. 本机配置多实例的Mysql运行，配置主从复制（具体参照上一个作业)
4. 数据源配置在application.yml（**定义url的属性名使用要是jdbc-url，不同意与单个datasource是url，否则爆找不到jdbcUrl错误**)

### 动态数据源
1. 采用Spring的AbstractRoutingDataSource，实现这个类
2. 实行虚拟方法determineCurrentLookupKey
3. 原理是AbstractRoutingDataSource维护一个Map，Key对应一个DataSource，每次要获取数据源时，通过determineCurrentLookupKey方法获取Key值
```java
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
   @Override
   protected Object determineCurrentLookupKey() {
   return DynamicDataSourceContextHolder.getDataSourceKey();
   }
}
```
### 数据源上下文
1. 数据源key的维护由上下文类DynamicDataSourceContextHolder负责
2. key是一个ThreadLocal<String>
3. DynamicDataSourceContextHolder的方法采用静态全局方法来选择key
4. 按照作业要求，从库配置多个，使用从库可以按照一定策略选择。这里实现通过轮询的方法选择从库key

### 数据源配置
1. DataSourceConfigurer配置多个数据源的bean
2. 要配置@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })，否则会有bean循环依赖
3. 主库和从库的DataSource分别配置，属性通过application.yml的前缀区分
4. 定义动态切换数据源的Bean：DynamicRoutingDataSource。要打上Primary的标签，这样默认使用该DataSource
5. 打上Primary后，不需要配置SqlSessionFactory，自动会选择Primary的DataSource注入。这里要注意，网上很多参考要重新定义SqlSessionFactory，我试了很久都报错。后来设置了Primary就可以了，不用重新定义SqlSessionFactory。
6. 定义DynamicRoutingDataSource，要注意要做以下：
```java
public DataSource dynamicDataSource() {

        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        //通过key-value的方式，把所有数据源的bean放在一个Map里面
        Map<Object, Object> dataSourceMap = new HashMap<>();
        //Stream.of(DataSourceKey.values()).forEach( k -> dataSourceMap.put(k.name(), applicationContext.getBean(k.name())));
        dataSourceMap.put(DataSourceKey.MASTER, master());
        dataSourceMap.put(DataSourceKey.SLAVE1, slave1());
        dataSourceMap.put(DataSourceKey.SLAVE2, slave2());
        dynamicRoutingDataSource.setDefaultTargetDataSource(master());
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);

        return dynamicRoutingDataSource;
    }
```
### 配置切面
1. 定义标签readOnly，打上该标签的方法采用从库。否则默认使用主库
2. 定义切面，在切面的方法实现Key的切换（就是库的切换逻辑)
```java
@Aspect
@Slf4j
@Component
public class DynamicDataSourceAspect {
    @Pointcut("@annotation(huangzihao.homework.multidatasource.data.annotation.ReadOnly)")
    public void readOnlyPointCut(){
    }

    //ReadOnly注解的方法使用从库
    @Before("readOnlyPointCut()")
    public void readOnlyBefore() throws Throwable{
        DynamicDataSourceContextHolder.useSlaveDataSource();
        log.info("Use Slave Source : " + DynamicDataSourceContextHolder.getDataSourceKey());
    }

    @After("readOnlyPointCut()")
    public void readOnlyAfter() throws Throwable{
        DynamicDataSourceContextHolder.clearDataSourceKey();
    }

}
```
3. 相应方法打上标签

### 其他问题
1. 参考 https://mianshenglee.github.io/2020/01/16/multi-datasource-2.html
2. 没有考虑事务，网上说如果使用事务，切换数据源不成功，还没研究过怎么解决


