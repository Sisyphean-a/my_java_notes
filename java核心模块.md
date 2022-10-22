## 常用类的概述和使用

### Object类

概念：java.lang.Object类是java类层次结构的根类，即任意一个类都是它的直接或间接子类

说明：

- 如果定义一个类时没有使用extends关键字声明其父类，则其父类为java.lang.Object类
- Object类定义了“对象”的基本行为，被子类默认继承

#### Object

作用：使用无参方式构造对象

声明：Object()  

#### equals

作用：指示某个其他对象是否“等于”此对象

声明：`boolean equals(Object obj)`

说明：

- 用于判断调用对象是否与参数对象相等
- 该方法默认比较两个对象的地址是否相等，与"=="的结果一致
- 若希望比较两个对象的内容，则需要重写该方法
- 若该方法被重写后，则应该重写hashCode方法来保持结果的一致性

```java
//equals的重写,用于比较两个Student类型的ID值是否相同
 @Override
    public boolean equals(Object obj){
        //当调用对象和参数对象一致时，返回true
        if (this == obj) return true;
        //当参数对象为空时，返回false
        if (null == obj) return false;
        //判断调用对象和是否为Student类型的对象，若是则条件成立
        if (obj instanceof Student) {
            Student ts = (Student) obj;
            return  this.getId() == ts.getId();
        }
        //若不是，则类型不同，一定不一致
        return false;
    }
```

#### hashCode

作用：用于获取调用对象的哈希码值

声明：`int hashCode()`

说明：

- 若两个对象调用equals方法相等，则各自调用该方法的结果必须相同
- 若两个调用对象equals方法不相等，则各自调用该方法的结果应该不相同
- 为了使得该方法与equals方法保持一致，需要重写该方法

```java
//hashCode的重写
    @Override
    public int hashCode(){
        return this.getId();
    }
```

#### toString

作用：用于获取调用对象的字符串形式

声明：String toString()

说明：

- 该方法默认返回的字符串为：包名.类名@哈希码值的十六进制
- 为了返回更有意义的数据，需要重写该方法
- 使用println打印引用或字符串拼接引用都会自动调用该方法

```java
@Override
public String toString(){
    return "Student[ID = " + getId() + " ， Name = " + getName() + "]";
}

//------------------------------------------------------------------
Student s1 = new Student(100,"a");
System.out.println(s1.toString());
System.out.println(s1); //当打印一个引用变量时，会自动调用toString方法
String st = "hello " + s1;
System.out.println(st);
```

#### 案例：

- 编程实现Student类的封装，特征：学号和姓名，要求提供打印所有特征的方法
- 编程实现StudentTest类，在main方法中使用有参方式构造两个Student类型的对象并打印特征

题目扩展：

- 如何实现以姓名为基准判断两个对象是否相等？以及以学号和姓名同时为基准判断两个对象是否相等

```java
@Override
public boolean equals(Object obj){
    //当调用对象和参数对象一致时，返回true
    if (this == obj) return true;
    //当参数对象为空时，返回false
    if (null == obj) return false;
    //判断调用对象和是否为Student类型的对象，若是则条件成立
    if (obj instanceof Student) {
        Student ts = (Student) obj;
        // 如果以id为基准去比较，可以用==进行判断，因为id是int类型，基本数据类型，空间存的是数值
        // return  this.getId() == ts.getId();
        // 如果以name为基准，不可以使用==进行判断，因为String类型是引用数据类型，空间存的是地址
        // 不过String类型内部已经重写了equals方法，可以直接使用引用.调用
        return this.getName().equals(ts.getName());
    }
    //若不是，则类型不同，一定不一致
    return false;
}

@Override
public int hashCode(){
    //return this.getId();
    // 同样，String类型无法返回数值，而String类型已经重写了此方法，同样可以直接调用
    return  this.getName().hashCode();
}
```

### 包装类

概念：通常情况下基本数据类型的变量不是对象，为了满足万物皆可对象的理念就需要对基本数据类型的变量进行打包处理变成对象，而负责将这些变量声明为成员变量进行对象化处理的相关类，叫做包装类

| 包装类              | 对应的基本类型 |
| ------------------- | -------------- |
| java.lang.Byte      | byte           |
| java.lang.Short     | short          |
| java.lang.Integer   | int            |
| java.lang.Long      | long           |
| java.lang.Float     | float          |
| java.lang.Double    | double         |
| java.lang.Boolean   | boolean        |
| java.lang.Character | char           |

java.lang.Number类是一个抽象类，是上述Byte,Short,Integer,Long,Float,Double类的父类。用来描述所有类共有的成员

#### Integer类

概念：内部包装了一个int类型的变量作为成员变量，主要为了实现对int类型的包装

##### 常用的变量

```java
System.out.println("最大值是：" + Integer.MAX_VALUE);    //2^31-1
System.out.println("最小值是：" + Integer.MIN_VALUE);    //-2^31
System.out.println("所表示的二进制位数是：" + Integer.SIZE);   //32
System.out.println("所占字节的个数是：" + Integer.BYTES);    //4
System.out.println("对应int类型的Class实例是：" + Integer.TYPE); //int
```

##### 常用的方法

