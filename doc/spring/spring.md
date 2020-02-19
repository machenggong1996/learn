# Spring

## Spring Import注解

1. @Import注解提供了@Bean注解的功能
2. @Import可以配合 Configuration , ImportSelector, ImportBeanDefinitionRegistrar 来使用

### 源码解读

@Import注解在ConfigurationClassPostProcessor这个后置处理器的processConfigBeanDefinitions方法中被处理
最终解析Import类是在ConfigurationClassParser中

#### 处理流程

* Spring IOC容器初使化的时候会调用AbstractApplicationContext 的refresh方法
* 在refresh里会调用各种BeanFactoryPostProcessor， 其中就包括我们需要关注的ConfigurationClassPostProcessor。
* ConfigurationClassPostProcessor 不但用于处理@Configuration注解，里面也有处理@Import注解。
* 最终的处理是通过 ConfigurationClassParser 这个类完成对Import各种情况的处理
* Import有三种导入方式，从ConfigurationClassParser代码里我们可以看到对于不同方式的处理。

## spring bean生命周期

1. 实例化 Instantiation
2. 属性赋值 Populate
3. 初始化 Initialization
4. 销毁 Destruction

[Bean生命周期](https://www.jianshu.com/p/1dec08d290c1)

BeanPostProcessor

## invokeBeanFactoryPostProcessors 方法

## BeanFactory和ApplicationContext的区别

### BeanFactory

是Spring里面最低层的接口，提供了最简单的容器的功能，只提供了实例化对象和拿对象的功能；

### ApplicationContext

应用上下文，继承BeanFactory接口，它是Spring的一各更高级的容器，提供了更多的有用的功能；

1. 国际化（MessageSource）
2. 访问资源，如URL和文件（ResourceLoader）
3. 载入多个（有继承关系）上下文 ，使得每一个上下文都专注于一个特定的层次，比如应用的web层  
4. 消息发送、响应机制（ApplicationEventPublisher）
5. AOP（拦截器）