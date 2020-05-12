#spark 学习

## 执行jar
bin/spark-submit \
--class org.apache.spark.examples.SparkPi \
--total-executor-cores 2 \
./examples/jars/spark-examples_2.11-2.4.5.jar \
100

bin/spark-shell

## spark scala word count

```
sc.textFile("input").flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).collect()
```
res8: Array[(String, Int)] = Array((scala,2), (hello,4), (world,1), (spark,1))

bin/spark-submit \
--class com.spark.App \
spark-scala-1.0-SNAPSHOT.jar

next p12

## 分区

spark分区与启动参数local有关，和cpu内核数和2的最大值

读取文件生成RDD可以传分区数

读取文件分区数为最小分区，使用的事hadoop分区规则

5/2 2,2,1 分区数是2但是分三组

按照字节数分区 回车换行都占字节数

## RDD算子

### 1.map(func)

### 2.mapPartitions

将分区拆开 对每个分区进行遍历，效率优于map，减少发送到执行器的交互次数，但是处理数据过大可能会导致OOM

### 3.mapPartitionsWithIndex

数据带分区号

### Driver & Executor

1.创建spark应用的叫Driver 也是spark上下文对象
2.Executor执行器用于接收任务并执行任务

所有RDD算子的计算功能都是由Executor执行

### flatMap(func)

### glom

1.将每一个分区形成一个数组

### Distinct

会进行shuffle洗牌操作，一个分区的数据打乱重组到其他分区

### coalesce 缩减分区数

实际上是合并分区，不会进行shuffle

### repartition 重新分区

### groupByKey和produceByKey

produceByKey会combine预聚合 groupByKey不会

### AggregateByKey

两部分参数，scala称为柯里化函数，zeroValue为初始化值

seqOp分区内部迭代计算

comOp分区间迭代计算

### FoldByKey

FoldByKey使用与AggregateByKey相同实现但是分区内和分区间使用同一函数计算

### CombineByKey