| 方法声明                                | 功能介绍                              |
| --------------------------------------- | ------------------------------------- |
| int **intValue**()                      | 获取调用对象中的整数值并返回          |
| static Integer **valueOf**(int i)       | 根据参数指定整数值得到Integer类型对象 |
| boolean **equals**(Object obj)          | 比较调用对象与参数指定的对象是否相等  |
| String **toString**()                   | 返回描述调用对象值得字符串形式        |
| static int **parseInt**(String s)       | 将字符串类型转换为int类型并返回       |
| static String **toString**(int i)       | 获取参数指定整数的十进制字符串形式    |
| static String **toBinaryString**(int i) | 获取参数指定整数的二进制字符串形式    |
| Static String **toHexString**(int i)    | 获取参数指定整数的十六进制字符串形式  |
| Static String **toOctalString**(int i)  | 获取参数指定整数的八进制字符串形式    |

```java
//等效于把int类型转换为integer类型，这种方式也叫做装箱
Integer it1 = Integer.valueOf(123);     //123
//等效于把string类型转换为integer类型
Integer it2 = Integer.valueOf("456");   //456
//等效于把integer类型转换为int类型，这种方式也叫做拆箱
int ia = it2.intValue();   //456

//----------------------------------------------
//java5增加了自动装箱和自动拆箱的机制
Integer it3 = 123; //使用赋值运算符直接进行运算
int ib = it3;
```

```java
// 静态方法的实现
int ic = Integer.parseInt("100");
//int id = Integer.parseInt("100a");//NumberFormatException 数字格式异常
System.out.println("字符串转换为整数的结果是：" + ic);
System.out.println("由传入的整数获取对应的十进制字符串：" + Integer.toString(200));
System.out.println("由传入的整数获取对应的二进制字符串：" + Integer.toBinaryString(200));
System.out.println("由传入的整数获取对应的十六进制字符串：" + Integer.toHexString(200));
System.out.println("由传入的整数获取对应的八进制字符串：" + Integer.toOctalString(200));
```

> NumberFormatException 数字格式异常，需记忆

##### 装箱拆箱笔试考点

```java
Integer it4 = 128;
Integer it5 = 128;
System.out.println(it4 == it5);  // false  ==比较的是地址，两个变量两个地址
System.out.println(it4.equals(it5)); // true equals比较的是内容，内容相同
Integer it6 = 127;
Integer it7 = 127;
System.out.println(it6 == it7);  // true 自动装箱池，-128~127之间的内容自动装箱
System.out.println(it4.equals(it5)); //true
```

#### Double

概念：内部包装了一个Double类型的变量作为成员变量，主要为了实现对Double类型的包装

##### 常用的变量

SIZE，BYTES，TYPE ，都对应的是Double类型的

##### 常用的方法

| 方法声明                                | 功能介绍                             |
| --------------------------------------- | ------------------------------------ |
| double **doubleValue**()                | 获取调用对象的中的浮点数据并返回     |
| static Double **valueOf**(double d)     | 根据传入浮点数据得到Double类型对象   |
| boolean **equals**(Object obj)          | 比较调用对象与参数指定的对象是否相等 |
| String **toString**()                   | 返回描述调用对象数值的字符串形式     |
| Static double **parseDouble**(String s) | 将字符串类型转换为double类型并返回   |
| boolean **isNaN**()                     | 判断调用对象的数值是否为非数字       |

```java
//实现从double到Double类型的转换，即装箱
Double db = Double.valueOf(3.14);
//实现从Double到double类型的转换，即拆箱
double d = db.doubleValue();
//实现从字符串到Double类型的转换
Double db2 = Double.parseDouble("3.1415");
//判断是否为非数字，若是数字则返回false
booble b = db2.isNaN();

//java5开始已经可以实现自动装箱和自动拆箱
Double db = 3.14;
double d =  db;
//Double没有自动装箱池
```

#### Boolean

概念：内部包装了一个boolean类型的变量作为成员变量，主要为了实现对boolean类型的包装

##### 常用的常量

FALSE（对应基值为false的对象），TRUE（对应基值为true的对象），TYPE

##### 常用的方法

| 方法声明                                  | 方法声明                       |
| ----------------------------------------- | ------------------------------ |
| boolean **booleanValue**()                | boolean **equals**(Object obj) |
| static Boolean **valueOf**(boolean b)     | String **toString**()          |
| static boolean **parseBoolean**(String s) | <同样提供自动拆箱装箱>         |

#### Character

概念：内部包装了一个boolean类型的变量作为成员变量，主要为了实现对boolean类型的包装

##### 常用的常量

SIZE，BYTES，TYPE 对应的是char类型

##### 常用的方法

| 方法声明                             | 方法声明                       |
| ------------------------------------ | ------------------------------ |
| char **charValue**()                 | boolean **equals**(Object obj) |
| static Character **valueOf**(char c) | String **toString**()          |

| 方法声明                                | 功能介绍                       |
| --------------------------------------- | ------------------------------ |
| static boolean **isUpperCase**(char ch) | 判断参数指定字符是否为大写字符 |
| static boolean **isLowerCase**(char ch) | 判断参数指定字符是否为小写字符 |
| static boolean **isDigit**(char ch)     | 判断参数指定字符是否为数字字符 |
| static char **toUpperCase**(char ch)    | 将参数指定的字符转换为大写字符 |
| static char **toLowerCase**(char ch)    | 将参数指定的字符转换为小写字符 |

