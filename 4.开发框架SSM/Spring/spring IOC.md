## Spring概述

Spring是分层的 Java SE/EE应用full-stack**(全栈式)轻量级开源框架**

提供了**表现层SpringMVC**和**持久层Spring JDBC Template**以及**业务层**事务管理等众多的企业级应用技术，还能整合开源世界众多著名的第三方框架和类库，逐渐成为使用最多的Java EE企业应用开源框架

两大核心：

- IOC（Inverse Of Control：控制反转）
- AOP（Aspect Oriented Programming：面向切面编程）

### Spring优势

1. 方便解耦，简化开发

   *Spring就是一个容器，可以将所有对象创建和关系维护交给Spring管理*

2. AOP编程的支持

   *Spring提供面向切面编程，方便实现程序进行权限拦截，运行监控等功能*

3. 声明式事务的支持

   *通过配置完成事务的管理，无需手动编程*

4. 方便测试，降低JavaEE API的使用

   *Spring对Junit4支持，可以使用注解测试*

5. 方便集成各种优秀框架

   *不排除各种优秀的开源框架，内部提供了对各种优秀框架的直接支持*

```
+-------------------------------------------------------------------------+
|                               Spring Architecture                       |
+-------------------------------------------------------------------------+
|  +-------------------+ +--------------------+ +----------------------+  |
|  |   Core Container  | |        Web         | |        Data          |  |
|  |                   | |                    | |  Access/Integration  |  |
|  |  +-------------+  | |  +--------------+  | |  +----------------+  |  |
|  |  |    Bean     |  | |  | Spring MVC   |  | |  |     JDBC       |  |  |
|  |  +-------------+  | |  +--------------+  | |  |     ORM        |  |  |
|  |  |    Core     |  | |  |  WebSocket   |  | |  |     OXM        |  |  |
|  |  +-------------+  | |  +--------------+  | |  |     JMS        |  |  |
|  |  |   Context   |  | |  |              |  | |  |  Transaction   |  |  |
|  |  +-------------+  | |  +--------------+  | |  |  Management    |  |  |
|  |  |  Expression |  | |                    | |  +----------------+  |  |
|  |  |  Language   |  | |                    | |                      |  |
|  |  +-------------+  | |                    | |                      |  |
|  +-------------------+ +--------------------+ +----------------------+  |
|                                                                         |
|      +---------------------+ +---------------+ +---------------+        |
|      |         AOP         | |    Messaging  | |     Test      |        |
|      |  (Aspect-Oriented)  | |               | |               |        |
|      | +----------------+  | | +-----------+ | |  +---------+  |        |
|      | |    AspectJ     |  | | |   STOMP   | | |  |  JUnit  |  |        |
|      | +----------------+  | | +-----------+ | |  +---------+  |        |
|      |                     | |               | |               |        |
|      +---------------------+ +---------------+ +---------------+        |
+-------------------------------------------------------------------------+
```

![img](./spring IOC.assets/spring-overview.png)

## IOC基础

### 概述

控制反转（Inverse Of Control）不是什么技术，而是一种设计思想。它的目的是指导我们设计出更加松耦合的程序

> 控制：在java中指的是对象的控制权限（创建、销毁）
> 反转：指的是对象控制权由原来 由开发者在类中手动控制 反转到 由Spring容器控制
>
> 理解：不再自己new实例，而是将new这个过程转交给spring，创建者反转了

### 自定义IOC容器

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans>
	<bean id="userDao" class="com.fusi.dao.impl.UserDaoImpl"></bean>
</beans>
```

## Spring快速入门

需求：借助spring的IOC实现service层与dao层代码解耦合  

```markdown
1. 创建java项目，导入spring开发基本坐标
2. 编写Dao接口和实现类
3. 创建spring核心配置文件
4. 在spring配置文件中配置 UserDaoImpl
5. 使用spring相关API获得Bean实例
```

1. 导入坐标

   ```xml
   <dependencies>
   	<dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-context</artifactId>
           <version>5.3.20</version>
       </dependency>
       <dependency>
           <groupId>junit</groupId>
           <artifactId>junit</artifactId>
           <version>4.13.1</version>
       </dependency>
   </dependencies>
   ```

2. Dao层

   ```java
   public interface UserDao {
   	public void save();
   }
   
   // ----------------------------------------------
   
   public class UserDaoImpl implements UserDao {
       public void save() {
           System.out.println("保存成功");
       }
   }
   ```

3. 创建Spring核心配置文件`applicationContex.xml`，并配置`UserDaoImpl`

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">
       
       <bean id="userDao" class="com.fusi.dao.impl.UserDaoImpl"></bean>
       
   </beans>
   ```

