package aqs;


import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Mutex implements Lock, Serializable {

    private static class Sync extends AbstractQueuedSynchronizer {

        //判断是否同步状态已经被占用了
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        @Override
        public boolean tryAcquire(int acquires) {
            if (compareAndSetState(0, 1)) {//CAS操作获取锁状态
                setExclusiveOwnerThread(Thread.currentThread());//将当前线程设置为获取同步状态的线程
                return true;
            }
            return false;
        }

        //释放锁操作
        @Override
        protected boolean tryRelease(int releases) {
            if (getState() == 0) {//当前同步状态值为0代表已经释放
                setExclusiveOwnerThread(null);
                setState(0);
                return true;
            }
            return false;
        }
    }


    @Override
    public void lock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}