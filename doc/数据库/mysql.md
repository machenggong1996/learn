# Mysql

### union 和 union all

Union：对两个结果集进行并集操作，不包括重复行，同时进行默认规则的排序；

Union All：对两个结果集进行并集操作，包括重复行，不进行排序；

### sql

[sql优化](https://www.cnblogs.com/wangqingming/p/9656999.html)

### sql练习

查询班级成绩前三名

[班级成绩前三名](https://blog.csdn.net/qq_35119422/article/details/81941696?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~all~first_rank_v2~rank_v28-2-81941696.nonecase&utm_term=mysql%E7%8F%AD%E7%BA%A7%E6%88%90%E7%BB%A9%E6%9F%A5%E8%AF%A2&spm=1000.2123.3001.4430)

https://blog.csdn.net/weixin_39634985/article/details/115927239

```sql
SELECT e1.*
FROM student e1
WHERE (SELECT count(1) FROM student e2 WHERE e2.cno = e1.cno AND e2.score >= e1.score) <= 3
ORDER BY cno,
         score DESC;
```

考虑并列情况

```sql
select a.class, a.name, a.score
from sc a
where EXISTS (select count(*)
              from (select distinct class, score from sc) b
              where a.class = b.class
                and a.score <= b.score
              group by b.class
              HAVING COUNT(*) <= 3)
order by a.class, a.score desc;
```

采用自关联查询 e2中大于等于85的为1个 e2中大于等于80的为两个

### mysql索引覆盖与回表

[索引覆盖与回表](https://www.jianshu.com/p/8991cbca3854)

InnoDB主键索引为聚簇索引且每张表只有一个聚簇索引，由主键直接定位到行记录，其他索引为非聚簇索引， 查询时先定位到主键然后再通过主键获取行记录，称为回表

覆盖索引使用聚合索引实现，可以防止回表

### 事务一致性理解分析

致性是对数据可见性的约束，保证在一个事务中的多次操作的数据中间状态。 对其他事务不可见的。因为这些中间状态，是一个过渡状态，与事务的开始 状态和事务的结束状态是不一致的。

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

## mysql开发约束

* [MySQL开发约束](https://blog.csdn.net/xcy1193068639/article/details/109263167)

## mysql为什么不用B树 而用B+树

* [mysql为什么不用b树_MySQL用B+树(而不是B树)做索引的原因](https://blog.csdn.net/weixin_29563497/article/details/113284907)
* [B（B-）树，B+树，B树和B+树的区别，B树和B+树的优点](https://blog.csdn.net/weixin_42228338/article/details/97684517)

1. B+树的磁盘读写代价更低：B+树的内部节点并没有指向关键字具体信息的指针，因此其内部节点相对B树更小，如果把所有同一内部节点的关键字存放在同一盘块中，那么盘块所能容纳的关键字数量也越多，一次性读入内存的需要查找的关键字也就越多，相对IO读写次数就降低了。
2. B+树的查询效率更加稳定：由于非终结点并不是最终指向文件内容的结点，而只是叶子结点中关键字的索引。所以任何关键字的查找必须走一条从根结点到叶子结点的路。所有关键字查询的路径长度相同，导致每一个数据的查询效率相当。
3. B+树更便于遍历：由于B+树的数据都存储在叶子结点中，分支结点均为索引，方便扫库，只需要扫一遍叶子结点即可，但是B树因为其分支结点同样存储着数据，我们要找到具体的数据，需要进行一次中序遍历按序来扫，所以B+树更加适合在区间查询的情况，所以通常B+树用于数据库索引。
4. B+树更适合基于范围的查询：B树在提高了IO性能的同时并没有解决元素遍历的我效率低下的问题，正是为了解决这个问题，B+树应用而生。B+树只需要去遍历叶子节点就可以实现整棵树的遍历。而且在数据库中基于范围的查询是非常频繁的，而B树不支持这样的操作或者说效率太低。

## mysql为什么建议使用自增主键

* 使用自增 id 可以避免页分裂
* 使用业务id,当往一个快满或已满的数据页中插入数据时，新插入的数据会将数据页写满，mysql 就需要申请新的数据页，并且把上个数据页中的部分数据挪到新的数据页上。这就造成了页分裂，这个大量移动数据的过程是会严重影响插入效率的

## mysql索引失效原因

1. 全值匹配
2. 最佳左前缀法则
3. 不在索引列上做任何操作
4. mysql在使用不等于（！=或<>）时候，无法使用索引导致全表扫描
5. is null，is not null也无法使用索引
6. like以通配符开头，mysql索引失效会编程全表扫描的操作
7. 字符串不加单引号，索引失效
8. 少用or，用它连接时索引会失效
9. 随着表的增长，where条件出来的数据太多，大于15%，使得索引失效（会导致CBO计算走索引花费大于走全表）

## mysql最左原则 组合索引才会有最左原则概念

* [mysql联合索引的生效规则](https://www.cnblogs.com/mintsd/p/13062308.html)
* [Mysql最左匹配原则](https://blog.csdn.net/sinat_41917109/article/details/88944290)

## mysql深分页

* [mysql深分页](https://blog.csdn.net/qq_43686863/article/details/135673673)

延迟关联 减少回表

从回表80100条数据变成回表100条数据

```sql
select *
      from wide_table
      inner join (select id from wide_table limit 80000, 100) as wt
      on wide_table.id = wt.id;
```
https://blog.csdn.net/weixin_44700876/article/details/135517676 四种方法