4. 使用Spring相关API获得Bean实例

   ```java
   public class UserTest {
       
       @Test
       public void testSave() throws Exception {
           // 导入配置文件applicationContex.xml，创建Spring的ApplicationContext容器对象
           ApplicationContext applicationContext = 
               new ClassPathXmlApplicationContext("applicationContext.xml");
   
           // 通过getBean()方法获取名为"userDao"的Bean对象，并将其转换为UserDao类型
           UserDao userDao = (UserDao) applicationContext.getBean("userDao");
   
        	// 调用方法   
           userDao.save();
       }
   }
   ```

总结：

```markdown
### Spring开发步骤：
1. 导入坐标
2. 创建Bean
3. 创建applicationContext.xml
4. 在配置文件中进行Bean配置
5. 创建ApplicationContext对象，执行getBean
```

## Spring相关API

Spring的API体系异常庞大，只关注两个BeanFactory和ApplicationContext  

 ### BeanFactory

BeanFactory是 IOC 容器的核心接口，它定义了IOC的基本功能  

特点：在第一次调用getBean()方法时，创建指定对象的实例  

```java
BeanFactory beanFactory = 
    new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
```

### ApplicationContext  

代表应用上下文对象，可以获得spring中IOC容器的Bean对象  

特点：在spring容器启动时，加载并创建所有对象的实例  

- 常用实现类：

  ```markdown
  1. ClassPathXmlApplicationContext
  	它是从类的根路径下加载配置文件 推荐使用这种
  2. FileSystemXmlApplicationContext
  	它是从磁盘路径上加载配置文件，配置文件可以在磁盘的任意位置
  3. AnnotationConfigApplicationContext
  	当使用注解配置容器对象时，需要使用此类来创建 spring 容器。它用来读取注解
  ```

- 常用方法

  ```java
  1. Object getBean(String name);
  	// 根据Bean的id从容器中获得Bean实例，返回是Object，需要强转
  2. <T> T getBean(Class<T> requiredType);
  	// 根据类型从容器中匹配Bean实例，当容器中相同类型的Bean有多个时，则此方法会报错
  3. <T> T getBean(String name,Class<T> requiredType);
  	// 根据Bean的id和类型获得Bean实例，解决容器中相同类型Bean有多个情况
  ```

## Spring配置文件

### Bean标签基本配置

```xml
<bean id="" class="" />
<!--
	用于配置对象交由Spring来创建
    id：Bean实例在Spring容器中的唯一标识
    class：Bean的全限定名
	默认情况下它调用的是类中的无参构造函数，如果没有无参构造函数则不能创建成功
-->
```

### Bean标签范围配置

```xml
<bean id="" class="" scope="" />
```

scope属性指对象的作用范围：

| 取值范围       | 说明                                                         |
| -------------- | ------------------------------------------------------------ |
| **singleton**  | 默认值，单例的                                               |
| **prototype**  | 多例的                                                       |
| request        | WEB项目中，Spring创建一个Bean的对象，将对象存入到request域中 |
| session        | WEB项目中，Spring创建一个Bean的对象，将对象存入到session域中 |
| global session | WEB项目中，应用在Portlet环境，如果没有Portlet环境那么globalSession 相当 于 session |

```markdown
1. 当scope的取值为singleton时
    Bean的实例化个数：1个
    Bean的实例化时机：当Spring核心文件被加载时，实例化配置的Bean实例
    Bean的生命周期：
        对象创建：当应用加载，创建容器时，对象就被创建了
        对象运行：只要容器在，对象一直活着
        对象销毁：当应用卸载，销毁容器时，对象就被销毁了
        
2. 当scope的取值为prototype时
    Bean的实例化个数：多个
    Bean的实例化时机：当调用getBean()方法时实例化Bean
    Bean的生命周期：
        对象创建：当使用对象时，创建新的对象实例
        对象运行：只要对象在使用中，就一直活着
        对象销毁：当对象长时间不用时，被 Java 的垃圾回收器回收了
```

### Bean生命周期配置  

```xml
<bean id="" class="" scope="" init-method="" destroy-method="" />
<!--
    init-method：指定类中的初始化方法名称
    destroy-method：指定类中销毁方法名称
-->
```

