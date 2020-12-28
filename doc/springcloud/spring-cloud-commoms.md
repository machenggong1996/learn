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

* 断路器
* circuitbreaker子包里面定义了一个注解@EnableCircuitBreaker和一个Import Selector。只要使用了该注解就会import这个selector
```java
@Import(EnableCircuitBreakerImportSelector.class)
public @interface EnableCircuitBreaker {
	
}
```

* EnableCircuitBreakerImportSelector继承了 SpringFactoryImportSelector， 内部会使用工厂加载机制。去加载META-INF/spring.factories里key为 
org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker 的类。该机制生效的前期是 spring.cloud.circuit.breaker.enabled 配置为true，默认值就是true。
* circuitbreaker子包相当于了定义了断路器的加载机制。在spring.factories里配置对应的类和开关配置即可生效。具体的实现由其它模块提供。

## 3. discovery功能

服务发现功能

* 定义了DiscoveryClient接口和EnableDiscoveryClient注解。
* 定义了一些各种服务发现组件客户端里的读取服务操作：
```java
public interface DiscoveryClient {
    String description(); // 描述
    List<ServiceInstance> getInstances(String serviceId); // 根据服务id获取具体的服务实例
    List<String> getServices(); // 获取所有的服务id集合
}
```
* @EnableDiscoveryClient注解import了EnableDiscoveryClientImportSelector这个selector。该注解内部有个属性 boolean autoRegister() default true; 表示是否自动注册，默认是true。
* selector内部会找出 META-INF/spring.factories里key为org.springframework.cloud.client.discovery.EnableDiscoveryClient的类。
* 如果自动注册属性为true，会在找出的这些类里再加上一个类：org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationConfiguration。 AutoServiceRegistrationConfiguration内部会使用
  @EnableConfigurationProperties(AutoServiceRegistrationProperties.class)触发构造AutoServiceRegistrationProperties这个bean。像eureka，nacos，它们的自动化配置类里都使用了@ConditionalOnBean(AutoServiceRegistrationProperties.class)
  来确保存在AutoServiceRegistrationProperties这个bean存在的时候才会构造AutoServiceRegistration进行注册。
* 如果自动注册属性为false，在Environment里加一个PropertySource，内部的配置项是spring.cloud.service-registry.auto-registration.enabled，值是false(代表不构造AutoServiceRegistrationProperties.class)。这样eureka，nacos都不会注册

### 3.1 discovery子包内部还有其它一些功能：

#### 3.1.1 simple

* 简单的服务发现实现类 SimpleDiscoveryClient，具体的服务实例从 SimpleDiscoveryProperties 配置中获取。 SimpleDiscoveryProperties 配置 读取前缀为 spring.cloud.discovery.client.simple 的配置。读取的结果放到Map里 Map<String, List<SimpleServiceInstance>>。
  这里 SimpleServiceInstance 实现了ServiceInstance接口。 具体的属性值从 SimpleDiscoveryProperties 中获取
* SimpleDiscoveryClientAutoConfiguration 自动化配置类在 spring.factories里key为 org.springframework.boot.autoconfigure.EnableAutoConfiguration的配置项中。内部会构造 SimpleDiscoveryProperties、 SimpleDiscoveryClient
  
#### 3.1.2 noop

什么都不做的服务发现实现类，已经被废弃，建议使用 simple 子模块里的类代替

#### 3.1.3 health

SpringBoot的那套health机制与SpringCloud结合。使用DiscoveryClient获取服务实例的信息

#### 3.1.4 event

定义了一些心跳检测事件，服务注册事件

#### 3.1.5 composite

* 定义了 CompositeDiscoveryClient。 看名字也知道，组合各个服务发现客户端的一个客户端。默认会根据CompositeDiscoveryClientAutoConfiguration自动化配置类构造出CompositeDiscoveryClient。
* 默认清下我们注入的DiscoveryClient就是这个CompositeDiscoveryClient

## 4. serviceregistry功能

