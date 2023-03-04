# MySQL基础&SQL入门

## 数据库基本概念

### 什么是数据库

-   数据库(DataBase) 就是存储和管理数据的仓库
-   其本质是一个文件系统, 还是以文件的方式,将数据保存在电脑上  

### 为什么使用数据库

| 存储方式 | 优点                                                         | 缺点                                           |
| -------- | ------------------------------------------------------------ | ---------------------------------------------- |
| 内存     | 速度快                                                       | 不能够永久保存,数据是临时状态的                |
| 文件     | 数据是可以永久保存的                                         | 使用IO流操作文件, 不方便                       |
| 数据库   | 1.数据可以永久保存<br />2.方便存储和管理数据 <br />3.使用统一的方式操作数据库 (SQL) | 占用资源,有些数据库需要付费(比如Oracle数据 库) |

### MySQL配置

![MySQL基础](./MySQL.assets/MySQL%E5%9F%BA%E7%A1%80.png)

#### 启动与关闭

```bash
#启动mysql
net start mysql80

#关闭mysql
net stop mysql80
```

#### 登录与退出

| 命令                              | 说明                                              |
| --------------------------------- | ------------------------------------------------- |
| mysql -u 用户名 -p 密码           | 使用指定用户名和密码登录当前计算机中的MySQL数据库 |
| mysql -h 主机IP -u 用户名 -p 密码 | -h 指定IP 方式,进行 登录                          |

```bash
#本机登录，如果想要隐式密码，则在-p之后直接回车即可
mysql -uroot -p123456

#指定ip登录
mysql -h127.0.0.1 -uroot -p123456

#退出 两种方式：
exit
quit
```

>   推荐使用SqlYog作为mysql的管理工具

#### MySQL目录结构

##### 安装目录

默认在：C:\Program Files\MySQL\MySQL Server**下

| 目录    | 目录内容                   |
| ------- | -------------------------- |
| bin     | 放置一些可执行文件         |
| docs    | 文档                       |
| include | 包含(头)文件               |
| lib     | 依赖库                     |
| share   | 用于存放字符集、语言等信息 |

##### 配置文件

默认在：C:\ProgramData\MySQL\MySQL Server**下

-   my.ini 是mysql的配置文件，一般不建议去修改
-   data目录是mysql管理的数据库文件所在的目录

几个概念：

-   数据库：文件夹

-   表：文件

-   数据：文件中的记录

### 数据库管理系统

概念：数据库管理系统（DataBase Management System，DBMS）：指一种操作和管理维护数据库的大型软件 。mysql就是一个DBMS

作用：用于建立、使用和维护数据库，对数据库进行统一的管理

![数据库管理系统](./MySQL.assets/%E6%95%B0%E6%8D%AE%E5%BA%93%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F.jpg)

### 数据库表

数据库中以表为组织单位存储数据

表类似我们java中的类，每个字段都有对应的数据类型

```
类	   --> 表;
类中属性 --> 表中字段;
对象	  --> 数据记录
```

## SQL重点

### 概念

#### 什么是SQL ？
结构化查询语言(Structured Query Language)简称SQL，是一种特殊目的的编程语言，是一种数据库查询和程序设计语言，用于存取数据以及查询、更新和管理关系数据库系统。

#### SQL 的作用

-   是所有关系型数据库的统一查询规范，不同的关系型数据库都支持SQL
-   所有的关系型数据库都可以使用SQL
-   不同数据库之间的SQL 有一些区别 方言  

### 通用语法

1.   SQL语句可以单行 或者 多行书写，以分号 结尾 ; （Sqlyog中可以不用写分号）

2.    可以使用空格和缩进来增加语句的可读性。

3.   MySql中使用SQL不区分大小写，一般关键字大写，数据库名 表名列名 小写。

4.   注释方式  

     ```mysql
     # show databases; 单行注释
     -- show databases; 单行注释,空格不可省略
     /*
     多行注释
     show databases;
     */
     ```

     

### SQL的分类

| 分类                 | 说明                                            |
| -------------------- | ----------------------------------------------- |
| 数据定义语言（DDL）  | 用来定义数据库对象：数据库，表，列 等           |
| 数据操作语言（DML）★ | 用来对数据库中表的记录进行更新                  |
| 数据查询语言（DQL）★ | 用来查询数据库中表的记录                        |
| 数据控制语言（DCL）  | 用来定义数据库的访问权限和安全级别， 及创建用户 |

## DDL 数据定义

![DDL 数据定义](./MySQL.assets/DDL%20%E6%95%B0%E6%8D%AE%E5%AE%9A%E4%B9%89-1668583647059-1.png)

### 库操作

核心格式：`库操作  database 数据库名`

#### 创建数据库

| 命令                                            | 说明                                                     |
| ----------------------------------------------- | -------------------------------------------------------- |
| create database 数据库名；                      | 创建指定名称的数据库。                                   |
| create database 数据库名 character set 字符集； | 创建指定名称的数据库，并且指定字符集（一般都 指定utf-8） |

```mysql
create database db1;
create database db2 characher set utf8;
```

#### 查看、选择数据库

| 命令                            | 说明                       |
| ------------------------------- | -------------------------- |
| use 数据库;                     | 切换数据库                 |
| select database();              | 查看当前正在使用的数据库   |
| show databases;   -- 复数带s    | 查看Mysql中 都有哪些数据库 |
| show create database 数据库名； | 查看一个数据库的定义信息   |

#### 修改数据库

| 命令                                           | 说明                   |
| ---------------------------------------------- | ---------------------- |
| alter database 数据库名 character set 字符集； | 数据库的字符集修改操作 |

```mysql
-- 将数据库db1的字符集修改为utf8
ALTER DATABASE db1 CHARACTER SET utf8;
```

#### 删除数据库

| 命令                   | 说明                          |
| ---------------------- | ----------------------------- |
| drop database 数据库名 | 从MySql中永久的删除某个数据库 |

### 表操作

核心格式：`表操作 table 表名 内容操作`

#### MySQL常见的数据类型

| 类型    | 描述                                                |
| ------- | --------------------------------------------------- |
| int     | 整型                                                |
| double  | 浮点型                                              |
| varchar | 字符串型                                            |
| date    | 日期类型，给是为 yyyy-MM-dd ,只有年月日，没有时分秒 |

注意：MySQL中的 char类型与 varchar类型，都对应了 Java中的字符串类型

|         | 区别                                 | 作用            |
| ------- | ------------------------------------ | --------------- |
| char    | 固定长度                             | 密码 ，性别一类 |
| varchar | 可变长度(只使用字符串长度所需的空间) | 大多数场景      |

#### 创建表

语法格式：

```mysql
create table 表名(
	字段名称1 字段类型（长度），
	字段名称2 字段类型 注意 最后一列不要加逗号
)；
```

实例：创建商品分类表  , 然后快速创建一个结构相同的表

```mysql
CREATE DATABASE db1; -- 创建一个数据库
USE db1;			-- 切换到此数据库

CREATE TABLE category(
	cid INT,
	cname VARCHAR(20)
);

/*
	创建一个结构相同的表
	格式：create table 新表名 like 旧表名;
*/
CREATE TABLE test LIKE category;
```

#### 查看表

