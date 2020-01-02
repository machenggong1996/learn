package thread.waitfivesecond;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by machenggong on 2020/1/2.
 */
public class WaitFiveSecondTest {

    volatile List list = new ArrayList<>();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        WaitFiveSecondTest w = new WaitFiveSecondTest();
        final Object lock = new Object();
        new Thread(() -> {
            System.out.println("t2启动鸟");
            synchronized (lock) {
                if (w.size() != 5) {
                    try {
                        lock.wait();          //1、t2先执行，元素个数还没有到5的时候wait（）等待，释放锁给t1。
                        // 2、直到t1wait（）释放了锁之后，t2重新拿到锁执行
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2执行结束鸟");
                lock.notify();          //3、t2执行完了之后，需要唤醒t1，直到t2执行完之后锁释放。t1拿到继续添加元素
            }
        }, "t2").start();
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t1启动鸟");      //t1拿到锁了开始启动执行添加元素
                for (int i = 0; i < 10; i++) {
                    w.add(new Object());
                    System.out.println("add" + i);
                    if (w.size() == 5) {         //t1往容器里面添加元素到5的时候
                        lock.notify();       //notify唤醒t2.但是notify不会释放锁，所以t2还不能执行
                        try {
                            lock.wait();     //那么需要让t1释放锁，让t2拿到。直到t2执行完，并唤醒t1，t1继续执行
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("t1执行结束鸟");
            }
        }, "t1").start();
    }
}
