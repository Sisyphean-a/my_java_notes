## 类和对象

### 面向对象

概念：指以属性和行为的观点去分析现实生活中的事物，在java中体现为内存空间的一块存储区域

三大重点：**封装、继承、多态**

### 类

概念：是对具有相同特征和行为的多个对象共性的抽象描述，在java中体现为一种引用数据类型，里面包含了描述特征、属性的成员变量以及描述行为的成员方法，类是用于构建对象的模板，对象的数据结构由定义它的类来决定

类的定义：`class 类名{类体；}`

> 但类名中由多个单词组成时，要求每个单词首字母都要大写（编程规范）

```java
/*java一个类对应一个文件*/
public clss Family{ 类体; }
```

### 成员变量

定义：`class 类名{数据类型 成员变量名 = 初始值；}`

> 当成员变量由多个单词组成时，通常要求从第二个单词起每个单词的首字母大写

```java
/*类中的变量，大家庭的一员，是类的属性*/
public class Family{
    int sisterOne;
    String sisterTwo;
}
```

### 对象

定义：`new 类名();`

> - 当一个类定义完毕后，可以使用new创建该类的对象，这个过程叫做实例化
> - 创建对象的本质就是在堆区申请一块存储区域，用于存放该对象独有特征信息

```java
/*将虚拟的概念与行为转化为实际存在的，对象的声明需要指明是哪个类*/
public class Family{
    public static void main(String[] args){
        //这里new Family()就是对象，前面的Family f是引用
        Family f = new Family();
    }
}
```

### 引用

基本概念：使用引用数据类型定义的变量，简称为“引用”

作用：主要用于记录对象在堆区中的内存地址信息，便于下次访问

格式：

- 定义：`类名 引用变量名`
- 使用：`引用变量名.成员变量名`

案例：编程实现person类的定义与使用

```java
/*类的定义*/
public class People{
	/*成员变量的定义*/
    String name;
    int age;
	
    /*成员方法的定义*/
    void setName(String a){
        name = a;
    }
    void setAge(int b){
        age = b;
    }

    void show(){
        System.out.println("姓名：" + name + "\n年龄：" + age);
    }

    public static void main(String[] args){
        /*对象的声明与引用*/
        People p = new People();
        /*引用变量的使用*/
        p.show();
        p.setName("lkwu");
        p.setAge(18);
        p.show();
    }
}
```

### 成员方法

定义：`class 类名() {返回值类型 成员方法名（形参列表）{成员方法体；}}`

> 在成员方法中引用成员变量是不需要加引用的，即：不用`p.name`，只需要`name`或者使用后文提到的`this.name`，这是因为成员变量和成员方法都是属于类的，不专属于某个实例，每个实例都可以调用，但是不能独占

#### 返回值类型详解

返回值：指从方法体内返回到方法体外的数据内容

返回值类型：主要指返回值的数据类型，可以是基本数据类型，也可以是引用数据类型

在方法体中使用return关键字可以返回具体的数据类型并结束当前方法

如果不需要返回值，类型可以填void

```java
public class People{

    String name;
    int age;
	//返回值的使用，需注意类型要对应
    String getName(){
        return name;
    }
    int getAge(){
        return age;
    }
    
    public static void main(String[] args){
        People p = new People();
        String a = p.getName();	//获取返回值并赋值
        int b = p.getAge();
        System.out.println(a + " " + b);
    }
}
```

#### 形参列表详解

形参：主要用于将该方法体外的数据内容带入到方法体内部

形参列表：指多个形参组成的列表

格式：数据类型 形参变量名1; 数据类型 形参变量名2;

若不带入，则空着即可

```java
public class People {
    String name;
    int age;
	//多参数的使用，需注意多参数需放到参数列表的末尾，格式为 (int... args)
    void showArgument(String a, int b ,int... args){
        System.out.println(a + " " + b);
        //输出多参数内容，多参数可以通过args[n]的方式调用，和数组一样
        for (int i=1 ;i<= args.length;i++){
            System.out.println("第" + i + "个数字为" + args[i-1]);
        }
    }

    public static void main(String[] args){
        People p = new People();
        p.showArgument("hello",18, 1,3,5,7,9);
    }
}
```

#### 方法体详解

概念：用于描述该方法功能的语句块

作用：可实现代码的复用，简化代码

> 成员变量和成员方法都属于类内部的成员，因此可以直接访问成员变量，而不需要再加 **引用.** 的前缀

### 参数传递的注意事项

- 传入基本数据类型的变量时，方法体内数值（形参）的改变通常不会影响到实参数值，因为两个变量有各自独立的内存空间
- 传入引用数据类型的变量时，形参指向内容的改变会影响到实参的数值，因为两者存进去的值是堆区的地址，是同一块内存空间
- 若不希望改变引用数据类型的内容，就再申请个空间即可

```java
import java.util.Arrays;
public class People{
	//基本数据类型传入，里面更改外面的值不会更改，因为是在栈区又申请了一个空间
    void show1 (int a){
        a = 300;
        System.out.println("a = " + a);
    }
	//引用数据类型的变量传入，外面也会同步更改，因为虽然又申请了一个栈区，但指向的是同一个空间
    void show2(int[] arr){
        //若希望不更改，可以再申请一个空间
        arr[2] = 300;
        System.out.println(Arrays.toString(arr));
        arr[2] = 4;
    }

    public static void main(String[] args){
        People p = new People();
        int[] arr = {1,3,4};
        int a = 7;
        p.show1(a);
        p.show2(arr);
    }
}
```

### 内存结构之栈区

jvm会为每一个方法的调用在栈中分配一个对应的空间，这个空间称为该方法的**栈帧**。一个栈帧对应一个正在调用的方法，栈帧中存储了该方法的参数，局部变量等数据。当一个方法调用完成后，其对应的栈帧将被清除

## 方法与封装

### 构造方法

概念：当创建一个实例时调用的方法， `new Person();`

定义：`class 类名{ 类名(形参列表){构造方法体；}}`

特点：

1. 构造方法与类名相同  
2.  没有返回值类型，故不需要void

### 默认构造方法

概念：也叫缺省构造方法，当一个类中未定义任何构造方法时，编译器会自动添加一个无参空构造方法，例：`Person(){}`

作用：使用new关键字创建对象时，会自动调用构造方法实现成员变量初始化工作

> 若类中出现了构造方法，则编译器不在提供任何形式的构造方法
> 此时若希望可以使用无参构造，需要自己去定义

### 方法重载

概念：若方法名称相同，参数列表不同，这样的方法之间构成重载关系

体现形式：参数的**个数不同，类型不同，顺序不同**，与返回值类型和形参变量名无关，但建议返回值类型最好相同