```java
Character c1 = 'a';  //自动装箱
char c2 = c1;       //自动拆箱
System.out.println("大写？" + Character.isUpperCase(c1));
System.out.println("小写？" + Character.isLowerCase(c1));
System.out.println("转为大写：" + Character.toUpperCase(c1));
System.out.println("转为小写：" + Character.toLowerCase(c1));
```

#### 包装类使用总结

- 基本数据类型转换为对应包装类的方式：

  调用包装类的静态方法即可

- 获取包装类对象中基本数据类型变量数值的方式

  调用包装类的xxxValue方法即可

- 字符串转换为基本数据类型的方式

  调用包装类的parseXxx方法即可

### 数学处理类

#### Math类

基本概念：java.lang.Math类主要用于提供执行数学运算的方法，如：对数，平方根

##### 常用的方法

| 方法声明                                  | 功能介绍                 |
| ----------------------------------------- | ------------------------ |
| static int **max**(int a, int b)          | 返回两个参数中的最大值   |
| static int **min**(int a ,int b)          | 返回两个参数中的最小值   |
| static double **pow**(double a ,double b) | 返回第一个参数的幂       |
| static int **abs**(int a)                 | 返回参数指定数值的绝对值 |
| static long **round**(double a)           | 返回参数四舍五入的结果   |
| static double **sqrt**(double a)          | 返回参数的平方根         |
| static double **random**()                | 返回0.0到1.0的随机数     |

```java
System.out.println("两个参数的最大值：" + Math.max(10,20));
System.out.println("两个参数的最小值：" + Math.min(10,20));
System.out.println("第一个参数的幂：" + Math.pow(2,3)); //8.0
System.out.println("参数的绝对值：" + Math.abs(-2));
System.out.println("参数四舍五入后：" + Math.round(5.888));//6
System.out.println("参数平方根：" + Math.sqrt(4));    //2.0
System.out.println("返回一个0~1随机数：" + Math.random());
```

#### BigDecimal类

概念：由于float类型和double类型在运算时可能会有误差，若希望实现精确运算则借助java.math.BigDecimal类型加以描述

##### 常用方法

| 方法的声明                                       | 功能介绍                     |
| ------------------------------------------------ | ---------------------------- |
| **BigDecimal**(String val)                       | 根据参数指定的字符串构建对象 |
| BigDecimal **add**(BigDecimal augend)            | 用于实现加法运算             |
| BigDecimal **subtract**(BigDecimal subtrahend)   | 用于实现减法运算             |
| BigDecimal **multiply**(BigDecimal multiplicand) | 用于实现乘法运算             |
| BigDecimal **divide**(BigDecimal divisor)        | 用于实现除法运算             |

```java
BigDecimal bd1 = new BigDecimal("0.2");
BigDecimal bd2 = new BigDecimal("0.1");
System.out.println("加法：" + bd1.add(bd2));
System.out.println("减法：" + bd1.subtract(bd2));
System.out.println("乘法：" + bd1.multiply(bd2));
System.out.println("除法：" + bd1.divide(bd2, RoundingMode.HALF_DOWN));
```

#### BigInteger类

概念：若希望表示比long类型范围还大的整数数据，则需要借助java.math.BigInteger类型描述

| 方法声明                                             | 功能介绍                       |
| ---------------------------------------------------- | ------------------------------ |
| **BigInteger**(String val)                           | 根据参数指定的字符串来构造对象 |
| BigInteger **add** (BigInteger val)                  | 用于实现加法运算               |
| BigInteger **subtract**(Biginteger val)              | 用于实现减法运算               |
| BigInteger **multiply**(Biginteger val)              | 用于实现乘法计算               |
| BigInteger **divide**(Biginteger val)                | 用于实现除法计算               |
| BigInteger **remainder**(Biginteger val)             | 用于实现取余运算               |
| BigInteger[] **divideAndRemainder** (Biginteger val) | 用于实现取商和余数的运算       |

```java
BigInteger b1 = new BigInteger("108");
BigInteger b2 = new BigInteger("24");
System.out.println("加法：" + b1.add(b2));
System.out.println("减法：" + b1.subtract(b2));
System.out.println("乘法：" + b1.multiply(b2));
System.out.println("除法：" + b1.divide(b2)); //4
System.out.println("取余：" + b1.remainder(b2));//12

BigInteger[] arr = b1.divideAndRemainder(b2);
System.out.println("取商和取余：" + arr[0] + ";" + arr[1]); //4;12
```

## String类的概述和使用

概念：java.lang.String类用于描述字符串，java程序中所有的字符串字面值都可以使用该类的对象加以描述

注意：

- 该类由final关键字修饰，表示该类不能被继承
- 从jdk1.9开始该类的底层不使用char[]来存储数据，而是改成byte[]加上编码标记，从而节约空间
- 该类描述的字符串内容是个常量不可更改，因此可以被共享使用。如果重新赋值，改变的是指向而不是值

#### 常量池

概念：由于String类型描述的字符串内容是常量不可改变，因此java虚拟机将首次出现的字符串放入常量池中，若后续代码中出现了相同字符串内容则直接使用池中已有的字符串对象而无需申请内存和创建对象，从而提高了性能

#### 常用的构造方法（重点）

