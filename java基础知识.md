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

