# Mybatis Spring

* [浅析Mybatis利用Spring扩展点之ImportBeanDefinitionRegistrar](https://blog.csdn.net/oyc619491800/article/details/105599535)
* [spring源码学习之整合Mybatis原理分析](https://blog.csdn.net/lihuayong/article/details/83934744)
* [mybatis与spring事务管理分析](https://www.cnblogs.com/timfruit/p/11508873.html)

## 1. MapperScannerConfigurer

### 1.1 registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry)

AnnotationMetadata参数可以获取@Import注解使用类的注解信息，包括当前注解，和当前注解使用的注解

### 1.2 MapperFactoryBean

### 1.3 SqlSessionFactoryBean

## 2. 两种方式

### 2.1 @Mapper

### 2.2 @MapperScan和@MapperScans

## 3. 事务管理