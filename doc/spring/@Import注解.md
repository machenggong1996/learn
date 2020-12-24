# @Import注解

## 1. ImportBeanDefinitionRegistrar

* ImportBeanDefinitionRegistrar接口是也是spring的扩展点之一,它可以支持我们自己写的代码封装成BeanDefinition对象;
* 实现此接口的类会回调postProcessBeanDefinitionRegistry方法，注册到spring容器中。
* 把bean注入到spring容器不止有 @Service @Component等注解方式；还可以实现此接口。

### 1.1 ImportBeanDefinitionRegistrar使用优势

其实除了 ImportBeanDefinitionRegistrar 可以注册BeanDefinition之外，BeanDefinitionRegistryPostProcessor也可以注册bean，
但是后者需要手动添加进spring容器内，前者只需要使用@Import注解即可，实现解耦

## 2. ImportSelector

```java
public interface ImportSelector {
    
	String[] selectImports(AnnotationMetadata importingClassMetadata);

}
```
selectImports返回类的全路径名即可将类注册为bean
