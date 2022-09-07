# java11新特性

* [Java11新特性一览](https://blog.csdn.net/zyj1051574045/article/details/123618412)
* [Java 11 新特性介绍](https://blog.csdn.net/weixin_30478757/article/details/97338306)
* [Epsilon:JDK 11中的无为GC](https://juejin.cn/post/6844904017248681991)
* [Java11新特性](https://www.jianshu.com/p/a76d735f29fe)

## JVM层面

* ZGC停顿不超过10ms
* JFR 飞行记录 黑匣子 性能开销最大不超过1%
* Low-Overhead Heap Profiling 通过获取对象分配细节，为JDK补足了对象分配诊断方面的一些短板，可以通过JVMTI使用这个能力增强自身的工具

## 类库

* HTTP/2 Client API
* 安全类库
* JDK瘦身工作

## 官方更新列表

* 181: Nest-Based Access Control（基于嵌套的访问控制）
* 309: Dynamic Class-File Constants（动态的类文件常量）
* 315: Improve Aarch64 Intrinsics（改进 Aarch64 Intrinsics）
* 318: Epsilon: A No-Op Garbage Collector（Epsilon 垃圾回收器，又被称为"No-Op（无操作）"回收器）
* 320: Remove the Java EE and CORBA Modules（移除 Java EE 和 CORBA 模块，JavaFX 也已被移除）
* 321: HTTP Client (Standard)
* 323: Local-Variable Syntax for Lambda Parameters（用于 Lambda 参数的局部变量语法）
* 324: Key Agreement with Curve25519 and Curve448（采用 Curve25519 和 Curve448 算法实现的密钥协议）
* 327: Unicode 10 （支持新的编码）
* 328: Flight Recorder（飞行记录仪）
* 329: ChaCha20 and Poly1305 Cryptographic Algorithms（实现 ChaCha20 和 Poly1305 加密算法）
* 330: Launch Single-File Source-Code Programs（启动单个 Java 源代码文件的程序）
* 331: Low-Overhead Heap Profiling（低开销的堆分配采样方法）
* 332: Transport Layer Security (TLS) 1.3（对 TLS 1.3 的支持）
* 333: ZGC: A Scalable Low-Latency Garbage Collector (Experimental)（ZGC：可伸缩的低延迟垃圾回收器，处于实验性阶段）
* 335: Deprecate the Nashorn JavaScript Engine（弃用 Nashorn JavaScript 引擎）
* 336: Deprecate the Pack200 Tools and API（弃用 Pack200 工具及其 API）

## java11 shell

## var局部变量类型推断

* var a; 这样不可以推断
* 类的属性不可以使用var

## API

### list集合

* List.of("a","b","c"); 不可以添加元素
* Arrays.asList 不可以添加元素

### Set集合

Set.of 重复元素抛出异常

### 流中的增强方法

* Stream.ofNullable(null) 

Stream.of(1, 2, 3, 2, 1)
.dropWhile(n -> n < 3)
.collect(Collectors.toList());  // [3, 2, 1]

Stream.of(1, 2, 3, 2, 1)
.takeWhile(n -> n < 3)
.collect(Collectors.toList());  // [1, 2]

一旦判断为假就终止运行

## 字符串

strip方法可以去除英文和其他所有语言中的空白

## Epsilon垃圾收集器

## ZGC 突破性GC