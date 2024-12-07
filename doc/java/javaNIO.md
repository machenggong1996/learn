# NIO

通道建立缓冲区 双向的

通道channel数据传输 缓冲区buffer存储

IO：面向流 阻塞IO

NIO：面向缓冲区 非阻塞IO 有选择器

## 缓冲区

缓冲区底层是数组，负责存储不同类型的数据，根据数据类型不同(boolean除外)，提供了响应类型缓冲区
* ByteBuffer 常用
* CharBuffer
* ShortBuffer
* IntBuffer
* LongBuffer
* FloatBuffer
* DoubleBuffer

上述缓冲区管理方式一致，通过allocate获取缓冲区

缓冲区存取数据的两个方法 put get

缓冲区核心属性

capacity：容量，表示缓冲区最大存储容量，一旦声明不能改变
limit：界限，表示缓冲区可操作数据大小。limit后面数据不能操作进行读写
position：位置，表示正在操作数据位置

mark: 标记记录position位置，可以通过reset恢复到mark位置

position<=limit<=capacity

```java
public class BufferTest{
    public void test(){
        String str = "abcde";
        //分配一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        System.out.println(buf.position());//0
        System.out.println(buf.limit());//1024
        System.out.println(buf.capacity());//1024
        
        //2. 利用put存数据到缓冲区
        //position位置变为5 position前变为读数据模式
        //调用flip()模式读取数据 position变为0 limit变为5 0-5变为读
        buf.put(str.getBytes());
        System.out.println(buf.position());//5
        System.out.println(buf.limit());//1024
        System.out.println(buf.capacity());//1024
        
        //3. 切换成读取数据模式
        buf.flip();
        System.out.println(buf.position());//0
        System.out.println(buf.limit());//5
        System.out.println(buf.capacity());//1024
        
        //4. get读取数据
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println(new String(dst,0,dst.length));
        System.out.println(buf.position());//5
        System.out.println(buf.limit());//5
        System.out.println(buf.capacity());//1024
        
        //5. rewind 可重复读数据
        buf.rewind();
        System.out.println(buf.position());//0
        System.out.println(buf.limit());//5
        System.out.println(buf.capacity());//1024
        
        //6. clear 清空缓冲区 会到最初状态 缓冲区中的数据依然存在 被遗忘状态 只是指针归位
        buf.clear();
        System.out.println(buf.position());//0
        System.out.println(buf.limit());//1024
        System.out.println(buf.capacity());//1024
        
        System.out.println((char)buf.get());//a
    }
}
```

### 直接缓冲区和非直接缓冲区

1. 非直接缓冲区 通过allocate() 方法分配缓冲区，建立在jvm的内存中
 * 应用程序read 物理磁盘-> 内核地址空间 -> copy到用户地址空间 -> 应用程序
2. 直接缓冲区 通过allocateDirect() 方法分配缓冲区，建立在系统物理内存中
 * 物理内存映射文件：在物理内存中建立缓冲区 省略中间copy过程 写入到物理内存中之后由操作系统控制

```java
public class TestBuffer{
    public void test(){
        //分配直接缓冲区
       ByteBuffer buf = java.nio.ByteBuffer.allocateDirect(1024);
       System.out.println(buf.isDirect());//true 直接缓冲区
    }
}
```

### 通道channel

channel类似于传统的流，只不过channel本身不能直接访问数据，channel只能与buffer进行交互

channel是一个完全独立的处理器，专门用于IO操作

1. 通道(Channel)：用于源节点与目标节点的连接。在java NIO中负责缓存区中的数据传输。本身不存储数据
2. 通道实现类
 * java.nio.Channel接口
   - FileChannel
   - SocketChannel
   - ServerSocketChannel
   - DatagremChannel
3. 获取通道getChannel()方法
 * 本地IO
   - FileInputStream/FileOutputStream
   - RandomAccessFile
 * 网络IO
   - SocketChannel
   - ServerSocketChannel
   - DatagremChannel
