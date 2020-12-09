# innodb四种隔离级别实现原理

* [一文讲清楚MySQL事务隔离级别和实现原理，开发人员必备知识点](https://www.cnblogs.com/fengzheng/p/12557762.html)
* [MySql的隔离级别和锁的关系](https://www.cnblogs.com/mengbin0546/p/10987679.html)

## 1. 读未提交 （READ UNCOMMITTED）

读未提交隔离级别是不加锁的，所以它的性能是最好的，没有加锁、解锁带来的性能开销

## 2. 读提交 （READ COMMITTED）

* 每个 select 语句都有自己的一份快照，而不是一个事务一份，所以在不同的时刻，查询出来的数据可能是不一致的。
* 读提交解决了脏读的问题，但是无法做到可重复读，也没办法解决幻读。

## 3. 可重复读 （REPEATABLE READ）MVCC

读执行select的第一份快照

## 4. 串行化 （SERIALIZABLE）

读的时候加共享锁，也就是其他事务可以并发读，但是不能写。写的时候加排它锁，其他事务不能并发写也不能并发读。