* 服务注册功能。
* 定义了服务注册的接口 ServiceRegistry<R extends Registration>，Registration接口继承了服务实例ServiceInstance接口，未新增新方法，留作以后扩展使用。
```java
public interface ServiceRegistry<R extends Registration> {
	// 注册服务实例
	void register(R registration);
	// 下线服务实例
	void deregister(R registration);
	// 生命周期方法，关闭操作
	void close();
	// 设置服务实例的状态，状态值由具体的实现类决定
	void setStatus(R registration, String status);
	// 获取服务实例的状态
	<T> T getStatus(R registration);
}
```
* 定义了一些自动化配置类:
  - ServiceRegistryAutoConfiguration 内部会根据条件注解判断是否构造ServiceRegistryEndpoint，该endpoint会暴露ServiceRegistry的状态信息，也可以设置ServiceRegistry的状态信息
  - AutoServiceRegistrationAutoConfiguration 在@EnableDiscoveryClient注解打开自注册开关的时候才会生效，内部import了AutoServiceRegistrationConfiguration这个类，该类内部会使用@EnableConfigurationProperties注解构造AutoServiceRegistrationProperties这个bean
* 定义了一个接口AutoServiceRegistration 和一个抽象类 AbstractAutoServiceRegistration，用于处理服务自动注册逻辑。一般我们自定义的服务注册逻辑只需要继承该类即可。
* AutoServiceRegistration接口无任何方法声明，用于标记是否是服务自动注册。
* AbstractAutoServiceRegistration 抽象类实现了AutoServiceRegistration接口，定义了4个抽象方法：
```java_method
// 服务注册信息的配置数据
protected abstract Object getConfiguration();
// 该注册器是否可用
protected abstract boolean isEnabled();
// 获取注册信息
protected abstract R getRegistration();
// 获取注册信息的management信息
protected abstract R getManagementRegistration();
```

### 4.1 AbstractAutoServiceRegistration抽象类内部逻辑总结

