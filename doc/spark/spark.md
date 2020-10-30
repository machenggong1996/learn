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

两部分参数，scala称为柯里化函数， zeroValue为初始化值

seqOp分区内部迭代计算

comOp分区间迭代计算

### FoldByKey

FoldByKey使用与AggregateByKey相同实现但是分区内和分区间使用同一函数计算

### CombineByKey

### 累加器

只写变量，共享变量累加数据

## RDD


### 定义

1. 数据集：存储数据的计算逻辑
2. 分布式：从不同的网络节点取数据，数据来源，计算，存储都是分布式的
3. 弹性：
  * 血缘：spark可以通过特殊的处理方案简化依赖关系，
  * 计算：基于内存计算速度快，可以在内存和磁盘间切换
  * 分区：创建分区后可以通过算子改变分区
  * 容错：运算时如果过发生错误将会进行重试容错处理
4. spark数量：
  * executor：默认两个，可以通过提交参数设定
  * partition：默认情况下读取文件采用hadoop的切片规则，读取内存根据特定算法进行设定，可以特定算子进行分区，多阶段场合取决于上一阶段最后shuffle最后分区数
  * stage：1（resultStage）+shuffle依赖数量（shuffleMapStage），划分目的是执行任务的等待
  * task：原则上一个分区就是一个任务，实际上可以动态调整
### 创建

1. 内存中创建
2. 存储中创建，文件中
3. 从其他RDD中创建

### 属性

1. 分区
2. 依赖关系
3. 分区器
4. 优先属性
5. 计算函数

### 使用

1. 转换算子
  * 单value
  * 双value
  * K-V

2. 行动算子 触发job执行

## 广播变量 分布式只读变量

## 累加器 分布式只写变量

## sparkSQL

### dataFrame dataSet RDD三者关系

类似于数据表二维表格，dataSet相当于对象数据

RDD增加结构变为DataFrame，DataFrame增加类和属性变为DataSet

RDD toDF方法可以将RDD转为DataFrame

三者可以通过函数相互转换 as toDF toDS rdd
