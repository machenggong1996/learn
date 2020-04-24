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

