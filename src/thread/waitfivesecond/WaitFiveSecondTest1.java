package thread.waitfivesecond;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by machenggong on 2020/1/2.
 */
public class WaitFiveSecondTest1 {

    volatile List list = new ArrayList<>();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        WaitFiveSecondTest1 w = new WaitFiveSecondTest1();
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            System.out.println("t2启动鸟");
            if (w.size() != 5) {
                try {
                    latch.await();     //等着开门，await不需要锁定任何对象。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2执行结束鸟");

        }, "t2").start();

        new Thread(() -> {
            System.out.println("t1启动鸟");
            for (int i = 0; i < 10; i++) {
                w.add(new Object());
                System.out.println("add" + i);
                if (w.size() == 5) {
                    latch.countDown();
                }
            }
            System.out.println("t1执行结束鸟");
        }, "t1").start();

    }
}
