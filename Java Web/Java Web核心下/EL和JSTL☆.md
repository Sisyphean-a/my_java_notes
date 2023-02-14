## EL表达式（熟悉）

### 概念

EL（Expression Language）表达式提供了在JSP中简化表达式的方法，可以方便地访问各种数据并输出

### 功能

1. 依次访问pageContext、request、session和application作用域对象存储的数据
2. 获取请求参数值
3. 访问Bean对象的属性
4. 访问集合中的数据
5. 输出简单的运算结果  

### 访问内置对象的数据

原JSP方式：`<%=request.getAttribute("varName")%>`
现EL方式 ：`\${ varName }  `

### 访问请求参数的数据

不使用EL：

`request.getParameter(name);
request.getParameterValues(name);  `

使用EL：

- param：接收的参数只有一个值
- paramValues：接受的参数有多个值  

```jsp
<!-- 获取指定参数的数值 -->
\${param.name}
<!-- 获取指定参数中指定下标的数值 -->
\${paramValues.hobby[0]}
```

### 访问Bean对象的属性

访问方式：

方式一： \${ 对象名 . 属性名 }，例如：\${user.name}
方式二： \${ 对象名 ["属性名"] }，例如：\${user["name"]}  

> 区别：当要存取的属性名中包含一些特殊字符，如： . 或 ,等并非字母或数字的符号，就一定要使用 [ ]而不是.的方式  

### 访问集合中的数据

```jsp
<body>
<%
    List<String> list = new LinkedList<>();
    list.add("one");
    list.add("two");
    list.add("three");
    pageContext.setAttribute("list",list);

    Map<String,Integer> map = new HashMap<>();
    map.put("one",1);
    map.put("two",2);
    map.put("three",3);
    pageContext.setAttribute("map",map);
%>

<%-- 使用EL表达式获取列表中的元素 --%>
集合中下标为0的值为：\${list[0]}<br>
集合中下标为1的值为：\${list[1]}<br>
集合中下标为2的值为：\${list[2]}<br>

<%-- 使用EL表达式获取Map中的元素--%>
整个Map中的元素有：\${map}<br>
</body>
```

### 常用的内置对象

| 类别       | 标识符           | 描述                                              |
| ---------- | ---------------- | ------------------------------------------------- |
| JSP        | pageContext      | PageContext 处理当前页面                          |
| 作用域     | pageScope        | 同页面作用域属性名称和值有关的Map类               |
|            | requestScope     | 同请求作用域属性的名称和值有关的Map类             |
|            | sessionScope     | 同会话作用域属性的名称和值有关的Map类             |
|            | applicationScope | 同应用程序作用域属性的名称和值有关的Map类         |
| 请求参数   | param            | 根据名称存储请求参数的值的Map类                   |
|            | paramValues      | 把请求参数的所有值作为一个String数组来存储的Map类 |
| 请求头     | header           | 根据名称存储请求头主要值的Map类                   |
|            | headerValues     | 把请求头的所有值作为一个String数组来存储的Map类   |
| Cookie     | cookie           | 根据名称存储请求附带的cookie的Map类               |
| 初始化参数 | initParam        | 根据名称存储Web应用程序上下文初始化参数的Map类    |

### 常用的运算符

- 算数运算符：

  | 算术运算符 | 说 明 | 范 例                | 运算结果 |
  | ---------- | ----- | -------------------- | -------- |
  | +          | 加    | \${1+2}              | 3        |
  | －         | 减    | \${2-1}              | 1        |
  | *          | 乘    | \${2*3}              | 6        |
  | / 或 div   | 除    | \${16/5}或\${16div5} | 3.2      |
  | % 或 mod   | 取余  | \${16%5}或\${16mod5} | 1        |

- 关系运算符

  | 关系运算符 | 说 明    | 范 例                  | 运算结果 |
  | ---------- | -------- | ---------------------- | -------- |
  | ==或eq     | 等于     | \${1==2}或\${1 eq 2}   | false    |
  | != 或ne    | 不等于   | \${2!=1}或\${1 ne 2}   | true     |
  | < 或lt     | 小于     | \${2<3}或\${2 lt 3 }   | true     |
  | > 或 gt    | 大于     | \${16>5}或\${16 gt 5}  | true     |
  | <= 或 le   | 小于等于 | \${16<=5}或\${16 le 5} | false    |
  | >= 或 ge   | 大于等于 | \${16>=5}或\${16 ge 5} | true     |

- 逻辑运算符

  | 逻辑运算符 | 说 明  | 范 例                                | 运算结果 |
  | ---------- | ------ | ------------------------------------ | -------- |
  | && 或 and  | 与运算 | \${true&&true}或\${true and true}    | true     |
  | \|\| 或or  | 或运算 | \${true\|\|false}或\${true or false} | true     |
  | ! 或not    | 非运算 | \${!true}或\${not true }             | false    |

- 条件运算符

  `${条件表达式? 语句1 : 语句2}  `

- 验证运算符

  `${empty 表达式}  `

## JSTL标签（熟悉）

### 概念

JSTL( JSP Standard Tag Library ) 被称为JSP标准标签库  

开发人员可以利用这些标签取代JSP页面上的Java代码，从而提高程序的可读性，降低程序的维护难度  

### 使用方式

- 下载JSTL的jar包并添加到项目中

  下载地址为：https://tomcat.apache.org/download-taglibs.cgi  

- 在JSP页面中使用taglib指定引入jstl标签库  

  ```jsp
  <!-- prefix属性用于指定库前缀 -->
  <!-- uri属性用于指定库的标识 -->
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  ```

### 常用核心标签

1. 输出标签

   `<c:out></c:out>` 用来将指定内容输出的标签  

2. 设置标签

   `<c:set></c:set>` 用来设置属性范围值的标签  

3. 删除标签

   `<c:remove></c:remove>` 用来删除指定数据的标签  

4. 单条件判断标签

   ```jsp
   <c:if test ="EL条件表达式">
   	满足条件执行
   </c:if >
   ```

5. 多条件判断标签

   ```jsp
   <c:choose >
       <c:when test =“EL表达式”>
       	满足条件执行
       </c:when>
       … 
       <c:otherwise>
       	不满足上述when条件时执行
       </c:otherwise>
   </c:choose >
   ```

6. 循环标签

   ```jsp
   <c:forEach var="循环变量" items="集合">
   	…
   </c:forEach>
   ```

### 常用函数标签

`<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  `

### 常用格式化标签  

`<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  `

### 自定义标签

- 编写标签类继承SimpleTagSupport类或TagSupport类并重写doTag方法或doStartTag方法。  

- 定义标签库文件（tld标签库文件）并配置标签说明文件到到WEB-INF下  

- 在JSP中添加taglib指令引入标签库使用：  

  `<%@ taglib prefix="hello" uri="http://lagou.com" %>  `







