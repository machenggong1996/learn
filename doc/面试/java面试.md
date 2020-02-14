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

* 加载 获取类的二进制字节流，生成方法区的运行时存储结构，在内存中生成class对象
* 验证 确保字节流符合jvm需求
* 准备 初始化静态变量
* 解析 将常量池的符号引用替换为直接引用
* 初始化 执行静态代码块 为变量赋值
* 使用
* 卸载

#### 垃圾收集器

[垃圾收集器](https://blog.csdn.net/qq_26525215/article/details/84294481)

### 数据库

#### mysql锁类型

* 表锁(myisam,bdb,innodb)
* 行锁(innodb)
* 页锁(bdb)
* 间隙锁(innodb)

#### 事务隔离级别

1. 脏读：事务B修改数据但未提交，事务A读数据，然后B回滚，则A读到的是脏数据。
  * 事务A读到事务B的数据 但是事务B没有提交数据
2. 不可重复读：事务A第一次读取数据，事务B修改数据提交，事务A第二次读数据，两次数据不一致。
  * 事务A读了两次数据 但是在这两次读取之间事务B进行了数据修改并提交
3. 幻读：事务Aupdate表的全部行，事务B插入一行，事务A就会发现表中还有未修改的行。（一般加间隙锁）
  * 事务A修改过程中事务B新增一行 事务A发现好像并没有完全修改成功 所以认为出现幻觉

事务的隔离级别分为：未提交读(read uncommitted)、已提交读(read committed)、可重复读(repeatable read)、串行化(serializable)。

未提交读：A事务已执行，但未提交；B事务查询到A事务的更新后数据；A事务回滚；---出现脏数据

已提交读：A事务执行更新；B事务查询；A事务又执行更新；B事务再次查询时，前后两次数据不一致；---不可重复读

可重复读：A事务无论执行多少次，只要不提交，B事务查询值都不变；B事务仅查询B事务开始时那一瞬间的数据快照；

串行化：不允许读写并发操作，写执行时，读必须等待；

mysql默认可重复读

#### spring事务隔离级别

1. PROPERGATION_MANDATORY:　方法必须运行在一个事务中，不存在事务则抛出异常

2. PROPERGATION_NESTED:　　存在事务则运行在嵌套事务中，不存在则创建一个事务

3. PROPERGATION_NEVER: 当前方法不能运行在事务中，存在事务则抛出异常

4. PROPERGATION_NOT_SUPPORT: 当前存在事务则将其 挂起

5. PROPERGATION_REQUIRED: 不存在事务则创建一个事务

6. PROPERGATION_REQUIRES_NEW:  新建一个自己的事务，不论当前是否存在事务

7. PROPERGATION_SUPPORT: 存在事务则加入，不存在也可以

8. SUPPORTS: 声明了事务，则就用这个事务，如果没有声明事务，那就不用事务

[spring事务传播级别](https://blog.csdn.net/qq_26323323/article/details/81908955)

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

### tcp三次握手四次挥手

[三次握手四次挥手](https://blog.csdn.net/qq_38950316/article/details/81087809)

### 死锁条件

互斥条件：一个资源每次只能被一个进程使用，即在一段时间内某 资源仅为一个进程所占有。此时若有其他进程请求该资源，则请求进程只能等待。

请求与保持条件：进程已经保持了至少一个资源，但又提出了新的资源请求，而该资源 已被其他进程占有，此时请求进程被阻塞，但对自己已获得的资源保持不放。

不可剥夺条件:进程所获得的资源在未使用完毕之前，不能被其他进程强行夺走，即只能 由获得该资源的进程自己来释放（只能是主动释放)。

循环等待条件: 若干进程间形成首尾相接循环等待资源的关系

这四个条件是死锁的必要条件，只要系统发生死锁，这些条件必然成立，而只要上述条件之一不满足，就不会发生死锁。