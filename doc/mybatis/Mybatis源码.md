# 源码

## 执行器 Executor

Executor接口中只有两种方法 update 和 query update操作包含insert update delete，query包含select操作，
这样设计的原因是update操作返回值为数字即影响的行数，select操作的返回值是ResultHandler返回的结果类型


1. 简单执行器
simpleExecutor，每次执行SQL需要预编译SQL语句。
2. 可重用执行器
ReuseExecutor，同一SQL语句执行只需要预编译一次SQL语句
3. 批处理执行器
BatchExecutor，只针对修改操作的SQL语句预编译一次，并且需要手动刷新SQL执行才生效。
4. 执行器抽象类
BaseExecutor，执行上面3个执行器的重复操作，比如一级缓存、doQuery、doUpdate方法。
5. 二级缓存
CachingExecutor，与一级缓存的区别：一级缓存查询数据库操作后会直接缓存，二级缓存需要当次数据库操作提交事务后才能进行缓存(二级缓存跨线程处理，一级缓存不用)。

## 执行逻辑

![avatar](pics/执行器执行流程.png)

sqlSession->二级缓存->一级缓存->query->doQuery

二级缓存CachingExecutor中使用装饰器模式对BaseExecutor的实现类进行装饰，达到一级二级缓存同时使用的效果

## 一级缓存命中

![avatar](pics/一级缓存生效条件.png)

1. sql和参数必须相同
2. 必须是相同的statementId即mapper中的方法名称
3. sqlSession必须相同
4. RowBound行范围必须相同
5. 未手动清空
5. 未调用flushCache=true查询
6. 未执行update操作，即使update语句中执行的是select语句
7. 缓存作用域是STATEMENT localCacheScope=STATEMENT
8. 不执行rollback

## 一级缓存源码

![avatar](pics/源码执行流程1.png)

![avatar](pics/源码执行流程2.png)

## 一级缓存总结

1. 和会话相关
2. 和查询条件有关
3. 和提交回滚有关

## 一级缓存失效

sqlSession和executor是一对一的，mybatis和spring整合之后sqlSession用的是不一样的，
会导致sqlSession失效，加上事务之后一级缓存生效

### spring

![avatar](pics/spring中mybatis流程.png)

### 二级缓存

1. 一级缓存不能跨线程使用
2. 二级缓存可以跨线程调用，生命周期是整个应用，需要考虑容量






