# Mybatis Spring

* [浅析Mybatis利用Spring扩展点之ImportBeanDefinitionRegistrar](https://blog.csdn.net/oyc619491800/article/details/105599535)

## 1. MapperScannerConfigurer

### 1.1 registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry)

AnnotationMetadata参数可以获取@Import注解使用类的注解信息，包括当前注解，和当前注解使用的注解

## 2. 两种方式

### 2.1 @Mapper

### 2.2 @MapperScan和@MapperScans

## 3. 事务管理