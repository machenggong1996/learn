# @Import注解

## 1. ImportBeanDefinitionRegistrar

* ImportBeanDefinitionRegistrar接口是也是spring的扩展点之一,它可以支持我们自己写的代码封装成BeanDefinition对象;
* 实现此接口的类会回调postProcessBeanDefinitionRegistry方法，注册到spring容器中。
* 把bean注入到spring容器不止有 @Service @Component等注解方式；还可以实现此接口。