| 命令         | 说明                       |
| ------------ | -------------------------- |
| show tables; | 查看当前数据库中的所有表名 |
| desc 表名；  | 查看数据表的结构           |

```mysql
-- 查看当前数据库中的所有表名
SHOW TABLES;

-- 显示当前数据表的结构
DESC category;

-- 查看创建表的SQL语句
SHOW CREATE TABLE category;
```

#### 删除表

| 命令                        | 说明                                               |
| --------------------------- | -------------------------------------------------- |
| drop table 表名；           | 删除表（从数据库中永久删除某一张表）               |
| drop table if exists 表名； | 判断表是否存在， 存在的话就删除,不存在就不执行删除 |

```mysql
-- 直接删除 test1 表
DROP TABLE test1;

-- 先判断 再删除test2表
DROP TABLE IF EXISTS test2;
```

#### 修改表

基本格式：alter table 表名 ......

| 功能                   | 语法格式                                          |
| ---------------------- | ------------------------------------------------- |
| 修改表名               | rename table 旧表名 to 新表名                     |
| 修改表的字符集         | alter table 表名 character set 字符集             |
| 向表中添加列           | alert table 表名 add 字段名称 字段类型            |
| 修改列的数据类型或长度 | alter table 表名 modify 字段名称 字段类型         |
| 修改列名               | alter table 表名 change 旧列名 新列名 类型(长度); |
| 删除列                 | alter table 表名 drop 列名;                       |

## DML 数据操作

核心格式：`数据操作 表名 ......`

![DML 数据操作](./MySQL.assets/DML%20%E6%95%B0%E6%8D%AE%E6%93%8D%E4%BD%9C.png)

### 插入数据

语法格式：`insert into 表名 （字段名1，字段名2...） values(字段值1，字段值2...);  `

```mysql
# 创建学生表
CREATE TABLE student(
    sid INT,
    sname VARCHAR(20),
    age INT,
    sex CHAR(1),
    address VARCHAR(40)
);

-- 方式一：插入全部字段，将所有字段名都写出来
INSERT INTO student (sid,sname,age,sex,address) VALUES(1,'孙悟空',20,'男','花果山');

-- 方式二：插入全部字段，不写字段名
INSERT INTO student VALUES(2,'孙悟饭',10,'男','地球');

-- 方式三：插入指定字段的值
INSERT INTO category (cname) VALUES('白骨精');
```

### 更改数据

语法格式：

1.   `update 表名 set 列名 = 值`  -- 不带条件的修改
2.   `update 表名 set 列名 = 值 [where 条件表达式：字段名 = 值 ]`  --带条件的修改

```mysql
-- 不带条件修改，修改所有内容，慎用！
UPDATE student SET sex = '女';
-- 带条件的修改，只更改指定id的学生
UPDATE student SET sex = '男' WHERE sid = 3;
-- 一次修改多个列
UPDATE student SET age = 20,address = '北京' WHERE sid = 2;
```

### 删除数据

语法格式：

1.   `delete from 表名`  -- 删除所有数据
2.   `delete from 表名 [where 字段名 = 值]`   -- 指定条件删除数据

```mysql
-- 删除指定id的数据
DELETE FROM student WHERE sid = 1;
-- 删除所有数据
DELETE FROM student;
-- 删除表中的所有数据
truncate table student;
```

## DQL 数据查询

<img src="./MySQL.assets/DQL%20%E6%95%B0%E6%8D%AE%E6%9F%A5%E8%AF%A2.png" alt="DQL 数据查询" style="zoom:67%;" />

### 查询

核心格式：`select 列名 from 表名 [where 条件]`

```mysql
#创建员工表
CREATE TABLE emp(
    eid INT,
    ename VARCHAR(20),
    sex CHAR(1),
    salary DOUBLE,
    hire_date DATE,
    dept_name VARCHAR(20)
);
#添加数据
INSERT INTO emp VALUES(1,'孙悟空','男',7200,'2013-02-04','教学部');
INSERT INTO emp VALUES(2,'猪八戒','男',3600,'2010-12-02','教学部');
INSERT INTO emp VALUES(3,'唐僧','男',9000,'2008-08-08','教学部');
INSERT INTO emp VALUES(4,'白骨精','女',5000,'2015-10-07','市场部');
INSERT INTO emp VALUES(5,'蜘蛛精','女',5000,'2011-03-14','市场部');
INSERT INTO emp VALUES(6,'玉兔精','女',200,'2000-03-14','市场部');
INSERT INTO emp VALUES(7,'林黛玉','女',10000,'2019-10-07','财务部');
INSERT INTO emp VALUES(8,'黄蓉','女',3500,'2011-09-14','财务部');
INSERT INTO emp VALUES(9,'吴承恩','男',20000,'2000-03-14',NULL);
INSERT INTO emp VALUES(10,'孙悟饭','男', 10,'2020-03-14',财务部);
```

#### 简单查询

查询不会对数据进行修改，只是一种显式数据的方式

语法格式：`select 列名 from 表名  `

```mysql
-- 查询emp中的所有数据，使用 * 表示所有列
SELECT * FROM emp; 

-- 查询emp表中的所有记录，仅显式id和name字段
SELECT eid,ename FROM emp;

-- 别名查询，使用关键字as，需注意，这只是一种显式方式
SELECT
    eid AS '编号',
    ename AS '姓名' ,
    sex AS '性别',
    salary AS '薪资',
    hire_date '入职时间', -- AS 也可以省略
    dept_name '部门名称'
FROM emp;

-- 去重查询，使用关键字distinct
SELECT DISTINCT dept_name FROM emp;

-- 运算查询（查询结果参与运算），例如把所有工资增加1000
SELECT ename , salary + 1000 FROM emp;
```

#### 条件查询

语法格式：`select 列名 from 表名 where 条件表达式  `

运算符：

1.   比较运算符

     | 运算符            | 说明                                                         |
     | ----------------- | ------------------------------------------------------------ |
     | > < <= >= = <> != | 大于、小于、大于(小于)等于、不等于                           |
     | between ...and... | 显示在某一区间的值 例如: 2000-10000之间： Between 2000 and 10000 |
     | in(集合)          | 集合表示多个值,使用逗号分隔,例如: name in (悟空，八戒) in中的每个数据都会作为一次条件,只要满足条件就会显示 |
     | like '%张%'       | 模糊查询                                                     |
     | is null           | 查询某一列为NULL的值, 注: 不能写 = NULL                      |

2.   逻辑运算符

     | 运算符  | 说明             |
     | ------- | ---------------- |
     | And &&  | 多个条件同时成立 |
     | Or \|\| | 多个条件任一成立 |
     | Not     | 不成立，取反     |

```mysql
# 查询员工姓名为黄蓉的员工信息
SELECT * FROM emp WHERE ename = '黄蓉';

# 查询薪水价格为5000的员工信息
SELECT * FROM emp WHERE salary = 5000;

# 查询薪水价格不是5000的所有员工信息
SELECT * FROM emp WHERE salary != 5000;
SELECT * FROM emp WHERE salary <> 5000;

# 查询薪水价格大于6000元的所有员工信息
SELECT * FROM emp WHERE salary > 6000;

# 查询薪水价格在5000到10000之间所有员工信息
SELECT * FROM emp WHERE salary BETWEEN 5000 AND 10000;

# 查询薪水价格是3600或7200或者20000的所有员工信息
-- 方式1: or
SELECT * FROM emp WHERE salary = 3600 OR salary = 7200 OR salary = 20000;
-- 方式2: in() 匹配括号中指定的参数
SELECT * FROM emp WHERE salary IN(3600,7200,20000);
```

