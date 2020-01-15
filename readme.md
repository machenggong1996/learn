# 学习计划

## 算法

## redis

### 数据类型

### 高级特性

#### 哨兵机制

[哨兵机制](https://www.cnblogs.com/bingshu/p/9776610.html)

#### 一致性哈希

[一致性哈希](https://blog.csdn.net/bntX2jSQfEHy7/article/details/79549368)

## 源码

### spring源码回顾

### springboot源码学习

`https://blog.csdn.net/woshilijiuyi/article/details/82219585`

#### 启动流程

第一步：获取并启动监听器
第二步：构造容器环境
第三步：创建容器
第四步：实例化SpringBootExceptionReporter.class，用来支持报告关于启动的错误
第五步：准备容器
第六步：刷新容器
第七步：刷新容器后的扩展接口

## java

### 多线程

### 常见笔试题

### 设计模式

### 常见面试题

1. hashmap
```https://www.jianshu.com/p/e2f75c8cce01```

* jdk1.7 头结点插入,Entry<K,V>存储数据,resize过程中get方法会死循环
* jdk1.8 尾结点插入(因为要遍历node个数),Node<K,V>存储,node个数大于8转为红黑树,resize之后get不会出现死循环，但会数据丢失
* jdk1.7 ConcurrentHashMap Segment 分段锁 在Segment加锁 Segment个数=数组长度/Segment总数
* jdk1.8 ConcurrentHashMap Segment 分段锁 在put加锁 Segment个数=数组长度 但是Segment无用

2. 集合

### 读书计划

1. 设计模式
2. GoLang
3. java并发编程
4. java编程思想
5. springboot