4. JDK1.7以后NIO2 提供静态方法open
5. NIO2 Files.newByteChannel()
```java
public class TestChannel{
    public void test(){
        //利用通道完成文件复制 非直接缓冲区 
        java.io.FileOutputStream fis = null;
        java.io.FileOutputStream fos = null;
        
        //获取通道
        java.nio.channels.FileChannel inChannel = null;
        java.nio.channels.FileChannel outChannel = null;
        try{
           //利用通道完成文件复制
              fis = new java.io.FileOutputStream("1.jpg");
              fos = new java.io.FileOutputStream("2.jpg");
               
               //获取通道
              inChannel = fis.getChannel();
              outChannel = fos.getChannel();
              
              //分配缓冲区
              java.nio.ByteBuffer buf = java.nio.ByteBuffer.allocate(1024);
              
              //数据存入缓冲区
              while(inChannel.read(buf)!=-1){
                  //切换成读取数据模式
                  buf.flip();
                  //缓冲区数据写入通道
                  outChannel.write(buf);
                  buf.clear();
              }
                    
        }catch (java.io.IOException e){
            e.printStackTrace();
        }finally{
            if (outChannel!=null){
                try{
                    outChannel.close();
                }catch (java.io.IOException e){
                    e.printStackTrace();
                }
            }
            
            if (inChannel!=null){
                try{
                    inChannel.close();
                }catch (java.io.IOException e){
                    e.printStackTrace();
                }
            }
           
            if(fos!=null){
                try{
                    fos.close();
                }catch (java.io.IOException e){
                    e.printStackTrace();
                }
            }
            if (fis!=null){
                try{
                    fis.close();
                }catch (java.io.IOException e){
                    e.printStackTrace();
                }
            }
            
        }
        
    }
    
    public void test2() throws java.io.IOException{
        //利用通道完成文件复制 直接缓冲区 内存映射文件方式
      
       java.nio.channels.FileChannel inChannel = java.nio.channels.FileChannel.open(java.nio.file.Paths.get("1.jpg"),java.nio.file.StandardOpenOption.READ); 
       java.nio.channels.FileChannel outChannel = java.nio.channels.FileChannel.open(java.nio.file.Paths.get("2.jpg"),java.nio.file.StandardOpenOption.WRITE,java.nio.file.StandardOpenOption.READ,java.nio.file.StandardOpenOption.CREATE_NEW);//存在创建不存在报错
       //内存映射文件 道理是直接获取物理内存 只有ByteBuffer支持
       java.nio.MappedByteBuffer inMapperBuf = inChannel.map(MapMode.READ_ONLY,0,inChannel.size());
       java.nio.MappedByteBuffer outMapperBuf = outChannel.map(MapMode.READ_WRITE,0,inChannel.size());
       
       //直接对缓冲区的读写操作
       byte[] dst = new byte[inMapperBuf.limit()];
       inMapperBuf.get(dst);
       outMapperBuf.put(dst);
       
       inChannel.close();
       outChannel.close();
       
    }
    
    public void test3() throws java.io.IOException{
            //通道之间的数据传输 
          
           java.nio.channels.FileChannel inChannel = java.nio.channels.FileChannel.open(java.nio.file.Paths.get("1.jpg"),java.nio.file.StandardOpenOption.READ); 
           java.nio.channels.FileChannel outChannel = java.nio.channels.FileChannel.open(java.nio.file.Paths.get("2.jpg"),java.nio.file.StandardOpenOption.WRITE,java.nio.file.StandardOpenOption.READ,java.nio.file.StandardOpenOption.CREATE_NEW);//存在创建不存在报错
           
           //使用直接缓冲区方式
           inChannel.transferTo(0,inChannel.size(),outChannel);//或者outChannel.transferFrom(inChannel,0,inChannel.size());
         
           inChannel.close();
           outChannel.close();
        }
}
```

### 分散(Scatter)与聚集(Gather)

分散读取(Scatter Reads) :将通道中的数据分散到多个缓冲区中

聚集写入(Gather Writes):将多个缓冲区中的数据聚集到通道

```java
public class TestChannel{
    public void test1(){
        java.io.RandomAccessFile raf1 = new java.io.RandomAccessFile("1.txt","rw");
        
        //1.获取通道
        java.nio.channels.FileChannel channel1 = raf1.getChannel();
        //2.分配指定大小缓冲区
        ByteBuffer buf1 = java.nio.ByteBuffer.allocate(100);
        java.nio.ByteBuffer buf2 = java.nio.ByteBuffer.allocate(1024);
        
        //3. 分散读取
        
        java.nio.ByteBuffer[] bufs = {buf1,buf2};
        channel1.read(bufs);
        for(java.nio.ByteBuffer byteBuffer : bufs){
            byteBuffer.flip();
        }
        
        System.out.println(new String(bufs[0].array(),0,bufs[0].limit()));
        System.out.println(new String(bufs[1].array(),0,bufs[1].limit()));
        
        // 4. 聚集写入
        java.io.RandomAccessFile raf2 = new java.io.RandomAccessFile("2.txt","rw");
        java.nio.channels.FileChannel channel2 = raf2.getChannel();
        
        channel2.write(bufs);
    }
}
```
### 字符集：Charset

