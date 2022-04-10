# spring18问

## 容器创建过程

1. 先创建容器
2. 加载配置文件，封装成BeanDefinition
3. 调用执行BeanFactoryPostProcessor
4. 准备工作 准备BeanPostProcessor
5. 实例化
6. 初始化
7. 获取到完整对象

## 解决循环依赖

### 为什么使用三级缓存

* [Spring 为何需要三级缓存解决循环依赖，而不是二级缓存？](https://blog.csdn.net/weixin_45727359/article/details/114696668)

getEarlyBeanReference处理动态代理 二级缓存不能对动态代理对象进行处理

SmartInstantiationAwareBeanPostProcessor#getEarlyBeanReference的实现类有AbstractAutoProxyCreator
会对动态代理进行判断





