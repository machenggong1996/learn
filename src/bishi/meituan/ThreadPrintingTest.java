package bishi.meituan;

import java.util.concurrent.CountDownLatch;

/**
 * @author machenggong
 * @date 2021/3/10
 * @description
 */
public class ThreadPrintingTest {
    private volatile int i = 1;
    private volatile boolean flag = true;
    CountDownLatch countDownLatch = new CountDownLatch(100);
    public void print(){
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                while (i<=100){
                    if(flag){
                        System.out.println("t1="+i);
                        i++;
                        flag = false;
                        countDownLatch.countDown();
                    }
                }
            }
        });

        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                while (i<=100){
                    if(!flag){
                        System.out.println("t2="+i);
                        i++;
                        flag = true;
                        countDownLatch.countDown();
                    }
                }
            }
        });
        A.start();
        B.start();
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPrintingTest threadPrintingTest = new ThreadPrintingTest();
        threadPrintingTest.print();
        threadPrintingTest.countDownLatch.await();
    }

}