### Bean实例化三种方式

1. 无参构造方法实例化

   最常用的方式。但如果bean中没有默认无参构造函数，将会创建失败  

   ```xml
   <bean id="userDao" class="com.fusi.dao.impl.UserDaoImpl"/>
   ```

2. 工厂静态方法实例化

   适用于Bean实例化需要调用一些静态方法来产生新实例的场景，因为静态工厂方法的特性意味着调用时省略了实例化Bean的过程

   ```java
   public class StaticFactoryBean {
       public static UserDao createUserDao(){
       	return new UserDaoImpl();
       }
   }
   ```

   ```xml
   <bean id="userDao" class="com.fusi.factory.StaticFactoryBean"
   factory-method="createUserDao" />
   ```

3. 工厂普通方法实例化  

   适用于需要创建特定类型的 Bean 实例，并且希望灵活地定义 Bean 实例化过程的场景

   ```java
   public class DynamicFactoryBean {
       public UserDao createUserDao(){
           return new UserDaoImpl();	
       }
   }
   ```

   ```xml
   <bean id="dynamicFactoryBean" class="com.fusi.factory.DynamicFactoryBean"/>
   <bean id="userDao" factory-bean="dynamicFactoryBean" factorymethod="createUserDao"/>
   ```

### Bean依赖注入概述  

**依赖注入DI**（Dependency Injection）：它是 Spring 框架核心 IOC 的具体实现  

指在Spring框架中将实例化的Bean对象之间的依赖关系，由Spring容器负责自动注入，而不是通过代码中硬编码的方式实现

也就是说，Spring容器会自动将需要注入的属性或构造函数参数的实例对象注入到应该接收这些实例的Bean对象中去

这种方式解耦了Bean之间的依赖关系

### Bean依赖注入方式

1. 构造方法

   在UserServiceImpl中创建有参构造  

   ```java
   public class UserServiceImpl implements UserService {
       
       private UserDao userDao;
       
       public UserServiceImpl(UserDao userDao) {
           this.userDao = userDao;
       } 
      
       ......
   }
   ```

   配置Spring容器调用有参构造时进行注入  

   ```xml
   <!--创建一个userDao的Bean对象-->
   <bean id="userDao" class="com.fusi.dao.impl.UserDaoImpl"/>
   
   <!--创建一个userServer的Bean对象，然后把userDao作为参数传入-->
   <bean id="userService" class="com.fusi.service.impl.UserServiceImpl">
   	<constructor-arg name="userDao" ref="userDao"/>
   </bean>
   ```

2. set方法

   ```java
   public class UserServiceImpl implements UserService {
       
       private UserDao userDao;
       
       public void setUserDao(UserDao userDao) {
           this.userDao = userDao;
       }
       
       ......
   }
   ```

   ```xml
   <!--创建一个userDao的Bean对象-->
   <bean id="userDao" class="com.fusi.dao.impl.UserDaoImpl"/>
   
   <!--创建一个userServer的Bean对象，然后把userDao作为参数传入-->
   <bean id="userService" class="com.fusi.service.impl.UserServiceImpl">
   	<property name="userDao" ref="userDao"/>
   </bean>
   ```

3. P命名空间注入

   P命名空间注入本质也是set方法注入，使用较少，一般不用

   ```xml
   首先引入P命名空间
   xmlns:p="http://www.springframework.org/schema/p"
   
   其次修改注入方式
   <bean id="userDao" class="com.fusi.dao.impl.UserDaoImpl"/>
   <bean id="userService" class="com.fusi.service.impl.UserServiceImpl"
   p:userDao-ref="userDao"/>
   ```

### Bean依赖注入的数据类型

除了对象的引用可以注入，普通数据类型和集合也可以在容器中进行注入

```java
public class UserDaoImpl implements UserDao {
    private List<Object> list;
    private Set<Object> set;
    private Object[] array;
    private Map<String, Object> map;
    private Properties properties;

    public void setList(List<Object> list) {...}
    public void setSet(Set<Object> set) {...}
    public void setArray(Object[] array) {...}
    public void setMap(Map<String, Object> map) {...}
    public void setProperties(Properties properties) {...}
}
```

1. 普通数据类型

   ```xml
   <bean id="user" class="com.fusi.domain.User">
       <property name="username" value="jack"/>
       <property name="age" value="18"/>
   </bean>
   ```