3.   通配符 模糊查询

     | 通配符 | 说明                    |
     | ------ | ----------------------- |
     | %      | 表示匹配任意多个字符串, |
     | _      | 表示匹配 一个字符       |

```mysql
# 查询含有'精'字的所有员工信息
SELECT * FROM emp WHERE ename LIKE '%精%';

# 查询以'孙'开头的所有员工信息
SELECT * FROM emp WHERE ename LIKE '孙%';

# 查询第二个字为'兔'的所有员工信息
SELECT * FROM emp WHERE ename LIKE '_兔%';

# 查询没有部门的员工信息
SELECT * FROM emp WHERE dept_name IS NULL;
-- SELECT * FROM emp WHERE dept_name = NULL;

# 查询有部门的员工信息
SELECT * FROM emp WHERE dept_name IS NOT NULL;
```


# SQL单表&约束&事务

## SQL单表

<img src="./MySQL.assets/SQL%E5%8D%95%E8%A1%A8.png" alt="SQL单表" style="zoom: 25%;" />

### 排序

通过order by子句，可以将查询出来的结果进行排序（只是显式）

两种排序方式：asc -- 升序（默认） ； desc -- 降序；

```mysql
select 字段名 from 表名 [where 字段 = 值] order by 字段名 [ASC/DESC]

# 单列排序
-- 按照薪资的大小进行升序排序
SELECT * FROM emp ORDER BY salary ASC;
-- 按照薪资的大小对女性进行降序排序
SELECT * FROM emp WHERE sex = '女' ORDER BY salary DESC;

# 组合排序，如果薪资一样就按照id进行降序排序
SELECT * FROM emp ORDER BY salary DESC, eid DESC
```

### 聚合函数

对列值进行计算，例如求和，求最大值等等

语法格式：select 聚合函数（字段名） from 表名;

| 聚合函数    | 作用                         |
| ----------- | ---------------------------- |
| count(字段) | 统计指定列不为NULL的记录行数 |
| sum(字段)   | 计算指定列的数值和           |
| max(字段)   | 计算指定列的最大值           |
| min(字段)   | 计算指定列的最小值           |
| avg(字段)   | 计算指定列的平均值           |

```mysql
#1 查询员工的总数
SELECT COUNT(eid) FROM emp;
#2 查看员工总薪水、最高薪水、最小薪水、薪水的平均值
SELECT 
	SUM(salary) '总薪水',
	MAX(salary) '最高薪水',
	MIN(salary) '最低薪水',
	AVG(salary) '平均值' 
FROM emp;
#3 查询薪水大于4000员工的个数
SELECT COUNT(eid) FROM emp WHERE salary > 4000;
#4 查询部门为'教学部'的所有员工的个数
SELECT COUNT(eid) FROM emp WHERE dept_name = '教学部';
#5 查询部门为'市场部'所有员工的平均薪水
SELECT AVG(salary) FROM emp WHERE dept_name = '市场部'; 
```

### 分组

使用group by 语句，对查询的信息进行分组，相同数据为一组

语法格式：

```mysql
SELECT 分组字段/聚合函数 FROM 表名 GROUP BY 分组字段 [HAVING 条件];
```

>   注意：分组时可以查询要分组的字段，或者使用聚合函数进行统计操作
>
>   查询其他字段没有意义

```mysql
#1.查询所有部门信息
SELECT dept_name FROM emp GROUP BY dept_name ;

#2.查询每个部门的平均薪资
SELECT 
	dept_name '部门名称', 
	AVG(salary) '平均薪资'
FROM emp GROUP BY dept_name;

#3.查询每个部门的平均薪资, 部门名称不能为null
SELECT 
	dept_name '部门名称', 
	AVG(salary) '平均薪资'
FROM emp WHERE dept_name IS NOT NULL GROUP BY dept_name ;

# 查询平均薪资大于6000的部门
-- 需要在分组后再次进行过滤,使用 having
SELECT
dept_name ,
AVG(salary)
FROM emp WHERE dept_name IS NOT NULL GROUP BY dept_name HAVING AVG(salary) >
6000 ;
```

### limit关键字

作用：

-   limit是限制的意思,用于限制返回的查询结果的行数 (可以通过limit指定查询多少行数据)
-   limit 语法是 MySQL的方言,用来完成分页  

语法结构：`select 字段1,字段2... from 表名 limit offset , length;  `

limit offset , length：关键字可以接受一个或者两个为0或者正整数的参数。其中offset代表起始行数，默认为0，可以省略，length代表返回的行数

```mysql
# 查询emp表中的前5条数据
-- 参数1 起始值,默认是0 , 参数2 要查询的条数
SELECT * FROM emp LIMIT 5;
SELECT * FROM emp LIMIT 0 , 5;
# 查询emp表中 从第4条开始,查询6条
-- 起始值默认是从0开始的.
SELECT * FROM emp LIMIT 3 , 6;

-- 分页操作 每页显示3条数据
SELECT * FROM emp LIMIT 0,3; -- 第1页
SELECT * FROM emp LIMIT 3,3; -- 第2页 2-1=1 1*3=3
SELECT * FROM emp LIMIT 6,3; -- 第三页
```

## SQL约束

作用：对表中的数据进行限制，从而保证数据的正确性，有效性，完整性。违背约束的不正确数据，无法插入到表中

常见的约束：

| 约束名 | 约束关键字  |
| ------ | ----------- |
| 主键   | primary key |
| 唯一   | unique      |
| 非空   | not null    |
| 外键   | foreign key |

![SQL约束](./MySQL.assets/SQL%E7%BA%A6%E6%9D%9F.png)

### 主键约束

特点：不可重复，唯一，非空

作用：用来表示数据库中的每一条记录

主键是给数据库和程序使用的，和客户无关。所有主键只要能够保证不重复就好,比如 身份证就可以作为主键

#### 添加主键约束

语法格式：`字段名 字段类型 primary key  `

```mysql
# 方式1 创建一个带主键的表
CREATE TABLE emp2(
    -- 设置主键 唯一 非空
    eid INT PRIMARY KEY,
    ename VARCHAR(20),
    sex CHAR(1)
);

-- 删除表
DROP TABLE emp2;

-- 方式2 创建一个带主键的表
CREATE TABLE emp2(
    eid INT ,
    ename VARCHAR(20),
    sex CHAR(1),
    -- 指定主键为 eid字段
    PRIMARY KEY(eid)
);

-- 方式3 创建一个带主键的表
CREATE TABLE emp2(
    eid INT ,
    ename VARCHAR(20),
    sex CHAR(1)
);

-- 创建的时候不指定主键,然后通过 DDL语句进行设置
ALTER TABLE emp2 ADD PRIMARY KEY(eid);

-- 使用desc查看表的详细信息
DESC emp2;
```

#### 删除主键约束

