package thread.sortthread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by mache on 2019/11/5.
 */
public class SortThread1 {

    private int flag = 1;
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void printA() {
        try {
            lock.lock();
            if (flag != 1) {
                try {
                    conditionA.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(Thread.currentThread().getName());
            flag = 2;
            conditionB.signal();
        } finally {
            lock.unlock();
        }

    }

    public void printB() {
        try {
            lock.lock();
            if (flag != 2) {
                try {
                    conditionB.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(Thread.currentThread().getName());
            flag = 3;
            conditionC.signal();
        } finally {
            lock.unlock();
        }

    }

    public void printC() {
        try {
            lock.lock();
            if (flag != 3) {
                try {
                    conditionC.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(Thread.currentThread().getName());
            flag = 1;
            conditionA.signal();
        } finally {
            lock.unlock();
        }
    }

}
