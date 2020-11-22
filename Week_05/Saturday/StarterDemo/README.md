## 给前面课程提供的 Student/Klass/School 实现自动配置和 Starter
1. 定义配置文件的结构是以school为前缀，school.name表示school名字，school.klasses为班级的集合。班级的students为学生的集合。例子见application.properties
2. 定义配置文件类SchoolConfigurationProperties
3. 定义自动化配置类SpringBootConfiguration，引用配置文件类，school函数完成School的Bean生成
4. 定义spring.factories和spring.provides