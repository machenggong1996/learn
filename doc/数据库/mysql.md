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