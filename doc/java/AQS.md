# AQS

## Reentrantlock

[代码分析](https://www.cnblogs.com/zaizhoumo/p/7756310.html)

单个线程或者交替执行 和队列无关 

* 自旋
* park unpark
* CAS

tryAcqure false t2获取锁判断自己是否需要排队
true t2没拿到锁入队

公平锁与非公平锁的实现上差hasQueuedPredecessors()方法 此方法判断是否有线程在队列中等待

## AQS源码

AQS队列对头node的Thread永远为空

enq()通过死循环方式维护队列关系，将等待线程入队 自旋，队列里第一个不是排队的，第二个才是排队的

入队之后判断能不能拿到锁 拿不到线程阻塞 LockSupport.park()

