package thread.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author machenggong
 * @date 2020/12/08
 */
public class ThreadPoolTest {

    private static ThreadFactory threadFactory = new ThreadFactory() {
        AtomicInteger incr = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            int i = incr.incrementAndGet();
            if (i > 1000) {
                incr.set(0);
                i = incr.incrementAndGet();
            }
            thread.setName("spp-io-thread-" + i);
            return thread;
        }
    };

    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                    Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors() * 2, 1,
                    TimeUnit.MINUTES, new LinkedBlockingQueue<>(), threadFactory,
                    new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            int finalI = i;
            Runnable runnable = () -> System.out.println("thread------>start:" + finalI);
            threadPoolExecutor.execute(runnable);
        }
        threadPoolExecutor.shutdown();
    }

}
