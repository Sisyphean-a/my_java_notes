### 使用maven添加generator插件

generator插件需要依赖数据库，所以添加上mysql依赖

一般会添加下面几个组件：web ，lombok，mybatis，mysql，annotation-api

```xml
<!--mybatis generator自动生成代码插件-->
<plugin>
    <groupId>org.mybatis.generator</groupId>
    <artifactId>mybatis-generator-maven-plugin</artifactId>
    <version>1.4.0</version>
    <configuration>
        <!--配置文件位置-->
        <configurationFile>src/main/resources/generator/generator-config.xml</configurationFile>
        <!--允许生成的同名文件覆盖-->
        <overwrite>true</overwrite>
        <!--允许显示文件生成的具体过程-->
        <verbose>true</verbose>
    </configuration>
    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.32</version>
        </dependency>
    </dependencies>
</plugin>
```

### 配置generator-config.xml

如果提示网址资源找不到，alt+enter下载即可

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
    <context id="Mysql" targetRuntime="MyBatis3" defaultModelType="flat">

        <!-- 自动检查关键字，为关键字增加反引号 -->
        <property name="autoDelimitKeywords" value="true"/>
        <property name="beginningDelimiter" value="`"/>
        <property name="endingDelimiter" value="`"/>

        <!--覆盖生成XML文件-->
        <plugin type="org.mybatis.generator.plugins.UnmergeableXmlMappersPlugin" />
        <!-- 生成的实体类添加toString()方法 -->
        <plugin type="org.mybatis.generator.plugins.ToStringPlugin"/>

        <!-- 不生成注释 -->
        <commentGenerator>
            <property name="suppressAllComments" value="true"/>
        </commentGenerator>

        <jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"
                        connectionURL="jdbc:mysql:....?characterEncoding=UTF8"
                        userId="root"
                        password="123456">
        </jdbcConnection>

        <!-- domain类的位置 -->
        <javaModelGenerator targetProject="src\main\java"
                            targetPackage="com.fusi.mywiki.domain"/>

        <!-- mapper xml的位置 -->
        <sqlMapGenerator targetProject="src\main\resources"
                         targetPackage="mapper"/>

        <!-- mapper类的位置 -->
        <javaClientGenerator targetProject="src\main\java"
                             targetPackage="com.fusi.mywiki.mapper"
                             type="XMLMAPPER"/>

        <!--<table tableName="demo" domainObjectName="Demo"/>-->
        <!--<table tableName="doc"/>-->
        <table tableName="content"/>
    </context>
</generatorConfiguration>

```

### 配置对应的application.properties

```properties
server.port=8880

spring.datasource.url=jdbc:mysql:....?characterEncoding=UTF8&autoReconnect=true
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver


# 指定Mybatis的mapper文件的位置
mybatis.mapper-locations=classpath:/mapper/**/*.xml
# 配置domain或者bean层，自动扫描对应的bean层
mybatis.type-aliases-package=com.example.demo.entity

# 打印所有的sql日志：sql, 参数, 结果
logging.level.com.fusi.wiki.mapper=trace
```

