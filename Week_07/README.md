### Week07 作业题目（周四）

#### 按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率

1. 使用JDBC插入，代码 <https://github.com/Albertsirius/JAVA-000/tree/main/Week_07/SQLDemo>
- 使用逐条insert的方式，耗时大概642s
- 使用prepareStatement的批量插入方式，耗时大概566s

2. 准备好100万条数据的数据文件，采用load data的插入方式，耗时12.11s
3. 结论，大量数据插入，采用load data方式效率更好。

### Week07 作业题目（周六）

#### 配置一遍异步复制，半同步复制、组复制

配置说明见https://github.com/Albertsirius/JAVA-000/tree/main/Week_07/MySQL_MultiInstance

#### 读写分离 - 动态切换数据源版本 1.0

作业见https://github.com/Albertsirius/JAVA-000/tree/main/Week_07/multi-datasource1

#### 读写分离 - 数据库框架版本 2.0

作业见https://github.com/Albertsirius/JAVA-000/tree/main/Week_07/multi-datasource2