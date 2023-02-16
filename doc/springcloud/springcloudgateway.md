# spring cloud gateway

## 1. 网关启动&配置加载流程

### 1.1 springboot reactive项目启动

最终服务启动的是netty服务

refresh()->ReactiveWebServerApplicationContext#onRefresh->ReactiveWebServerApplicationContext#createWebServer
->ReactiveWebServerApplicationContext.ServerManager#get->ReactiveWebServerApplicationContext.ServerManager#ServerManager
->NettyReactiveWebServerFactory#getWebServer

### 1.2 GatewayAutoConfiguration

* GatewayProperties

routes属性对应配置文件相关配置

![avatar](pics/gateway/GatewayProperties.png)

### GatewayReactiveLoadBalancerClientAutoConfiguration

### GatewayNoLoadBalancerClientAutoConfiguration

## 2. 请求流程

### 2.1 DispatcherHandler

* org.springframework.web.reactive.DispatcherHandler#handle
```java
@Override
public Mono<Void> handle(ServerWebExchange exchange) {
    if (this.handlerMappings == null) {
        return createNotFoundError();
    }
    if (CorsUtils.isPreFlightRequest(exchange.getRequest())) {
        return handlePreFlight(exchange);
    }
    return Flux.fromIterable(this.handlerMappings)// 轮询
        .concatMap(mapping -> mapping.getHandler(exchange))// 
        .next()
        .switchIfEmpty(createNotFoundError())
        .flatMap(handler -> invokeHandler(exchange, handler))
        .flatMap(result -> handleResult(exchange, result));}
```



