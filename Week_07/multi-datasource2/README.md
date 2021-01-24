## 使用shardingsphere-jdbc动态切换数据源

### 环境准备
参考上一题的例子

### 配置
1. 引入依赖
```xml
<dependency>
            <groupId>org.apache.shardingsphere</groupId>
            <artifactId>sharding-jdbc-spring-boot-starter</artifactId>
            <version>4.1.1</version>
</dependency>
```
2. 添加配置
```yaml
spring:
  shardingsphere:
    datasource:
      names: master,slave1,slave2
      master:
        type: com.zaxxer.hikari.HikariDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        jdbc-url: jdbc:mysql://127.0.0.1:3307/emall
        username: root
      slave1:
        type: com.zaxxer.hikari.HikariDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        jdbc-url: jdbc:mysql://127.0.0.1:3308/emall
        username: root
      slave2:
        type: com.zaxxer.hikari.HikariDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        jdbc-url: jdbc:mysql://127.0.0.1:3309/emall
        username: root
    masterslave:
      load-balance-algorithm-type: round_robin
      name: ms
      master-data-source-name: master
      slave-data-source-names: slave1,slave2
    props:
      sql:
        show: true
```
**type一定要添加连接池，否则报错**
mybatis的配置不变
3. 其他mabatis的定义见上题