判断方法能否重载的核心：调用方法时能否加以区分

实际意义：调用者只需要记住一个方法名就可以调用各种不同的版本，来实现不同的功能

```java
public class Point{

    int x;
    int y;
	//有参的构造方法，与下面的方法构成了重载
    Point(int a,int b){
        x = a;
        y = b;
    }
	//无参的构造方法，和上面的方法构成了重载
    Point(){
    }

    void show(){
        System.out.println("横坐标：" + x + " , 纵坐标：" + y);
    }

    public static void main(String[] args){
        //定义一个使用无参构造的实例
        Point p1 = new Point();
        p1.show();
        //定义了一个使用有参构造的实例
        Point p2 = new Point(3,5);
        p2.show();
    }
}
```

> 重载和后文提到的重写的区别：重载的方法都位于同一个类中，而重写的方法则都在不同的类中

### this

概念：this关键字用于引用方法或构造函数中的当前对象，即在哪个方法里就代表哪个方法本身，本质上就是当前类型的引用变量，注意层级是方法而不是类

工作原理：在构造方法中访问成员变量时，编译器会加上this.的前缀，当不同的对象调用同一个方法时，由于调用方法的对象不同，导致**this关键字指向的内容的不同**，从而this方式访问的结果也就随之不同

#### 使用方式：

1. 使用this关键字处理变量名的歧义性

   ```java
   //如果不用this，就近原则导致age自身赋值给自身
   int age;
   MyClass(int age){
       age = age;
   }
   
   //使用this,避免歧义性
   MyClass(int age){
       this.age = age;
   }
   ```

2. 使用this关键字在一个方法中调用同一个类的另一个方法

   ```java
   public void jump() {
       System.out.println("正在执行jump方法");
   }
   
   // 不使用this，需要声明一个对象才能使用另一个方法
   public void run() {
       Dog d = new Dog();
       d.jump();			//run()方法需调用jump()方法
       System.out.println("正在执行 run 方法");
       
   //使用this，直接调用另一个方法，此时的this可以省略
   public void run(){
       this.jump();
       System.out.println("正在执行 run方法");
   }
   ```

   > 对于static修饰的方法而言，可以直接调用该方法，如果再使用this，则会报错，原因是：this属于方法层级，而static属于类层级。所以，static修饰的方法不能使用this引用。
   >
   > 静态成员不能直接访问非静态成员

3. 在构造函数重载中使用this

   ```java
   //在构造方法的第一行可以使用this()的方式来调用本类中的其他构造方法
   People(int num){
       this.People();
       System.out.println("调用了有参构造函数");
   }
   
   People(){
       System.out.println("调用了无参构造函数");
   }
   ```

案例：this的基本使用

```java
public class Boy{
    
    String  name;
    int age;

    Boy() {
        //this在这里代表boy(),所有下一行等同于Boy("...");即调用了一次有参构造
        this("调用并更改");
        System.out.println("无参构造方法");
    }

    Boy(String name){
        this.name = name;
        System.out.println("有参构造方法");
    }
	
    void grow(){
        age++;
    }
    
    void show(){
        //这里使用了this来调用同一个类下的grow方法
        this.grow();
        System.out.println("姓名：" + name);
    }

    public static void main(String[] args){
        Boy b1 = new Boy();
        Boy b2 = new Boy("K");

        b1.show();
        b2.show();
    }
}
```

> this和后文提到的super的区别：this在一个类中起作用，可以调用自身的方法，而super()是在子类中调用父类的方法

### 空指针异常

1. 引用类型变量用于存放对象地址，可以给引用类型赋值为null，表示不指向任何内容
2. 当某个引用类型变量为null时，无法对对象实施访问，此时如果通过引用访问成员变量或调用方法，会产生**空指针异常 NullPointException** （需记忆）

案例：编程实现参数n的阶乘并返回

```java

public class JieChengTest{

    int jieChengFor(int n){
        // 使用for循环计算累乘积
        int num=1;
        for (int i = 1;i<=n;i++){
            num *= i;
        }
        return num;
    }
    
    int jieChengDi(int n){
        //使用递归的方式进行计算
        if (n == 1) return 1;
        return n * jieCheng(n-1);
    }

    public static void main(String[] args){
        JieChengTest jct = new JieChengTest();

        int num1 = jct.jieChengFor(3);
        int num2 = jct.jieChengDi(5);

        System.out.println("num1 = " + num1 + "，num2 = " + num2);
    }
}
```

### 递归

定义：指在函数的定义中使用函数自身的方法

> 知乎上的一个比喻：递归就是包子馅的包子。它的极限是馒头

注意事项：

1. 使用递归必须有递归的规律以及退出条件
2. 使用递归必须使得问题简单化而不是复杂化
3. 若递归影响到程序的执行性能，则使用递推取代之

案例：编程实现费氏数列中的第n项的数值并返回

费氏数列：1，1，2，3，5，7，13，21。。。

```java
public class FeeTest{

    int fee(int n){
        if (n==1 || n ==2) return 1;
        return fee(n-1) + fee(n-2);
    }

    public static void main(String[] args){
        FeeTest ft = new FeeTest();

        int num = ft.fee(30);
        System.out.println("num = " + num);

    }
}
```

### 封装

概念：通常情况下可以在测试类给成员变量赋值一些合法但是不合理的数值，无论编译和运行都不会报错，为了避免这种错误的发生，需要对成员变量进行密封包装，来隐藏成员变量的细节以及其数值的合理性，这种机制就叫做封装

#### 实现流程：

1. 私有化成员变量

   使用private关键字修饰private修饰过的变量只能在当前类的内部使用，与public关键字对应

2. 提供公有的get和set方法，并在方法体中进行合理值的判断

3. 在公有的构造方法中调用set方法，而不是直接赋值，进行合理值判断

#### 案例：提示用户输入学生人数以及每个学生的信息

```java
//Student类，用于实现基本get与set功能
public class Student{
	//使用private来封装封装
    private int id;
    private String name;
	//有参构造函数，这个函数也是后面使用的类型
    public Student(int id , String name){
        setId(id);
        setName(name);
    }
	//-------------------set和get方法-------------------------
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public void setId(int id){
        if (id > 0){
            this.id = id;
        } else {
            System.out.println("id不应该小于0！");
        }
    }
    public void setName(String name){
        this.name = name;
    }
	//-----------------------------------------------------
    
    public void show(){
        System.out.println("学生姓名：" + name + " ，学生ID：" + id);
    }
}
```

