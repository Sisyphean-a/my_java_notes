## AOP基础

### 什么是AOP

AOP 为 Aspect Oriented Programming 的缩写，意思为**面向切面编程**

它是OOP（面向对象编程）的扩展。

在传统的OOP编程中，我们的程序是由各个对象协同完成，如果我们想要对某个对象的方法进行增强、修改，需要修改这个方法的代码（在方法前后添加代码）。这种方式会使得代码的可读性、可维护性变得很差。

而在AOP编程中，我们会把横切逻辑（例如：日志输出、性能统计等）与业务逻辑（核心功能）分离，通过"切面"编程的方式，不改变原有的核心业务逻辑，来增强或控制原有的系统行为。这样可以实现代码的重用，提高系统可维护性、扩展性和灵活性。

> AOP 的底层是通过 Spring 提供的的动态代理技术实现的  

```markdown
## AOP 相关术语

- Target（目标对象）：代理的目标对象

- Proxy （代理）：一个类被 AOP 织入增强后，就产生一个结果代理类

- Joinpoint（连接点）：所谓连接点是指那些可以被拦截到的点。
	在spring中,这些点指的是方法，因为spring只支持方法类型的连接点

- Pointcut（切入点）：所谓切入点是指我们要对哪些 Joinpoint 进行拦截的定义

- Advice（通知/ 增强）：所谓通知是指拦截到 Joinpoint 之后所要做的事情就是通知分类：
	前置通知、后置通知、异常通知、最终通知、环绕通知

- Aspect（切面）：是切入点和通知（引介）的结合

- Weaving（织入）：是指把增强应用到目标对象来创建新的代理对象的过程。
	spring采用动态代理织入，而AspectJ采用编译期织入和类装载期织入
```

### AOP开发

1. 开发阶段

   ```markdown
   1. 编写核心业务代码（目标类的目标方法） 切入点
   
   2. 把公用代码抽取出来，制作成通知（增强功能方法） 通知
   
   3. 在配置文件中，声明切入点与通知间的关系，即切面
   ```

2. 运行阶段（Spring完成）

   Spring 框架监控切入点方法的执行。一旦监控到切入点方法被运行，使用代理机制，动态创建目标对象的代理对象，根据通知类别，在代理对象的对应位置，将通知对应的功能织入，完成完整的代码逻辑运行

3. 底层代理实现  

   在 Spring 中，框架会根据目标类是否实现了接口来决定采用哪种动态代理的方式

   - 当bean实现接口时，会用JDK代理模式  

   - 当bean没有实现接口，用cglib实现，也可以强制使用cglib

     **<aop:aspectjautoproxy proxyt-target-class="true"/>**

## 基于XML的AOP开发

步骤分析：

```markdown
1. 创建java项目，导入AOP相关坐标
2. 创建目标接口和目标实现类（定义切入点）
3. 创建通知类及方法（定义通知）
4. 将目标类和通知类对象创建权交给spring
5. 在核心配置文件中配置织入关系，及切面
6. 编写测试代码
```

1. 导入AOP相关坐标

   ```xml
   <dependencies>
       <!--导入spring的context坐标，context依赖aop-->
       <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-context</artifactId>
           <version>5.3.20</version>
       </dependency>
       <!-- aspectj的织入（切点表达式需要用到该jar包） -->
       <dependency>
           <groupId>org.aspectj</groupId>
           <artifactId>aspectjweaver</artifactId>
           <version>1.8.13</version>
       </dependency>
       <!--spring整合junit-->
       <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-test</artifactId>
           <version>5.1.5.RELEASE</version>
       </dependency>
       <dependency>
           <groupId>junit</groupId>
           <artifactId>junit</artifactId>
           <version>4.13.1</version>
       </dependency>
   </dependencies>
   ```

2. 创建目标接口和目标实现类  

   ```java
   public interface AccountService {
       public void transfer();
   }
   
   // -------------------------------------
   
   public class AccountServiceImpl implements AccountService {
       @Override
       public void transfer() {
           System.out.println("转账业务...");
       }
   }
   ```

3. 创建通知类

   ```java
   public class MyAdvice {
       public void before() {
           System.out.println("前置通知...");
       }
   }
   ```

4. 将目标类和通知类对象创建权交给spring  

   ```xml
   <!--目标类交给IOC容器-->
   <bean id="accountService" class="com.lagou.service.impl.AccountServiceImpl" />
   
   <!--通知类交给IOC容器-->
   <bean id="myAdvice" class="com.lagou.advice.MyAdvice" />
   ```

