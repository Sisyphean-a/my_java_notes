## 变量

- 基本概念：
  可以发生变化的单个数据的存储单元

- 变量声明方式：
  数据类型 变量名 = 初始值；

- 固定格式搭配

  ```java
  /*
  * 多行备注
  */
  public class Filename{
      public static void main(String[] args){
          System.out.println(); //单行注释
      }
  }
  ```

## 标识符

- 由数字、字母、下划线以及$等组成，其中数字不能开头
- 不能使用关键字，不建议使用中文，不建议使用过长的变量名

### 实例1

```java
/*
 编程实现变量的输入输出
*/
import java.util.Scanner;

public class VarIOText{
    public static void main(String[] args){
        System.out.println("请输入你的名字和年龄");
        //创建一个扫描器来扫描键盘的输入，System.in表示键盘的输入
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        int age = sc.nextInt();
        System.out.println("name = " + name + "，age = " + age);
    }
}
```

## 数据类型

### （1）基本数据类型

- byte
- short
- int
- long
- float
- double
- boolean
- char

### （2）引用数据类型

- 数组、类、接口、枚举、标注

## 进制

- 八进制和十六进制其实都是二进制的简写

### 二进制

- 表示方式：前缀：0b/0B

- 二进制的最高位（最左边）用于代表符号位，若该位是0则表示非负数，若该位是1则表示负数，注意：**此最高位表示的是所有位数的最高位**，例如64位的最高位
- 正十进制转为二进制的方法：
  - 除2取余法：需倒叙计数
  - 拆分法：需记忆二进制权重：1，2，4，8，16，32，64，128，。。。
- 正二进制转为十进制：
  - 加权法：使用二进制的每个数字乘以当前位的权重再累加

##  算术运算符

- 基本运算符：+,-,*,/(除法),%(取余，取模)

- 声明两个变量的方法
	- 用逗号分开，不推荐
	- 使用分号，换行，推荐

- 算术运算的基本概念：
	- (操作数 操作符，操作数) --> 表达式

- 注意点：
	- java中整数相除时，结果值只保留整数部分，舍弃小数部分
	- 若希望保留小数，则改变其中的任一操作数使之为浮点数即可
		- 使用强制类型转换：(double)num，尽量不要用
		- 让其中一个数乘以1.0即可，推荐使用
	- 0不能作为被除数
		- *ArithmeticException*，算术异常，需记忆
		- 若整数除以0.0,则输出Infinity无穷
		- 若0/0.0,输出NaN,不是一个数

###  案例：拆分整数为时分秒

```java
/*
 * 输入正整数使用算术运算符进行拆分输出时分秒
 *
 * */

import java.util.Scanner;
public class TwoCase {
	public static void main(String[] args) {
		System.out.println("请输入需要拆分的正整数");
		Scanner sc = new Scanner(System.in);
		int i1 = sc.nextInt();
		boolean b1 = i1 > 0;
		System.out.println("是否为正整数：" + b1);
		int hours = i1 / 3600;
		int minites = i1 / 60 % 60;
		int seconds = i1 % 60;
		System.out.println("时间是" +hours+ " hou "+minites+" min " +seconds+" sec");
	}
}
```


### 字符串连接符运算符

可以实现字符串与其他符号的连接

何时为运算，何时为连接：
	只有当加号+两边都不是字符串时，则为运算符
	判定是从左往右进行的，判定后类型会变换

> (a+b+c) (a+b+c+"") (a+""+b+c)  (""+a+b+c)


##  关系运算符

- 类型：< <= >= > == !=

- 输出布尔类型，故而赋值时需使用boolean

##  自增减运算符

- 只用于变量，常量不可变

- ++ : 自增运算符，使当前变量自身的数值加一
- -- : 自减运算符，使当前变量自身的数值减一

使用格式：
- a++; //等同于a = a+1;因为是等号在后，故是先赋值后运算
- ++a; //等同于a = a+1;因为是等号在前，故是先运算后赋值

注意：
a++,++a属于表达式，a属于变量，两个不等同，最终的a的值是相同的，但是两者赋值的对象不相同
故a++ != ++a，但是a=a

例：a = 1; b=a++; c=++a; 三者的最终结果为：3,1,3