2. 集合数据类型  

   

   ```xml
   <bean id="userDao" class="com.fusi.dao.impl.UserDaoImpl">
       
       <!--list集合-->
       <property name="list">
           <list>
               <value>aaa</value>
               <ref bean="user"></ref>
           </list>
       </property>
       
       <!--set集合-->
       <property name="set">
           <list>
               <value>bbb</value>
               <ref bean="user" />
           </list>
       </property>
       
       <!--array数组-->
       <property name="array">
           <array>
               <value>ccc</value>
               <ref bean="user"></ref>
           </array>
       </property>
       
       <!--map集合-->
       <property name="map">
           <map>
               <entry key="k1" value="ddd"/>
               <entry key="k2" value-ref="user"></entry>
           </map>
       </property>
       
       <!--Properties配置-->
       <property name="properties">
           <props>
               <prop key="k1">v1</prop>
               <prop key="k2">v2</prop>
               <prop key="k3">v3</prop>
           </props>
       </property>
       
   </bean>
   ```

### 配置文件模块话

实际开发中，Spring的配置内容非常多，这就导致Spring配置很繁杂且体积很大，所以，可以将部分配置拆解到其他配置文件中，也就是所谓的配置文件模块化

1. 并列的多个配置文件（不常用）

   ```java
   ApplicationContext act =
       new ClassPathXmlApplicationContext("beans1.xml","beans2.xml","...");
   ```

2. 主从配置文件（常用）

   ```xml
   <import resource="applicationContext-xxx.xml"/>
   ```

> 同一个xml中不能出现相同名称的bean,如果出现会报错
>
> 多个xml如果出现相同名称的bean，不会报错，但是后加载的会覆盖前加载的bean  

### 知识总结

```xml
<bean>标签：创建对象并放到spring的IOC容器
    id属性:在容器中Bean实例的唯一标识，不允许重复
    class属性:要实例化的Bean的全限定名
    scope属性:Bean的作用范围，常用是Singleton(默认)和prototype
    
<constructor-arg>注入 Bean 对象的构造方法参数
    name属性：属性名称
    value属性：注入的普通属性值
    ref属性：注入的对象引用值
    
<property>注入 Bean 对象的属性,即通过 setter 方法来注入依赖
    name属性：属性名称
    value属性：注入的普通属性值
    ref属性：注入的对象引用值
    <list> , <set>....
   
推荐使用 <constructor-arg> 来注入需要被构造函数显式依赖的对象，
        使用 <property> 来注入不需要显式依赖的对象、新添加的属性等可选配置
        
<import>标签:导入其他的Spring的分文件
```

## Spring注解开发

Spring是轻代码而重配置的框架，配置比较繁重，影响开发效率，所以注解开发是一种趋势，注解代替xml配置文件可以简化配置，提高开发效率  

### Spring常用注解

Spring常用注解主要是替代 `<bean> `的配置

| 注解            | 说明                                          |
| --------------- | --------------------------------------------- |
| **@Component**  | 使用在类上用于实例化Bean                      |
| **@Controller** | 使用在web层类上用于实例化Bean                 |
| **@Service**    | 使用在service层类上用于实例化Bean             |
| **@Repository** | 使用在dao层类上用于实例化Bean                 |
| **@Autowired**  | 使用在字段上用于根据类型依赖注入              |
| **@Qualifier**  | 结合@Autowired一起使用,根据名称进行依赖注入   |
| @Resource       | 相当于@Autowired+@Qualifier，按照名称进行注入 |
| **@Value**      | 注入普通属性                                  |
| **@Scope**      | 标注Bean的作用范围                            |
| @PostConstruct  | 使用在方法上标注该方法是Bean的初始化方法      |
| @PreDestroy     | 使用在方法上标注该方法是Bean的销毁方法        |

使用注解进行开发时，需要在applicationContext.xml中配置组件扫描，作用是指定哪个包及其子包下的Bean需要进行扫描以便识别使用注解配置的类、字段和方法  

```xml
<!--注解的组件扫描-->
<context:component-scan base-package="com.fusi"></context:component-scan>
```

1. Bean实例化（IOC）

   ```java
   // 使用xml的方式
   <bean id="userDao" class="com.lagou.dao.impl.UserDaoImpl"></bean>
   
   // 使用@Compont或@Repository标识UserDaoImpl需要Spring进行实例化
   // @Component("userDao")
   @Repository // 如果没有写value属性值，Bean的id为：类名首字母小写
   public class UserDaoImpl implements UserDao {...}
   ```

