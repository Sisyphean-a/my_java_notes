## ajax异步交互

SpringMVC默认用MappingJackson2HttpMessageConverter对json数据进行转换，需要加入jackson的包，同时使用<mvc:annotation-driven />

> `<mvc:annotation-driven /> `可以让 Spring MVC 框架自动配置和启用一些常见的注解驱动的功能
>
> 1. 启用 @RequestMapping 注解，用于处理 HTTP 请求映射；
> 2. 启用 @RequestBody 注解，用于将 HTTP 请求的内容绑定到方法的参数上；
> 3. 启用 @ResponseBody 注解，用于将方法的返回值绑定到 HTTP 响应的内容上；
> 4. 启用 @Valid 和 @ModelAttribute 注解，用于支持数据验证和数据绑定；
> 5. 启用常见的视图技术，如使用 JSP 或 Thymeleaf 等模板引擎。

```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.9.8</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
    <version>2.9.8</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-annotations</artifactId>
    <version>2.9.0</version>
</dependency>
```

### @RequestBody

该注解用于Controller的方法的形参声明，当使用ajax提交并指定contentType为json形式时，通过HttpMessageConverter接口转换为对应的POJO对象

```jsp
<button id="btn1">ajax异步提交</button>
<script>
    $("#btn1").click(function () {
        let url = '${pageContext.request.contextPath}/ajaxRequest';
        let data = '[{"id":1,"username":"张三"},{"id":2,"username":"李四"}]';
        $.ajax({
            type: 'POST',
            url: url,
            data: data,
            contentType: 'application/json;charset=utf-8',
            success: function (resp) {
                alert(JSON.stringify(resp))
            }
        })
    })
</script>
```

```java
@RequestMapping(value = "/ajaxRequest")
public void ajaxRequest(@RequestBody List<User>list) {
    System.out.println(list);
}
```

### @ResponseBody

该注解用于将Controller的方法返回的对象，通过HttpMessageConverter接口转换为指定格式的数据如：json,xml等，通过Response响应给客户端。  

```java
/*
    @RequestMapping
    produces = "application/json;charset=utf-8" 响应返回数据的mime类型和编码，默认为json
*/
@RequestMapping(value = "/ajaxRequest")
@ResponseBody
public List<User> ajaxRequest(@RequestBody List<User> list) {
    System.out.println(list);
    return list;
}
```

## RESTful

### 什么是RESTful

Restful是一种软件架构风格、设计风格。主要用于客户端和服务器交互类的软件，基于这个风格设计的软件可以更简洁，更有层次，更易于实现缓存机制等。  

Restful风格的请求是使用“url+请求方式”表示一次请求目的的，HTTP 协议里面四个表示操作方式的动词如下  

- GET：读取（Read）
- POST：新建（Create）
- PUT：更新（Update）
- DELETE：删除（Delete）  

| 客户端请求 | 原来风格URL地址     | RESTful风格URL地址 |
| ---------- | ------------------- | ------------------ |
| 查询所有   | /user/findAll       | GET /user          |
| 根据ID查询 | /user/findById?id=1 | GET /user/{1}      |
| 新增       | /user/save          | POST /user         |
| 修改       | /user/update        | PUT /user          |
| 删除       | /user/delete?id=1   | DELETE /user/{1}   |

### 代码实现

- `@PathVariable  `

  用来接收RESTful风格请求地址中占位符的值  

- `@RestController ` 

  RESTful风格多用于前后端分离项目开发，前端通过ajax与服务器进行异步交互，我们处理器通常返回的是json数据所以使用@RestController来替代@Controller和@ResponseBody两个注解

  ```java
  // @Controller
  @RestController
  public class RestFulController {
      
      @GetMapping(value = "/user/{id}")
      // 相当于 @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
      // @ResponseBody
      public String get(@PathVariable Integer id) {
          return "get：" + id;
      } 
      
      @PostMapping(value = "/user")
      // @ResponseBody
      public String post() {
          return "post";
      } 
      
      @PutMapping(value = "/user")
      // @ResponseBody
      public String put() {
          return "put";
      } 
      
      @DeleteMapping(value = "/user/{id}")
      // @ResponseBody
      public String delete(@PathVariable Integer id) {
          return "delete："+ id;
      }
  }
  ```

## 文件上传

### 文件上传三要素

- 表单项 type="file"
- 表单的提交方式 method="POST"
- 表单的enctype属性是多部分表单形式 enctype=“multipart/form-data"  

```jsp
<form action="${pageContext.request.contextPath}/fileUpload" method="post" enctype="multipart/form-data">
    名称：<input type="text" name="username"><br>
    文件：<input type="file" name="password"><br>
    <input type="submit" value="单文件上传">
</form>
```

### 文件上传原理

- 当form表单修改为多部分表单时，`request.getParameter()`将失效。
- 当form表单的enctype取值为 `application/x-www-form-urlencoded` 时，
  - form表单的正文内容格式是： `name=value&name=value`