```mysql
-- 使用DDL语句 删除表中的主键
-- 语法格式：alter table emp2 drop primary key
ALTER TABLE emp2 DROP PRIMARY KEY;
DESC emp2;
```

#### 主键的自增

关键字：`auto_increment  `，一般和primary key配合使用

```mysql
-- 创建主键自增的表
CREATE TABLE emp2(
    -- 关键字 AUTO_INCREMENT,主键类型必须是整数类型
    eid INT PRIMARY KEY AUTO_INCREMENT,
    ename VARCHAR(20),
    sex CHAR(1)
);

-- 主键自增后添加数据
INSERT INTO emp2(ename,sex) VALUES('张三','男');
INSERT INTO emp2 VALUES(NULL,'艳秋','女');
```

#### 修改主键自增的起始值

```mysql
-- 创建主键自增的表,自定义自增其实值
CREATE TABLE emp2(
    eid INT PRIMARY KEY AUTO_INCREMENT,
    ename VARCHAR(20),
    sex CHAR(1)
)AUTO_INCREMENT=100; -- 在这里设置
```

#### delete 和 truncate 对自增长的影响

| 清空表数据的方式 | 特点                                                         |
| ---------------- | ------------------------------------------------------------ |
| delete           | 只是删除表中所有数据,对自增没有影响                          |
| truncate         | truncate 是将整个表删除掉,然后创建一个新的表 自增的主键,重新从 1开始 |

### 非空约束

特点：某一列不允许为空

语法格式：`字段名 字段类型 not null  `

```mysql
# 非空约束
CREATE TABLE emp2(
    eid INT PRIMARY KEY AUTO_INCREMENT,
    -- 添加非空约束, ename字段不能为空
    ename VARCHAR(20) NOT NULL,
    sex CHAR(1)
);
```

### 唯一约束

特点：某一列的值不能重复（对null不做唯一的判断）

语法格式：`字段名 字段值 unique  `

```mysql
#创建emp3表 为ename 字段添加唯一约束
CREATE TABLE emp3(
    eid INT PRIMARY KEY AUTO_INCREMENT,
    ename VARCHAR(20) UNIQUE,
    sex CHAR(1)
);
```

### 外键约束

暂略，多表中学习

### 默认值

语法格式：`字段名 字段类型 default 默认值  `

```mysql
-- 创建带有默认值的表
CREATE TABLE emp4(
    eid INT PRIMARY KEY AUTO_INCREMENT,
    -- 为ename 字段添加默认值
    ename VARCHAR(20) DEFAULT '未知',
    sex CHAR(1)
);
```

## 数据库事务

概念：事务是一个整体，由一条或者多条SQL 语句组成，这些SQL语句要么都执行成功，要么都执行失败， 只要有一条SQL出现异常,整个操作就会回滚，整个业务执行失败  

![数据库事务](./MySQL.assets/%E6%95%B0%E6%8D%AE%E5%BA%93%E4%BA%8B%E5%8A%A1.png)

### MySQL事务操作

两种方式进行事务的操作：

1.   手动提交事物
2.   自动提交事物

### 手动提交事务

语法格式：

| 功能     | 语句                           |
| -------- | ------------------------------ |
| 开启事务 | start transaction; 或者 begin; |
| 提交事务 | commit;                        |
| 回滚事务 | rollback;                      |

-   执行成功的情况： 开启事务 -> 执行多条 SQL 语句 -> 成功提交事务
-   执行失败的情况： 开启事务 -> 执行多条 SQL 语句 -> 事务的回滚  

```mysql
use db2;

-- 开启事务
start transaction;
-- A账户减少500元
update account set money - 500 where name = 'A';
-- B账户增加500云
update account set money + 500 where name = 'B';
-- 提交事务
commit;
```

### 自动提交事务

-   MySQL 默认每一条 DML(增删改)语句都是一个单独的事务，每条语句都会自动开启一个事务，语句执行完毕 自动提交事务，MySQL 默认开始自动提交事务
-   MySQL默认是自动提交事务  

#### 取消自动提交

```mysql
-- 查看autocommit状态，on:自动提交， off:手动提交
SHOW VARIABLES LIKE 'autocommit';
-- 取消自动提交
SET @@autocommit=off;

-- 现在修改数据后都需要提交才能生效
-- 选择数据库
use db2;
-- 修改数据
update account set money = money - 500 where name = 'jack';
-- 手动提交
commit;
```

### 事务的四大特性ACID

| 特 性    | 含义                                                         |
| -------- | ------------------------------------------------------------ |
| 原 子 性 | 每个事务都是一个整体，不可再拆分，事务中所有的 SQL 语句要么都执行成功， 要么都 失败。 |
| 一 致 性 | 事务在执行前数据库的状态与执行后数据库的状态保持一致。如：转账前2个人的 总金额 是 2000，转账后 2 个人总金额也是 2000. |
| 隔 离 性 | 事务与事务之间不应该相互影响，执行时保持隔离的状态.          |
| 持 久 性 | 一旦事务执行成功，对数据库的修改是持久的。就算关机，数据也是要保存下来的. |

### MySQL事务隔离级别（了解）

数据并发访问：

一个数据库可能拥有多个访问客户端，这些客户端都可以并发方式访问数据库。数据库的相同数据可能被多个事务同时访问，如果不采取隔离措施，就会导致各种问题，破坏数据的完整性  

#### 数据并发访问的问题：

| 并发访问的问题 | 说明                                                         |
| -------------- | ------------------------------------------------------------ |
| 脏读           | 一个事务读取到了另一个事务中尚未提交的数据                   |
| 不可重复 读    | 一个事务中两次读取的数据内容不一致, 要求的是在一个事务中多次读取时数据是一 致的. 这是进行 update 操作时引发的问题 |
| 幻读           | 一个事务中,某一次的 select 操作得到的结果所表征的数据状态, 无法支撑后续的业务 操作. 查询得到的数据状态不准确,导致幻读. |

#### 四种隔离级别

| 级 别 | 名字      | 隔离级别         | 脏 读 | 不可重复 读 | 幻 读 | 数据库的默认隔离级 别 |
| ----- | --------- | ---------------- | ----- | ----------- | ----- | --------------------- |
| 1     | 读未提 交 | read uncommitted | 会    | 会          | 会    |                       |
| 2     | 读已提 交 | read committed   | 不会  | 会          | 会    | Oracle和SQLServer     |
| 3     | 可重复 读 | repeatable read  | 不会  | 不会        | 会    | MySql                 |
| 4     | 串行化    | serializable     | 不会  | 不会        | 不会  |                       |

#### 相关命令

```mysql
-- 查看隔离级别
select @@tx_isolation;

-- 设置事务隔离级别，需要退出 MySQL 再重新登录才能看到隔离级别的变化
set global transaction isolation level 级别名称;
/*
	级别名称：
	read uncommitted 读未提交
	read committed 读已提交
	repeatable read 可重复读
	serializable 串行化
*/
```

# 多表&数据库设计

## 多表

在实际开发中，一个项目通常需要很多表才能完成

单表最大的问题，是冗余，同一个字段出现大量的重复数据

### 多表设计

-   department 部门表 : id, dep_name, dep_location
-   employee 员工表: eid, ename, age, dep_id  

关系分析：

