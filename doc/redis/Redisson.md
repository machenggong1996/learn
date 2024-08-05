# Redisson

## Redisson分布式锁

* [Redisson分布式锁原理](https://www.cnblogs.com/hanease/p/15922813.html)

加锁lua脚本 org.redisson.RedissonLock#tryLockInnerAsync
解锁使用lua脚本 发布订阅 org.redisson.RedissonLock#unlockInnerAsync

1. RedissonRedLock 红锁 基于RedissonMultiLock实现 [红锁实现原理](https://way2j.com/a/1338)
2. RedissonMultiLock 联锁

### 看门狗

* [看门狗原理](https://blog.csdn.net/qq_37436172/article/details/130656960)

1. 不指定leaseTime参数的时候watch dog机制会启动
2. watch dog 在当前节点存活时每10s给分布式锁的key续期 30s，这是不指定lockWatchdogTimeout参数值默认30秒
3. watchdog 会每 lockWatchdogTimeout/3时间，去延时
4. watchdog 通过 类似netty的 Future功能来实现异步延时，watchdog 最终还是通过 lua脚本来进行延时
5. RedissonLock#tryAcquireAsync-RedissonBaseLock#scheduleExpirationRenewal-RedissonBaseLock#renewExpiration-RedissonBaseLock#renewExpirationAsync 方法自动延期

lua脚本更新过期时间
```
protected CompletionStage<Boolean> renewExpirationAsync(long threadId) {
        return evalWriteAsync(getRawName(), LongCodec.INSTANCE, RedisCommands.EVAL_BOOLEAN,
                "if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then " +
                        "redis.call('pexpire', KEYS[1], ARGV[1]); " +
                        "return 1; " +
                        "end; " +
                        "return 0;",
                Collections.singletonList(getRawName()),
                internalLockLeaseTime, getLockName(threadId));
    }
```