```java
//StudentTest类，程序主题
import java.util.Scanner;
public class StudentTest{

    public static void main(String[] args){

        System.out.println("请输入学生人数：");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
		
        //新建一个Student类型的数组，即此数组中每一个元素都是Student类
        Student[] arr = new Student[num];

        for (int i=0;i<arr.length;i++){
            System.out.println("请输入学生信息（学号 姓名）");
            //新建一个Student对象，申请一个初始化的空间，赋值给arr[i]
            arr[i] = new Student(sc.nextInt(),sc.next());
        }

        for (int i=0;i<arr.length;i++){
            //使用show()方法打印输出，如果使用println方法，打印出来的是堆区地址
            arr[i].show();
        }
    }
}
```

## static关键字与继承

### static关键字

概念：使用static关键字修饰成员变量表示静态的含义，此时成员变量由对象层级提升为**类层级**，也就是整个类**只有一份并被所有对象共享**，该成员变量随着类的加载准备就绪，**与是否创建对象无关**

例如：`private static int age;`

使用：可以使用**引用.**的方式访问，更推荐使用**类名.**的方式，例如`People.age`，而不是`p.age`

1. 在非静态成员方法中既能访问非静态的成员又能访问静态的成员（成员：成员变量+成员方法，静态成员被所有对象共享）
2. 在静态方法中只能访问静态成员不能访问非静态成员（因为此时可能还没有创建对象）
3. 在以后的开发中只有隶属于类层级并**被所有对象共享的内容**才可以使用static关键字修饰（不能滥用static关键字）

```java
public class StaticTest {

    private int a = 1;
    private static int b = 2;

    //自定义非静态的成员方法，需要使用引用.的方式访问
    public void show(){
        //非静态方法所有的成员都可以访问
        System.out.println(a);
        System.out.println(b);
    }

    //自定义静态的成员方法，推荐使用类名.的方式访问
    public static void test(){
        //错误: 无法从静态上下文中引用非静态 变量 a
        //静态方法只能访问静态成员而不能访问非静态成员，
        //System.out.println(a); //静态方法没有this关键字,所有无法调用
        System.out.println(b); //都是类层级，可以调用
    }

    public static void main(String[] args){
        StaticTest st = new StaticTest();
        st.show();
        StaticTest.test();
    }
}
```

### 构造块和静态代码块

构造块：在类体中直接使用{}括起来的代码块，每创建一个对象都会执行一次构造块

静态代码块：使用static关键字修饰的构造块

```java
public class BlockTest{

    //当需要在执行构造方法体之前做一些准备工作时，将准备工作的相关代码写在构造块之中即可，例如对成员变量进行统一的初始化操作
    //会在构造方法体之前，静态代码块之后运行
    {
        System.out.println("构造块");
    }

    //会随着类的加载而准备就绪，会第一个运行，只会运行一次，例如加载数据库的驱动包之类的内容即可放入其中
    static{
        System.out.println("静态代码块");
    }

    public BlockTest(){
        System.out.println("构造方法体");
    }

    public static void main(String[] args){

        BlockTest bt1 = new BlockTest();
        BlockTest bt2 = new BlockTest();
    }
}
```

#### 笔试：

题目：如果一个子类继承了父类，那么子类与父类的构造块，静态代码块，构造方法体运行顺序如何

1. 父类-静态代码块    	// 静态代码块在加载时就开始执行，而父类要先加载
2. --子类-静态代码块
3. 父类-构造块             // 在子类对象构造之前，父类的构造方法要先加载
4. 父类-构造方法
5. --子类-构造块
6. --子类-构造方法

### main方法

语法格式：`public static void main(String[] args){}`

```java
public class MainTest{

    public static void main(String[] args){
        // 如果需要传递参数，可以在使用的的时候加上空格参数传入
        // 例如：java MainTest a b c
        System.out.println(args.length);
        for (int i=0;i<args.length;i++){
            System.out.println(args[i]);
        }
    }
}
```

### 案例：实现Singleton类的封装

编程实现SingletonTest类对Singleton类进行测试，要求main方法中能得到且只能得到该类的一个对象

```java
public class Singleton{

    //2，创建一个私有的gao'zhao函数，这样就不能多次调用了，但问题是一次也无法调用
    private Singleton(){}

    //3. 虽然无法在外面调用，但是在自己类里面可以调用,但是这种调用属于一个类的成员变量，属于对象层级，外面访问需要使用 引用. 来访问，但是外面是没有对象的
    // Singleton sin = new Singleton();

    //4.加一个static关键字，提升层级为类层级，这样外面就可以使用类名.来访问。但是依然有漏洞
    //static Singleton sin = new Singleton();

    //6. 进行封装，先进行私有化，再提供get方法
    private static Singleton sin = new Singleton();

    //7.提供公有的get方法把sin返回出去，同样若希望引用，则需要提升到类层级，加上static关键字，这样外面的就只能获取对象，而无法随意传入了
    public static Singleton getInstance(){
        return sin;
    }
}
```

```java
public class SingletonTest{

    public static void main(String[] args){
        //1. 可以创建多个对象，不合题意
        /*Singleton s1 = new Singleton();
        Singleton s2 = new Singleton();
        System.out.println(s1 == s2); */

        //5. 如果赋予一个值为null，则可以使引用变量无效，为了避免这种情况，需要进行封装
        //Singleton.sin = null;

        //8.此时可以类名调用了，而且只能调用一个
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1 == s2);
    }
}
```



### 单例设计模式

概念：在某些特殊场合中，一个类对外提供且只提供一个对象时，这样的类叫做单例类，而设计单例的流程和思想叫做单例设计模式

实现流程：

1. 私有化构造方法，使用private关键字修饰
2. 声明本类类型的引用指向本类类型的对象，并使用private static关键字共同修饰
3. 提供共有的get方法负责将对象返回出去，并使用public static关键字共同修饰

实例：windows系统的任务管理器就是一个单例设计模式，无论启动多少次，都只会启动并存在一个，提供且只提供一个实例

实现方式：饿汉式 和 懒汉式，推荐使用饿汉式，上面实现的就是饿汉式

```java
public class Singleton{
    //饿汉式，直接在开始就声明变量，对应的return也很简洁
    private static Singleton sin = new Singleton();
    //懒汉式，先创建一个空的对象，什么时候使用什么时候声明变量
    private static Singleton sin = null;
    
    private Singleton();
    
    public static Singleton getInstance(){
        //饿汉式返回变量
        return sin;
        //懒汉式返回对象
        if(null == sin){
            sin = new Singleton();
        }
        return sin;
    }
}
```

### 继承

概念：当多个类之间有相同的特征和行为时，可以将相同的内容提取出来组成一个公共类，让多个类吸收公共类中已有特种课行为在多个类型只需要编写自己独有特征和行为的机制，叫做继承

