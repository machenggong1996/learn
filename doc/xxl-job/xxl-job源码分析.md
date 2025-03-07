# xxl-job源码分析

## 资料

* [xxl-job源码解析(看这一篇就够了，超简约且详细)](https://blog.csdn.net/Nuan_Feng/article/details/115619448)
* [xxl-job 源码解析](https://www.bilibili.com/video/BV13V4y1E7CB)
* [xxl-job 源码注释地址](https://github.com/machenggong1996/xxl-job)

## 每张表的作用

* xxl_job_info 存放任务信息
* xxl_job_log 任务运行日志
* xxl_job_log_report 任务统计报告
* xxl_job_registry 任务注册
* xxl_job_group 组
* xxl_job_user 用户
* xxl_job_lock 分布式锁

服务端 admin项目
客户端 自己写的项目

## xxl-job-code源码

### com.xxl.job.core.biz 

* 客户端和服务端相互调用的接口 使用netty通信


### 问题

1. admin如何触发的客户端 客户端内置netty服务器
2. 


1. 启动流程 XxlJobExecutor
2. 注册流程 XxlJobExecutor
3. 执行流程 ExecutorBizImpl IJobHandler

1. 如何分片 策略如何做的
2. 如何定期执行的
3. 集群情况下如何处理分布式问题

1. 线程如何创建的 JobThread

### 分片

com.xxl.job.admin.core.trigger.XxlJobTrigger#trigger

group

### 日志

* XxlJobFileAppender

### handler

* MethodJobHandler bean的形式 使用反射执行方法

### Executor

* XxlJobExecutor

### 线程

* JobThread com.xxl.job.core.executor.XxlJobExecutor#registJobThread 注册之后线程就会启动

### server

* EmbedServer netty服务器

### glue 胶水 可以执行脚本和代码

* GlueFactory 加载java代码
* SpringGlueFactory 对代码进行spring注入

## admin项目源码分析

1. 如何解析corn表达式进行定时任务执行 CronExpression
2. 分布式锁
3. 快慢线程池
4. 路由策略
5. 阻塞策略

### UserController

* 用户管理

### JobScheduleHelper

* [xxl-job调度线程与时间轮算法](https://blog.csdn.net/qq_35946969/article/details/122588968)
* [JobScheduleHelper 源码注释地址](https://github.com/machenggong1996/xxl-job/blob/master/xxl-job-admin/src/main/java/com/xxl/job/admin/core/thread/JobScheduleHelper.java)
* 普通调度 5秒时间对齐
* 时间轮调度 1秒时间对齐 下次执行时间在5秒之内的放入时间轮调度

### JobTriggerPoolHelper

* [JobTriggerPoolHelper源码解读](https://blog.csdn.net/u011236069/article/details/121092471)
* 快慢线程池 部分慢执行的线程，会拖慢整个线程池，因此需要将快慢分离。 需要区分出哪些是慢线程，这里给一个依据是一分钟内的慢执行（耗时大于500ms）次数为10次。


### 路由策略

## 版本对比 2.2.0 与 2.4.0

* 2.4.0获取参数使用XxlJobHelper，2.2.0没有 需要在方法入口写参数

## 触发一次的调用链

1. admin项目 com.xxl.job.admin.controller.JobInfoController#triggerJob
2. admin项目 com.xxl.job.admin.core.thread.JobTriggerPoolHelper#trigger
3. admin项目 com.xxl.job.admin.core.thread.JobTriggerPoolHelper#addTrigger 快慢线程池切换逻辑
4. admin项目 com.xxl.job.admin.core.trigger.XxlJobTrigger#trigger
5. admin项目 com.xxl.job.admin.core.trigger.XxlJobTrigger#processTrigger 区分分片路由策略和其他策略，分片路由策略是for循环执行
6. admin项目 com.xxl.job.admin.core.trigger.XxlJobTrigger#runExecutor
7. core项目 com.xxl.job.core.biz.client.ExecutorBizClient#run 远程调用
8. core项目 com.xxl.job.core.biz.impl.ExecutorBizImpl#run 实际调用逻辑执行
  * 创建JobThread线程
  * jobThread.pushTriggerQueue(triggerParam) 放入队列
  * com.xxl.job.core.thread.JobThread#run 从队列中获取任务并执行
    * 队列中获取参数
    * 封装XxlJobContext上下文
    * FutureTask异步执行调用 带超时的 handler.execute()方法实际执行 使用反射
    * 循环30次队列任务为0 停止任务
    * TriggerCallbackThread.pushCallBack 执行回调
    * com.xxl.job.core.thread.TriggerCallbackThread#doCallback
    * com.xxl.job.admin.core.thread.JobCompleteHelper#callback
    * com.xxl.job.admin.core.complete.XxlJobCompleter#updateHandleInfoAndFinish 触发子任务执行

## 阻塞策略

* com.xxl.job.core.biz.impl.ExecutorBizImpl#run

```
    // 单机串行
    SERIAL_EXECUTION("Serial execution"),
    /*CONCURRENT_EXECUTION("并行"),*/
    // 丢弃后续调度
    DISCARD_LATER("Discard Later"),
    // 覆盖以前调度
    COVER_EARLY("Cover Early");
```

```
// executor block strategy
        if (jobThread != null) {
            // 阻塞处理策略
            ExecutorBlockStrategyEnum blockStrategy = ExecutorBlockStrategyEnum.match(triggerParam.getExecutorBlockStrategy(), null);
            if (ExecutorBlockStrategyEnum.DISCARD_LATER == blockStrategy) {
                // discard when running
                // 如果当前任务线程正在运行中 返回失败
                if (jobThread.isRunningOrHasQueue()) {
                    return new ReturnT<String>(ReturnT.FAIL_CODE, "block strategy effect："+ExecutorBlockStrategyEnum.DISCARD_LATER.getTitle());
                }
            } else if (ExecutorBlockStrategyEnum.COVER_EARLY == blockStrategy) {
            // 覆盖策略 把当前任务线程置空
                // kill running jobThread
                if (jobThread.isRunningOrHasQueue()) {
                    removeOldReason = "block strategy effect：" + ExecutorBlockStrategyEnum.COVER_EARLY.getTitle();

                    jobThread = null;
                }
            } else {
                // just queue trigger
            }
        }
```

## 调度策略算法

### 1. 一致性哈希

![avatar](pics/一致性哈希.png)

```java
public class  ExecutorRouteConsistentHash extends ExecutorRouter {

    private static int VIRTUAL_NODE_NUM = 100;

    /**
     * get hash code on 2^32 ring (md5散列的方式计算hash值)
     * @param key
     * @return
     */
    private static long hash(String key) {

        // md5 byte
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not supported", e);
        }
        md5.reset();
        byte[] keyBytes = null;
        try {
            keyBytes = key.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unknown string :" + key, e);
        }

        md5.update(keyBytes);
        byte[] digest = md5.digest();

        // hash code, Truncate to 32-bits
        long hashCode = ((long) (digest[3] & 0xFF) << 24)
                | ((long) (digest[2] & 0xFF) << 16)
                | ((long) (digest[1] & 0xFF) << 8)
                | (digest[0] & 0xFF);

        long truncateHashCode = hashCode & 0xffffffffL;
        return truncateHashCode;
    }

    public String hashJob(int jobId, List<String> addressList) {

        // ------A1------A2-------A3------
        // -----------J1------------------
        TreeMap<Long, String> addressRing = new TreeMap<Long, String>();
        for (String address: addressList) {
            // 虚拟节点200个
            for (int i = 0; i < VIRTUAL_NODE_NUM; i++) {
                long addressHash = hash("SHARD-" + address + "-NODE-" + i);
                addressRing.put(addressHash, address);
            }
        }

        long jobHash = hash(String.valueOf(jobId));
        // tailMap 获取尾部 比jobHash大的数据
        SortedMap<Long, String> lastRing = addressRing.tailMap(jobHash);
        if (!lastRing.isEmpty()) {
            // 获取lastRing第一个
            return lastRing.get(lastRing.firstKey());
        }
        // 没有数据从头开始 行程环状
        return addressRing.firstEntry().getValue();
    }

    @Override
    public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList) {
        String address = hashJob(triggerParam.getJobId(), addressList);
        return new ReturnT<String>(address);
    }

}
```

## 客户端项目启动流程

## 时间轮优化项目中的每秒扫库任务

5秒一次扫库线程，1秒一次时间轮线程

扫库数据根据前后三个五秒分片判断是否需要立即执行，以及放入时间轮中

核心代码参考JobScheduleHelper类