1.   员工表中有一个字段dept_id与部门表中的主键对应，员工表的这个字段就叫做**外键**
2.   拥有外键的员工表被称为**从表**，与外键对应的主键所在的表叫做**主表**

### 外键约束

#### 外键的意义

多表设计上的问题：多张表只有主观上的联系，而实际并没有进行关联，这是没有意义的。需要约束

而使用外键约束可以让两张表之间产生一个对应关系，并产生强制性的外键数据检查，从而保证主从表引用的完整性

#### 主从表概念

-   主表: 主键id所在的表, 约束别人的表
-   从表: 外键所在的表多, 被约束的表  

#### 创建外键约束

语法格式：

```mysql
-- 新建表时添加外键
[constrint] [外键约束名称] foreign key(外键字段名) references 主表名(主键字段名)

-- 已有表添加外键
alter table 从表 add [constraint] [外键约束名称] foreign key (外键字段名) references主表 (主键字段名);
```

实例：

```mysql
-- 创建 employee表,添加外键约束，与department的id键进行连接
CREATE TABLE employee(
    eid INT PRIMARY KEY AUTO_INCREMENT,
    ename VARCHAR(20),
    age INT,
    dept_id INT,
    -- 添加外键约束
    CONSTRAINT emp_dept_fk 
    FOREIGN KEY(dept_id) REFERENCES department(id)
);
```

#### 删除外键约束

语法格式：`alter table 从表 drop foreign key 外键约束名称  `

```mysql
-- 删除employee 表中的外键约束,外键约束名 emp_dept_fk
ALTER TABLE employee DROP FOREIGN KEY emp_dept_fk;

-- 可以省略外键名称, 系统会自动生成一个
ALTER TABLE employee ADD FOREIGN KEY (dept_id) REFERENCES department(id);
```

#### 注意事项

-   从表外键类型必须与主表主键类型一致 否则创建失败

-   添加数据时，应该先添加主表中的数据

-   删除数据时，应该先删除从表中的数据

#### 级联删除操作（了解）

如果想实现删除主表数据的同时，也删除掉从表数据，可以使用级联删除操作  

级联操作是一次性的，是一个表的属性，在创建表的时候使用

语法格式：`on delete cascade`

```mysql
CREATE TABLE employee(
    eid INT PRIMARY KEY AUTO_INCREMENT,
    ename VARCHAR(20),
    age INT,
    dept_id INT,
    -- 添加外键约束
    FOREIGN KEY(dept_id) REFERENCES department(id)
    -- 添加级联删除
    ON DELETE CASCADE
);
```

## 多表关系设计

实际开发中，一个项目通常需要很多张表才能完成。例如：一个商城项目就需要分类表(category)、商品表(products)、订单表(orders)等多张表。且这些表的数据之间存在一定的关系

![多表](./MySQL.assets/%E5%A4%9A%E8%A1%A8.png)

### 表与表的三种关系

| 关系       | 作用                                    |
| ---------- | --------------------------------------- |
| 一对多关系 | 最常见的关系, 学生对班级,员工对部门     |
| 多对多关系 | 学生与课程, 用户与角色                  |
| 一对一关系 | 使用较少,因为一对一关系可以合成为一张表 |

### 一对多关系（常见）

-   一对多建表原则：
    在从表(多方)创建一个字段,字段作为外键指向主表(一方)的主键  

<img src="./MySQL.assets/%E4%B8%80%E5%AF%B9%E5%A4%9A.jpg" alt="一对多" style="zoom: 40%;" />

```mysql
/*
	设计  省&市表
	分析  省和市之间的关系是一对多，一个省包含多个市
*/

#创建省表 (主表,注意: 一定要添加主键约束)
CREATE TABLE province(
    id INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(20),
    description VARCHAR(20)
);

#创建市表 (从表,注意: 外键类型一定要与主表主键一致)
CREATE TABLE city(
    id INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(20),
    description VARCHAR(20),
    pid INT,
    -- 添加外键约束
    FOREIGN KEY (pid) REFERENCES province(id)
);
```



### 多对多关系（常见）

-   多对多关系建表原则
    需要创建第三张表，中间表中至少两个字段，这两个字段分别作为外键指向各自一方的主键。  

<img src="./MySQL.assets/%E5%A4%9A%E5%AF%B9%E5%A4%9A.jpg" alt="多对多" style="zoom: 40%;" />

```mysql
/*
	设计  演员&角色表
	分析  省和市之间的关系是多对多，一个演员可以饰演多个角色，角色也可以被不同的演员扮演
*/

#创建演员表
CREATE TABLE actor(
    id INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(20)
);

#创建角色表
CREATE TABLE role(
    id INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(20)
);

#创建中间表，中间表的名字一般是两个表的集合体
CREATE TABLE actor_role(
    -- 中间表自己的主键
    id INT PRIMARY KEY AUTO_INCREMENT,
    -- 指向actor 表的外键
    aid INT,
    -- 指向role 表的外键
    rid INT
);

-- 添加外键约束
-- 为中间表的aid字段,添加外键约束 指向演员表的主键
ALTER TABLE actor_role ADD FOREIGN KEY(aid) REFERENCES actor(id);
-- 为中间表的rid字段, 添加外键约束 指向角色表的主键
ALTER TABLE actor_role ADD FOREIGN KEY(rid) REFERENCES role(id);
```

## 多表查询

DQL: 查询多张表,获取到需要的数据
比如 我们要查询家电分类下 都有哪些商品,那么我们就需要查询分类与商品这两张表  

### 数据准备

```mysql
#分类表 (一方 主表)
CREATE TABLE category (
    cid VARCHAR(32) PRIMARY KEY ,
    cname VARCHAR(50)
);
#分类数据
INSERT INTO category(cid,cname) VALUES('c001','家电');
INSERT INTO category(cid,cname) VALUES('c002','鞋服');
INSERT INTO category(cid,cname) VALUES('c003','化妆品');
INSERT INTO category(cid,cname) VALUES('c004','汽车');

#商品表 (多方 从表)
CREATE TABLE products(
    pid VARCHAR(32) PRIMARY KEY ,
    pname VARCHAR(50),
    price INT,
    flag VARCHAR(2), #是否上架标记为：1表示上架、0表示下架
    category_id VARCHAR(32),
-- 添加外键约束
FOREIGN KEY (category_id) REFERENCES category (cid)
);
#商品数据
INSERT INTO products(pid, pname,price,flag,category_id) VALUES('p001','小米电视
机',5000,'1','c001');
INSERT INTO products(pid, pname,price,flag,category_id) VALUES('p002','格力空
调',3000,'1','c001');
INSERT INTO products(pid, pname,price,flag,category_id) VALUES('p003','美的冰
箱',4500,'1','c001');
INSERT INTO products (pid, pname,price,flag,category_id) VALUES('p004','篮球
鞋',800,'1','c002');
INSERT INTO products (pid, pname,price,flag,category_id) VALUES('p005','运动
裤',200,'1','c002');
INSERT INTO products (pid, pname,price,flag,category_id) VALUES('p006','T
恤',300,'1','c002');
INSERT INTO products (pid, pname,price,flag,category_id) VALUES('p007','冲锋
衣',2000,'1','c002');
INSERT INTO products (pid, pname,price,flag,category_id) VALUES('p008','神仙
水',800,'1','c003');
INSERT INTO products (pid, pname,price,flag,category_id) VALUES('p009','大
宝',200,'1','c003');
```