5. 在核心配置文件中配置织入关系，及切面  

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:aop="http://www.springframework.org/schema/aop"
          xsi:schemaLocation="
           http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/aop
           http://www.springframework.org/schema/aop/spring-aop.xsd">
   
       <!--目标类交给IOC容器-->
       <bean id="accountService" class="com.lagou.service.impl.AccountServiceImpl" />
       <!--通知类交给IOC容器-->
       <bean id="myAdvice" class="com.lagou.advice.MyAdvice" />
       
       <aop:config>
           <!--引入通知类-->
           <aop:aspect ref="myAdvice">
               <!--配置目标类的transfer方法执行时，使用通知类的before方法进行前置增强-->
               <aop:before method="before" pointcut="execution(public void com.lagou.service.impl.AccountServiceImpl.transfer())" />
           </aop:aspect>
       </aop:config>
       
   </beans>
   ```

### XML配置AOP详解

#### 切点表达式  

- 表达式语法：`execution([修饰符] 返回值类型 包名.类名.方法名(参数))`

  > 访问修饰符可以省略
  > 返回值类型、包名、类名、方法名可以使用星号 * 代替，代表任意
  > 包名与类名之间一个点 . 代表当前包下的类，两个点 .. 表示当前包及其子包下的类
  > 参数列表可以使用两个点 .. 表示任意个数，任意类型的参数列表  

- 切点表达式抽取 ：

  ```xml
  <aop:config>
      <!--抽取的切点表达式-->
      <aop:pointcut id="myPointcut" 
                    expression="execution(* com.lagou.service..*.*(..))" /> 
      <aop:aspect ref="myAdvice">
          <aop:before method="before" pointcut-ref="myPointcut" />
      </aop:aspect>
  </aop:config>
  ```

#### 通知类型

通知的配置语法：

`<aop:通知类型 method="通知类中方法名" pointcut="切点表达式"></aop:通知类型>  `

| 名称     | 标签                  | 说明                                     |
| -------- | --------------------- | ---------------------------------------- |
| 前置通知 | \<aop:before>         | 指定增强的方法在切入点方法之前执行       |
| 后置通知 | \<aop:afterReturning> | 指定增强的方法在切入点方法之后执行       |
| 异常通知 | \<aop:afterThrowing>  | 指定增强的方法出现异常后执行             |
| 最终通知 | \<aop:after>          | 无论切入点方法执行时是否有异常，都会执行 |
| 环绕通知 | \<aop:around\>        | 开发者可以手动控制增强代码在什么时候执行 |

> 通常情况下，环绕通知都是独立使用的  

## 基于注解的AOP开发

- 将目标类和通知类对象创建权交给spring  

  ```java
  @Service
  public class AccountServiceImpl implements AccountService {}
  
  @Component
  public class MyAdvice {}
  ```

- 在通知类中使用注解配置织入关系，升级为切面类  

  ```java
  @Component
  @Aspect
  public class MyAdvice {
      
      @Before("execution(* com.lagou..*.*(..))")
      public void before() {
          System.out.println("前置通知...");
      }
  }
  ```

- 在配置文件中开启组件扫描和 AOP 的自动代理  

  ```xml
  <!--组件扫描-->
  <context:component-scan base-package="com.lagou"/>
  
  <!--aop的自动代理-->
  <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
  ```

### 注解配置AOP详解  

- 切点表达式的抽取  

  ```java
  @Component
  @Aspect
  public class MyAdvice {
      
      @Pointcut("execution(* com.lagou..*.*(..))")
      public void myPoint(){}
      
      @Before("MyAdvice.myPoint()")
      public void before() {
          System.out.println("前置通知...");
      }
  }
  ```

- 通知类型  

  通知的配置语法：@通知注解("切点表达式")  

  1. 前置通知：@Before
  2. 后置通知：@AfterReturning
  3. 异常通知：@AfterThrowing
  4. 最终通知：@After
  5. 环绕通知：@Around

  > 当前四个通知组合在一起时，执行顺序如下：
  > @Before -> @After -> @AfterReturning（如果有异常：@AfterThrowing）  

- 纯注解配置  

  ```java
  @Configuration
  @ComponentScan("com.lagou")
  @EnableAspectJAutoProxy //替代 <aop:aspectj-autoproxy />
  public class SpringConfig {}
  ```

### 知识小结

```markdown
- 使用@Aspect注解，标注切面类
- 使用@Before等注解，标注通知方法
- 使用@Pointcut注解，抽取切点表达式
- 配置aop自动代理 <aop:aspectj-autoproxy/> 或 @EnableAspectJAutoProxy
```



