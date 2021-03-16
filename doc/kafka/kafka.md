# Kafka

* [kafka内核](https://zhuanlan.zhihu.com/p/87558802)
* [Kafka-LEO和HW概念及更新流程](https://www.cnblogs.com/youngchaolin/p/12641463.html)
* [kafka基本原理介绍，以及重新选举，replica复制机制，isr等](https://blog.csdn.net/dshf_1/article/details/82467558)

## kafka基础概念

* producer：消息生产者，发布消息到kafka集群的终端或服务。
* broker kafaka集群中包含的服务器
* topic 每条发布到kafka集群的消息属于的类别，即kafka是面向topic的。
* partition partition是物理上的概念，每个topic包含一个或多个partition，kafka分配的单位是partition。
* Consumer 消费者，从kafka集群中消费消息的终端或服务。
* consumer group high-level consumer api中，每个consumer都属于一个consumer group，每条消息只能被consumer
* group 中的一个consumer消费，但可以被多个consumer group 消费
* replica partition的副本，保障partition的高可用
* leader replica中的一个角色，producer和consumer只跟leader交互
* follower replica中的一个角色，从leader中复制数据
* controller 不知道大家有没有思考过一个问题，就是kafka集群中某个broker宕机之后，是谁负责感知到他的宕机，以及 负责进行leader
  partition的选举？如果您在kafka集群中新加入一些机器，此时谁来负责把集群中的数据 尽心负载均衡的迁移，包括你的kafka集群的各种元数据，比如说每台机器有哪些partition，谁是leader，谁
  是follower，是谁来管理呢？比如你要删除一个topic，那么背后的各种partition如何删除，是谁来控制？ 还有就是比如kafka集群扩容加入一个新的broker，是谁负责监听这个broker的加入？

## isr

kafka不是完全同步，也不是完全异步，是一种特殊的ISR（In Sync Replica）

1.leader会维持一个与其保持同步的replica集合，该集合就是ISR，每一个partition都有一个ISR，它时有leader动态维护。

2.我们要保证kafka不丢失message，就要保证ISR这组集合存活（至少有一个存活），并且消息commit成功。

## HW,LEO

## 消费问题

kafka消费者只会消费leader数据，一个分区只能被一个消费者消费

## 提交问题

kafka可以自动提交，可以通过设置变为手动提交，如果执行时间过长没有提交，kafka会认为消费者掉线，默认5分钟，可以通过设置max.poll.interval.ms属性 解决这个问题

## kafka 高吞吐实现

* [Kafka 高吞吐率的实现](https://blog.csdn.net/u010039929/article/details/77934910)

### 1. 顺序读写
kafka的消息是不断追加到文件中的，这个特性使kafka可以充分利用磁盘的顺序读写性能顺序读写不需要硬盘磁头的寻道时间，只需很少的扇区旋转时间，所以速度远快于随机读写

### 2. 零拷贝
先简单了解下文件系统的操作流程，例如一个程序要把文件内容发送到网络,这个程序是工作在用户空间，文件和网络socket属于硬件资源，两者之间有一个内核空间在操作系统内部，整个过程为：

### 3. 文件分段
kafka的队列topic被分为了多个区partition，每个partition又分为多个段segment，所以一个队列中的消息实际上是保存在N多个片段文件中通过分段的方式，每次文件操作都是对一个小文件的操作，非常轻便，同时也增加了并行处理能力

### 4. 批量发送
Kafka允许进行批量发送消息，先将消息缓存在内存中，然后一次请求批量发送出去比如可以指定缓存的消息达到某个量的时候就发出去，或者缓存了固定的时间后就发送出去如100条消息就发送，或者每5秒发送一次这种策略将大大减少服务端的I/O次数

### 5. 数据压缩
Kafka还支持对消息集合进行压缩，Producer可以通过GZIP或Snappy格式对消息集合进行压缩压缩的好处就是减少传输的数据量，减轻对网络传输的压力Producer压缩之后，在Consumer需进行解压，虽然增加了CPU的工作，但在对大数据处理上，瓶颈在网络上而不是CPU，所以这个成本很值得

## kafka如何保证不重复消费 不丢失数据

1. 代码层面或者数据库层面保证幂等性，已经存在的数据进行更新，数据库唯一主键
2. 从生产者 消费者 kafka本身 三个方向处理
* [kafka 如何保证数据不丢失](https://www.cnblogs.com/MrRightZhao/p/11498952.html)

## kafka落盘方式

异步批量写入磁盘

## kafka重平衡过程（美团面试题）

* [Kafka 重平衡 全流程解析](https://blog.csdn.net/q322625/article/details/101461087)
* [Kafka详解（八）消费者组重平衡全流程](https://blog.csdn.net/fedorafrog/article/details/104099674)

* 给消费组每个消费者分配消费任务的过程，

### 1. kafka重平衡的时机

* 订阅主题数发生变化
* 主题分区发生变化
* 消费端的消费者组成员变化
  - 消费者处理消息超时
  - 心跳超时
  
### 2. Kafka的心跳机制 与 Rebalance

* 重平衡过程是靠消费者端的心跳线程（Heartbeat Thread）通知到其他消费者实例的

### 3. 消费者组重平衡流程

1. 加入组 当组内成员加入组时，它会向协调者发送 JoinGroup 请求，领导者消费者的任务是收集所有成员的订阅信息，然后根据这些信息，制定具体的分区消费分配方案。
2. SyncGroup请求 SyncGroup 请求的主要目的，就是让协调者把领导者制定的分配方案下发给各个组内成员。当所有成员都成功接收到分配方案后，消费者组进入到 Stable 状态，即开始正常的消费工作

### 4. Broker端（协调者Coordinator）重平衡流程

* 新成员加入场景
* 组成员主动离组场景
* 组成员崩溃离组场景
* 重平衡时协调者对组内成员提交位移的处理

### 5. 总结

* 基本流程就是 Coordinator 感知到 消费者组的变化，
* 然后在心跳的过程中发送重平衡信号通知各个消费者离组，
* 然后消费者重新以 JoinGroup 方式加入 Coordinator，并选出Consumer Leader。
* 当所有消费者加入 Coordinator，
* Consumer Leader会根据 Coordinator给予的分区信息给出分区方案。
* Coordinator 将该方案以 SyncGroup 的方式将该方案执行下去，通知各个消费者，
这样就完成了一轮 重平衡了
  
## kafka使用的通讯协议

* 基于TCP

## 从CAP角度说明kafka不能读写分离

[干货|为什么Kafka不支持读写分离](https://blog.csdn.net/zl1zl2zl3/article/details/87982038)

kafka是CAP中的 CA 一致性 可用性 不满足分区容错性 所以不能读写分离

## kafka启动及基本操作命令

进入mac下面文件夹 /usr/local/Cellar/kafka/2.5.0/libexec/bin

启动zookeeper nohup sh zookeeper-server-start.sh ../config/zookeeper.properties > zklog.log 2>&1 &

启动kafka nohup sh kafka-server-start.sh ../config/server.properties > kafka.log 2>&1 &

创建topic sh kafka-topics.sh --create --zookeeper localhost:2181 --partitions 2 --replication-factor 1 --topic test1
输出：Created topic "test1".

查看topic sh kafka-topics.sh --list --zookeeper localhost:2181

创建生产者 sh kafka-console-producer.sh --broker-list localhost:9092 --topic test1

创建消费者 sh kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test1 --from-beginning

查看topic详情 sh kafka-topics.sh --describe --zookeeper localhost:2181 --topic test1

查看消费情况 sh kafka-consumer-groups.sh --describe --bootstrap-server localhost:9092 --group testGroup

调整topic分区数 kafka-topics.sh --alter --zookeeper localhost:2181 --topic testTopic --partitions 8

sh kafka-consumer-groups.sh --describe --bootstrap-server 10.10.0.142:9093 --group testGroup

sh kafka-topics.sh --describe --zookeeper 10.10.0.142:2181 --topic reflection_sync

sh kafka-topics.sh --alter --zookeeper localhost:2181 --topic testTopic1 --partitions 2