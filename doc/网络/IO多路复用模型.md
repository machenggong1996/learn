# IO多路复用模型

* [IO多路复用技术详解](https://www.cnblogs.com/wlwl/p/10293057.html)

# IO多路复用概念

* IO多路复用：I/O是指网络I/O,多路指多个TCP连接(即socket或者channel）,复用指复用一个或几个线程。
  意思说一个或一组线程处理多个TCP连接。最大优势是减少系统开销小，不必创建过多的进程/线程，也不必维护这些进程/线程。
* IO多路复用使用两个系统调用(select/poll/epoll和recvfrom)，blocking IO只调用了recvfrom；select/poll/epoll 
  核心是可以同时处理多个connection，而不是更快，所以连接数不高的话，性能不一定比多线程+阻塞IO好,
  多路复用模型中，每一个socket，设置为non-blocking,阻塞是被select这个函数block，而不是被socket阻塞的。