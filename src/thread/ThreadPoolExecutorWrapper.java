package thread;

import java.util.concurrent.*;

/**
 * @author machenggong
 * @date 2021/3/26
 * @description 线程池execute方法重写
 */
public class ThreadPoolExecutorWrapper extends ThreadPoolExecutor {

    public ThreadPoolExecutorWrapper(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                     BlockingQueue<Runnable> workQueue, ThreadFactory factory,
                                     RejectedExecutionHandler rejectedExecutionHandler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, factory, rejectedExecutionHandler);
    }

    @Override
    public void execute(final Runnable r) {
        try {
            super.execute(() -> {
                try {
                    r.run();
                } catch (Exception e) {

                    throw e;
                } finally {

                }
            });
        } finally {

        }
    }

}