| 方法声明                                    | 功能介绍                                                     |
| ------------------------------------------- | ------------------------------------------------------------ |
| **String**()                                | 使用无参方式构造对象得到空字节序列                           |
| String(byte[] bytes.int offset,int length)  | 使用bytes数组中下标从offset位置开始的length个字节来构造对象  |
| String(byte[] bytes)                        | 使用bytes数组中的所有内容构造对象                            |
| String(char[] value, int offset, int count) | 使用value数组中下边从offset位置开始的count个字符来构造对象   |
| String(char[] value)                        | 使用value数组中的所有内容构造对象                            |
| String(String original)                     | 根据参数指定的字符串内容来构造对象，新创建对象为参数对象的副本 |

```java
String s1 = new String();
//输出为空而不是null，空是有对象无内容，null没有对象
System.out.println("s1 = " + s1);   // "" 自动调用toString方法

byte[] bArr = {97,98,99,100,101};
//会将数字转换为对应的字符，例如 'b'==98;然后截取内容
String s2 = new String(bArr,1,3);	//bcd
//输出全部内容
String s3 = new String(bArr);		//abcde

char[] cArr = {'a','b','c','d'};
//把char数组所有内容串起来
String s4 = new String(cArr); 		//abcd

//使用字符串构造字符串
String s5 = new String("hello");	//hello
```

#### String构造笔试题

```java
//笔试题1：下面的代码会创建几个对象，分别存放在什么地方
String s6 = "abc"; //一个对象，存放在常量池中
String s7 = new String("hello"); //两个对象，一个在常量池中，一个在堆区中

//笔试题2：分析下面代码的执行结果
String st1 = "hello";       //常量池
String st2 = "hello";       //常量池
String st3 = new String("hello"); //申请一个堆区复制存放常量池中的"hello"
String st4 = new String("hello");
System.out.println(st1 == st2);     //比较地址；true
System.out.println(st1.equals(st2));//比较内容；true
System.out.println(st3 == st4);     //false
System.out.println(st4.equals(st4));//true
System.out.println(st2 == st4);     //false
System.out.println(st2.equals(st4));//true

//笔试题3：常量的优化机制
System.out.println("------------------------------");
String st5 = "abcd";
String st6 = "ab" + "cd";   //常量优化机制，两个常量相加直接合并成一个常量
String st7 = "ab";
String st8 = st7 + "cd";    //变量+常量，不会触发常量优化机制
System.out.println(st5 == st6); //true
System.out.println(st5 == st8); //false
```

#### 常用的成员方法（重点）

| 方法声明                 | 功能介绍（字符串类型的转换与拆分）       |
| ------------------------ | ---------------------------------------- |
| String **toString**()    | 返回字符串本身                           |
| byte[] **getBytes**()    | 将当前字符串内容转换为byte数组并返回     |
| char[] **toCharArray**() | 用于将当前字符串内容转换为char数组并返回 |

```java
String s1 = "world";

//过程：将String类型拆分为char类型，然后char转换为byte类型
byte[] bytes = s1.getBytes();   //119, 111, 114, 108, 100
System.out.println("bytes = " + Arrays.toString(bytes));
//把byte类型再转换为String类型，直接使用构造方法
String s2 = new String(bytes);

//把String类型转换为char类型
char[] chars = s2.toCharArray();    //w, o, r, l, d
System.out.println("chars = " + Arrays.toString(chars));
//把char数组类型转换为String类型
String s3 = new String(chars);
```

| 方法声明                   | 功能介绍（获取字符串的长度） |
| -------------------------- | ---------------------------- |
| char **charAt**(int index) | 用于返回字符串指定位置的字符 |
| int **length**()           | 返回字符串字符序列的长度     |
| boolean **isEmpty**()      | 判断字符串是否为空           |

```java
System.out.println("world".charAt(0));  //w
System.out.println("world".length());   //5
System.out.println("world".isEmpty());  //false

//笔试考点
//使用两种方法实现"12345"到12345的转变
String st = "12345";
//1. 利用Integer的方法parseInt进行转换
int ia = Integer.parseInt(st);
//2. 利用ASCII方法进行转换
int ib = 0;
for (int i = 0 ; i < st.length() ; i++){
    ib = ib*10 + (st.charAt(i) - '0');
}
System.out.println(ib);
 
//实现从12345到"12345"的转换
String str = String.valueOf(ia);
```

##### 案例：判断语句是否是回文

```java
public static void main(String[] args) {
    //1.获取长度，获取一半长度内容
    String st = "上海自来水来自海上";
    int size = st.length();
    // 2. 通过循环进行比较
    for (int i=0; i<size/2;i++){
        if (st.charAt(i) != st.charAt(size-1-i)){
            System.out.println(st + " ：不是回文");
            return; //仅仅用于结束方法，避免所有结果都会输出“是回文”
        }
    }
    System.out.println(st + " ：是回文");
}
```

| 方法声明                                | 功能介绍（字符串的比较）                 |
| --------------------------------------- | ---------------------------------------- |
| int **compareTo**(String anotherString) | 用于比较调用对象和参数对象的大小关系     |
| int **compareToIgnoreCase**(String str) | 不考虑大小写，也就是'a'和'A'是相等的关系 |

