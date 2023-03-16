## Spring的JdbcTemplate

JdbcTemplate是spring框架中提供的一个模板对象，是对原始繁琐的Jdbc API对象的简单封装

```java
// 核心对象
JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSource dataSource);

// 核心方法
int update(); // 执行增、删、改语句
List<T> query(); // 查询多个
T queryForObject(); // 查询一个
new BeanPropertyRowMapper<>(); // 实现ORM映射封装
```

### Spring整合JdbcTemplate  

基于Spring的xml配置实现账户的CRUD案例

```java
@Repository
public class AccountDaoImpl implements AccountDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Account> findAll() {
        // 编写sql
        String sql = "select * from account";
        // 执行sql
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Account.class));
    }
    
    @Override
    public Account findById(Integer id) {
        // 编写sql
        String sql = "select * from account where id = ?";
        // 执行sql
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Account.class),id);
    }
    
    @Override
    public void save(Account account) {
        // 编写sql
        String sql = "insert into account values(null,?,?)";
        // 执行sql
        jdbcTemplate.update(sql, account.getName(), account.getMoney());
    }
}
```

核心配置文件：

```xml
<!--IOC注解扫描-->
<context:component-scan base-package="com.fusi"/>

<!--加载jdbc配置文件-->
<context:property-placeholder location="classpath:jdbc.properties"/>

<!--把数据库连接池交给IOC容器-->
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="driverClassName" value="${jdbc.driver}"/>
    <property name="url" value="${jdbc.url}"/>
    <property name="username" value="${jdbc.username}"/>
    <property name="password" value="${jdbc.password}"/>
</bean>

<!--把JdbcTemplate交给IOC容器-->
<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
    <constructor-arg name="dataSource" ref="dataSource"></constructor-arg>
</bean>
```

## Spring 事务

Spring的事务控制可以分为编程式事务控制和声明式事务控制

**编程式**
	开发者直接把事务的代码和业务代码耦合到一起，在实际开发中不用
**声明式**
	开发者采用配置的方式来实现的事务控制，业务代码与事务代码实现解耦合，使用的AOP思想  

### 编程式事务控制相关对象（了解）

#### PlatformTransactionManager  

PlatformTransactionManager接口，是spring的事务管理器

| 方法                                                         | 说明               |
| ------------------------------------------------------------ | ------------------ |
| TransactionStatus getTransaction(TransactionDefinition definition); | 获取事务的状态信息 |
| void commit(TransactionStatus status)；                      | 提交事务           |
| void rollback(TransactionStatus status)；                    | 回滚事务           |

```markdown
- PlatformTransactionManager 是接口类型，不同的 Dao 层技术则有不同的实现类

- Dao层技术是jdbcTemplate或mybatis时：
		DataSourceTransactionManager
- Dao层技术是hibernate时：
		HibernateTransactionManager
- Dao层技术是JPA时：
		JpaTransactionManager
```

#### TransactionDefinition  

TransactionDefinition接口提供事务的定义信息  

| 方法                         | 说明               |
| ---------------------------- | ------------------ |
| int getIsolationLevel()      | 获得事务的隔离级别 |
| int getPropogationBehavior() | 获得事务的传播行为 |
| int getTimeout()             | 获得超时时间       |
| boolean isReadOnly()         | 是否只读           |

1. 事务隔离级别

   设置隔离级别，可以解决事务并发产生的问题，如脏读、不可重复读和虚读（幻读）

   - ISOLATION_DEFAULT 使用数据库默认级别
   - ISOLATION_READ_UNCOMMITTED 读未提交
   - ISOLATION_READ_COMMITTED 读已提交
   - ISOLATION_REPEATABLE_READ 可重复读
   - ISOLATION_SERIALIZABLE 串行化  

2. 事务传播行为

   事务传播行为指的就是当一个业务方法【被】另一个业务方法调用时，应该如何进行事务控制  

   | 参数          | 说明                                                         |
   | ------------- | ------------------------------------------------------------ |
   | **REQUIRED**  | 如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。一般的选择（默认值） |
   | **SUPPORTS**  | 支持当前事务，如果当前没有事务，就以非事务方式执行（没有事务） |
   | MANDATORY     | 使用当前的事务，如果当前没有事务，就抛出异常                 |
   | REQUERS_NEW   | 新建事务，如果当前在事务中，把当前事务挂起                   |
   | NOT_SUPPORTED | 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起     |
   | NEVER         | 以非事务方式运行，如果当前存在事务，抛出异常                 |
   | NESTED        | 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行REQUIRED 类似的操作 |

   - read-only（是否只读）：建议查询时设置为只读  
   - timeout（超时时间）：默认值是-1，没有超时限制。如果有，以秒为单位进行设置  

3. TransactionStatus

   TransactionStatus 接口提供的是事务具体的运行状态  

   | 方法                       | 说明         |
   | -------------------------- | ------------ |
   | boolean isNewTransaction() | 是否是新事务 |
   | boolean hasSavepoint()     | 是否是回滚点 |
   | boolean isRollbackOnly()   | 事务是否回滚 |
   | boolean isCompleted()      | 事务是否完成 |

> **事务管理器**通过读取**事务定义参数**进行事务管理，然后会产生一系列的**事务状态**

### 基于XML的声明式事务控制(重点)

在 Spring 配置文件中声明式的处理事务来代替代码式的处理事务。底层采用AOP思想来实现的

声明式事务控制明确事项：

- 核心业务代码(目标对象) （切入点是谁？）
- 事务增强代码(Spring已提供事务管理器)）（通知是谁？）
- 切面配置（切面如何配置？）  

