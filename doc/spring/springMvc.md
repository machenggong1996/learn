# Spring MVC

## 流程图

![avatar](pic/springmvc.png)

## 源码分析

HandlerMapping是定义在spring mvc的配置文件中的，spring mvc有两类HandlerMapping，一类是BeanName方式的
一类是注解方式的

handler就是controller对象

HandlerAdapter目的是区分不同类型controller 有三种成为controller的方式，最终方法调用逻辑
在这里