```java
String a = "hello";
//this.charAt(k)-anotherString.charAt(k)
//从第一位开始比，如果都相同就比长度
System.out.println(a.compareTo("world"));   //'h' - 'w' = -15
System.out.println(a.compareTo("helli"));   //'o' - 'i' = 6
System.out.println(a.compareTo("helloworld"));   //长度 5 - 10 = -5
System.out.println(a.compareToIgnoreCase("HELLO")); //0
```

| 方法声明                                       | 功能介绍（字符串的修改与判断）               |
| ---------------------------------------------- | -------------------------------------------- |
| String **concat**(String str)                  | 用于实现字符串的拼接（很少使用）             |
| boolean **contains**(CharSequence s)           | 用于判断当前字符串是否包含参数指定的内容     |
| String **toLowerCase**()                       | 返回字符串的小写形式                         |
| String **toUpperCase**()                       | 返回字符串的大写形式                         |
| String **trim**()                              | 返回去掉前导和后继空白的字符串(即去前后空格) |
| boolean **startsWith**(String prefix)          | 判断字符串是否以参数字符串开头               |
| boolean startsWith(String prefix ,int toffset) | 从指定位置开始是否以参数字符串开头           |
| boolean **endsWith**(String suffix)            | 判断字符串是否以参数字符串结尾               |

```java
String st1 = "  Hello World";

//使用contains方法判断字符串是否存在
boolean bl = st1.contains("hello"); // false
System.out.println(bl);
bl = st1.contains("Hello");     //true

//转换字符串为大写或小写
String st2 = st1.toUpperCase(); //  HELLO WORLD
System.out.println(st1);  //此时st1仍未原值，因为字符串为常量，不可变
String st3 = st1.toLowerCase(); //  hello world

//去除空格
String st4 = st1.trim();    //Hello World

//判断字符串的开头与结尾
bl = st1.startsWith("Hello");   //false
bl = st1.startsWith(" ");   //true
bl = st1.startsWith("Hello",2);   //true，计数从0开始
System.out.println(bl);
bl = st1.endsWith("rld"); //true
System.out.println(bl);
```

| 方法声明                                           | 功能介绍（常用方法）                           |
| -------------------------------------------------- | ---------------------------------------------- |
| boolean **equals**(Object anObject)                | 用于比较字符串内容是否相等并返回               |
| int **hashCode**()                                 | 获取调用对象的哈希码值                         |
| boolean **equalsIgnoreCase**(String anotherString) | 用于比较字符串内容是否相等并返回，不考虑大小写 |

##### 案例：判断用户名和方法

提示用户输入用户名和密码信息，若为"admin"和"123456"则提示"登陆成功"，否则提示"用户名或密码错误，还有n此机会"，若用户输入三次依然错误，则提示用户"此账户已冻结"

```java
public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    for (int i = 3 ; i>0; i--) {
        // 提示用户输入用户名和密码信息
        System.out.println("请输入用户名: ");
        String sName = sc.next();
        System.out.println("请输入密码: ");
        String sPass = sc.next();

        // 对用户名和密码信息进行判断
        // boolean bl1 = sName.equalsIgnoreCase("admin");
		boolean bl1 = "admin".equals(sName);
        boolean bl2 = "123456".equals(sPass); //固定值放前面,避免空指针异常


        if (bl1 && bl2) {
            System.out.println("登录成功");
            //return;
            break; //如果相同就退出循环,和return效果类似,但是作用区域不同
        }
        if (i == 1) {
            System.out.println("此账号已冻结");
        } else {
            System.out.println("用户名或密码错误,还有" + (i - 1) + "次机会");
        }
    }
    // 关闭扫描器
    sc.close();
}
```

| 方法声明                                   | 功能介绍（获取字符或字符串位置）                        |
| ------------------------------------------ | ------------------------------------------------------- |
| int **indexOf**(int ch)                    | 用于返回当前字符串中参数ch指定的字符第一次出现的下标    |
| int indexOf(int ch , int fromIndex)        | 用于从fromIndex位置开始查找ch指定的字符                 |
| int indexOf(String str)                    | 在字符串中检索str返回其第一次出现的位置，若找不到返回-1 |
| int indexOf(String str , int fromIndex)    | 表示从字符串的fromIndex位置开始检索str第一次出现的位置  |
| int **lastIndexOf**(int ch)                | 用于返回参数ch指定的字符最后一次出现的下标              |
| int lastIndexOf(int ch , int fromIndex)    | 用于从fromIndex位置开始查找ch指定字符出现的下标         |
| int lastIndexOf(string str)                | 返回str指定字符串最后一次出现的下标                     |
| int lastIndexOf(String str, int fromIndex) | 用于从fromIndex位置开始反向搜索的第一次出现的下标       |

```java
String st = "Hello World ,";
System.out.println(st);

//返回传入参数对应的第一个索引位置
int ia = st.indexOf("L");//若不存在,则返回-1
int ib = st.indexOf("l");//2 
int ic = st.indexOf("llo"); //2
//返回从指定下标开始查找l第一次出现的的索引位置(包含0)
int id = st.indexOf("l",0); // 2
int ie = st.indexOf("l",3); // 3

//-----------------------------------------------------
//案例：打印字符串中所有"l"的索引
int pot = st.indexOf("l");
while (-1 != pot){
    System.out.println("pot = " + pot);
    pot = st.indexOf("l" , pot+1);
}

//上述语句有重复字段,可以优化一下
int pot1 = 0;
while ((pot1 = st.indexOf("l",pot1)) != -1){
    System.out.println("pot1 = " + pot1);
    pot1 += 1;
}
//-----------------------------------------------------

//返回传入参数对应的最后一个索引位置，反向查找
int a = st.lastIndexOf("l");    //9
int b = st.lastIndexOf("l",8);  //3
```

