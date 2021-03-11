package thread;

import java.util.concurrent.Semaphore;

/**
 * @author machenggong
 * @date 2021/3/10
 * @description
 */
public class ShunxuPrint {

    public Semaphore seam_first_two = new Semaphore(0);

    public Semaphore seam_two_second = new Semaphore(0);

    public ShunxuPrint() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        System.out.println(111);
        printFirst.run();
        seam_first_two.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        System.out.println(222);
        seam_first_two.acquire();
        printSecond.run();
        seam_two_second.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        System.out.println(333);
        seam_two_second.acquire();
        printThird.run();
    }

    public static void main(String[] args) throws InterruptedException {
        ShunxuPrint shunxuPrint = new ShunxuPrint();
        Runnable first = () -> System.out.println(1);
        Runnable second = () -> System.out.println(2);
        Runnable third = () -> System.out.println(3);
        new Thread(() -> {
            try {
                shunxuPrint.second(second);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                shunxuPrint.first(first);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                shunxuPrint.third(third);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(1000);
    }

}
