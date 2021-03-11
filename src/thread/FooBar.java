package thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author machenggong
 * @date 2021/3/11
 * @description 交替打印FooBar
 */
public class FooBar {

    private int n;
    private AtomicInteger flag = new AtomicInteger(0);
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                while (flag.get() != 0) {
                    condition1.await();
                }

                printFoo.run();
                flag.set(1);
                condition2.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                while (flag.get() != 1) {
                    condition2.await();
                }

                printBar.run();
                flag.set(0);
                condition1.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FooBar fooBar = new FooBar(100);
        new Thread(()->{
            try {
                fooBar.foo(()->{
                    System.out.println("foo");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                fooBar.bar(()->{
                    System.out.println("bar");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