| 方法声明                                            | 功能介绍（获取子字符串）                                     |
| --------------------------------------------------- | ------------------------------------------------------------ |
| String **substring**(int beginIndex , int endIndex) | 返回字符串从下标beginIndex(包括)开始到endIndex(不包括)结束的子字符串 |
| String substring(int beginIndex)                    | 返回字符串从下标beginIndex(包括)开始到字符串结尾的子字符串   |

```java
String st = "To be or not to be";
String sst  = st.substring(9);		//not to be
String sst1  = st.substring(9,12);	//not 	

//案例: 获取指定字符后面的字符串
int ia = st.indexOf("or");
String sst2 = st.substring(ia + 3);
System.out.println(sst2);
```

### 正则表达式（了解）

概念：用于对字符串数据的格式进行验证，以及匹配，查找，替换等操作。该字符串通常使用^运算符作为开头标志，使用$运算符作为结尾标志，当然也可以忽略

| 正则表达式  | 说明                              |
| ----------- | --------------------------------- |
| [abc]       | 可以出现abc中任意一个字符         |
| [^abc]      | 可以出现除了abc之外的任亿一个字符 |
| [a-z]       | 可以出现从a到z中的任意一个字符    |
| [a-zA-Z0-9] | 可以出现任意的英文字符和数字      |

| 正则表达式 | 说明                                 |
| ---------- | ------------------------------------ |
| .          | 任意一个字符（不含换行符）           |
| \d         | 任意一个数字字符                     |
| \D         | 任意一个非数字字符                   |
| \s         | 空白字符，不包含特殊字符             |
| \S         | 非空白字符                           |
| \w         | 任意一个单词字符，等效于[a-zA-Z_0-9] |
| \W         | 任意一个非单词字符                   |

| 正则 数量限制 | 说明                        |
| ------------- | --------------------------- |
| X?            | X可以出现0~1次，最多1次     |
| X*            | X可以出现0~n次，任意次      |
| X+            | X可以出现1~n次，至少1次     |
| X{n}          | X可以出现恰好n次            |
| X{n,}         | X可以出现至少n次            |
| X{n,m}        | X可以出现至少n次且不超过m次 |

#### 正则表达式相关的方法

| 方法名称                          | 方法说明                                                 |
| --------------------------------- | -------------------------------------------------------- |
| boolean **matches**(String regex) | 判断当前正在调用的字符串是否匹配参数指定的正则表达式规则 |

```java
String st = "123456";
String re1 = "^[0-9]{6}$"; // = "\\d{6}";
System.out.println(st.matches(re1));

//描述QQ号码的规则：由非0开头的5~15位数组成
String st2 = "12455";
String re2 = "^[1-9]\\d{4,14}";

//描述手机号码的规则：由1开头，第二位是3、4、5、7、8中的一位，共11位
String st3 = "15000000000";
String re3 = "^1[34578]\\d{9}$";

//描述身份证号码的规则：总共18位，6位数字代表地区，4位数字代表年，2位数字代表月，2位数字代表日期，三位数字代表个人最后一位可能是X
String st4 = "999999999999999999";
//()代表分组,|代表或
String re4 = "(\\d{6})[12]\\d{10}([0-9|X])";
```



| 方法名称                                                 | 方法说明                                                |
| -------------------------------------------------------- | ------------------------------------------------------- |
| String[] **split**(String regex)                         | 以regex所表示的字符串为分隔符，将字符串拆分为字符串数组 |
| String **replace**(char oldChar , char newChar)          | 使用参数newChar替换字符串中出现的所有参数oldChar        |
| String **replaceFirst**(String regex,String replacement) | 替换此字符串匹配给定的正则表达式的第一个字符串          |
| String **replaceAll**(String regex,String replacement)   | 将字符串中匹配regex的字符串替换成replacement            |

```java
String st = "1001,zhang,30";
//使用参数分割字符串
String[] s = st.split(","); //[1001, zhang, 30]

//使用参数替换字符串
String s1 = st.replace(",","|");    //  1001|zhang|30
//使用正则表达式替换字符串,方便匹配第一个与所有的
String s2 = st.replaceFirst("[0]" ,"1");// 1101,zhang,30
String s3 = st.replaceAll("[0]","9");//   1991,zhang,39
```

## 可变字符串类和日期相关类

### 可变字符串类（重点）

概念：由于String 类描述的字符串内容是个常量不可改变，但需要在java代码中描述大量类似的字符串是，只能单独申请和存储，因此造成内存空间的浪费。为了解决这种情况，可以使用java.lang.StringBuilder类的java.lang.StringBuffer类来描述字符序列可以改变的字符串

- StringBuffer类是从jdk1.0开始存在，属于线程安全的类，因此效率比较低
- StringBuilder类是从jdk1.5开始存在，属于非线程安全的类，效率比较高

#### StringBuilder类常用的构造方法

