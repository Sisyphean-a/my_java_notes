// @Configuration注解表明这是一个配置类,相当于XML配置中的<beans>
@Configuration
// @ComponentScan注解用于自动扫描并注册Bean
@ComponentScan("com.mypackage") 

public class AppConfig {

  // @Bean注解表示该方法将会实例化、配置和初始化一个Bean
  @Bean
  public MyService myService() {
    return new MyServiceImpl();
  }

  // @Bean注解也可以用在导入第三方库的类上,等同于XML中 <bean class=""/>
  @Bean
  public ThirdPartyLibrary thirdPartyLibrary(){
    return new ThirdPartyLibrary();
  }
  
  // @ImportResource注解用来加载XML配置文件
  @ImportResource("classpath:app-config.xml")
  public class AppConfig {
  
  }
  
  // @PropertySource读取外部 properties 文件中的配置键值对,
  // 并把它们注入 Spring 环境中
  @PropertySource("app.properties")
  public class AppConfig {
  
    // @Value注解注入属性值
    @Value("${app.name}") 
    String appName;
    
    @Value("${db.url}")
    String dbUrl;
  }
}