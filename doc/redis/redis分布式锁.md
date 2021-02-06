# redis分布式锁

* [Redis分布式锁的实现原理](https://juejin.cn/post/6844903717641142285)
* [可靠的分布式锁 RedLock 与 redisson 的实现](https://techlog.cn/article/list/10183625)

## 1. setnx命令问题

* setnx在做加锁操作的时候不能设置过期时间需要配合expire命令使用，但是需要加事务否则不能保证原子性
* redis在2.6.12版本之后SET 本身已经包含了设置过期时间的功能，也就是说，我们前面需要的功能只用 SET 就可以实现。

```redis_cmd
SET resource_name my_random_value NX PX 30000
```

## 2. del解锁操作

如果一个请求更新缓存的时间比较长，甚至比锁的有效期还要长，导致在缓存更新过程中，锁就失效了，此时另一个请求会获取锁，但前一个请求在缓存更新完毕的时候，如果不加以判断直接删除锁，就会出现误删除其它请求创建的锁的情况，所以我们在创建锁的时候需要引入一个随机值

```lua
if redis.call("get",KEYS[1]) == ARGV[1] then
    return redis.call("del",KEYS[1])
else
    return 0
end
```

## 3. redis px nx ex xx

* EX second ：设置键的过期时间为 second 秒。 SET key value EX second 效果等同于 SETEX key second value 。
* PX millisecond ：设置键的过期时间为 millisecond 毫秒。 SET key value PX millisecond 效果等同于 PSETEX key millisecond value 。
* NX ：只在键不存在时，才对键进行设置操作。 SET key value NX 效果等同于 SETNX key value 。
* XX ：只在键已经存在时，才对键进行设置操作

## 4. redLock 分布式锁算法

* 顺序向五个节点请求加锁
* 根据一定的超时时间来推断是不是跳过该节点
* 三个节点加锁成功并且花费时间小于锁的有效期
* 认定加锁成功
* 假设锁 30 秒过期，三个节点加锁花了 31 秒，自然是加锁失败了
* 假设我们设置有效期是 30 秒，图中超时了两个 Redis 节点。那么加锁成功的节点总共花费了 3 秒，所以锁的实际有效期是小于 27 秒的。

## 5. redisson实现redis多实例分布式锁

java 语言中，redisson 包实现了对 redlock 的封装，主要是通过 redis client 与 lua 脚本实现的，之所以使用 lua 脚本，是为了实现加解锁校验与执行的事务性。

### 5.1 唯一 ID 的生成

1. 分布式事务锁中，为了能够让作为中心节点的存储节点获悉锁的持有者，从而避免锁被非持有者误解锁，每个发起请求的 client 节点都必须具有全局唯一的 id。
2. 通常我们是使用 UUID 来作为这个唯一 id，redisson 也是这样实现的，在此基础上，redisson 还加入了 threadid 避免了多个线程反复获取 UUID 的性能损耗：

```java_method
protected final UUID id = UUID.randomUUID();
String getLockName(long threadId) {
	return id + ":" + threadId;
}
```

### 5.2 加锁逻辑

* redisson 加锁的核心代码非常容易理解，通过传入 TTL 与唯一 id，实现一段时间的加锁请求。
* 下面是可重入锁的实现逻辑：
```java_method
<T> RFuture<T> tryLockInnerAsync(long leaseTime, TimeUnit unit, long threadId, RedisStrictCommand<T> command) {
	internalLockLeaseTime = unit.toMillis(leaseTime);
	// 获取锁时向1个redis实例发送的命令 只向一个实例发命令
	return commandExecutor.evalWriteAsync(getName(), LongCodec.INSTANCE, command,
			  // 校验分布式锁的KEY是否已存在，如果不存在，那么执行hset命令（hset REDLOCK_KEY uuid+threadId 1），并通过pexpire设置失效时间（也是锁的租约时间）
			  "if (redis.call('exists', KEYS[1]) == 0) then " +
				  "redis.call('hset', KEYS[1], ARGV[2], 1); " +
				  "redis.call('pexpire', KEYS[1], ARGV[1]); " +
				  "return nil; " +
			  "end; " +
			  // 如果分布式锁的KEY已存在，则校验唯一 id，如果唯一 id 匹配，表示是当前线程持有的锁，那么重入次数加1，并且设置失效时间
			  "if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then " +
				  "redis.call('hincrby', KEYS[1], ARGV[2], 1); " +
				  "redis.call('pexpire', KEYS[1], ARGV[1]); " +
				  "return nil; " +
			  "end; " +
			  // 获取分布式锁的KEY的失效时间毫秒数
			  "return redis.call('pttl', KEYS[1]);",
			  // KEYS[1] 对应分布式锁的 key；ARGV[1] 对应 TTL；ARGV[2] 对应唯一 id
				Collections.<Object>singletonList(getName()), internalLockLeaseTime, getLockName(threadId));
}
```

### 5.3 释放锁逻辑

```java_method
protected RFuture<Boolean> unlockInnerAsync(long threadId) {
	// 向5个redis实例都执行如下命令
	return commandExecutor.evalWriteAsync(getName(), LongCodec.INSTANCE, RedisCommands.EVAL_BOOLEAN,
			// 如果分布式锁 KEY 不存在，那么向 channel 发布一条消息
			"if (redis.call('exists', KEYS[1]) == 0) then " +
				"redis.call('publish', KEYS[2], ARGV[1]); " +
				"return 1; " +
			"end;" +
			// 如果分布式锁存在，但是唯一 id 不匹配，表示锁已经被占用
			"if (redis.call('hexists', KEYS[1], ARGV[3]) == 0) then " +
				"return nil;" +
			"end; " +
			// 如果就是当前线程占有分布式锁，那么将重入次数减 1
			"local counter = redis.call('hincrby', KEYS[1], ARGV[3], -1); " +
			// 重入次数减1后的值如果大于0，表示分布式锁有重入过，那么只设置失效时间，不删除
			"if (counter > 0) then " +
				"redis.call('pexpire', KEYS[1], ARGV[2]); " +
				"return 0; " +
			"else " +
				// 重入次数减1后的值如果为0，则删除锁，并发布解锁消息
				"redis.call('del', KEYS[1]); " +
				"redis.call('publish', KEYS[2], ARGV[1]); " +
				"return 1; "+
			"end; " +
			"return nil;",
			// KEYS[1] 表示锁的 key，KEYS[2] 表示 channel name，ARGV[1] 表示解锁消息，ARGV[2] 表示 TTL，ARGV[3] 表示唯一 id
			Arrays.<Object>asList(getName(), getChannelName()), LockPubSub.unlockMessage, internalLockLeaseTime, getLockName(threadId));
}
```

### 5.4 redisson RedLock 的使用

```java_method
Config config = new Config();
config.useSentinelServers().addSentinelAddress("127.0.0.1:6369","127.0.0.1:6379", "127.0.0.1:6389")
		.setMasterName("masterName")
		.setPassword("password").setDatabase(0);
RedissonClient redissonClient = Redisson.create(config);
RLock redLock = redissonClient.getLock("REDLOCK_KEY");
boolean isLock;
try {
	isLock = redLock.tryLock(500, 10000, TimeUnit.MILLISECONDS);
	if (isLock) {
		//TODO if get lock success, do something;
	}
} catch (Exception e) {
} finally {
	redLock.unlock();
}
```
