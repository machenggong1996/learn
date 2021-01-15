# jvm

* [JVM参数设置、分析](https://www.cnblogs.com/redcreen/archive/2011/05/04/2037057.html)

## 1. java虚拟机介绍

1. 执行java字节码
2. 一次编译到处运行
3. 自动内存管理垃圾回收

### 1.1 架构模型

1. 基于栈的
2. 基于寄存器的

基于栈的优点，跨平台性，指令集小，指令多，但是执行性能比寄存器差

javap -v class文件 可以看字节码

### 1.2 jvm生命周期

1. 虚拟机启动 启动通过引导类加载器 bootstrap class loader 创建初始类
2. 虚拟机执行 java程序执行的时候会有一个java虚拟机进程
3. 虚拟机退出 正常结束，异常，System.exit()

### 1.3 jvm发展历程

1. 1996 Sun Classic VM 只提供解释器
2. JDK1.2 Exact Memory VM 能知道内存中某个位置的数据类型，热点探测，编译器与解释器混合使用
3. HotSpot JDK1.3 jvm默认虚拟机 jdk8 引入元空间 之前叫方法区 
4. BEA公司 JRocket 针对服务端 
5. IBM J9
6. Oracle javaME CDC/CLDC
7. Azul VM 高性能 指定平台
8. Apache Harmony
9. Microsoft VM
10. TaoBao JVM 对intel cpu耦合
11. Dalvik VM 支持提前编译
12. Graal VM 跨语言全栈虚拟机 

#### 1.3.1 HotSpot热点探测技术

1. 通过计数器找到最具编译价值的代码，触发即时编译或者栈上替换
2. 通过编译器与解释器协同工作，在最优化程序响应时间和执行性能取得平衡

## 2. 内存结构概述

![avatar](pics/jvm内存结构模型.png)

### 2.1 类加载过程

#### 2.1.1 类加载器

ClassLoader
