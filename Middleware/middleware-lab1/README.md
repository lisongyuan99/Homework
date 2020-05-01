# 软件架构与中间件 实验1

添加 `src/main/resources/application.properties` 文件如下

```properties
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://数据库IP地址:数据库端口号/database_lab1?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
spring.datasource.username=数据库用户名
spring.datasource.password=数据库密码
mybatis.mapper-locations=classpath:mybatis/mapper/*Mapper.xml
```

