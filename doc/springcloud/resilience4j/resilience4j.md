# springcloud-resilience4j 使用

* [熔断器配置](https://blog.csdn.net/hataksumo/article/details/128854295)
* [resilience4j源码分析](https://www.cnblogs.com/hama1993/p/12019485.html)

## 1. CircuitBreakerRegistry创建

![avatar](pics/resilience4j-springfactories.png)

1. springboot starter启动 CircuitBreakerAutoConfiguration中创建
2. 默认使用的是InMemoryCircuitBreakerRegistry 注册器
3. CircuitBreakerStateMachine其内部持有一个CircuitBreakerState对象的引用