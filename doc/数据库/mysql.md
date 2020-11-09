# Mysql

### union 和 union all

Union：对两个结果集进行并集操作，不包括重复行，同时进行默认规则的排序；

Union All：对两个结果集进行并集操作，包括重复行，不进行排序；

### sql

[sql优化](https://www.cnblogs.com/wangqingming/p/9656999.html)

### sql练习

查询班级成绩前三名

[班级成绩前三名](https://blog.csdn.net/qq_35119422/article/details/81941696?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~all~first_rank_v2~rank_v28-2-81941696.nonecase&utm_term=mysql%E7%8F%AD%E7%BA%A7%E6%88%90%E7%BB%A9%E6%9F%A5%E8%AF%A2&spm=1000.2123.3001.4430)

```sql
SELECT
	e1.* 
FROM
	student e1 
WHERE
	( SELECT count( 1 ) FROM student e2 WHERE e2.cno = e1.cno AND e2.score >= e1.score ) <= 3 
ORDER BY
	cno,
	score DESC;
```

采用自关联查询 e2中大于等于85的为1个 e2中大于等于80的为两个

### mysql索引覆盖与回表

[索引覆盖与回表](https://www.jianshu.com/p/8991cbca3854)

InnoDB主键索引为聚簇索引且每张表只有一个聚簇索引，由主键直接定位到行记录，其他索引为非聚簇索引，
查询时先定位到主键然后再通过主键获取行记录，称为回表

覆盖索引使用聚合索引实现，可以防止回表

### 事务一致性理解分析

致性是对数据可见性的约束，保证在一个事务中的多次操作的数据中间状态。
对其他事务不可见的。因为这些中间状态，是一个过渡状态，与事务的开始
状态和事务的结束状态是不一致的。

一致性和原子性的区别

原子性和一致性的的侧重点不同：原子性关注状态，要么全部成功，要么全部失败，不存在部分成功的状态。

而一致性关注数据的可见性，中间状态的数据对外部不可见，只有最初状态和最终状态的数据对外可见

### Mysql常见的日志种类及作用
1.redo 重做日志

　　作用：确保日志的持久性，防止在发生故障，脏页未写入磁盘。重启数据库会进行redo log执行重做，达到事务一致性

2.undo 回滚日志

　  作用：保证数据的原子性，记录事务发生之前的一个版本，用于回滚，innodb事务可重复读和读取已提交 隔离级别就是通过mvcc+undo实现

3.errlog 错误日志

　　作用：Mysql本身启动，停止，运行期间发生的错误信息

4.slow query log 慢查询日志

　　作用：记录执行时间过长的sql，时间阈值可以配置，只记录执行成功

5.bin log 二进制日志

　　作用：用于主从复制，实现主从同步

6.relay log 中继日志

　　作用：用于数据库主从同步，将主库发来的bin log保存在本地，然后从库进行回放

7.general log 普通日志

　　作用：记录数据库的操作明细，默认关闭，开启后会降低数据库性能

#### 主从读写机制原理：

1. 主库打开binlog
2. 当有增删改操作时，必须记录到主库的binlog=
3. 主库通过IO线程把binlog里的内容传给从库relay log
4. 从库sql线程负责读取relay log里的信息并应用到数据库

### 间隙锁 record lock，gap lock，next key lock

next lock = record lock + gap lock

[间隙锁](https://www.jianshu.com/p/32904ee07e56)

1. 加锁的基本单位是（next-key lock）,他是前开后闭原则
2. 插叙过程中访问的对象会增加锁
3. 索引上的等值查询--给唯一索引加锁的时候，next-key lock升级为行锁
4. 索引上的等值查询--向右遍历时最后一个值不满足查询需求时，next-key lock 退化为间隙锁
5. 唯一索引上的范围查询会访问到不满足条件的第一个值为止
