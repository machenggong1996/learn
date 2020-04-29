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