### 笛卡尔积

直接使用交叉连接查询（一般不用）：

语法格式：`select 字段名 from 表1, 表2;  `

例如：`SELECT * FROM category , products;  `

观察查询结果，产生了笛卡尔积 (得到的结果是无法使用的)  

笛卡尔积概念：

假设集合A={a, b}，集合B={0, 1, 2}，则两个集合的笛卡尔积为{(a, 0), (a, 1), (a, 2), (b, 0), (b, 1), (b, 2)}  

### 多表查询的分类

#### 内连接查询

内连接的特点:

-   通过指定的条件去匹配两张表中的数据，匹配上就显示，匹配不上就不显示。
-   例如：通过从  表的外键 = 主表的主键  方式去匹配  

#### 隐式内连接

使用where条件过滤无用的数据

语法格式：`select 字段名 from 左表, 右表 where 连接条件;  `

```mysql
# 隐式内连接
-- 1.查询所有商品信息对应的分类信息
SELECT * FROM products,category WHERE category_id = cid;

-- 2.查询商品表的商品名称，价格，以及商品的分类信息
-- 	 推荐使用给表起别名的方式
SELECT 
	p.`pname`,
	p.`price`,
	c.`cname`
FROM category c , products p WHERE category_id = cid;

-- 3.查询格力空调是属于哪一个分类下的商品
SELECT 
	p.`pname`,
	c.`cname` 
FROM products p , category c
WHERE p.`category_id` = c.`cid` AND p.`pname` = '格力空调';
```

#### 显式内连接

使用inner join ... on 这种方式，就是显式内连接

语法格式：`select 字段名 from 左表 [inner] join 右表 on 条件  `

```mysql
# 显式内连接
-- 1.查询所有商品信息对应的分类信息
SELECT * FROM 
products p INNER JOIN category c ON p.category_id = c.cid;

-- 2.查询鞋服分类下，价格大于500的商品名称和价格
SELECT
    p.pname,
    p.price
FROM products p INNER JOIN category c ON p.category_id = c.cid
WHERE p.price > 500 AND cname = '鞋服';
```

#### 左外连接查询

使用`left outer join `，其中outer可以省略

特点：

-   以左表为基准, 匹配右边表中的数据,如果匹配的上,就展示匹配到的数据
-   如果匹配不到, 左表中的数据正常展示, 右边的展示为null.  

语法格式：`select 字段名 from 左表 left [outer] join 右表 on 条件  `

```mysql
SELECT * FROM category c LEFT JOIN products p ON c.`cid`= p.`category_id`;

-- 查询每个分类下的商品个数
SELECT
	c.`cname` AS '分类名称',
	COUNT(p.`pid`) AS '商品个数'
FROM category c LEFT JOIN products p ON c.`cid` = p.`category_id`
-- 别忘了进行分组
GROUP BY c.`cname`;
```

#### 右外连接

使用`right outer join `，其中outer可以省略

右外连接的特点：

-   以右表为基准，匹配左边表中的数据，如果能匹配到，展示匹配到的数据
-   如果匹配不到，右表中的数据正常展示, 左边展示为null  

语法格式：`select 字段名 from 左表 riget [outer] join 右表 on 条件  `

```mysql
SELECT * FROM products p RIGHT JOIN category c ON p.`category_id` = c.`cid`;
```

#### 各种连接方式的总结

<img src="./MySQL.assets/%E5%A4%9A%E8%A1%A8%E6%9F%A5%E8%AF%A2%E6%80%BB%E7%BB%93.jpg" alt="多表查询总结" style="zoom:33%;" />

-   内连接: inner join , 只获取两张表中 交集部分的数据.
-   左外连接: left join , 以左表为基准 ,查询左表的所有数据, 以及与右表有交集的部分
-   右外连接: right join , 以右表为基准,查询右表的所有的数据,以及与左表有交集的部分  

## 子查询

### 什么是子查询

概念：

-   一条select 查询语句的结果, 作为另一条 select 语句的一部分

特点：

1.   子查询必须放在小括号中
2.   子查询一般作为父查询的查询条件使用

常见分类：

| 分类     | 效果                                                         |
| -------- | ------------------------------------------------------------ |
| where型  | 将子查询的结果, 作为父查询的比较条件                         |
| from型   | 将子查询的结果, 作为 一张表,提供给父层查询使用               |
| exists型 | 子查询的结果是单列多行, 类似一个数组, 父层查询使用 IN 函数 ,包含子查询的结果 |

### 子查询的结果作为查询条件

语法格式：`select 查询字段 from 表 where 字段=(子查询);`

```mysql
-- 1.通过子查询的方式, 查询价格最高的商品信息
SELECT * FROM products 
-- 子查询一定要在括号里
WHERE price = (SELECT MAX(price) FROM products);

-- 2.查询化妆品分类下的 商品名称 商品价格
SELECT
    p.`pname`,
    p.`price`
FROM products p
-- 根据分类id ,去商品表中查询对应的商品信息
WHERE p.`category_id` = (SELECT cid FROM category WHERE cname = '化妆品');

-- 3.查询小于平均价格的商品
SELECT * FROM products
WHERE price < (SELECT AVG(price) FROM products);
```

### 子查询的结果作为一张表

语法格式：`select 查询字段 from（子查询）表别名 where 条件;  `

```mysql
# 查询商品中,价格大于500的商品信息,包括 商品名称 商品价格 商品所属分类名称

-- 1. 先查询分类表的数据
SELECT * FROM category;
-- 2.将上面的查询语句 作为一张表使用
SELECT
    p.`pname`,
    p.`price`,
    c.cname
FROM 
-- 子查询作为一张表使用时 要起别名 才能访问表中字段
products p INNER JOIN (SELECT * FROM category) c
ON p.`category_id` = c.cid WHERE p.`price` > 500;
```

>   当子查询作为一张表的时候，需要起别名，否则无法访问表中的字段。  

### 子查询结果是单列多行

-   子查询结果类似于数组，父层查询使用**in**函数，包含子查询的结果

语法格式：`select 查询字段 from 表 where 字段 in（子查询）; ` 

```mysql
# 查询价格小于两千的商品,来自于哪些分类(名称)
-- 先查询价格小于2000 的商品的分类ID
SELECT DISTINCT category_id FROM products WHERE price < 2000;
-- 在根据分类的id信息,查询分类名称
-- 报错: Subquery returns more than 1 row
-- 子查询的结果不能大于一行
SELECT * FROM category
WHERE cid = (SELECT DISTINCT category_id FROM products WHERE price < 2000);
```

使用in函数， in(c002 , c003)

```mysql
-- 如果子查询获取的是单列多行数据，使用in
SELECT * FROM category
WHERE cid IN (SELECT DISTINCT category_id FROM products WHERE price < 2000);

# 查询家电类 与 鞋服类下面的全部商品信息
-- 先查询出家电与鞋服类的 分类ID
SELECT cid FROM category WHERE cname IN ('家电','鞋服');
-- 根据cid 查询分类下的商品信息
SELECT * FROM products
WHERE category_id IN (SELECT cid FROM category WHERE cname IN ('家电','鞋服'));
```

