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