使用：在java中使用extends关键字来表示继承关系

例如：`public class Worker extends Person{}` - 表示Worker类继承自Person类

- Person类叫做超类、父类、基类
- Worker类叫做派生类、子类、孩子类

意义：使用继承提高了代码的复用性，可维护性及扩展性，是多态的前提条件

特点：

1. 子类不能继承父类的构造方法和私有方法，但私有成员变量可以被继承，只是不能直接访问
2. 无论使用何种方式，构造子类的对象时都会自动调用父类的无参构造方法，来初始化从父类中继承的成员变量，相当于在构造方法的第一行增加代码super()的效果
3. 使用继承必须满足逻辑关系：子类 is a 父类，也就是不能滥用继承
4. java中只支持单继承不支持多继承，即一个子类只能有一个父类，但一个父类可以有多个子类

```java
//父类People的封装
public class People{

    private String name;
    private int age;

    public People(String name , int age){
        System.out.println("people(s,i)");
        setName(name);
        setAge(age);
    }
    public People(){
        System.out.println("people()");
    }
	
    //属性的set与get方法
    public void setName(String name){
        this.name = name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }

    //成员方法
    public void show(){
        System.out.println("姓名：" + name + " ，年龄：" + age);
    }
    public void eat(String food){
        System.out.println(food + "还行");
    }
    public void play(String game){
        System.out.println(game + "挺好玩");
    }
}
```

```java
//子类Worker的调用
public class Worker extends People{

    private int salary;

    public Worker(){
        super(); //表示调用父类的无参构造方法，若没有则编辑器自动添加
        System.out.println("work()");
    }
    public Worker(String name,int age ,int salary){
        super(name,age);//如不希望调用无参，可以传入参数变成有参
        System.out.println("work(s,i,i)");
        setSalary(salary);
    }

    //薪资的get与set方法
    public void setSalary(int salary){
        if (salary >=2200) {
            this.salary = salary;
        } else {
            System.out.println("薪资不对啊！");
        }
    }
    public int getSalary(){
        return salary;
    }

    //成员方法
    public void work(){
        System.out.println("努力工作中。。。");
    }
}
```

### 方法重写

概念：从父类中继承下来的方法不满足子类的需求时，就需要在子类中重新写一个和父类一样的方法覆盖从父类中继承下来的版本，该方式就叫做方法的重写（Override）

```java
// 在Worker类中重写show()方法

@Override //注解，用于说明下面的是一个重写，如没有构成则编译会报错
public void show(){
    super.show(); //表示调用父类的show方法
    System.out.println("薪水：" + getSalary());
}
```

原则：

1. 要求方法名相同、参数列表相同以及返回值类型相同，从java5开始允许返回子类类型
2. 要求方法的访问权限不能变小，可以相同或者变大，例如父类的public在子类中不能变成private（因为继承是对父类的扩展）
3. 要求方法不能抛出更大的异常（异常机制）

### 权限修饰符与包

#### 常用的访问控制符

| 修饰符    | 本类     | 同一个包中的类 | 子类 | 其他类 |
| --------- | -------- | -------------- | ---- | ------ |
| public    | 可以访问 | 可以           | 可以 | 可以   |
| protected | 可以     | 可以           | 可以 | 不能   |
| 默认      | 可以     | 可以           | 不能 | 不能   |
| private   | 可以     | 不能           | 不能 | 不能   |

- public修饰的成员可以在任意位置使用
- private修饰的成员只能在本类内部使用
- 通常情况下，**成员方法都使用public关键字修饰，成员变量都使用private关键字修饰**

#### package

**作用**：定义类时需要指定类的名称，但如果仅仅将类名作为类的唯一标识，则不可避免的出现命名冲突的问题，这会给组件复用以及团队间的合作造成很大的麻烦，包(package)的概念就是用于解决命名冲突的问题

包的定义：在定义一个类时，除了定义类的名称一般还需要指定一个包名

- package 包名;
- package 包名1.包名2.。。。。包名n;

**效果**：为了实现项目管理，解决命名冲突以及权限控制的效果

**规范**：指定包名时应该按照一定的规范

​	例：org.apache.commons.lang.StringUtil

- StringUtils是类名，而org.apache.commons.lang是多层包名
- org.apache表示公司或组织的信息（例如公司域名的反写）
- common表示项目的名称信息
- lang表示模块的名称信息

**包的导入**： 

- 使用import关键字导入包 例如：`import java.util.Scanner;`

- 使用import关键字导入静态成员，很少使用

  ```java
  import static java.lang.System.out
      out.println("同样使用打印的功能")
  ```

### final修饰类

#### 作用：

1. 修饰类，体现在该类不能被继承。主要用于防止滥用继承

   使用：`public final class Final`

   例如： `java.lang.String`类

2. 修饰成员方法，体现在该方法不能被重写但可以被继承

   使用：`public final void show()`

   例如：`java.text.Dateformat`类中format方法

3. 修饰成员变量，体现在该变量必须初始化且不能改变

   使用：`private final int aa = 0`; //显式初始化

   ​          `private final int aa;   { aa=0; }` //构造块中初始化

​		当然，也可以在构造方法体中初始化，需注意，值不可改变

#### 常量

常量：公有的public，类层级的static，不可修改的final

在开发中，很少单独使用final关键字来修饰成员变量，通常使用`public static final`关键字共同修饰成员变量来表达常量的含义，常量的命名规范**要求所有字母都要大写**，不同的单词之间采用**下划线连接**

例如；圆周率 ：`public static final double PI = 3.14;`

## 多态与特殊类

### 多态

概念：主要指同一种事物表现出来的多种形态，多态的前提是继承。

例如：人可以分为：学生，工人，保安，工人

语法格式：`父类类型 引用变量名 = new 子类类型();`

例如：`Parent s = new Sub();   s.show();`

理解：s 在编译阶段理解为Parent类型，而在执行阶段理解为Sub类型

**特点**：

1. 父类类型的引用可以直接调用父类独有的方法 。

   例如：`s.parentMethod();`是可以的

2. 父类类型的引用不可以直接调用子类独有的方法。

   例如：`s.subMethod();`不可以。如果非要调用，可以使用强制类型转换，`((Sub) s).subMethod();`

3. 对于父子类都有的非静态方法来说，编译阶段调用父类版本，运行阶段调用子类重写的版本（**动态绑定**）。

   例如：`s.show();`可以，而且父类和子类的show()方法都会调用

4. 对于父类和子类都有的静态方法来说，编译和运行阶段都调用父类版本。

   例如：`Parent.staticMethod();`可以，会调用父类的方法。因为静态方法是类层级的，被所有对象调用，和多态的对象没啥关系

