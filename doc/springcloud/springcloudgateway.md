# spring cloud gateway

## 1. 网关启动&配置加载流程

### 1.1 springboot reactive项目启动

最终服务启动的是netty服务

refresh()->ReactiveWebServerApplicationContext#onRefresh->ReactiveWebServerApplicationContext#createWebServer ->
ReactiveWebServerApplicationContext.ServerManager#get->ReactiveWebServerApplicationContext.ServerManager#ServerManager
->NettyReactiveWebServerFactory#getWebServer

### 1.2 GatewayAutoConfiguration

* GatewayProperties

routes属性对应配置文件相关配置

![avatar](pics/gateway/GatewayProperties.png)
```java
public class RouteDefinitionRouteLocator
		implements RouteLocator, BeanFactoryAware, ApplicationEventPublisherAware {

    /**
     * Default filters name.
     */
    public static final String DEFAULT_FILTERS = "defaultFilters";

    protected final Log logger = LogFactory.getLog(getClass());

    private final RouteDefinitionLocator routeDefinitionLocator;

    private final ConfigurationService configurationService;

    private final Map<String, RoutePredicateFactory> predicates = new LinkedHashMap<>();

    private final Map<String, GatewayFilterFactory> gatewayFilterFactories = new HashMap<>();

    private final GatewayProperties gatewayProperties;
}
```
* RouteDefinitionRouteLocator 断言，过滤器等的实际加载类
* GatewayFilterFactory子类
* RoutePredicateFactory子类

```
@Bean
public RouteLocator routeDefinitionRouteLocator(GatewayProperties properties,
        List<GatewayFilterFactory> gatewayFilters,
        List<RoutePredicateFactory> predicates,
        RouteDefinitionLocator routeDefinitionLocator,
        ConfigurationService configurationService){
        return new RouteDefinitionRouteLocator(routeDefinitionLocator,predicates,
        gatewayFilters,properties,configurationService);
        }

```

使用名称简写

```
    public RouteDefinitionRouteLocator(RouteDefinitionLocator routeDefinitionLocator,
        List<RoutePredicateFactory> predicates,
        List<GatewayFilterFactory> gatewayFilterFactories,
        GatewayProperties gatewayProperties,
        ConfigurationService configurationService){
        this.routeDefinitionLocator=routeDefinitionLocator;
        this.configurationService=configurationService;
        initFactories(predicates);
        gatewayFilterFactories.forEach(
        factory->this.gatewayFilterFactories.put(factory.name(),factory));
        this.gatewayProperties=gatewayProperties;
        }
```

### GatewayReactiveLoadBalancerClientAutoConfiguration

### GatewayNoLoadBalancerClientAutoConfiguration

## 2. 请求流程

