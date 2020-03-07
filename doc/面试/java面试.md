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

#### 如何快速向mysql插入100万条数据

使用存储过程

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

不能两次握手是因为要防止中间一次传输丢失

两次握手可能会出现失效的请求发起的握手请求，服务端接收之后给了回应然后一直等客户端发消息过来最终导致资源浪费

[三次握手四次挥手](https://blog.csdn.net/qq_38950316/article/details/81087809)

### 死锁条件

互斥条件：一个资源每次只能被一个进程使用，即在一段时间内某 资源仅为一个进程所占有。此时若有其他进程请求该资源，则请求进程只能等待。

请求与保持条件：进程已经保持了至少一个资源，但又提出了新的资源请求，而该资源 已被其他进程占有，此时请求进程被阻塞，但对自己已获得的资源保持不放。

不可剥夺条件:进程所获得的资源在未使用完毕之前，不能被其他进程强行夺走，即只能 由获得该资源的进程自己来释放（只能是主动释放)。

循环等待条件: 若干进程间形成首尾相接循环等待资源的关系

这四个条件是死锁的必要条件，只要系统发生死锁，这些条件必然成立，而只要上述条件之一不满足，就不会发生死锁。

### IO

同步异步是指客户端是否要继续发送请求，阻塞非阻塞是指线程要不要继续做其他事情

1. 同步：发送方发送请求之后，需要等接收方发回响应后才接着发
2. 异步：发送方发送一个请求之后不等待接收方响应这个请求，就继续发送下个请求。
3. 阻塞：调用结果返回之前，当前线程会被挂起。调用线程只有在得到结果之后才会返回，该线程在此过程中不能进行其他处理
4. 非阻塞：调用结果不能马上返回，当前线程也不会被挂起，而是立即返回执行下一个调用。（网络通信中主要指的是网络套接字Socket的阻塞和非阻塞方式，而soket 的实质也就是IO操作）
5. 同步阻塞方式 发送方发送请求之后一直等待响应。接收方处理请求时进行的IO操作如果不能马上等到返回结果，就一直等到返回结果后，才响应发送方，期间不能进行其他工作
6. 同步非阻塞方式发送方发送请求之后，一直等待响应，接受方处理请求时进行的IO操作如果不能马上的得到结果，就立即返回，取做其他事情。但是由于没有得到请求处理结果，不响应发送方，发送方一直等待。一直等到IO操作完成后，接收方获得结果响应发送发后，接收方才进入下一次请求过程。（实际不应用）
7. 异步阻塞方式 发送方向接收方请求后，不等待响应，可以继续其他工作，接收方处理请求时进行IO操作如果不能马上得到结果，就一直等到返回结果后，才响应发送方，期间不能进行其他操作。 （实际不应用）
8. 异步非阻塞方式发送方向接收方请求后，不等待响应，可以继续其他工作，接收方处理请求时进行IO操作如果不能马上得到结果，也不等待，而是马上返回取做其他事情。当IO操作完成以后，将完成状态和结果通知接收方，接收方在响应发送方。（效率最高）

### 多态

这是由于在编译阶段，只是检查参数的引用类型

* 静态多分派-方法重载-返回值和形参内容都改变
* 动态多分派-方法重写-返回值和形参不变内容改变

### spring mvc

