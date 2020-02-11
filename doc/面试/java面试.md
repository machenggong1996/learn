# java面试

## 基础

### Integer缓存策略

Integer在自动装箱时会将数值进行缓存 缓存范围为-128<i<127 执行new Integer()不会进行缓存

```java
public class JavaIntegerCache {
    public static void main(String... strings) {
 
        Integer integer1 = 3;
        Integer integer2 = 3;
 
        if (integer1 == integer2)
            System.out.println("integer1 == integer2"); //true
        else
            System.out.println("integer1 != integer2");
 
        Integer integer3 = 300;
        Integer integer4 = 300;
 
        if (integer3 == integer4)
            System.out.println("integer3 == integer4"); //false
        else
            System.out.println("integer3 != integer4");
 
    }
}
```

Integer.valueOf(10)方法会判断数值是否在缓存范围内，在的话进行缓存，不再的话进行new Integer(),

[博客链接](https://www.cnblogs.com/SzBlog/p/8459351.html)

### HTTP／1.0／1.1／2.0

[三种协议介绍](https://www.jianshu.com/p/52d86558ca57)

[SSL/TLS](https://blog.csdn.net/freekiteyu/article/details/76423436)

### AIO NIO BIO

[IO详解](http://www.imooc.com/article/265871)

[加select epoll epoll](https://www.cnblogs.com/eryun/p/12040508.html)

### 进程通讯方式

### 多线程

#### 线程池

### JVM

[jvm详解](https://wiki.jikexueyuan.com/project/java-vm/class-loading-mechanism.html)

#### 类加载机制

#### 垃圾收集器

[垃圾收集器](https://blog.csdn.net/qq_26525215/article/details/84294481)

### 数据库

#### mysql锁类型

* 表锁(myisam,bdb,innodb)
* 行锁(innodb)
* 页锁(bdb)
* 间隙锁(innodb)

### CAS(Compare and Swap)

CAS有3个操作数，内存值V，旧的预期值A，要修改的新值B。当且仅当预期值A和内存值V相同时，将内存值V修改为B，否则什么都不做。

CAS的缺点

1.只能保证对一个变量的原子性操作
当对一个共享变量执行操作时，我们可以使用循环CAS的方式来保证原子操作，但是对多个共享变量操作时，循环CAS就无法保证操作的原子性，这个时候就可以用锁来保证原子性。

2.长时间自旋会给CPU带来压力
我们可以看到getAndAddInt方法执行时，如果CAS失败，会一直进行尝试。如果CAS长时间一直不成功，可能会给CPU带来很大的开销。

3.ABA问题
如果内存地址V初次读取的值是A，并且在准备赋值的时候检查到它的值仍然为A，那我们就能说它的值没有被其他线程改变过了吗？
如果在这段期间它的值曾经被改成了B，后来又被改回为A，那CAS操作就会误认为它从来没有被改变过。这个漏洞称为CAS操作的“ABA”问题。Java并发包为了解决这个问题，提供了一个带有标记的原子引用类“AtomicStampedReference”，它可以通过控制变量值的版本来保证CAS的正确性。因此，在使用CAS前要考虑清楚“ABA”问题是否会影响程序并发的正确性，如果需要解决ABA问题，改用传统的互斥同步可能会比原子类更高效。

### redis

#### redis分布式锁

### 正则表达式

[快速开始正则表达式](https://www.cnblogs.com/xyou/p/7427779.html)

[正则表达式](https://www.runoob.com/java/java-regular-expressions.html)