> 静态方法重写不可以使用静态标注@Override，因为不是真正意义上的重写

### 引用数据类型之间的转换

- 引用数据类型之间的转换方式同样有两种：自动类型转换 、强制类型转换

- 自动类型转换主要指小类型向大类型的转换，也就是子类转为父类，也叫做向上转型 

  例如：`Parent s = new Sub(); `

- 强制类型转换主要指大类型向小类型的转换，也就是父类转为子类，也叫做向下转型或显示类型转换

#### 注意事项

- 引用数据类型的转换必须发生在父子类之间，否则编译报错

  例如：`String st = (String)s;` 报错，Parent不能转换为String

- 若强转的目标类型并不是该引用真正指向的数据类型时则编译通过，运行阶段发生类型转换异常

  例如：`SubTwo st = (SubTwo)s;`报错，Sub不能转换为SubTwo

>  `ClassCastException`：类型转换异常，需记忆

- 为了避免上述错误的发生，应该在强转之前使用**instanceof**关键字进行判断，格式如下：

  `if (引用变量 instanceof 数据类型)`

  ```java
  if(s instanceof SubTwo){
      System.out.println("可以转换");
      SubTwo st = (SubTwo)s;
  } else {
      System.out.println("不可以强转");
  }
  ```

  判断引用变量指向的对象是否为后面的数据类型

### 多态的实际意义

- 多态的实际意义在于屏蔽不同子类的差异性实现通用的编程带来不同的效果

  ```java
  /*父类：Shape   ；其子类：Rect,Circle*/
  public class ShapeTest {
  	
      //自定义成员变量打印矩形对象，对象由参数传入
      //形参等效于 Rect r = new Rect(a,b,c,d)
  	/*public static void drow(Rect r){
          r.show();
      }
      
      //自定义成员变量打印圆形对象，对象由参数传入
      //形参等效于 Circle c = new Circle(a,b,r)
      public static void drow(Circle c){
          c.show();
      }*/
      
      //自定义成员变量，既能打印圆形又能打印矩形
      //形参等效于 Shape s = new Circle(a,b,r)
      //或等效于 Shape s = new Rect(a,b,c,d)
      //形成了多态，编译时使用父类方法，运行时使用子类方法
      public static void drow(Shape s){
          s.show();
      }
  
      public static void main(String[] args) {
          drow(new Rect(4,2,5,7));
          drow(new Circle(4,6,3));
      }
  }
  ```

- 直接在方法体中使用抽象类的引用指向子类类型的对象

  ```java
  //AbstractTest是一个抽象类，而SubAbstractTest是它的子类
  AbstractTest at = new SubAbstractTest();
  at.show();
  ```

#### 经验分享

- 在以后的开发中推件使用多态的格式，此时父类类型引用直接调用的所有方法一定是父类中拥有的方法，若以后更换子类时，只需要将new关键字后的子类类型修改而无需更改其他地方即可立即生效，从而提高了代码的可维护性和可扩展性
- 缺点：父类引用不能直接调用子类独有的方法，若调用则需要强制类型转换

### 抽象方法与抽象类

#### 抽象方法

概念：指不能具体实现的方法并且使用**abstract**关键字修饰。也就是没有方法体

格式：`访问权限 abstract 返回值类型 方法名（形参列表）;`

例如：`public abstract void cry();`

#### 抽象类

概念：指不能具体实例化的类并且使用**abstract**关键字修饰，也就是不能创建对象

例如：`public abstract class Cry`

#### 抽象类和抽象方法的关系

- 抽象类中可以有成员变量、构造方法、成员方法；

- 抽象类中可以有也可以没有抽象方法，但是抽象方法必须在抽象类中
- 一个严谨的抽象类应该是具有抽象方法并且使用abstract关键字修饰的类

#### 抽象类的实际意义

- 抽象类的实际意义不在于创建对象而在于被继承
- 当一个类继承抽象类会必须重写抽象方法，否则该类也变为抽象类，就是抽象类对子类具有强制性和规范性，因此叫做**模板设计模式**

```java
public abstract class Animals{
    //若想继承此类，必须要重写所有的抽象方法，除非子类也是抽象类
    public abstract void live();
}

public class SubAnimals extents Animals{
    public void live(){}
}
```

#### 案例

银行有定期账户和活期账户。继承自账户类。账户类中：

`public class Account{`

​	`private double money; public double getLixi(){}}`

### 笔试考点

- `private`和`abstract`不能同时修饰一个方法

  私有的方法不能被继承，而abstract的意义在于继承

- `final` 和 `abstract `不能同时修饰一个方法或者一个类

  final不能被重写可以被继承，而abstract则希望被重写

- `static` 和 `abstract `不能同时修饰一个方法

  static可以通过类名调用，违背了abstract不能被调用所以不让调用对象的意义

### 接口

概念：比抽象类还抽象的类，体现在其中所有的方法都是抽象方法

关键字：`interface`，与类的关键字`class`一个层级，二者存一

特点：只能有常量，只能有抽象方法。接口只能继承接口

作用：一个类可以继承多个接口，弥补java中不可以多继承的缺陷

```java
//哺乳动物接口
public interface Lactation {
    void crawl();
}
//--------------------------------------------

//飞行动物接口
public interface Bird {
    void flight();
}
//--------------------------------------------

//蝙蝠类的实现
public class Bat implements Bird,Lactation{
    @Override
    public void flight() {
        System.out.println("我可以飞翔");
    }

    @Override
    public void crawl() {
        System.out.println("我可以爬行");
    }

    public static void main(String[] args) {
        Bird b = new Bat();
        b.flight();

        Lactation l = new Bat();
        l.crawl();
    }
}
```

#### 类和接口之间的关系

| 名称       | 关键字                 | 关系       |
| ---------- | ---------------------- | ---------- |
| 类和类     | extends表达继承关系    | 支持单继承 |
| 类和接口   | implements表达实现关系 | 支持多实现 |
| 接口和接口 | extends表达继承关系    | 支持多继承 |

#### 接口和抽象类的主要区别

- 定义抽象类的关键字是abstract，而定义接口的关键字是interface
- 继承抽象类的关键字是extends，而实现接口的关键字是implements
- 继承抽象类支持单继承，而实现接口支持多实现
- 抽象类可以有构造方法，而接口不可以有构造方法
- 抽象类中可以有成员变量，而接口中只可以有常量
- 抽象类可以有成员方法，而接口中只可以有抽象方法
- 新特性：接口中允许出现非抽象方法和静态方法，但非抽象方法需要使用`default`关键字修饰
- 新特性：接口中允许出现私有方法

