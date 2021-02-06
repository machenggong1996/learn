# redis分布式锁

* [Redis分布式锁的实现原理](https://juejin.cn/post/6844903717641142285)

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