* [请求流程](https://www.jianshu.com/p/f5f6cebcdf88)
  
ReactorHttpHandlerAdapter#apply开始执行

![avatar](pics/gateway/请求流程图.png)

### 2.1 DispatcherHandler

* org.springframework.web.reactive.DispatcherHandler#handle

```
@Override
public Mono<Void> handle(ServerWebExchange exchange){
        if(this.handlerMappings==null){
        return createNotFoundError();
        }
        if(CorsUtils.isPreFlightRequest(exchange.getRequest())){
        return handlePreFlight(exchange);
        }
        return Flux.fromIterable(this.handlerMappings)// 轮询
        //流式执行 getHandler方法
        .concatMap(mapping->mapping.getHandler(exchange))
        // 将流操作中返回的第一个项发送到新的Mono中 
        .next()
        .switchIfEmpty(createNotFoundError())
        // 从获取到的handler中执行 invokeHandler(exchange, handler) 调用拦截器链
        .flatMap(handler->invokeHandler(exchange,handler))
        //从获取到的handler获取到result执行  handleResult(exchange, result)
        .flatMap(result->handleResult(exchange,result));}
```

#### 2.1.1 invokeHandler方法调用拦截器链

DispatcherHandler#invokeHandler
->SimpleHandlerAdapter#handle
->org.springframework.cloud.gateway.handler.FilteringWebHandler#handle 过滤器排序
->DefaultGatewayFilterChain#filter

```
		@Override
		public Mono<Void> filter(ServerWebExchange exchange) {
			return Mono.defer(() -> {
				if (this.index < filters.size()) {
					GatewayFilter filter = filters.get(this.index);
					DefaultGatewayFilterChain chain = new DefaultGatewayFilterChain(this,
							this.index + 1);
					return filter.filter(exchange, chain);
				}
				else {
					return Mono.empty(); // complete
				}
			});
		}
```

## 3. Routes配置

### 3.1 Predicate

DispatcherHandler#handle->
AbstractHandlerMapping#getHandler->
RoutePredicateHandlerMapping#getHandlerInternal->
RoutePredicateHandlerMapping#lookupRoute断言匹配 ->
RouteDefinitionRouteLocator#getRoutes->
RouteDefinitionRouteLocator#convertToRoute->
RouteDefinitionRouteLocator#combinePredicates->
RouteDefinitionRouteLocator#lookup

```
	protected Mono<Route> lookupRoute(ServerWebExchange exchange) {
		return this.routeLocator.getRoutes()
				// individually filter routes so that filterWhen error delaying is not a
				// problem
				.concatMap(route -> Mono.just(route).filterWhen(r -> {
					// add the current route we are testing
					exchange.getAttributes().put(GATEWAY_PREDICATE_ROUTE_ATTR, r.getId());
					// 断言匹配
					return r.getPredicate().apply(exchange);
				})
						// instead of immediately stopping main flux due to error, log and
						// swallow it
						.doOnError(e -> logger.error(
								"Error applying predicate for route: " + route.getId(),
								e))
						.onErrorResume(e -> Mono.empty()))
				// .defaultIfEmpty() put a static Route not found
				// or .switchIfEmpty()
				// .switchIfEmpty(Mono.<Route>empty().log("noroute"))
				.next()
				// TODO: error handling
				.map(route -> {
					if (logger.isDebugEnabled()) {
						logger.debug("Route matched: " + route.getId());
					}
					validateRoute(route, exchange);
					return route;
				});

		/*
		 * TODO: trace logging if (logger.isTraceEnabled()) {
		 * logger.trace("RouteDefinition did not match: " + routeDefinition.getId()); }
		 */
	}
```

断言未匹配上

![avatar](pics/gateway/断言未匹配上.png)

#### RoutePredicateFactory

* [路由谓词工厂 RoutePredicateFactory](https://blog.csdn.net/u010647035/article/details/84495302)
* [SpringCloud-Gateway之RoutePredicateFactory](https://blog.csdn.net/weixin_44100910/article/details/106439122)

![avatar](pics/gateway/RoutePredicateFactory.png)

#### 自定义RoutePredicateFactory

* 优点TODO

### 3.2 Filters

* [Spring Cloud GateWay-过滤器](https://blog.csdn.net/lucky_love816/article/details/124978639)

#### GlobalFilter 全局拦截器

![avatar](pics/gateway/GlobalFilter.png)

#### GatewayFilter 配置化拦截器

* 需要通过spring.cloud.routes.filters 配置在具体路由下，只作用在当前路由上或通过spring.cloud.default-filters配置在全局，作用在所有路由上。
![avatar](pics/gateway/GatewayFilterFactory.png)
  
#### 自定义GatewayFilterFactory

1. 优点 TODO

### 3.3 Metadata

默认的配置就两个 连接超时和响应超时，在NettyRoutingFilter过滤器中使用

```java
public final class RouteMetadataUtils {

    /**
     * Response timeout attribute name.
     */
    public static final String RESPONSE_TIMEOUT_ATTR = "response-timeout";

    /**
     * Connect timeout attribute name.
     */
    public static final String CONNECT_TIMEOUT_ATTR = "connect-timeout";

    private RouteMetadataUtils() {
        throw new AssertionError("Must not instantiate utility class.");
    }

}
```

自定义配置之后可以在 route.getMetadata().get(nameKey)使用

```
Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
// get all metadata properties
route.getMetadata();
// get a single metadata property
route.getMetadata(someKey);
```

## 4. 熔断



## 5. 限流