### 子查询总结

1.   子查询如果查出的是一个字段(单列), 那就在where后面作为条件使用
2.   子查询如果查询出的是多个字段(多列), 就当做一张表使用(要起别名).  

## 数据库设计

### 数据库三范式（空间最省）

概念: 三范式就是设计数据库的规则.

-   为了建立冗余较小、结构合理的数据库，设计数据库时必须遵循一定的规则。在关系型数据库中这种规则就称为范式。范式是符合某一种设计要求的总结。要想设计一个结构合理的关系型数据库，必须满足一定的范式
-   满足最低要求的范式是第一范式（1NF）。
-   在第一范式的基础上进一步满足更多规范要求的称为第二范式（2NF） 
-   其余范式以此类推。一般说来，数据库只需满足第三范式(3NF）就行了  

### 第一范式 1NF

概念：

-   原子性, 做到列不可拆分
-   第一范式是最基本的范式。数据库表里面字段都是单一属性的，不可再分, 如果数据表中每个字段都是不可再分的最小数据单元，则满足第一范式。  

例如：如果一个字段contry中的内容是 "中国上海"，那么就不符合第一范式，应该拆分成contry和city两个字段，一个放'中国'，一个放'上海'

### 第二范式 2NF

概念：

-   在第一范式的基础上更进一步，目标是确保表中的每列都和主键相关。
-   一张表只能描述一件事.  

例如：学员信息表中包含（姓名，性别，学科，成绩）这些字段，就不符合第二范式。

因为其实描述了两个事物 , 一个是学员的信息,一个是课程信息如果放在一张表中，会导致数据的冗余，如果删除学员信息，成绩的信息也被删除了  

### 第三范式 3NF

概念：

-   消除传递依赖
-   表的信息，如果能够被推导出来，就不应该单独的设计一个字段来存放

例如：商品表中包含（数量，单价，总金额）这些字段，就不符合第三范式

因为通过数量与 单价字段就可以计算出总金额，不要在表中再做记录(空间最省)  

## 数据库反三范式

概念：

-   反范式化指的是通过增加冗余或重复的数据来提高数据库的读性能
-   浪费存储空间,节省查询时间 (以空间换时间)  

冗余字段的概念：

​	设计数据库时，某一个字段属于一张表，但它同时出现在另一个或多个表，且完全等同于它在其本来所属表的意义表示，那么这个字段就是一个冗余字段  

例如：

两张表，用户表、订单表，用户表中有字段name，而订单表中也存在字段name  

## 数据库设计总结

创建一个关系型数据库设计，我们有两种选择

1.   尽量遵循范式理论的规约，尽可能少的冗余字段，让数据库设计看起来精致、优雅、让人心醉。
2.   合理的加入冗余字段这个润滑剂，减少join，让数据库执行性能更高更快。  

# SQL索引&视图

## MySQL索引

### 什么是索引

在数据库表中，对字段建立索引可以大大提高查询速度。通过善用这些索引，可以令MySQL的查询和运行更加高效。  类似于字典的目录一样

### 常见索引分类

| 索引名称               | 说明                                                         |
| ---------------------- | ------------------------------------------------------------ |
| 主键索引 (primary key) | 主键是一种唯一性索引,每个表只能有一个主键, 用于标识数据表中的每一 条记录 |
| 唯一索引 (unique)      | 唯一索引指的是 索引列的所有值都只能出现一次, 必须唯一.       |
| 普通索引 (index)       | 最常见的索引,作用就是 加快对数据的访问速度                   |

MySQL将一个表的索引都保存在同一个索引文件中，如果对其中数据进行增删操作，MySQL都会自动更新索引

### 主键索引(primary key)

特点：主键是一种唯一性索引，每个表只能有一个主键，用于标识数据表中的某一条记录

一个表可以没有主键，但最多只能有一个主键，而且主键值不能包含null

语法格式：

```mysql
-- 1. 创建表的时候直接添加主键索引（最常用）
create table 表名(
    -- 添加主键 (主键是唯一性索引,不能为null,不能重复,)
    字段名 类型 primary key,
);

-- 2. 修改表结构，添加主键索引
alter table 表名 add primary key ( 列名 )
```

### 唯一索引(unique)

特点：索引列的所有值都只能出现一次，必须唯一

唯一索引可以保证数据记录的唯一性。事实上，在许多场合，人们创建唯一索引的目的往往不是为了提高访问速度，而只是为了避免数据出现重复。  

语法格式：

```mysql
-- 1. 创建表的时候直接添加唯一索引
create table 表名(
    列名 类型(长度),
    -- 添加唯一索引
    unique [索引名称] (列名)
);

-- 2. 使用create语句创建，在已有的表上创建索引
create unique index 索引名 on 表名（列名（长度））

-- 3. 修改表结构添加索引
alter table 表名 add unique (列名)
```

### 普通索引(index)

普通索引（由关键字KEY或INDEX定义的索引）的唯一任务是加快对数据的访问速度。因此，应该只为那些最经常出现在查询条件（where column=）或排序条件（orderby column）中的数据列创建索引。  

语法格式：

```mysql
-- 1. 使用create index语句创建，在已有的表上创建索引
create index 索引名 on 表名（列名[长度]）

-- 2. 修改表结构添加索引
alter table 表名 add index 索引名 （列名）
```

### 删除索引

由于索引会占用一定的磁盘空间，因此，为了避免影响数据库的性能，应该及时删除不再使用的索引  

语法格式：`alter table 表名 drop index 索引名称;`

### 索引的优缺点总结

添加索引首先应考虑在 where 及 order by 涉及的列上建立索引。
索引的优点

1.   大大的提高查询速度
2.   可以显著的减少查询中分组和排序的时间。

索引的缺点

1.   创建索引和维护索引需要时间，而且数据量越大时间越长
2.   当对表中的数据进行增加，修改，删除的时候，索引也要同时进行维护，降低了数据的维护速度  

## MySQL视图

### 什么是视图

1.   视图是一种虚拟表。
2.   视图建立在已有表的基础上, 视图赖以建立的这些表称为基表。
3.   向视图提供数据内容的语句为 SELECT 语句, 可以将视图理解为存储起来的 SELECT 语句.
4.   视图向用户提供基表数据的另一种表现形式  

### 视图的作用

权限控制时可以使用

-   比如,某几个列可以运行用户查询,其他列不允许,可以开通视图 查询特定的列, 起到权限控制的作用

简化复杂的多表查询

-   视图本身就是一条查询SQL,我们可以将一次复杂的查询 构建成一张视图, 用户只要查询视图就可以获取想要得到的信息(不需要再编写复杂的SQL)
-   视图主要就是为了简化多表的查询  

### 视图的使用

#### 创建视图

语法格式：

```mysql
create view 视图名 [column_list] as select语句;
/*
    view: 表示视图
    column_list: 可选参数，表示属性清单，指定视图中各个属性的名称
    as : 表示视图要执行的操作
    select语句: 向视图提供数据内容
*/

-- 创建一张视图
#1. 先编写查询语句
#查询所有商品 和 商品的对应分类信息
SELECT * FROM products p LEFT JOIN category c ON p.`category_id` = c.`cid`;
#2.基于上面的查询语句,创建一张视图
CREATE VIEW products_category_view
AS SELECT * FROM products p LEFT JOIN category c ON p.`category_id` = c.`cid`;
```

#### 通过视图进行查询

```mysql
-- 查询各个分类下的商品平价价格
# 通过视图查询 可以省略连表的操作
SELECT
    cname AS '分类名称',
    AVG(price) AS '平均价格'
FROM products_category_view GROUP BY cname;

-- 查询鞋服分类下最贵的商品的全部信息
SELECT * FROM products_category_view pcv
WHERE pcv.`cname` = '鞋服'
AND pcv.`price` = (SELECT MAX(price) FROM products_category_view WHERE cname ='鞋服')
```

### 视图与表的区别

-   视图是建立在表的基础上，表存储数据库中的数据，而视图只是做一个数据的展示
-   通过视图不能改变表中数据（一般情况下视图中的数据都是表中的列 经过计算得到的结果,不允许更新）
-   删除视图，表不受影响，而删除表，视图不再起作用  

## 存储过程

存储过程其实就是一堆 SQL 语句的合并。中间加入了一些逻辑控制。  

### 优缺点

优点:

-   存储过程一旦调试完成后，就可以稳定运行，（前提是，业务需求要相对稳定，没有变化）
-   存储过程减少业务系统与数据库的交互，降低耦合，数据库交互更加快捷（应用服务器，与数据库服务器不在同一个地区）

缺点:

-   在互联网行业中，大量使用MySQL，MySQL的存储过程与Oracle的相比较弱，所以较少使用，并且互联网行业需求变化较快也是原因之一
-   尽量在简单的逻辑中使用，存储过程移植十分困难，数据库集群环境，保证各个库之间存储过程变更一致也十分困难。
-   阿里的代码规范里也提出了禁止使用存储过程，存储过程维护起来的确麻烦；  

### 创建过程

语法格式：

```mysql
/*
	方式1
*/
# 创建简单的存储过程
delimiter $$ -- 声明语句结束符，可以自定义 一般使用$$
create procedure 过程名称() -- 声明存储过程
begin -- 开始编写存储过程
	-- 要执行的操作
end $$ -- 存储过程结束

# 调用存储过程
call 存储过程名称

/*
	方式2
*/
-- in 输入参数 ：表示调用者向存储过程传入值
create procedure 存储过程名称（in 参数名 参数类型）

-- 变量赋值
set @变量名=值
-- out输出参数：表示存储过程向调用者传出值
out 变量名 数据类型
```

## 触发器（了解）

语法格式：

```mysql
delimiter $ -- 将Mysql的结束符号从 ; 改为 $,避免执行出现错误
create trigger Trigger_Name -- 触发器名，在一个数据库中触发器名是唯一的
before/after（insert/update/delete） -- 触发的时机 和 监视的事件
on table_Name -- 触发器所在的表
for each row -- 固定写法 叫做行触发器, 每一行受影响，触发事件都执行
begin
	-- begin和end之间写触发事件
end 
$ -- 结束标记
```

## DCL(数据控制语言)

MySql默认使用的都是 root 用户，超级管理员，拥有全部的权限。除了root用户以外，我们还可以通过DCL语言来定义一些权限较小的用户, 分配不同的权限来管理和维护数据库。  

### 创建用户

语法格式：`create user '用户名'@'主机名' identified by '密码';  `

| 参数   | 说明                                                         |
| ------ | ------------------------------------------------------------ |
| 用户名 | 创建的新用户,登录名称                                        |
| 主机名 | 指定该用户在哪个主机上可以登陆，本地用户可用 localhost <br />如果想让该用户可以 从任意远程主机登陆，可以使用通配符 % |
| 密码   | 登录密码                                                     |

### 用户授权

语法格式：`GRANT 权限 1, 权限 2... ON 数据库名.表名 TO '用户名'@'主机名';  `

| 参数 | 说明                                                         |
| ---- | ------------------------------------------------------------ |
| 权限 | 授予用户的权限，如 CREATE、ALTER、SELECT、INSERT、UPDATE 等。 如果要授 予所有的权限则使用 ALL |
| ON   | 用来指定权限针对哪些库和表。                                 |
| TO   | 表示将权限赋予某个用户。                                     |

### 查看权限

语法格式：`show grants for '用户名'@'主机名';  `

### 删除用户

语法格式：`drop user '用户名'@'主机名';  `

### 查询用户

语法格式：`select * from user;`

## 数据库备份&还原

```mysql
-- 语法格式
mysqldump -u 用户名 -p 密码 数据库 > 文件路径

-- 通过命令行备份的数据，如果需要恢复，需要先创建一个对应的数据库
source sql文件地址
```



# 课后习题

## 基础查询

### 数据准备

插入部门数据

```mysql
CREATE TABLE dept(
	did INT PRIMARY KEY AUTO_INCREMENT,
	dname VARCHAR(20)
);

ALTER TABLE dept CHANGE NAME NAME VARCHAR(255) CHARACTER SET utf8;

INSERT INTO dept (NAME) VALUES ('开发部'),('市场部'),('财务部');
```

插入员工数据

```mysql
CREATE TABLE employee (
	eid INT PRIMARY KEY AUTO_INCREMENT,
	ename VARCHAR(10),
	gender CHAR(1),   -- 性别
	salary DOUBLE,   -- 工资
	join_date DATE,  -- 入职日期
	dept_id INT,
	FOREIGN KEY (dept_id) REFERENCES dept(id) -- 外键，关联部门表(部门表的主键)
);

INSERT INTO employee(NAME,gender,salary,join_date,dept_id) VALUES('孙悟空','男',7200,'2013-02-24',1);
INSERT INTO employee(NAME,gender,salary,join_date,dept_id) VALUES('猪八戒','男',3600,'2010-12-02',2);
INSERT INTO employee(NAME,gender,salary,join_date,dept_id) VALUES('唐僧','男',9000,'2008-08-08',2);
INSERT INTO employee(NAME,gender,salary,join_date,dept_id) VALUES('白骨精','女',5000,'2015-10-07',3);
INSERT INTO employee(NAME,gender,salary,join_date,dept_id) VALUES('蜘蛛精','女',4500,'2011-03-14',1);
```

### 习题

```mysql
-- 查询工资最高的员工是谁
SELECT 
	*
FROM employee 
WHERE salary = (SELECT MAX(salary) FROM employee);

-- 查询工资小于平均工资的员工有哪些
SELECT 
	*
FROM employee
WHERE salary < (SELECT AVG(salary) FROM employee);

-- 查询大于5000的员工，来自于哪些部分，输出部分的名字
CREATE VIEW employee_dept AS 
SELECT
	e.ename,
	e.gender,
	e.salary,
	e.join_date,
	d.dname
FROM employee e , dept d WHERE e.dept_id = d.did;

SELECT 
	dname
FROM employee_dept 
WHERE salary > 5000;

-- 查询开发部与财务部所有的员工信息
SELECT 
	*
FROM employee_dept
WHERE dname = '开发部' || dname = '财务部';

-- 查询2011年以后入职的员工信息和部门信息
SELECT
	*
FROM employee_dept
WHERE join_date > '2011-00-00';
```

## 