- 当form表单的enctype取值为 `mutilpart/form-data` 时，请求正文内容就变成多部分形式：  

### 单文件上传

1. 导入fileupload和io坐标

   ```xml
   <dependency>
       <groupId>commons-fileupload</groupId>
       <artifactId>commons-fileupload</artifactId>
       <version>1.3.3</version>
   </dependency>
   <dependency>
       <groupId>commons-io</groupId>
       <artifactId>commons-io</artifactId>
       <version>2.6</version>
   </dependency>
   ```

2. 配置文件上传解析器

   ```java
   <!--文件上传解析器-->
   <bean id="multipartResolver"
   class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
       <!-- 设定文件上传的最大值为5MB，5*1024*1024 -->
       <property name="maxUploadSize" value="5242880"></property>
       <!-- 设定文件上传时写入内存的最大值，如果小于这个参数不会生成临时文件，默认为10240 -->
       <property name="maxInMemorySize" value="40960"></property>
   </bean>
   ```

3. 编写文件上传代码

   ```xml
   <form action="${pageContext.request.contextPath}/fileUpload" method="post"
   enctype="multipart/form-data">
       名称：<input type="text" name="username"> <br>
       文件：<input type="file" name="filePic"> <br>
       <input type="submit" value="单文件上传">
   </form>
   ```

   ```java
   @RequestMapping("/fileUpload")
   public String fileUpload(String username, MultipartFile filePic) throws IOException {
       System.out.println(username);
       // 获取文件名
       String originalFilename = filePic.getOriginalFilename();
       //保存文件
       filePic.transferTo(new File("d:/upload/"+originalFilename));
       return "success";
   }
   ```

### 多文件上传

```xml
<form action="${pageContext.request.contextPath}/filesUpload" method="post" enctype="multipart/form-data">
    名称：<input type="text" name="username"> <br>
    文件1：<input type="file" name="filePic"> <br>
    文件2：<input type="file" name="filePic"> <br>
    <input type="submit" value="多文件上传">
</form>
```

```java
@RequestMapping("/filesUpload")
public String filesUpload(String username, MultipartFile[] filePic) throws
IOException {
    System.out.println(username);
    for (MultipartFile multipartFile : filePic) {
        // 获取文件名
        String originalFilename = multipartFile.getOriginalFilename();
        // 保存到服务器
        multipartFile.transferTo(new File("d:/upload/" + originalFilename));
    } 
    return "success";
}
```

## 异常处理

系统的dao、service、controller出现都通过throws Exception向上抛出，最后由springmvc前端控制器交由异常处理器进行异常处理

```mermaid
graph TD
A(Request) --> B(Filter)
B --> C(Controller)
C --> D(Service)
D --> E(DAO)
E --> F(Response)
F --> G(Filter)
G --> H(Error Handling)

subgraph Spring MVC Exception Handling
  I(Filter) -- Handles Filter Exception --> H(Error Handling)
  C(Controller) -- Handles Controller Exception --> H(Error Handling)
  D(Service) -- Handles Service Exception --> H(Error Handling)
  E(DAO) -->|Throws Exception| H(Error Handling)
end
```

![image-20230322120630702](./SpringMVC进阶.assets/image-20230322120630702.png)

### 自定义异常处理器

1. 创建异常处理器类实现HandlerExceptionResolver

   ```java
   public class GlobalExceptionResolver implements HandlerExceptionResolver {
       @Override
       public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
           ModelAndView modelAndView = new ModelAndView();
           modelAndView.addObject("error", ex.getMessage());
           modelAndView.setViewName("error");
           return modelAndView;
       }
   }
   ```

2. 配置异常处理器

   ```java
   @Component
   public class GlobalExecptionResovler implements HandlerExceptionResolver {}
   
   // --------------------------------------------------------
   <bean id="globalExecptionResovler" class="com.lagou.exception.GlobalExecptionResovler"></bean>
   ```

3. 编写异常页面

   ```jsp
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <html>
   <head>
       <title>error</title>
   </head>
   <body>
       <h3>这是一个最终异常的显示页面</h3>
       <p>${error}</p>
   </body>
   </html>
   ```

4. 测试异常跳转  

   ```java
   @RequestMapping("/testException")
   public String testException() {
       int i = 1 / 0;
       return "success";
   }
   ```

### web的异常处理机制

```xml
<!--处理500异常-->
<error-page>
    <error-code>500</error-code>
    <location>/500.jsp</location>
</error-page>

<!--处理404异常-->
<error-page>
    <error-code>404</error-code>
    <location>/404.jsp</location>
</error-page>
```

## 拦截器interceptor  

Spring MVC 的拦截器类似于 Servlet 开发中的过滤器 Filter，用于对处理器进行预处理和后处理  

将拦截器按一定的顺序联结成一条链，这条链称为拦截器链（InterceptorChain）。在访问被拦截的方法或字段时，拦截器链中的拦截器就会按其之前定义的顺序被调用。拦截器也是AOP思想的具体实现







