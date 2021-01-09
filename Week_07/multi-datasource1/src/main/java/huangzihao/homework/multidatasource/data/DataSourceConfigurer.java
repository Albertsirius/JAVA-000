package huangzihao.homework.multidatasource.data;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置类，每个数据源生成一个bean
 */

@Configuration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class DataSourceConfigurer {

    /**
     * 主数据源Bean，要设置为Primary
     *
     * @return
     */
    @Bean(name="master")
    @ConfigurationProperties(prefix = "spring.master")
    public DataSource master() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 以下配置从数据源Bean
     */
    @Bean(name="slave1")
    @ConfigurationProperties(prefix = "spring.slave1")
    public DataSource slave1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="slave2")
    @ConfigurationProperties(prefix = "spring.slave2")
    public DataSource slave2() {
        return DataSourceBuilder.create().build();
    }


    /**
     * 动态数据源bean，初始化配置这个bean作为数据源
     * @return
     */
    @Bean
    @Primary
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

    /**
     * 配置SqlSessionFactoryBean，注入动态数据源Bean
     * @return
     */
    //@Bean
    //@ConfigurationProperties(prefix = "mybatis")
    /*public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setTypeAliasesPackage("huangzihao.homework.multidatasource.mapper");
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource("classpath:mappers/*.xml"));
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        return sqlSessionFactoryBean;
    }*/

    /**
     * 配置事务管理
     * @return
     */
/*    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }*/


}
