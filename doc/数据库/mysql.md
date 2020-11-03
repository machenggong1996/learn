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