[spring mvc流程图](https://blog.csdn.net/floating_dreaming/article/details/89089214)

1. 用户发送请求至前端控制器DispatcherServlet。

2. DispatcherServlet收到请求调用HandlerMapping处理器映射器。

3. 处理器映射器找到具体的处理器(可以根据xml配置、注解进行查找)，生成处理器对象及处理器拦截器(如果有则生成)一并返回给DispatcherServlet。

4. DispatcherServlet调用HandlerAdapter处理器适配器。

5. HandlerAdapter经过适配调用具体的处理器(Controller，也叫后端控制器)。

6. Controller执行完成返回ModelAndView。

7. HandlerAdapter将controller执行结果ModelAndView返回给DispatcherServlet。

8. DispatcherServlet将ModelAndView传给ViewReslover视图解析器。

9. ViewReslover解析后返回具体View.

10. DispatcherServlet根据View进行渲染视图（即将模型数据填充至视图中）。 

11. DispatcherServlet响应用户。

### list删除元素

迭代器删除

[迭代器删除](https://blog.csdn.net/jam_yin/article/details/82382044)

### jdk动态代理

由于java的单继承，动态生成的代理类已经继承了Proxy类的，就不能再继承其他的类，所以只能靠实现被代理类的接口的形式，故JDK的动态代理必须有接口。

另外，为何调用代理类的方法就会自动进入InvocationHandler 的 invoke（）方法呢？

其实是因为在动态代理类的定义中，构造函数是含参的构造，参数就是我们invocationHandler 实例，而每一个被代理接口的方法都会在代理类中生成一个对应的实现方法，并在实现方法中最终调用invocationHandler 的invoke方法，这就解释了为何执行代理类的方法会自动进入到我们自定义的invocationHandler的invoke方法中，然后在我们的invoke方法中再利用jdk反射的方式去调用真正的被代理类的业务方法，而且还可以在方法的前后去加一些我们自定义的逻辑。比如切面编程AOP等。

### jvm Survivor区

如果没有Survivor，Eden区每进行一次Minor GC，存活的对象就会被送到老年代。老年代很快被填满，触发Major GC（因为Major GC一般伴随着Minor GC，也可以看做触发了Full GC）。老年代的内存空间远大于新生代，进行一次Full GC消耗的时间比Minor GC长得多。你也许会问，执行时间长有什么坏处？频发的Full GC消耗的时间是非常可观的，这一点会影响大型程序的执行和响应速度，更不要说某些连接会因为超时发生连接错误了

在GC开始的时候，对象只会存在于Eden区和名为“From”的Survivor区，Survivor区“To”是空的。紧接着进行GC，
Eden区中所有存活的对象都会被复制到“To”，而在“From”区中，仍存活的对象会根据他们的年龄值来决定去向。
年龄达到一定值(年龄阈值，可以通过-XX:MaxTenuringThreshold来设置)的对象会被移动到年老代中，没有达
到阈值的对象会被复制到“To”区域。经过这次GC后，Eden区和From区已经被清空。这个时候，“From”和“To”会
交换他们的角色，也就是新的“To”就是上次GC前的“From”，新的“From”就是上次GC前的“To”。不管怎样，都会
保证名为To的Survivor区域是空的。Minor GC会一直重复这样的过程，直到“To”区被填满，“To”区被填满之后，
会将所有对象移动到年老代中。

### hashmap链表长度超过8就一定会变成红黑树吗？

```
// putVal方法中相关部分(put方法调用了putVal())
if (binCount >= TREEIFY_THRESHOLD - 1) {
	// 转换成红黑树,如果hash table的长度不到MIN_TREEIFY_CAPACITY即64,
	// 那么只是做扩容处理,并不会转换为红黑树
    treeifyBin(tab, hash);
}

/ treeifyBin方法中相关部分
// MIN_TREEIFY_CAPACITY = 64
if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY){
	// 扩容,不转换
	resize();
}else if ((e = tab[index = (n - 1) & hash]) != null) {
	// 转换为红黑树节点
    TreeNode<K,V> hd = null, tl = null;
    do {
     // 具体转换逻辑
     ...
    } while ((e = e.next) != null);
    if ((tab[index] = hd) != null){
    	hd.treeify(tab);// 转换为红黑树
    }
}
```

### mysql like性能问题

[mysql like性能问题](https://blog.csdn.net/weixin_38860401/article/details/81331972)

### websocket

WebSocket 是 HTML5 开始提供的一种在单个 TCP 连接上进行全双工通讯的协议。

### equals()与==

1. 当使用==比较时，如果相比较的两个变量是引用类型，那么比较的是两者的物理地址值（内存地址），
如果相比较的两个变量都是数值类型，那么比较的是具体数值是否相等；而引用类型进行比较时使用的是
他们的hashCode()方法的返回结果。
2. 当使用equals()方法进行比较时，比较的结果实际上取决于equals()方法的具体实现。众所周知，任
何类都继承自Object类，因此所有的类均具有Object类的特性，比如String、Integer等，他们在自己
的类中重写了equals()方法，此时他们进行的是数值的比较，而在Object类的默认实现中，equals()方
法的底层是通过==来实现的。也就是按照引用（物理地址）来比较的方式

### Exception和Error有什么区别

首先Exception和Error都是继承于Throwable 类，在 Java 中只有 Throwable 类型的实例才可以被抛出（throw）
或者捕获（catch），它是异常处理机制的基本组成类型。

Exception和Error体现了JAVA这门语言对于异常处理的两种方式。

Exception是java程序运行中可预料的异常情况，咱们可以获取到这种异常，并且对这种异常进行业务外的处理。

Error是java程序运行中不可预料的异常情况，这种异常发生以后，会直接导致JVM不可处理或者不可恢复的情况。所
以这种异常不可能抓取到，比如OutOfMemoryError、NoClassDefFoundError等。

其中的Exception又分为检查性异常和非检查性异常。两个根本的区别在于，检查性异常 必须在编写代码时，使用
try catch捕获（比如：IOException异常）。非检查性异常 在代码编写使，可以忽略捕获操作（比如：ArrayIndexOutOfBoundsException），
这种异常是在代码编写或者使用过程中通过规范可以避免发生的。 切记，Error是Throw不是Exception 。

运行时异常，也就是extends RuntimeException的异常编译时不用try{}catch(){}和throws

编译时异常，也就是extends Exception 的异常需要在调用时try{}catch(){}或throws

### JAVA序列化ID问题 serialVersionUID

ObjectInputStream读 和 ObjectOutputStream写

如果没有serialVersionUID反序列化过程中会报 java.io.InvalidClassException

### 深克隆和浅克隆

[深克隆和浅克隆](https://blog.csdn.net/lovezhaohaimig/article/details/80372233)

### java重写equals()方法的几个原则

1、自反性：对于任何非空引用x，x.equals(x)应该返回true。

2、对称性：对于任何引用x和y，如果x.equals(y)返回true，那么y.equals(x)也应该返回true。

3、传递性：对于任何引用x、y和z，如果x.equals(y)返回true，y.equals(z)返回true，那么x.equals(z)也应该返回true。

4、一致性：如果x和y引用的对象没有发生变化，那么反复调用x.equals(y)应该返回同样的结果。

5、非空性：对于任意非空引用x，x.equals(null)应该返回false。

(1)当obj1.equals(obj2)为true时，obj1.hashCode() == obj2.hashCode()必须为true

(2)当obj1.hashCode() == obj2.hashCode()为false时，obj1.equals(obj2)必须为false

### 重写equals()方法为什么要同时重写hashcode()方法？

重写equals()方法同时重写hashcode()方法，就是为了保证当两个对象通过equals()方法比较相等时，那么他们的hashCode值也一定要保证相等。

和hash相关的集合需要计算使用hashcode计算位置，可能相同对象但是hashcode不同导致存储位置不同

### 接口和抽象类优点 作用

### linux过滤日志

### 数据倾斜

[数据倾斜解决方案](https://blog.csdn.net/chyeers/article/details/78320778)

### redis分布式锁

[redis分布式锁](https://blog.csdn.net/dazou1/article/details/88088223)

setnx命令 成功设置key返回1 失败返回0

getset 命令防止死锁 防止其他进程获取锁

### spring反射工厂

### jvm类加载机制

（类的解析阶段）虚拟机会把类的二进制数据中的符号引用替换为直接引用

### jvm GC条件

[jvm GC](https://www.jianshu.com/p/1196cf7cb8b8)