1. 编码：字符串转换成字节数组
2. 解码：字节数组转换成字符串

```java
public class TestCharset{
    public void test1(){
        Map<String,java.nio.charset.Charset> map = java.nio.charset.Charset.availableCharsets();
       
    }
    
    public void test2(){
        java.nio.charset.Charset cs1 = java.nio.charset.Charset.forName("GBK");
        java.nio.charset.CharsetEncoder ce = cs1.newEncoder();
        java.nio.charset.CharsetDecoder ce = cs1.newDecoder();
        
        java.nio.CharBuffer cBuf = java.nio.CharBuffer.allocate(1024);
        
    }
}
```
### NIO非阻塞式网络通信

1. 阻塞：客户端发起请求，服务端不能确认数据有效，线程会进入等待状态，当前线程不会做任何事情
2. NIO非阻塞：选择器(selector)，每一个传输数据的通道都注册到选择器上，选择器监控每个通道状态，当客户端准备就绪时选择器将任务分配到服务端一个或者多个线程上面

使用NIO完成网络通信的三个核心
1. 通道 Channel
2. 缓冲区Buffer
3. 选择器 selectorChannel多路复用器

```java
public class TestNIO{
    
    public void blockClient() throws java.io.IOException{
        
        //1. 获取通道
        java.nio.channels.SocketChannel socketChannel = java.nio.channels.SocketChannel.open("127.0.0.1",9898);
        
        java.nio.channels.FileChannel fileChannel = java.nio.channels.FileChannel.open(java.nio.file.Paths.get("1.jpg"),java.nio.file.StandardOpenOption.READ);
        
        //2. 分配指定大小的缓冲区
        java.nio.ByteBuffer buf = java.nio.ByteBuffer.allocate(1024);
        
        //3. 读取本地文件并发送到服务端去
        while (fileChannel.read(buf)!=-1){
            buf.flip();
            socketChannel.write(buf);
            buf.clear();
        }
        
        socketChannel.shutdownOutput();
        
        //接收服务端反馈
        int len = 0;
        while ((len = socketChannel.read(buf))!=-1){
            buf.flip();
            System.out.println(new String(buf.array()),0,len);
            buf.clear();
        }
        
        fileChannel.close();
        socketChannel.close();
        
    }
    
    public void blockServer(){
        //1. 获取通道
        java.nio.channels.ServerSocketChannel serverSocketChannel = java.nio.channels.ServerSocketChannel.open();
        
        java.nio.channels.FileChannel fileChannel = java.nio.channels.FileChannel.open(java.nio.file.Paths.get("2.jpg"),java.nio.file.StandardOpenOption.WRITE,java.nio.file.StandardOpenOption.CREATE_NEW);
        
        //2. 绑定连接
        serverSocketChannel.bind(new java.net.InetSocketAddress(9898));
        
        //3. 获取客户端连接的通道
        java.nio.channels.SocketChannel socketChannel = serverSocketChannel.accept();
        
        //4. 分配指定大小的缓冲区
        java.nio.ByteBuffer buf = java.nio.ByteBuffer.allocate(1024);
        
        //接收客户端数据
        while (fileChannel.read(buf)!=-1){
            buf.flip();
            socketChannel.write(buf);
            buf.clear();
        }
        
        //发送反馈给客户端
        buf.put("服务端接收数据成功".getBytes());
        buf.flip();
        socketChannel.write(buf);

        socketChannel.close();
        fileChannel.close();
        serverSocketChannel.close();
    }
    
    /**
    * 无阻塞
    */
    public void NoBlockClient() throws java.io.IOException{
        
        //1. 获取通道
        java.nio.channels.SocketChannel socketChannel = java.nio.channels.SocketChannel.open("127.0.0.1",9898);
        
        //2. 切换非阻塞
        socketChannel.configureBlocking(false);
        
        //3. 分配指定大小的缓冲区
        java.nio.ByteBuffer buf = java.nio.ByteBuffer.allocate(1024);
        
        //4. 发送数据
        
        buf.put(new Date().toString().getBytes());
        buf.flip();
        socketChannel.write(buf);
        buf.clear();
        
        socketChannel.close();
    }
    
    public void NoBlockServer() throws java.io.IOException{
        
        //1. 获取通道
        java.nio.channels.ServerSocketChannel serverSocketChannel = java.nio.channels.ServerSocketChannel.open();
        
        //2. 切换非阻塞
        serverSocketChannel.configureBlocking(false);

        //3. 绑定连接
        serverSocketChannel.bind(new java.net.InetSocketAddress(9898));
        
        //4. 选择器
        java.nio.channels.Selector selector = java.nio.channels.Selector.open();
        
        //5. 将通道注册到选择器上 监听接收事件
        serverSocketChannel.register(selector,java.nio.channels.SelectionKey.OP_ACCEPT);
        
        //6. 轮询式的获取选择器上已经准备好的事件
        while (selector.select()>0){
            //7. 获取当前选择器上注册的选择键 监听事件
            java.util.Iterator<java.nio.channels.SelectionKey> it = selector.selectedKeys().iterator();
        
            while (it.hasNext()){
                //8. 获取准备就绪的事件
                java.nio.channels.SelectionKey sk = it.next();
                
                if(sk.isAcceptable()){
                    //10. 若接收就绪 获取客户端连接
                    java.nio.channels.SocketChannel socketChannel = serverSocketChannel.accept();
                    
                    socketChannel.configureBlocking(false);
                    
                    //12. 将通道注册到选择器上
                    socketChannel.register(selector,java.nio.channels.SelectionKey.OP_READ);
                }else if(sk.isValid()){
                    
                    // 13. 获取当前选择器上读就绪状态通道
                   java.nio.channels.SocketChannel socketChannel = sk.channel();
                   
                   //14. 读取数据
                   java.nio.ByteBuffer buf = java.nio.ByteBuffer.allocate(1024);
                   
                   int len = 0;
                   while ((len = socketChannel.read(buf))!=-1){
                       buf.flip();
                       System.out.println(new String(buf.array()),0,len);
                       buf.clear();
                   }
                    
                   
                }
                //取消选择键
                it.remove();
            }
        }
        
    }
    
    
}
```

