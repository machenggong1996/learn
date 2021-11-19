# tomcat

* [tomcat参数](https://www.cnblogs.com/kismetv/p/7806063.html#t2)

## 请求流程分析

![avatar](pics/tomcat请求流程分析.png)

### valve pipeline

每个组件都可以使用valve管道，valve有标准的执行顺序

![avatar](pics/tomcat-valve.png)

Http11NioProtocol NioEndpoint Acceptor 源码

lsof -i:8080

maxThreads=5
maxConnections=10 大于5个的部分会进入线程池队列等待
acceptCount=5
最大连接数是maxConnections+acceptCount

相当于医院科室 maxThreads是5个医生
maxConnections是十个挂号 包含看病的5个病人
acceptCount+maxConnections=15是科室最多容纳15个人





