# mybatis源码

## 架构设计

配置类

Mapper注册中心->执行器->StatementHandler->结果集Handler

之间可以加拦截器 handler为字段映射处理器