```java
public interface Hunter extends Runner{
    
    //一个抽象类，没有方法体
    void hunting();

    private void show(){
        System.out.println("这是一个私有方法，只能内部调用")；
    }
    
    //使用default关键字可以实现“子类”自由选择是否重写此方法
    public default void show1(){
        show()；
        System.out.println("此方法可以被重写，也可以不被重写");
    }

    //使用static的关键字可以直接使用接口.的方式调用，避开了实例化
    public static void show2(){
        show()；
        System.out.println("此方法可以直接使用接口.的方式访问");
    }
}

```

## 特殊类

### 内部类

概念：当一个类的定义出现在另外一个类的类体中时，那么这个类叫做内部类(Inner)，而这个内部类所有的类叫做外部类(Outer)

编译：内部类同样可以编译文件，格式为`外部类名$内部类名.class`

作用：当一个类存在的价值仅仅是为某一个类单独服务时，那么就可以将这个类定义为所服务类中的内部类，这样可以隐藏该类的实现细节并且可以方便的访问外部类的私有成员而不再需要提供公有的get和set方法

内部类的分类：

- 普通内部类：直接将一个类的定义放在另外一个类的类体中
- 静态内部类：使用static关键字修饰的内部类，隶属于类层级
- 局部内部类：直接将一个类的定义放在方法体的内部时
- 匿名内部类：没有名字的内部类

### 普通（成员）内部类

格式：`访问修饰符 class 外部类的类名{访问修饰符 class 内部类的类名{内部类的类体；}}`

使用方式：

1. 普通内部类和普通类一样可以定义成员变量、成员方法以及构造方法等

2. 普通内部类和普通类一样可以使用final或者abstract关键字修饰

3. 普通内部类还可以使用private或protected关键字进行修饰

4. 普通内部类还需要使用外部类对象来创建对象

   ```java
   //外部类
   public class NormalOuter {
       private int cnt = 1;
   	//内部类
       public class NormalInter{
           private int a = 2;
           public void show(){
               //内部类可以调用外部变量
               System.out.println("cnt = " + cnt);
               System.out.println("a = " + a);
           }
       }
   }
   //-----------------------------------------------------
   //测试类
   public class NormalOuterTest {
       public static void main(String[] args) {
           //因为内部类是成员层级不是类层级，故需要使用引用.的方式访问
           NormalOuter no = new NormalOuter();
           //no.就是使用方法
           NormalOuter.NormalInter ni = no.new NormalInter();
           ni.show();
       }
   }
   ```

5. 如果内部类访问外部类中与本类内部同名的成员变量或方法时，需要使用this关键字

   ```java
   public class NormalOuter {
       private int cnt = 1;
   
       public class NormalInter{
           private int cnt = 3;
           public void show(int cnt){
               System.out.println("形参中的cnt = " + cnt);
               System.out.println("内部类中的cnt = " + this.cnt);
               //调用外部类的成员变量可以使用外部类.this的方式
               System.out.println("外部类中的cnt = " + NormalOuter.this.cnt);
           }
       }
   }
   ```

### 静态内部类

格式：`访问修饰符 class 外部类的类名{访问修饰符 static class 内部类的类名{内部类的类体；}}`

使用方式：

1. 静态内部类不能直接访问外部类的非静态成员
2. 静态内部类可以直接创建对象
3. 如果静态内部类访问外部类中与本类内同名的成员变量或方法时，需要使用类名.的方式访问

```java
public class StaticOuter {
    private int cnt = 1;	
    private static int snt = 2;	//定义一个静态外部变量

    public static void show(){
        System.out.println("外部类方法");
    }
    
    public static class StaticInter{
        private int a = 3;

        public void StaticInter(){
            System.out.println("静态内部类的构造方法");
        }

        public void show(){
            System.out.println("外部静态变量snt的值为：" + snt);
            //内部变量可以直接访问
            System.out.println("内部变量a的值为：" + a);
		    //System.out.println("外部非静态变量cnt的值为：" + cnt);	
           		//静态方法无法访问非静态变量
        }
        
        public void show2(int snt){
            //加了static关键字变量属于类层级，变量不能使用引用.的方式，而应该使用类名.的方式
            System.out.println("内部的snt：" + StaticInter.snt);
            System.out.println("外部的snt: " + StaticOuter.snt);
            System.out.println("形参的snt: " + snt);
            StaticOuter.show();
            //如果外部的show方法没有static，则需要创建对象去使用
            new StaticOuter.show()
        }
    }
}
```

### 局部（方法）内部类

格式： `访问修饰符 class 外部类的类名{访问修饰符 返回值类型 成员方法名（形参列表）{class 内部类的类名{内部类的类体；}}}`

使用方式：

1. 局部内部类只能在该方法的内部可以使用
2. 局部内部类不能使用访问控制符和static关键字修饰符
3. 局部内部类可以在方法体内部直接创建对象
4. 局部内部类可以使用外部方法的局部变量，但是必须时final的，因为局部内部类和局部变量的声明周期不同，内部类使用时是拷贝了一份，故不可改

```java
public void show(){
    //可以不加final，因为不加编译器会自动加上，但是建议加
    final int num = 3;
    
    class AreaInter{
        private int snt = 2;

        public AreaInter(){
            System.out.println("局部内部类的构造方法");
        }

        public void text(){
            System.out.println("snt = " + snt);
            //num = 4; //会报错，因为num的值是拷贝过来的，不可改变
            System.out.println("num = " + num);
        }
    }
    AreaInter ai = new AreaInter();
    ai.text();
}
```

### 回调模式

概念：如果一个方法的参数是接口类型，则在调用该方法时，需要创建并传递一个实现此接口类型的对象；而该方法在运行时会调用到参数对象中所实现的方法（接口定义的）

```java
//定义一个接口CallBackInterface
public interface CallBackInterface {
    //定义一个抽象方法
    public abstract void show();
}

//--------------------------------------------
//定义一个接口的实现类
public class CallBackInterfaceImpl implements CallBackInterface{
    //重写show抽象方法
    @Override
    public void show() {
        System.out.println("接口类的实现");
    }
}

//--------------------------------------------
//回调接口的测试类
public class CallBackInterfaceTest {
    //新建一个类层级的方法，希望调用接口类型的方法
    public static void test(CallBackInterface ia){
        ia.show();
    }
	
    public static void main(String[] args) {
        //传入一个对象，但是此对象必须是实现类的对象，形成了多态
        CallBackInterfaceTest.test(new CallBackInterfaceImpl());
    }
}

```

### 匿名内部类

#### 开发经验分享

- 当接口/类类型的引用作为方法的形参时，实参的转递方法有两种：
- 自定义类实现接口/继承类并重写方法，然后创建该类对象作为实参传递；

