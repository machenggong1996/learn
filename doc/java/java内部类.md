# java内部类

## 1. 内部类的基本概念

### 1.1 内部类的定义

内部类： 所谓内部类就是在一个类内部进行其他类结构的嵌套操作

```java
class Outer{
    private String str ="外部类中的字符串";
    //************************** 
    //定义一个内部类
    class Inner{
        private String inStr= "内部类中的字符串";
        //定义一个普通方法
        public void print(){
            //调用外部类的str属性
            System.out.println(str);
        }
    }
    //************************** 
    //在外部类中定义一个方法，该方法负责产生内部类对象并调用print()方法
    public void fun(){
        //内部类对象
        Inner in = new Inner();
        //内部类对象提供的print
        in.print();
    }
}
public class Test{
    public static void main(String[] args)
    {
        //创建外部类对象
        Outer out = new Outer();
        //外部类方法
        out.fun();
    }
}
```

### 1.2 内部类的优缺点

#### 内部类的优点:

1. 内部类与外部类可以方便的访问彼此的私有域（包括私有方法、私有属性）。
2. 内部类是另外一种封装，对外部的其他类隐藏。
3. 内部类可以实现java的单继承局限。

#### 内部类的缺点:

结构复杂。
记录：使用内部类实现多继承：

```java
class A {
    private String name = "A类的私有域";
    public String getName() {
        return name;
    }
}
class B {
    private int age = 20;
    public int getAge() {
        return age;
    }
}
class Outter {
    private class InnerClassA extends A {
        public String name() {
            return super.getName();
    }
}
    private class InnerClassB extends B {
        public int age() {
            return super.getAge();
    }
}
    public String name() {
        return new InnerClassA().name();
    }
    public int age() {
        return new InnerClassB().age();
    }
}
public class Test2 {
        public static void main(String[] args) {
            Outter outter = new Outter();
            System.out.println(outter.name());
            System.out.println(outter.age());
        }
}
```

## 2. 创建内部类

### 2.1 在外部类外部 创建非静态内部类

* 语法： 外部类.内部类 内部类对象 = new 外部类().new 内部类();
* 举例： Outer.Inner in = new Outer().new Inner();

### 2.2 在外部类外部 创建静态内部类

* 语法： 外部类.内部类 内部类对象 = new 外部类.内部类();
* 举例： Outer.Inner in = new Outer.Inner();

### 2.3 在外部类内部创建内部类语法

在外部类内部创建内部类，就像普通对象一样直接创建：Inner in = new Inner();

## 3. 内部类的分类

在Java中内部类主要分为成员内部类、静态内部类、方法内部类、匿名内部类

### 3.1 成员内部类

```java
class Outer {
    private String name = "test";
    public static int age = 20;

    class Inner {
        //成员内部类变量不允许是static的
        public int num = 10;

        public void fun() {
            System.out.println(name);
            System.out.println(age);
        }
    }
}

public class Test {
    public static void main(String[] args) {
        Outer.Inner in = new Outer().new Inner();
        in.fun();
    }
}
```

### 3.2 静态内部类

```java
class Outer {
    public static String name = "test";
    private static int age =20;

    static class Inner{
        //private String name;
        public void fun()
        {
            //name 要求外部类变量为静态
            System.out.println(name);
            System.out.println(age);
        }
    }
}
public class Test{
    public static void main(String [] args)
    {
        Outer.Inner in = new Outer.Inner();
        in.fun();
    }
}
```

### 3.3 方法内部类

方法内部类顾名思义就是定义在方法里的类

1. 方法内部类不允许使用访问权限修饰符（public、private、protected）均不允许。
2. 方法内部类对外部完全隐藏，除了创建这个类的方法可以访问它以外，其他地方均不能访问 (换句话说其他方法或者类都不知道有这个类的存在)方法内部类对外部完全隐藏，出了创建这个类的方法可以访问它，其他地方均不能访问。
3. 方法内部类如果想要使用方法形参，该形参必须使用final声明（JDK8形参变为隐式final声明）

### 3.4 匿名内部类

匿名内部类就是一个没有名字的方法内部类，因此特点和方法与方法内部类完全一致，除此之外，还有自己的特点：

1. 匿名内部类必须继承一个抽象类或者实现一个接口。
2. 匿名内部类没有类名，因此没有构造方法。

```java
//匿名内部类
//声明一个接口
interface MyInterface {
    //接口中方法没有方法体
    void test();
}

class Outer {
    private int num = 5;

    public void dispaly(int temp) {
        //匿名内部类，匿名的实现了MyInterface接口
        //隐藏的class声明
        new MyInterface() {
            @Override
            public void test() {
                System.out.println("匿名实现MyInterface接口");
                System.out.println(temp);
            }
        }.test();
    }
}

public class Test {
    public static void main(String[] args) {
        Outer out = new Outer();
        out.dispaly(3);
    }
}
```

## 4. 内部类与外部类的关系

* 对于非静态的内部类，内部类的创建依赖外部类的实例对象，在没有外部类实例之前是无法创建内部类的。
* 内部类可以直接访问外部类的元素（包括私有域）—外部类在内部类之前创建，创建内部类时会将外部类的对象传入

## 5. 静态内部类

静态内部类延迟加载