# ReentrantLock重入锁

* [ReentrantLock重入锁](https://www.cnblogs.com/wait-pigblog/p/9347023.html)

## 1. 实现原理

ReentrantLock实现了Lock接口，首先可以看一下Lock接口定义了哪些方法ReentrantLock又是如何实现的

```java
public interface Lock {

    void lock();

    void lockInterruptibly() throws InterruptedException;

    boolean tryLock();

    boolean tryLock(long time, TimeUnit unit) throws InterruptedException;

    void unlock();

    Condition newCondition();
}
```

* lock()：获取同步状态
* unlock()：释放同步状态
* 公平锁：之前的AQS文章说过，当同步队列中首节点释放同步状态后，因为FIFO先进先出队列首先获取同步状态的为其后继节点如果不存在后继节点就获取等待时间最长的正常状态的线程。
  而这种唤醒的过程就是公平锁，当释放同步状态以后获取同步状态的要么是后继节点要么是等待时间最长的节点。但是由于公平锁的这个特性导致在并发很高的情况下其效率比非公平锁要低。

```java_method
public ReentrantLock(boolean fair) {
         sync = fair ? new FairSync() : new NonfairSync();//通过传入boolean类型的值确定到底是公平锁还是非公平锁
     }
```

* 非公平锁：相对于公平锁而言，非公平锁在释放同步状态以后所有的线程都会进入竞争同步状态，而获取同步状态的线程是随机的不确定的。有可能是等待时间最长的也有可能是等待时间最短的。

```java_method
pubic ReentrantLock(){
     　　sync = new NofairSync();//默认的构造方法是非公平锁
　　 }
```

* lock()：获取同步状态

```java_method
public void lock() {
         sync.lock();//调用了内部AQS实现类中的方法【有两种实现方式】
     }
```

* 公平锁获取同步状态

```java_method
final void lock() {
            acquire(1);//调用AQS的中方法，最终需要重点关注的方法为tryAcquire(int arg)
        }
```

## 2. 获取锁源码方法分析

### 2.1 tryAcquire(int args)：公平锁判断是否获取到锁

```java_method
protected final boolean tryAcquire(int acquires) {
            final Thread current = Thread.currentThread();//获取当前线程
            int c = getState();//获取锁状态
            if (c == 0) {　　　　　　　　　 //公平锁原理判断是否有超过了当前线程的等待时间的线程也就是说当前是否有等待时间比获取同步状态的线程长的，典型的FIFO队列
                if (!hasQueuedPredecessors() &&
                    compareAndSetState(0, acquires)) {//看同步队列
                    setExclusiveOwnerThread(current);//获取同步状态
                    return true;
                }
            }　　　　　　　//重入锁的实现原理（如果当前获取锁状态线程是已经获取锁的线程）
            else if (current == getExclusiveOwnerThread()) {
                int nextc = c + acquires;//锁状态计数器进行自增操作
                if (nextc < 0)
                    throw new Error("Maximum lock count exceeded");
                setState(nextc);//设置成锁状态
                return true;
            }
            return false;
        }
    }
```

### 2.2 hasQueuedProcesssors()

判断同步队列中是否存在比当前线程等待时间更长的线程

```java_method
 　　public final boolean hasQueuedPredecessors() {
          Node t = tail; //同步队列尾节点
          Node h = head; //同步队列头节点
          Node s;　　　　　　 //头节点和尾节点比较（如果头节点和尾节点重复代表同步对列中只有一个头节点释放以后不需要进行比较）
          return h != t &&((s = h.next) == null || s.thread != Thread.currentThread());　
     }
```

公平锁的原理就是：在通过CAS设置同步状态之前会先去同步队列中查询是否存在线程比当前的线程的等待时间长的，如果存在就不去改变该线程的状态如果不存在就进行改变获取同步状态。

### 2.3 非公平的获取锁

```java_method
final void lock() {
            if (compareAndSetState(0, 1))//通过CAS改变状态
                setExclusiveOwnerThread(Thread.currentThread());//成功就代表获取锁
            else
                acquire(1);//走AQS中的方法
        }
```

### 2.4 nonfairTryAcquire(int acquires)：非公平的获取同步状态

非公平获取锁的实现比公平获取锁的实现要简单很多，它不需要去同步队列中去与其它的节点进行比较，随便谁获取到同步状态

```java_method
final boolean nonfairTryAcquire(int acquires) {
            final Thread current = Thread.currentThread();//获取当前线程
            int c = getState();//获取同步状态值
            if (c == 0) {
                if (compareAndSetState(0, acquires)) {//CAS操作改变状态
                    setExclusiveOwnerThread(current);//成功就获取锁
                    return true;
                }
            }　　　　　　　//重入锁原理如果是当前线程就状态值自增
            else if (current == getExclusiveOwnerThread()) {
                int nextc = c + acquires;
                if (nextc < 0) // overflow
                    throw new Error("Maximum lock count exceeded");
                setState(nextc);//设置状态值
                return true;
            }
            return false;
        }
```

### 2.5 获取同步锁总结

对于ReentrantLock而言，它只实现了如何代表线程已经获取了同步状态，他不关心获取了同步状态的以后操作而这些操作都是由AQS本身去实现的，
这也证明了AQS在并发包中的重要性。再来看看ReentrantLock实现的获取锁，对于非公平锁而言谁获取同步状态都无所谓对于每个线程而言获取同 步状态的几率都是一样的而对于公平锁而言其就遵循了FIFO先进先出队列的原则。

## 3. 释放锁源码方法分析

### 3.1 unlock()：释放同步状态

```java_method
public void unlock() {
         sync.release(1);//调用AQS中的release方法
     }
```

### 3.2 tryRelease(int releases)：释放锁的具体方法

```java_method
protected final boolean tryRelease(int releases) {
            int c = getState() - releases;//通过同步状态值自减　　　　　　　 //判断当前释放同步状态的线程是否为获取同步状态的线程
            if (Thread.currentThread() != getExclusiveOwnerThread())
                throw new IllegalMonitorStateException();
            boolean free = false;//是否完全释放（因为是重入锁必须等待所有的锁释放才算释放）
            if (c == 0) {//代表完全释放
                free = true;
                setExclusiveOwnerThread(null);//获取锁状态的线程为null
            }
            setState(c);//设置状态
            return free;
        }
```

释放锁的过程很简单，每当释放的时候都会进行一次状态自减直到为0的时候代表着这个线程已经完全释放了这个同步状态接下来才会唤醒同步队列中的节点否则代表这个同步状态未释放完全