* 构造方法里必须有个ServiceRegistry参数，服务注册相关的逻辑都使用该接口完成
* 监听WebServerInitializedEvent事件。当WebServer初始化完毕后(Spring ApplicationContext也已经refresh后)，使用ServiceRegistry注册服务，具体的服务信息在抽象方法getRegistration()里由子类实现。当子类实现的getManagementRegistration()接口有返回具体的注册信息并且配置的management信息后注册这个management信息
* 该类销毁的时候使用ServiceRegistry下线服务(下线过程跟注册过程雷同，下线getRegistration()和getManagementRegistration()方法里返回的注册信息)，并调用ServiceRegistry`的close方法关闭注册器。
* 总结一下，在SpringCloud体系下要实现新的服务注册、发现需要这6个步骤(最新版本的spring-cloud-commons已经建议我们直接使用Registration，废弃ServiceInstance)：

* 实现ServiceRegistry接口，完成服务注册自身的具体逻辑
* 实现Registration接口，完成服务注册过程中获取注册信息的操作
* 继承AbstractAutoServiceRegistration，完成服务注册前后的逻辑
* 实现DiscoveryClient接口，完成服务发现的具体逻辑
* 实现ServiceInstance接口，在DiscoveryClient接口中被使用，完成服务注册组件与SpringCloud注册信息的转换
* 自动化配置类，将这些Bean进行构造

## 5. loadbalancer功能

* 客户端负载均衡功能。
* 一些接口的定义：
  - ServiceInstanceChooser：服务实例选择器，使用load balancer根据serviceId获取具体的实例。
```java
public interface ServiceInstanceChooser {
	// 根据服务id获取具体的服务实例
    ServiceInstance choose(String serviceId);
}
```
  - LoadBalancerClient：负载均衡客户端，继承ServiceInstanceChooser。
```java
public interface LoadBalancerClient extends ServiceInstanceChooser {
	// 根据serviceId使用ServiceInstance执行请求
	<T> T execute(String serviceId, LoadBalancerRequest<T> request) throws IOException;
	// 重载方法。同上
	<T> T execute(String serviceId, ServiceInstance serviceInstance, LoadBalancerRequest<T> request) throws IOException;
	// 基于服务实例和URI重新构造一个新的带有host和port信息的URI。比如http://myservice/path/to/service.这个地址 myservice 这个服务名将会替换成比如 192.168.1.122:8080
	URI reconstructURI(ServiceInstance instance, URI original);
}
```
  - RestTemplateCustomizer：RestTemplate的定制化器。
```java
public interface RestTemplateCustomizer {
	void customize(RestTemplate restTemplate);
}
```
  - LoadBalancerRequestTransformer：HttpRequest转换器，根据ServiceInstance转换成一个新的具有load balance功能的HttpRequest。
```java
@Order(LoadBalancerRequestTransformer.DEFAULT_ORDER)
public interface LoadBalancerRequestTransformer {
	public static final int DEFAULT_ORDER = 0;
	HttpRequest transformRequest(HttpRequest request, ServiceInstance instance);
}
```
  - LoadBalancerRequest：函数式接口。对ServiceInstance操作并返回具体的泛型T。LoadBalancerRequestFactory的createRequest方法内部实现了该接口。实现过程中使用LoadBalancerRequestTransformer对request进行转换并返回了ClientHttpResponse。
```java
public interface LoadBalancerRequest<T> {
	public T apply(ServiceInstance instance) throws Exception;
}
```
* @LoadBalanced注解用于修饰RestTemplate，表示使用负载均衡客户端。
* 使用该注解修饰的RestTemplate会在LoadBalancerAutoConfiguration自动化配置类中被处理：
```java_method
// 使用RestTemplateCustomizer定制化这些被@LoadBalanced注解修饰的RestTemplate
@Bean
public SmartInitializingSingleton loadBalancedRestTemplateInitializerDeprecated(
        final ObjectProvider<List<RestTemplateCustomizer>> restTemplateCustomizers) {
    return () -> restTemplateCustomizers.ifAvailable(customizers -> {
        for (RestTemplate restTemplate : LoadBalancerAutoConfiguration.this.restTemplates) {
            for (RestTemplateCustomizer customizer : customizers) {
                customizer.customize(restTemplate);
            }
        }
    });
}
// 内部的一个配置类LoadBalancerInterceptorConfig
// 如果没有依赖spring-retry模块。如果依赖spring-retry模块的话会构造另外一个配置类RetryInterceptorAutoConfiguration。内部也会1个拦截器和1个定制化器，分别是RetryLoadBalancerInterceptor和RetryLoadBalancerInterceptor。原理类似
@Configuration
@ConditionalOnMissingClass("org.springframework.retry.support.RetryTemplate")
static class LoadBalancerInterceptorConfig {
	// 构造http拦截器
    @Bean
    public LoadBalancerInterceptor ribbonInterceptor(
            LoadBalancerClient loadBalancerClient,
            LoadBalancerRequestFactory requestFactory) {
        return new LoadBalancerInterceptor(loadBalancerClient, requestFactory);
    }
	// 构造定制化器RestTemplateCustomizer，为这些RestTemplate添加拦截器LoadBalancerInterceptor
    @Bean
    @ConditionalOnMissingBean
    public RestTemplateCustomizer restTemplateCustomizer(
            final LoadBalancerInterceptor loadBalancerInterceptor) {
        return restTemplate -> {
            List<ClientHttpRequestInterceptor> list = new ArrayList<>(
                    restTemplate.getInterceptors());
            list.add(loadBalancerInterceptor);
            restTemplate.setInterceptors(list);
        };
    }
}
```
* LoadBalancerInterceptor拦截器内部会对request请求进行拦截。拦截器内部使用LoadBalancerClient完成请求的调用，这里调用的时候需要的LoadBalancerRequest由LoadBalancerRequestFactory构造，
  LoadBalancerRequestFactory内部使用LoadBalancerRequestTransformer对request进行转换。

## 6. hypermedia功能

springcloud对hateoas在服务发现领域上的支持。

## 7. 其它注解、接口、类

### 7.1 @SpringCloudApplication注解

整合了@SpringBootApplication、@EnableDiscoveryClient、@EnableCircuitBreaker这3个注解，说明@SpringCloudApplication注解表示一个分布式应用注解

### 7.2 ServiceInstance接口

表示服务发现系统里的一个服务实例

### 7.3 DefaultServiceInstance类

实现了ServiceInstance接口，是个默认的服务实例的实现

### 7.4 HostInfoEnvironmentPostProcessor类

属于EnvironmentPostProcessor。这个postprocessor会在Environment里加上当前vm的hostname和ip信息

### 7.5 CommonsClientAutoConfiguration类

自动化配置类。在该模块的 META-INF/spring.factories里配置，key为org.springframework.boot.autoconfigure.EnableAutoConfiguration。所以默认会被加载，内部会构造一些HealthIndicator，一些Endpoint

Spring Cloud Alibaba内部的spring-cloud-alibaba-nacos-discovery模块实现了spring-cloud-commons规范，提供了基于Nacos的服务发现，服务注册功能。Nacos是一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。