| 方法声明                    | 功能介绍                                              |
| --------------------------- | ----------------------------------------------------- |
| **StringBuilder**()         | 使用无参方式构造对象，容量为16                        |
| StringBuilder(int capacity) | 根据参数指定的容量来构造对象，容量为参数指定大小      |
| StringBuilder(String str)   | 根据参数指定的字符串来构造对象，容量为：16+字符串长度 |

#### StringBuilder类常用的成员方法

| 方法声明                                                 | 功能介绍                                    |
| -------------------------------------------------------- | ------------------------------------------- |
| int **capacity**()                                       | 用于返回调用对象的容量                      |
| int **length**()                                         | 用于返回字符串的长度，也就是字符的个数      |
| StringBuilder **insert**(int offset, String str)         | 插入字符串并返回调用对象的引用，就是自己    |
| StringBuilder **append**(String str)                     | 追加字符串                                  |
| StringBuilder **deleteCharAt**(int start)                | 将当前字符串中下标为index位置的单个字符删除 |
| StringBuilder **delete**(int start ,int end)             | 删除字符串[start end)                       |
| StringBuilder **replace**(int start ,int end,String Str) | 替换字符串                                  |
| StringBuilder **reverse**()                              | 字符串反转                                  |

```java
StringBuilder sb = new StringBuilder("morning");
System.out.println(sb.capacity());  //23
System.out.println(sb.length());    //7

//---------------------------需背诵记忆-----------------------
//  增 (插入）
sb.insert(0,"Hi ");        //Hi morning
sb.insert(3,"fusi ");       //Hi fusi morning
//  增 (追加）
//默认扩容算法：若超过原始容量：原始容量 * 2 + 2
//底层采用byte数组存放所有字符内容，使用位运算来进行计算
sb.append("!");                     //Hi fusi morning!

//  删 (指定下标删除)
sb.deleteCharAt(8);         //Hi fusi orning!
//  删 (删除指定区域) [) 左闭右开
sb.delete(8,sb.length());       //Hi fusi

//  改 (更改单个字符)
sb.setCharAt(3,'F'); //Hi Fusi
//  改 (替换多个字符)
sb.replace(3,7,"xixifu");   //Hi xixifu

//  查 (查找顺序第一个)
int ia = sb.indexOf("i");       //1
//  查 (查找逆序第一个)
int ib = sb.lastIndexOf("i");    //6

//  逆序
sb.reverse();    // ufixix iH
```

#### 笔试考点

- 既然StringBuilder类的对象本身就可以修改，为何还要有返回值

  答：为了可以连续调用：`sb.reverse().delete(8).append("a");`

- 如何实现StringBuilder和String类型的互相转换

  ```java
  String str = sb.toString();		//使用toString即可获取
  StringBuilder sb2 = new StringBuilder(str);		//使用构造方法即可
  ```

- String、StringBuilder、StringBuffer之间谁的效率高

  答：StringBuilder > StringBuffer > String

### Java8之前的日期相关类(熟悉)

#### System类的概述

概念： `java.lang.System`类中提供了一些有用的类字段和方法

常用的方法：

| 方法声明                        | 功能介绍                                             |
| ------------------------------- | ---------------------------------------------------- |
| static long currentTimeMillis() | 返回当前时间与1970年1月1日整之间以毫秒为单位的时间差 |

#### Date类的概述

概念：java.util.Date类主要用于描述特定的瞬间，也就是年月日时分秒，可以精确到毫秒

常用的方法：

| 方法声明                 | 功能介绍                                   |
| ------------------------ | ------------------------------------------ |
| Date()                   | 使用无参的方式构造对象，也就是当前系统时间 |
| Date(long date)          | 根据参数指定毫秒构造对象                   |
| long getTime()           | 获取调用对象距离1970.1.1的毫秒数           |
| void setTime(long time ) | 设置调用对象为距离基准时间time毫秒的时间点 |

#### SimpleDateFormat类

概念：java.**text**.SimpleDateFormat类主要用于实现日期和文本之间的转换

常用的方法：

| 方法声明                         | 功能介绍                                         |
| -------------------------------- | ------------------------------------------------ |
| SimpleDateFormat()               | 使用无参方式构造对象                             |
| SimpleDateFormat(String pattern) | 根据参数指定的模式来构造对象，y年M月d日H时m分s秒 |
| final String format(Date date)   | 用于将日期类型转换为文本类型                     |
| Date parse(String source)        | 用于将文本类型转换为日期类型                     |

```java
// 获取当前系统时间
Date d = new Date();

//实例化一个SimpleDateFormat的对象，并使用有参构造设置好时间输出格式
SimpleDateFormat sdf = new SimpleDateFormat("y-M-d H:m:ss");

//使用format方法转换传入的时间参数为指定格式的字符串类型
String st = sdf.format(d);

//使用parse方法转换传入的字符串格式的时间为Date类型
Date parse = sdf.parse(st);
```

#### Calendar类

概念： java.util.Calendar类主要用于描述特定的瞬间，取代Date类中的过时方法实现全球化

该类是一个抽象类，因此不能实例化对象，其具体子类针对不同国家的日历系统，其中应用最广泛的是GregorianCalendar（格里高利历），对应世界上绝大多数国家、地区使用的标准日历系统

常用的方法：