2. 属性依赖注入（DI）  

   ```xml
   <bean id="userService" class="com.lagou.service.impl.UserServiceImpl">
   	<property name="userDao" ref="userDaoImpl"/>
   </bean>
   ```

   ```java
   // 使用@Autowired或者@Autowired+@Qulifier进行userDao的注入
   
   @Service
   public class UserServiceImpl implements UserService {
       
       @Autowired
       private UserDao userDao;
       
       public void setUserDao(UserDao userDao) {
           this.userDao = userDao;
       }
   }
   ```

3. @Value

   ```java
   // 使用@Value进行字符串的注入，结合SPEL表达式获得配置参数
   @Service
   public class UserServiceImpl implements UserService {
       
       @Value("注入普通数据")
       private String str;
       
       @Value("${jdbc.driver}")
       private String driver;
   }
   ```

4. @Scope

   ```java
   // 使用@Scope标注Bean的范围
   @Service
   @Scope("singleton")
   public class UserServiceImpl implements UserService {
   ```

5. Bean生命周期

   ```java
   @PostConstruct
   public void init(){
       System.out.println("初始化方法....");
   } 
   
   @PreDestroy
   public void destroy(){
       System.out.println("销毁方法.....");
   }
   ```

### Spring新注解

上面的注解还不能全部替代xml配置文件，还需要使用注解替代的配置如下：  

```xml
非自定义的Bean的配置：<bean>
加载properties文件的配置：<context:property-placeholder>
组件扫描的配置：<context:component-scan>
引入其他文件：<import>
```

| 注解            | 说明                                                         |
| --------------- | ------------------------------------------------------------ |
| @Configuration  | 用于指定当前类是一个Spring 配置类，当创建容器时会从该类上加载注解 |
| @Bean           | 使用在方法上，标注将该方法的返回值存储到 Spring 容器中       |
| @PropertySource | 用于加载 properties 文件中的配置                             |
| @ComponentScan  | 用于指定 Spring 在初始化容器时要扫描的包                     |
| @Import         | 用于导入其他配置类                                           |

1. 编写Spring核心配置类  

   ```java
   // 标注当前类为Spring核心配置类
   @Configuration
   // 开启Spring注解扫描
   @ComponentScan("com.lagou")
   // 导入数据库配置信息类
   @Import(DataSourceConfig.class)
   public class SpringConfig {
       @Bean("queryRunner")
       public QueryRunner getQueryRunner(@Autowired DataSource dataSource) {
       return new QueryRunner(dataSource);
       }
   }
   ```

2. 编写数据库配置信息类  

   ```java
   // 使用classpath导入数据库配置文件
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
       
       @Bean("dataSource")
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

## Spring整合Junit  

在普通的测试类中，需要开发者手动加载配置文件并创建Spring容器，然后通过Spring相关API获得Bean实例；如果不这么做，那么无法从容器中获得对象。  

```java
ApplicationContext applicationContext =
	new ClassPathXmlApplicationContext("applicationContext.xml");
AccountService accountService =
	applicationContext.getBean(AccountService.class);
```

我们可以让SpringJunit负责创建Spring容器来简化这个操作，开发者可以直接在测试类注入Bean实例；但是需要将配置文件的名称告诉它  

1. 导入spring集成Junit的坐标  

   ```xml
   <!--此处需要注意的是，spring5 及以上版本要求 junit 的版本必须是 4.12 及以上-->
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-test</artifactId>
       <version>5.1.5.RELEASE</version>
   </dependency>
   <dependency>
       <groupId>junit</groupId>
       <artifactId>junit</artifactId>
       <version>4.12</version>
       <scope>test</scope>
   </dependency>
   ```

2. 使用@Runwith注解替换原来的运行器  

   ```java
   @RunWith(SpringJUnit4ClassRunner.class)
   public class SpringJunitTest {}
   ```

3. 使用@ContextConfiguration指定配置文件或配置类  

   ```java
   @RunWith(SpringJUnit4ClassRunner.class)
   //@ContextConfiguration(value = {"classpath:applicationContext.xml"}) 
   @ContextConfiguration(classes = {SpringConfig.class}) 
   public class SpringJunitTest {}
   ```

4. 使用@Autowired注入需要测试的对象  

   ```java
   @RunWith(SpringJUnit4ClassRunner.class)
   @ContextConfiguration(classes = {SpringConfig.class})
   public class SpringJunitTest {
       @Autowired
       private AccountService accountService;
   }
   ```

   











