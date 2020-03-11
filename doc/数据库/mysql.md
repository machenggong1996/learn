# Mysql

### union 和 union all

Union：对两个结果集进行并集操作，不包括重复行，同时进行默认规则的排序；

Union All：对两个结果集进行并集操作，包括重复行，不进行排序；

### sql

[sql优化](https://www.cnblogs.com/wangqingming/p/9656999.html)

### sql练习

查询班级成绩前三名

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