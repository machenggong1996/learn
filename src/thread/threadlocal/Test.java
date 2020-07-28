package thread.threadlocal;

import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            TestContext.start();
            TestContext.get().setVal("val--1");

            try {
                TimeUnit.SECONDS.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.err.println("thread1: " + TestContext.get().getVal());
            TestContext.remove();
        });

        Thread thread2 = new Thread(() -> {
            TestContext.start();
            TestContext.get().setVal("val--2");

            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.err.println("thread2: " + TestContext.get().getVal());
            TestContext.remove();
        });

        Thread thread3 = new Thread(() -> {
            TestContext.start();
            TestContext.get().setVal("val--3");

            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.err.println("thread3: " + TestContext.get().getVal());
            TestContext.remove();
        });

        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        thread2.start();
        TimeUnit.SECONDS.sleep(1);
        thread3.start();
    }
}