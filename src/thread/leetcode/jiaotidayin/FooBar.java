package thread.leetcode.jiaotidayin;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author machenggong
 * @since 2024/12/4
 */
public class FooBar {

    /**
     * https://www.cnblogs.com/larry1024/p/17986059 多线程交替打印
     */

    static class Printer {
        private final Object lock = new Object();
        public int count = 0;

        public void print(int n, int target, char content) {
            for (int i = 0; i < n; ) {
                synchronized (lock) {
                    System.out.println(content + "加锁");
                    while (count % 3 != target) {
                        try {
                            // wait之后别的线程可以加锁
                            lock.wait();
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                    System.out.println(content);
                    count++;
                    i++;
                    lock.notifyAll();
                }
            }
        }

        public void print() {
            new Thread(() -> {
                print(10, 1, 'B');
            }).start();
            new Thread(() -> {
                print(10, 0, 'A');
            }).start();
            new Thread(() -> {
                print(10, 2, 'C');
            }).start();
        }
    }

    static class Printer1 {
        private final Lock lock = new ReentrantLock();
        private volatile int count = 0;

        public void print(int n, int target, char content) {
            for (int i = 0; i < n; ) {
                lock.lock();
                try {
                    while (count % 3 == target) {
                        System.out.print(content);
                        count++;
                        i++;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    lock.unlock();
                }
            }
        }

        public void print() {
            new Thread(()-> {print(10, 0, 'A');}).start();
            new Thread(()-> {print(10, 1, 'B');}).start();
            new Thread(()-> {print(10, 2, 'C');}).start();
        }
    }

    static class ConditionPrinter {
        private final Lock lock = new ReentrantLock(); // 非公平锁
        private volatile int count = 0;
        private final int threadNumber = 10;
        private final Condition condition1 = lock.newCondition();
        private final Condition condition2 = lock.newCondition();
        private final Condition condition3 = lock.newCondition();

        public void print(int target, String content, Condition current, Condition next) {
            for (int i = 0; i < threadNumber; i++) {
                lock.lock();
                try {
                    // 执行临界区代码前判断：防止锁被不满足条件的线程抢占
                    while (count % 3 != target) {
                        current.await(); // 条件等待并释放锁
                    }
                    System.out.print(content);
                    count++; // 注意：这不是一个原子操作
                    next.signal(); // 唤醒一个等待该条件的线程
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    lock.unlock();
                }
            }
        }

        public void print() {
            new Thread(() -> {print(0, "A", condition1, condition2);}).start();
            new Thread(() -> {print(1, "B", condition2, condition3);}).start();
            new Thread(() -> {print(2, "C", condition3, condition1);}).start();
        }
    }

    static class ConditionPrinterEnhance {
        private final Lock lock = new ReentrantLock(true);
        private final Condition condition = lock.newCondition();
        private volatile int count = 0;
        private final int threadNumber = 10;
        public void print(int target, char content) {
            lock.lock();
            try {
                for (int i = 0; i < threadNumber; i++) {
                    while (count % 3 != target) {
                        condition.await();
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + content + " " + count);
                    count++;
                    condition.signal();
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                lock.unlock();
            }
        }

        public void print() {
            char content = 'A';
            for (int i = 0; i < 3; i++) {
                final int k = i;
                new Thread(() -> print( k, (char) (content + k))).start();
            }
        }
    }

    public static void main(String[] args) {
        new ConditionPrinter().print();
    }

}
