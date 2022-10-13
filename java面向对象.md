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

- 基本数据类型的变量作为方法的参数传递时，形参数值的改变通常不会影响到实参数值，因为两个变量有各自独立的内存空间
- 引用数据类型的变量作为方法的参数传递时，形参指向内容的改变会影响到实参的数值，因为两者存进去的值是堆区的地址，是同一块内存空间
- 若不希望改变引用数据类型的变量，就再申请个空间即可

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

作用：使用new关键字创建对象时会自动调用构造方法实现成员变量初始化工作、

> 若类中出现了构造方法，则编译器不在提供任何形式的构造方法

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
       this.age
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

   > 对于static修饰的方法而言，可以直接调用该方法，如果再使用this，则会报错，所以，static修饰的方法不能使用this引用
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

定义：`private static int age;`

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
        System.out.println(a); //静态方法没有this关键字
        System.out.println(b); //都是类层级，可以调用
    }

    public static void main(String[] args){
        StaticTest st = new StaticTest();
        st.show();
        StaticTest.test();
    }
}
```

### 构造块和静 态代码块

构造块：在类体中直接使用{}括起来的代码块，每创建一个对象都会执行一次构造块

静态代码块：使用static关键字修饰的构造块

```java
public class BlockTest{

    //当需要在执行构造方法体之前做一些准备工作是，将准备工作的相关代码写在构造块之中即可，例如对成员变量进行统一的初始化操作
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

1. 静态代码块    	// 静态代码块在加载时就开始执行，而父类要先加载
2. --子类静态代码块
3. 构造块             // 在子类对象构造之前，父类的构造方法要先加载
4. 构造方法
5. --子类构造块
6. --子类构造方法

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

    //2，创建一个私有的函数，这样就不能多次调用了，但问题是一次也无法调用
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
- 通常情况下，成员方法都使用public关键字修饰，成员变量都使用private关键字修饰

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

- 为了避免上述错误的发生，应该在强转之前进行判断，格式如下：

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

### 抽象方法与抽象类

#### 抽象方法

概念：指不能具体实现的方法并且使用abstract关键字修饰。也就是没有方法体

格式：`访问权限 abstract 返回值类型 方法名（形参列表）;`

例如：`public abstract void cry();`

#### 抽象类

概念：指不能具体实例化的类并且使用abstract关键字修饰，也就是不能创建对象

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