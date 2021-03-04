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

## kafka如何保证不重复消费 不丢失数据

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