- 使用上述匿名内部类的语法格式得到接口/类类型的引用即可；

语法格式（重点）：`接口/父类类型 引用变量名 = new 接口/父类类型（）{方法的重写}`

```java
public static void main(String[] args) {
    CallBackInterfaceTest.test(new CallBackInterfaceImpl());
	//接口/父类类型 引用变量名 = new 接口/父类类型（）{方法的重写}
    CallBackInterface cbt = new CallBackInterface() {
        @Override
        public void show() {
            System.out.println("匿名内部类的实现，不需要单独创建一个实现类");
        }
    };
    CallBackInterfaceTest.test(cbt);
    
    //java8新特性：可以使用lamda表达式简化代码 格式：(参数列表) -> {方法体}
    CallBackInterface cbt2 = () -> System.out.println("使用lamda表达式简化代码");
}
```

### 枚举

 概念：如果一个事物的取值只有明确的几个固定值，此时描述这些事的所有值都可以一一列举出来，这个列举出来的类型就叫做枚举类型

```java
//自定义枚举类
public class Direction {
    //使用private final修饰的变量的初始化需要赋值，
    //三种方式，显式赋值，构造方法赋值，代码块赋值
    private final String dect;

    //私有化的构造方法可以在内部实例化，为了让外部可以读取，使用public static修饰
    //为了避免在外部改变，使用final方法修饰，即转为常量，常量需大写
    public static final Direction DOWN = new Direction("down");
    public static final Direction UP = new Direction("up");
    public static final Direction LEFT = new Direction("left");
    public static final Direction RIGHT = new Direction("right");


    //为了限制变量的内容，不让变量可以随意赋值，模仿单例设计模式，私有化成员变量
    private Direction(String dect) {
        this.dect = dect;
    }

    public String getDect() {
        return dect;
    }
}

//------------------------------------------
//枚举类的测试方法
public class DirectionTest {
    public static void main(String[] args) {
        //变量值不可改变
        Direction d1 = Direction.DOWN;
        System.out.println(d1.getDect());
    }
}
```

定义：使用`public static final`表示的常量描述较为繁琐，使用`enum`关键字来定义枚举类型取代常量，枚举类型是一种引用数据类型

```java
public enum DirectionEnum {
    //枚举类型要求所有枚举值都放到最前方 
    //同时层级是类层级，需要使用枚举.的方式调用
    DOWN("down"),UP("up"),LEFT("left"),RIGHT("right");

    private final String dect;

    private DirectionEnum(String dect) {
        this.dect = dect;
    }

    public String getDect() {
        return dect;
    }
}
```

注意：

1. 枚举值就是当前类的类型，也就是指向本类的对象，默认使用`public static final`关键字共同修饰，因此采用枚举类型.的方式调用
2. 枚举类可以自定义构造方法，但是构造方法的修饰符必须是`private`，默认也是私有的

#### enum+switch

```java
public class DirectionUseTest {

    //switch已经支持枚举类型，可以直接传入
    public static void test(DirectionEnum de){

        switch (de) {
            case DOWN:
                System.out.println("向下走"); break;
            case UP:
                System.out.println("向上走");break;
            case LEFT:
                System.out.println("在左边");break;
            case RIGHT:
                System.out.println("在右边");break;
        }
    }

    public static void main(String[] args) {
        DirectionUseTest.test(DirectionEnum.UP);
    }
}
```

#### Enum类的概念和方法

- 所有的枚举类都继承自java.lang.Enum类，常用方法如下：

  | 方法                         | 功能                                     |
  | ---------------------------- | ---------------------------------------- |
  | static T[] values()          | 返回当前枚举类中的所有对象               |
  | String  toString()           | 返回当前枚举类对象的名称                 |
  | int ordinal()                | 获取枚举对象在枚举类中的索引位置         |
  | static T valueOf(String str) | 将参数指定的字符串名转为当前枚举类的对象 |
  | int compareTo(E o)           | 比较两个枚举对象在定义时的               |

  ```java
  public class DirectionEnumTest {
      public static void main(String[] args) {
          //需注意返回值类型是枚举的类型
          DirectionEnum[] arr = DirectionEnum.values();
  
          for (int i=0;i<arr.length;i++){
              System.out.print("获取的枚举类型的名称：" + arr[i].toString() + "  ");
              System.out.println("获取的枚举类型对应的索引：" + arr[i].ordinal());
          }
      }
      System.out.println("--------------------------------");
      //IllegalArgumentException 非法参数异常，即输入的值不是枚举的名称
      //DirectionEnum de = DirectionEnum.valueOf("down");
      DirectionEnum de = DirectionEnum.valueOf("UP");
      System.out.println("把字符串转为对应的枚举类型的值：" + de);
      //当打印引用变量时，会自动调用toString方法
  
      System.out.println("--------------------------------");
      for (int i=0;i< arr.length;i++){
          //结果为调用对象的下标值减去形参对象的下标值
          System.out.println("比较结果：" + de.compareTo(arr[i]));
          }
  }
  ```

> IllegalArgumentException 非法参数异常，需记忆

#### 枚举类实现接口的方式

枚举实现接口可以有两种方法：

- 每个枚举对象都重写一次接口（前文中的匿名类方式）
- 整个枚举类重写一次接口

```java
//一个接口，以及一个抽象方法
public interface DirectionInterface {
    public abstract void show();
}
//------------------------------------

//使用枚举类实现接口的两种方式
public enum DirectionEnum implements DirectionInterface{
	//每个枚举都重写单独的方式
    DOWN("down"){
        @Override
        public void show() {
            System.out.println("接口的向下的重写");
        }
    },UP("up") {
        @Override
        public void show() {
            System.out.println("接口的向上的重写");
        }
    },LEFT("left") {
        @Override
        public void show() {
            System.out.println("接口的向左的重写");
        }
    },RIGHT("right") {
        @Override
        public void show() {
            System.out.println("接口的向右的重写");
        }
    };

	//整个枚举类实现一个接口的重写
 /*   @Override
    public void show() {
        System.out.println("在枚举中实现了接口的重写");
    }*/
}
//------------------------------------------------------

//测试类
public class DirectionEnumTest {
    public static void main(String[] args) {
        for(int i=0;i<arr.length;i++){
        	arr[i].show();
        }
    }
}
```

### 注解

概念：注解（Annotation）又叫标注，是一种引用数据类型

作用：注解本质上就是代码中的特殊标记，通过这些标记可以在编辑、类加载以及运行时执行指定的处理

语法格式：`访问修饰符 @interface 注解名称{注解成员；}`

说明：

