# Hystrix结合ThreadLocal出现的问题

* [更改Hystrix的线程池](https://blog.csdn.net/chinasi2012/article/details/105891435)
* [解决feign和hystrix集成后多线程调用导致追踪链路参数丢失问题](https://blog.csdn.net/guntun8987/article/details/126520297)

如果不重写Hystrix的线程策略会导致在Hystrix的线程中拿到旧的数据出现莫名其妙的bug

```java
public class ThreadLocalThreadPoolExecutor extends ThreadPoolExecutor {

    private static final RejectedExecutionHandler defaultHandler = new ThreadPoolExecutor.AbortPolicy();

    public ThreadLocalThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                         BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public ThreadLocalThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                         BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, defaultHandler);
    }

    @Override
    public void execute(Runnable command) {
        super.execute(TtlRunnable.get(command));
    }

}

```

```java
@Slf4j
public class ThreadLocalHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {

    @Override
    public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey, HystrixProperty<Integer> corePoolSize,
                                            HystrixProperty<Integer> maximumPoolSize, HystrixProperty<Integer> keepAliveTime, TimeUnit unit,
                                            BlockingQueue<Runnable> workQueue) {
        return this.doGetThreadPool(threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey,
                                            HystrixThreadPoolProperties threadPoolProperties) {
        return this.doGetThreadPool(threadPoolKey, threadPoolProperties);
    }

    private ThreadPoolExecutor doGetThreadPool(final HystrixThreadPoolKey threadPoolKey,
                                               HystrixProperty<Integer> corePoolSize, HystrixProperty<Integer> maximumPoolSize,
                                               HystrixProperty<Integer> keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        final ThreadFactory threadFactory = getThreadFactory(threadPoolKey);

        final int dynamicCoreSize = corePoolSize.get();
        final int dynamicMaximumSize = maximumPoolSize.get();

        if (dynamicCoreSize > dynamicMaximumSize) {
            log.error("Hystrix ThreadPool configuration at startup for : " + threadPoolKey.name()
                    + " is trying to set coreSize = " + dynamicCoreSize + " and maximumSize = " + dynamicMaximumSize
                    + ".  Maximum size will be set to " + dynamicCoreSize
                    + ", the coreSize value, since it must be equal to or greater than the coreSize value");
            return new ThreadLocalThreadPoolExecutor(dynamicCoreSize, dynamicCoreSize, keepAliveTime.get(), unit, workQueue,
                    threadFactory);
        } else {
            return new ThreadLocalThreadPoolExecutor(dynamicCoreSize, dynamicMaximumSize, keepAliveTime.get(), unit, workQueue,
                    threadFactory);
        }
    }

    private ThreadPoolExecutor doGetThreadPool(final HystrixThreadPoolKey threadPoolKey,
                                               HystrixThreadPoolProperties threadPoolProperties) {
        final ThreadFactory threadFactory = getThreadFactory(threadPoolKey);

        final boolean allowMaximumSizeToDivergeFromCoreSize = threadPoolProperties
                .getAllowMaximumSizeToDivergeFromCoreSize().get();
        final int dynamicCoreSize = threadPoolProperties.coreSize().get();
        final int keepAliveTime = threadPoolProperties.keepAliveTimeMinutes().get();
        final int maxQueueSize = threadPoolProperties.maxQueueSize().get();
        final BlockingQueue<Runnable> workQueue = getBlockingQueue(maxQueueSize);

        if (allowMaximumSizeToDivergeFromCoreSize) {
            final int dynamicMaximumSize = threadPoolProperties.maximumSize().get();
            if (dynamicCoreSize > dynamicMaximumSize) {
                log.error("Hystrix ThreadPool configuration at startup for : " + threadPoolKey.name()
                        + " is trying to set coreSize = " + dynamicCoreSize + " and maximumSize = " + dynamicMaximumSize
                        + ".  Maximum size will be set to " + dynamicCoreSize
                        + ", the coreSize value, since it must be equal to or greater than the coreSize value");
                return new ThreadLocalThreadPoolExecutor(dynamicCoreSize, dynamicCoreSize, keepAliveTime, TimeUnit.MINUTES,
                        workQueue, threadFactory);
            } else {
                return new ThreadLocalThreadPoolExecutor(dynamicCoreSize, dynamicMaximumSize, keepAliveTime, TimeUnit.MINUTES,
                        workQueue, threadFactory);
            }
        } else {
            return new ThreadLocalThreadPoolExecutor(dynamicCoreSize, dynamicCoreSize, keepAliveTime, TimeUnit.MINUTES, workQueue,
                    threadFactory);
        }
    }

    private static ThreadFactory getThreadFactory(final HystrixThreadPoolKey threadPoolKey) {
        if (!PlatformSpecific.isAppEngineStandardEnvironment()) {
            return new ThreadFactory() {
                private final AtomicInteger threadNumber = new AtomicInteger(0);

                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r,
                            "planner-common-hystrix-" + threadPoolKey.name() + "-" + threadNumber.incrementAndGet());
                    thread.setDaemon(true);
                    return thread;
                }

            };
        } else {
            return PlatformSpecific.getAppEngineThreadFactory();
        }
    }

}
```

```java
@Slf4j
public class BizIdFeignInterceptor implements RequestInterceptor {

    private static final String BIZ_ID = "bizId";

    @Override
    public void apply(RequestTemplate template) {
        try {
            UserContext userContext = UserContextHolder.getUserContext();
            if (userContext != null) {
                Integer bizId = userContext.getBizId();
                if (bizId != null) {
                    if (log.isDebugEnabled()) {
                        log.debug("feign bizIdInterceptor bizId:{}", bizId);
                    }
                    Map<String, Collection<String>> headers = template.headers();
                    if (headers.get(BIZ_ID) != null) {
                        if (log.isDebugEnabled()) {
                            log.debug("当前bizId header:{}", JSON.toJSONString(headers.get(BIZ_ID)));
                        }
                    }
                    template.header(BIZ_ID, bizId.toString());
                }
            }
        } catch (Exception e) {
        }
    }
}
```

```java
@Slf4j
@Configuration
public class HystrixConfig {

    /**
     * Hystrix切换线程池
     */
    @PostConstruct
    public void initHystrix() {
        log.info("init hystrix strategy");
        HystrixPlugins.getInstance().registerConcurrencyStrategy(
                new ThreadLocalHystrixConcurrencyStrategy());
    }

}
```