快速入门：使用spring声明式事务控制转账业务  

1. 引入tx命名空间

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w2.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans
       http://www.springframework.org/s chema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop.xsd
       http://www.springframework.org/schema/tx
       http://www.springframework.org/schema/tx/spring-tx.xsd">
       
   </beans>
   ```

2. 事务管理器通知配置

   ```xml
   <!--事务管理器-->
   <bean id="transactionManager"
   class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
       <property name="dataSource" ref="dataSource" />
   </bean>
   <!--通知增强-->
   <tx:advice id="txAdvice" transaction-manager="transactionManager">
       <!--定义事务的属性-->
       <tx:attributes>
           <tx:method name="*"/>
       </tx:attributes>
   </tx:advice>
   ```

3. 事务管理器AOP配置  

   ```xml
   <!--aop配置-->
   <aop:config>
       <!--切面配置-->
       <aop:advisor advice-ref="txAdvice"
           pointcut="execution(* com.lagou.serivce..*.*(..))">
       </aop:advisor>
   </aop:config>
   ```

4. 测试事务控制转账业务代码  

#### 事务参数的配置详解

```xml
<!--
	name : 切点方法名称
	isolation : 事务的隔离级别
	propogation : 事务的传播行为
	timeout : 超时时间
	read-only : 是否只读
-->
<tx:method name="transfer" isolation="REPEATABLE_READ" propagation="REQUIRED" timeout="-1" read-only="false"/>

<!-------------------------------------------------------->
<!--CRUD常用配置-->
<tx:attributes>
    <tx:method name="save*" propagation="REQUIRED"/>
    <tx:method name="delete*" propagation="REQUIRED"/>
    <tx:method name="update*" propagation="REQUIRED"/>
    <tx:method name="find*" read-only="true"/>
    <tx:method name="*"/>
</tx:attributes>
```

### 基于注解的声明式事务控制（重点）

#### 常用注解

步骤分析：

- 修改service层，增加事务注解

  ```java
  @Service
  public class AccountServiceImpl implements AccountService {
      
      @Autowired
      private AccountDao accountDao;
      
      @Transactional(propagation = Propagation.REQUIRED, isolation =
      Isolation.REPEATABLE_READ, timeout = -1, readOnly = false)
      @Override
      public void transfer(String outUser, String inUser, Double money) {
          accountDao.out(outUser, money);
          int i = 1 / 0;
          accountDao.in(inUser, money);
      }
  }
  ```

- 修改spring核心配置文件，开启事务注解支持  

  ```xml
  <!--事务管理器-->
  <bean id="transactionManager"
  class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
      <property name="dataSource" ref="dataSource"></property>
  </bean>
  
  <!--事务的注解支持-->
  <tx:annotation-driven/>
  ```

#### 纯注解

- 核心配置类

  ```java
  @Configuration // 声明为spring配置类
  @ComponentScan("com.lagou") // 扫描包
  @Import(DataSourceConfig.class) // 导入其他配置类
  @EnableTransactionManagement // 事务的注解驱动
  public class SpringConfig {
      @Bean
      public JdbcTemplate getJdbcTemplate(@Autowired DataSource dataSource) {
          return new JdbcTemplate(dataSource);
      } 
      
      @Bean("transactionManager")
      public PlatformTransactionManager getPlatformTransactionManager(@Autowired
      DataSource dataSource) {
          return new DataSourceTransactionManager(dataSource);
      }
  }
  ```

- 数据源配置类

  ```java
  @PropertySource("classpath:jdbc.properties")
  public class DataSourceConfig {
      
      @Value("${jdbc.driver}")
      private String driver;
      @Value("${jdbc.url}")
      private String url;
      @Value("${jdbc.username}")
      private String username;
      @Value("${jdbc.password}")
      private String password;
      
      @Bean
      public DataSource getDataSource() {
          DruidDataSource dataSource = new DruidDataSource();
          dataSource.setDriverClassName(driver);
          dataSource.setUrl(url);
          dataSource.setUsername(username);
          dataSource.setPassword(password);
          return dataSource;
      }
  }
  ```

## Spring集成web环境

应用上下文对象是通过 new ClasspathXmlApplicationContext(spring配置文件) 方式获取的，但是每次从容器中获得Bean时都要编写 new ClasspathXmlApplicationContext(spring配置文件) ，这样的弊端是配置文件加载多次，应用上下文对象创建多次  

Spring提供了一个监听器ContextLoaderListener就是对上述功能的封装，该监听器内部加载Spring配置文件，创建应用上下文对象，并存储到ServletContext域中，提供了一个客户端工具WebApplicationContextUtils供使用者获得应用上下文对象  

使用方式：

1. 导入spring-web坐标

   ```xml
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-context</artifactId>
       <version>5.1.5.RELEASE</version>
   </dependency>
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-web</artifactId>
       <version>5.1.5.RELEASE</version>
   </dependency>
   ```

   

2. 在web.xml中配置ContextLoaderListener监听器

   ```xml
   <!--全局参数-->
   <context-param>
       <param-name>contextConfigLocation</param-name>
       <param-value>classpath:applicationContext.xml</param-value>•
   </context-param>
   
   <!--Spring的监听器-->
   <listener>
       <listener-class>
       org.springframework.web.context.ContextLoaderListener
       </listener-class>
   </listener>
   ```

3. 使用WebApplicationContextUtils获得应用上下文对象ApplicationContext  

   ```java
   ApplicationContext applicationContext =
   WebApplicationContextUtils.getWebApplicationContext(servletContext);
       Object obj = applicationContext.getBean("id");
   ```

   