1. 自定义注解自动继承`java.lang.annotation.Annotation`接口

2. 通过@注解名称的方式可以修饰包、类、方法名称、成员变量、构造方法、参数、局部变量的声明等

使用方式：

- 注解体中只有成员变量没有成员方法，而注解的成员变量以“无形参的方法”形式来声明，其方法名定义了该成员变量的名字，其返回值定义了该成员变量的类型
- 如果注解中只有一个参数成员，建议使用参数名为value，而类型只能是八种基本数据类型、String类型、Class类型、enum类型、Annotation类型

```java
//声明一个注解

//如果一个注解没有给任何的成员变量，则这样的注解叫做标记注解，或者标识注解
public @interface MyAnnotation {
    //声明一个成员变量，其返回值就是成员变量的类型，注意不是抽象方法，而是变量
    public String value();
    //同样可以使用default去声明一个成员变量的默认值
    public int age() default 18;
}

//------------------------------------------------
//使用一个注解

//如果注解有变量，需要在使用时给出变量值，格式：成员变量名 = 变量值,....
@MyAnnotation(value = "hello")
public class People {
}
```

#### 元注解

概念：元注解是可以注解到注解上的注解，或者说元注解是一种基本注解，但是它能够应用到其他的注解上面

种类：@Retention  @Documented  @Target  @Inherited  @Repeatable

#### 元注解@Retention

作用：@Retention应用到一个注解上用于说明该注解的生命周期，取值如下：

- RetentionPolicy.SOURCE：注解只在源码阶段保留，在编译器进行编译时它将被丢弃忽视

- RetentionPolicy.CLASS：注解只会被保留到编译进行的时候，它并不会被加载到JVM中，默认方式。
- RetentionPolicy.RUNTIME：注解可以保留到程序运行的时候，它会被加载进入到JVM中，所以在程序运行时可以获取到它们

```java
@Retention(RetentionPolicy.SOURCE) //下面的注解在源代码中有效
@Retention(RetentionPolicy.CLASS) //下面的注解在字节码阶段都有效，默认方式
@Retention(RetentionPolicy.RUNTIME) //下面的注解在运行阶段都有效
public @interface MyAnnotation {}
```

#### 元注解@Documented

作用：@Documented用于指定被注解将被javadoc工具提取成文档

- 使用javadoc工具可以从程序源代码中抽取类、方法、成员等注释形成一个和源代码配套的API帮助文档，而该工具抽取时默认不包括注解内容
- 定义为@Documented的注解必须设置Retention值为RUNTIME

#### 元注解@Target

作用：用于指定被修饰的注解能作用与哪些元素的修饰

| 注解修饰                    | 作用                                   |
| --------------------------- | -------------------------------------- |
| ElementType.ANNOTATION_TYPE | 可以给一个注解进行注解                 |
| ElementType.CONSTRUCTOR     | 可以给构造方法进行注解                 |
| ElementType.FIELD           | 可以给属性进行注解                     |
| ElementType.LOCAL_VARIABLE  | 可以给局部变量进行注解                 |
| ElementType.PACAGE          | 可以给一个包进行注解                   |
| ElementType.PARAMETER       | 可以给一个方法内的参数进行注解         |
| ElementType.TYPE            | 可以给类型进行注解，比如类、接口、枚举 |
| ElementType.METHOD          | 可以给方法进行注解                     |
| ElementType.TYPE_PARAMETER  | 该注解能写在类型变量的声明语句中       |
| ElementType.TYPE_USE        | 该注解能写在使用类型的任何语句中       |

#### 元注解@Inherited

作用：@Inherited并不是注解本身可以继承，而是说如果一个超类被该注解标记过的注解进行注解时，如果子类没有被任何注解应用时，则子类就继承超类的注解

```java
@Documented	
@Inherited	//上阵父子兵
@Target({ElementType.FIELD,ElementType.TYPE})
```

#### 元注解@Repeatable

作用：@Repeatable表示自然可重复的含义

```java
//此注解可以重复使用，不过需要调用一个多参数注解
@Repeatable(value = ManTypes.class)
public @interface ManType {
    String value() default "";
}

//-----------------------------------
//一个多参数类型的注解，类型是另一种注解
public @interface ManTypes {
    ManType[] value();
}

//-----------------------------------
//可以使用多个同样的注解但是不同的信息
@ManType(value = "a")
@ManType(value = "b")
public class Man {
}
```

#### 常见的预制注解

定义：就是java语言自身提供的注解

| 注解              | 作用                                                   |
| ----------------- | ------------------------------------------------------ |
| @author           | 标明开发该类模块的作者，多个作者之间使用，分割         |
| @version          | 表明该类模块的版本                                     |
| @see              | 参考转向，也就是相关主题                               |
| @since            | 从哪个版本开始增加                                     |
| @param            | 对方法中某参数的说明，如果没有参数就不能写             |
| @return           | 对方法返回值的说明，如果方法的返回值类型是void就不可写 |
| @exception        | 对方法可能抛出的异常进行说明                           |
| @Override         | 限定重写父类方法，该注解只能用于方法                   |
| @Deprecated       | 用于表示所修饰的元素已过时                             |
| @SuppressWarnings | 抑制编译器警告                                         |

### 面试题

1. 请问构造器能否被重写？为什么

   答：不能，构造方法是不能被继承的，所以不能被重写，但可以被重载

2. Overload和Override的区别是什么，参数列表相同但返回值类型不同能重载吗？

   答：需注意：重载也可以用在父子类中。。。重载与参数列表和返回值类型无关

3. 使用final关键字修饰一个引用变量时，请问是引用不能变还是引用的对象不能变呢？   

   答：引用不能变，即引用对应的栈区存放的地址不能变

4. 简述复制引用和复制对象的区别

   答：一个复制的是地址，一个复制的是值

5. 阅读下列程序并分析执行结果：

   ```java
   public class Base{
       private String baseName = "base";
       
       public Base(){
           callName();
       }
       
       public void callName(){
           System.out.println(baseName);
       }
   }
   
   //----------------------------------------
   class Sub extends Base{
       private String baseName = "sub";
       
       public void callName(){
           System.out.println(baseName);
       }
       
       public static void main(String[] args){
           Base b = new Sub();
       }
   }
   ```

   答：首先执行Base b = new Sub()；即多态，这里首先创建子类对象，而子类对象会先在内存区域内部创建一个父类对象，即会执行父类的初始化代码，baseName="base"。创建父类对象必然会调用父类构造方法，父类构造方法中只有一条：callName()。这句话会调用callName方法，而父类的此方法被子类重写了，所以会调用子类的callName，但是子类的baseName还没有定义，所以会返回默认值null，即结果为：null 
