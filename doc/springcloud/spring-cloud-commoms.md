# spring cloud commons

[spring-cloud-commons 源码分析](http://fangjian0423.github.io/2018/10/02/spring-cloud-commons-analysis/)

spring-cloud-commons模块是spring在分布式领域上(服务发现，服务注册，断路器，负载均衡)的规范定义(spring-cloud-netflix是具体的实现，也就是Netflix OSS里的各种组件实现了这个commons规范)，
可以被所有的Spring Cloud客户端使用(比如服务发现领域的eureka，consul)。下面将根据包名来分析一下内部的一些接口和类。

## 1. actuator功能

* spring cloud提供的featuresEndpoint，可以方便我们查看系统启动的一些features，进而了解系统特征。
* 在CommonsClientAutoConfiguration类中进行配置

### 1.1 FeaturesEndpoint

actuator框架端点，用来获取actuator

### 1.2 HasFeatures

* 内部两个属性abstractFeatures，namedFeatures
* HasFeatures用来填充上面两个属性，提供给FeaturesEndpoint使用
* 初始化在CommonsClientAutoConfiguration

### 1.3 NamedFeature

实体类

## 2. CircuitBreaker功能

断路器