### DatagramChannel

```java
public class Test{
    public void testClient(){
        java.nio.channels.DatagramChannel dc = java.nio.channels.DatagramChannel.open();
        dc.configureBlocking(false);
        java.nio.ByteBuffer buf = java.nio.ByteBuffer.allocate(1024);
        java.util.Scanner scan = new java.util.Scanner(System.in);
        
        while (scan.hasNext()){
            String str = scan.next();
            buf.put((new java.util.Date().toString()+"\n"+str).getBytes());
            buf.flip();
            dc.send(buf,new java.net.InetSocketAddress("127.0.0.1",9898));
            buf.clear();
        }
        dc.close();
    }
    
    public void testServer(){
            java.nio.channels.DatagramChannel dc = java.nio.channels.DatagramChannel.open();
            dc.configureBlocking(false);
            dc.bind(new java.net.InetSocketAddress(9898));
            
            java.nio.channels.Selector selector = java.nio.channels.Selector.open();
            
            dc.register(selector,java.nio.channels.SelectionKey.OP_READ);
            
            while(selector.select()>0){
                java.util.Iterator<java.nio.channels.SelectionKey> it = selector.selectedKeys().iterator();
                
                while (it.hasNext()){
                    java.nio.channels.SelectionKey sk = it.next();
                    if(sk.isReadable()){
                        java.nio.ByteBuffer buf = java.nio.ByteBuffer.allocate(1024);
                        dc.receive(buf);
                        buf.flip();
                        System.out.println(new String(buf.array(),0,buf.limit()));
                        buf.clear();
                    }
                    it.remove();
                }
            }
        }
}
```

### 管道Pipe

管道是两个线程之间单向数据连接，pipe有一个source通道和一个sink通道，数据会被写到sink通道，从source通道读取

```java
public class Test{
    public void test(){
        java.nio.channels.Pipe pipe = java.nio.channels.Pipe.open();
        
       java.nio.channels.Pipe.SinkChannel sinkChannel = pipe.sink();
       
       java.nio.ByteBuffer buf = java.nio.ByteBuffer.allocate(1024);
       buf.put("通过管道发送数据".getBytes());
       buf.flip();
       sinkChannel.write(buf);
       
       java.nio.channels.Pipe.SourceChannel sourceChannel = pipe.source();
       
       buf.flip();
       sourceChannel.read(buf);
       System.out.println(new String(buf.array(),0,len));
       sourceChannel.close();
       sinkChannel.close();
      
    }
}
```