| 方法声明                                                     | 功能介绍                         |
| ------------------------------------------------------------ | -------------------------------- |
| static Calendar getInstance()                                | 用于获取Calendar类型的引用       |
| void set(int year,int month,int date,int hourOfDay,int minute,int second) | 用于设置年月日时分秒信息         |
| Date getTime()                                               | 用于将Calendar类型转换为Date类型 |
| void set(int field,int value)                                | 设置指定字段的数值               |
| void add(int field,int amount)                               | 向指定字段增加数值               |

```java
/**
* 如果希望使用calendar获取值并按照指定格式打印,则需要使用以下流程
* 即:创建一个SimpleDateForamt,再创建一个Calendar,使用Calendar设置初始值
* 使用C的方法getTime()转换格式为Date类型,再使用SDF的方法format把Date类型转为自己的类型并输出
* 即 Calendar --> Date --> SimpleDateForamt
*/
//设置日期的输出格式
SimpleDateFormat sdf = new SimpleDateFormat("y-M-d H:m:s");
//获取Calendar抽象类的引用
Calendar c = Calendar.getInstance();

//设置指定的时间，需注意，月值基于0，也就是说0就是1月
c.set(2008,8-1,8,20,8,8);
//转换为date类型的引用,使用Calendar的getTime方法
Date time = c.getTime();
//转换Date为String格式,使用SimpleDateFormat中的format方法
String format = sdf.format(time);


//使用Calendar的set方法,设置指定的字段为指定的值
//需注意,使用的是常量,所以需要类名.的方式调用
c.set(Calendar.YEAR , 2018);
Date time1 = c.getTime();
System.out.println(sdf.format(time1));

//使用Calendar的add方法,增加月份的值
c.add(Calendar.MONDAY , 3);
Date time2 = c.getTime();
System.out.println(sdf.format(time2));
```

- 笔试题  : 既然Calendar是一个抽象类,为什么上面的方法可以获取Calendar类型的引用哪
  答:由源码可知,返回的并不是Calendar类型,而是Calendar类的子类的对象,形成了多态

#### 多态的使用场合

- 通过方法的参数传递形成多态

  ```java
  public static void draw(Shape s){
      s.show();
  }
  ```

- 在方法体中直接使用多态的语法格式

  ```java
  Account acc = new FixedAccount();
  ```

- 通过方法的返回值类型形成多态

  ```java
  Calendar getInstance(){
      return new GregorianCalendar(zone,aLocale);
  }
  ```

### Java8之后的日期相关类(熟悉)

之前的Date类和Calendar类都有很多问题,例如:

- 月份都是从0开始的
- 格式化只对Date类有用,对Calendar类不能使用,造成代码的繁琐
- 非线程安全等

所有从java8开始,发布了新的类:`Date.Time`类来加强对日期和时间的处理

#### Java日期类的概述

- java.time : 该包日期/时间API的基础包
- java.time.chrono包 : 该包提供对不同日历系统的访问
- java.time.format包 : 该包能格式化和解析日期时间对象
- java.time.temporal包 : 该包包含底层框架和扩展特性
- java.time.zone包 : 该包支持不同时区以及相关规则的类

#### LocalDate类

概念: java.time.LocalDate类主要用于描述年-月-日格式的日期信息,该类不表示时间和时区信息

常用的方法:

| 方法声明                   | 功能描述                           |
| -------------------------- | ---------------------------------- |
| static LocalDate **now**() | 在默认时区中从系统时钟获取当前日期 |

#### LocalTime类

概念 : java.time.localTime类主要用于描述时间信息,可以描述时分秒以及纳秒

| 方法声明                          | 功能介绍                           |
| --------------------------------- | ---------------------------------- |
| static LocalTime **now**()        | 从默认时区的系统时间中获取当前时间 |
| static LocalTime now(Zoneld zone) | 获取指定时区的当前时间             |

#### LocalDateTime类

| 方法声明                                                     | 功能介绍                                      |
| ------------------------------------------------------------ | --------------------------------------------- |
| static LocalDateTime **now**()                               | 从默认时区的系统时间中获取 当前日期时间       |
| static LocalDateTime **of**(int year, int month, int dayOfMonth, int hour, int minute, int second) | 根据参数指定的年月日时分秒 信息来设置日期时间 |
| int **getYear**()                                            | 获取年份字段的数值                            |
| int getMonthValue()                                          | 获取1到12之间的月份字段                       |
| int getDayOfMonth()                                          | 获取日期字段                                  |
| int getHour()  ; int getMinute()  ; int getSecond()          | 获取时分秒数                                  |
| LocalDateTime **withYear**(int year)                         | 设置为参数指定的年                            |
| withMonth ;  withDayOfMonth                                  | 设置为参数指定的月,日                         |
| withHour ; withMinute ; withSecond                           | 设置为参数指定的时,分,秒                      |
| LocalDateTime **plusYears**(long years)                      | 加上参数指定的年                              |
| plusMonths ; plusDays                                        | 加上参数指定的月,日                           |
| plusHours ; plusMinutes ; plusSeconds                        | 加上参数指定的时分秒                          |
| LocalDateTime **minusYears**(long years)                     | 减去参数指定的年                              |
| minusMonths ; minusDays ;                                    | 减去参数指定的月 , 天                         |
| minusHours ; minusMinutes ; minusSeconds ;                   | 减去时分秒                                    |
