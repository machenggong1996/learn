package aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock与Condition接口示例
 */
public class LockConditionDemo {

    //存储地方
    class Depot {
        private int capacity;
        private int size;
        private Lock lock;
        private Condition fullCondition;
        private Condition emptyCondition;

        public Depot(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.lock = new ReentrantLock();
            this.fullCondition = lock.newCondition();
            this.emptyCondition = lock.newCondition();
        }

        //生产操作
        public void produce(int newSize) throws InterruptedException {
            int left = newSize;
            lock.lock();
            try {
                while (left > 0) {
                    //代表超过了容量就不能再生产了
                    while (size >= capacity) {
                        fullCondition.await();//进行等待处理
                    }
                    //获取实际生产的数量(及库存中新增的数量)
                    //如果库存+要生产的大于了总的容量那么新增的就是总容量的数量相减
                    int inc = (size + left) > capacity ? (capacity - size) : left;
                    size += inc;
                    left -= inc;
                    System.out.println(Thread.currentThread().getName() + "------left剩余" + left + "------size容量" + size + "-------inc增长" + inc);
                    emptyCondition.signal();
                }
            } finally {
                lock.unlock();//解锁
            }
        }

        //消费操作
        public void consume(int newSize) throws InterruptedException {
            lock.lock();
            try {
                int left = newSize;
                while (left > 0) {
                    //库存为0等待生产者进行生产的操作
                    while (size <= 0) {
                        emptyCondition.await();
                    }
                    int dec = (size < left) ? size : left;
                    size -= dec;
                    left -= dec;
                    System.out.println(Thread.currentThread().getName() + "-------left剩余" + left + "-------size容量" + size + "--------减少量dec" + dec);
                    fullCondition.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    //生产者
    class Producer {
        private Depot depot;

        public Producer(Depot depot) {
            this.depot = depot;
        }

        //往存储地方生产
        public void produce(final int newSize) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        depot.produce(newSize);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    //消费者
    class Customer {
        private Depot depot;

        public Customer(Depot depot) {
            this.depot = depot;
        }

        //进行消费
        public void consume(final int newSize) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        depot.consume(newSize);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    public static void main(String[] args) {
        Depot depot = new LockConditionDemo().new Depot(100);
        Producer producer = new LockConditionDemo().new Producer(depot);
        Customer customer = new LockConditionDemo().new Customer(depot);
        producer.produce(60);
        producer.produce(120);
        customer.consume(90);
        customer.consume(150);
        producer.produce(110);
    }
}