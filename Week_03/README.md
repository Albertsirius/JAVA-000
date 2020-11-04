# 学习笔记

## 什么是高性能
1. **高并发用户(Concurrent Users)**
2. **高吞吐量(Throughout)**
3. **低延迟(Latency)**

如使用wrk进行压测，-c的参数表示并发的用户数，结果Request/sec表示 QPS or TPS，可以代表吞吐量，使用Latency参数后可以看到延迟

延迟(latancy):针对系统处理时间
响应时间（Response time）：针对客户端请求（相对延迟，还要考虑网络响应时间）
系统性能测量用P99: 99%以上的交易在哪个时间完成

### 实现高性能的负面作用
系统复杂度x10
建设维护成本
故障或BUG导致的破坏性x10以上

#### 应对策略
1. 容量
评估系统现在支持的并发用户量、吞吐量
2. 爆炸半径
新功能上线控制在一定范围内（微服务服务拆分）
3. 工程方面积累与改进


## Netty实现高性能
### Netty概览
网络应用开发框架
1. 异步
2. 事件驱动
3. 基于NIO

适用于：服务端、客户端、TCP/UDP

### 事件处理机制
1. 请求作为一个事件进来；事件有一个事件队列Event Queue
2. 事件分发器Event Mediator往后分发
3. 事件Event Channel接收事件的管道，根据不同的Event分配到不同的Event Channel
4. 事件处理Event Processor负责处理事件
#### 从事件处理机制到Reactor模型
Reactor模型首先是事件驱动的，有一个或者多个并发输入源，有一个Service Handler和多个EventHandlers。Service Handler会同步的将输入的请求多路复用的分发给相应的Event Handler
#### 从Reactor模型到Netty NIO
1. 模型1 - 单线程模型
dispatch分发，handler是单线程；由于单线程，处理比较慢
2. 模型2 - Reactor多线程模型
添加线程池，每个请求多线程处理（多个handler多个线程）
3. 模型3 - Reactor主从模型
拆两个Reactor，一个Reactor做Socket处理，另一个Reactor做业务处理，两个都多线程处理
#### Netty启动和处理流程
1. 创建NioEventLoopGroup(bossGroup, workerGroup)
2. ServerBootStrap.bind(port) NioServerSocketChannel注册bossGroup的Selector上面
3. 接受Client请求
4. 请求通过NioSocketChannel从boss EventLoopGroup到workerGroup
5. workerGroup的NioEventLoop处理
#### Netty的线程模式
每个NioEventLoop一个线程，SocketChannel绑定在EventLoop的Selector上（可以有多个Channel）,EventLoop内部有事件队列轮询监听IO事件，有事件进行处理。
**如果EventLoop阻塞了，后面所有的处理都很慢**

### 关键对象
1. Bootstrap: 启动线程，开启socket
2. EventLoopGroup
3. EventLoop
4. SocketChannel: 连接
5. ChannelInitializer: 初始化
6. ChannelPipeline: 处理器链
7. ChannelHandler: 处理器

**Bootstrap**
**EventLoop/EventLoopGroup**
**Channel**
**Hanlder**

#### ChannelPipeline
ChannelPipeline: 请求ChannelInboundHandlers;返回ChannelOutboundHandlers

#### Event & Handler
Inbound事件：
- 通道激活和停用
- 读操作
- 异常事件
- 用户事件

Outbound事件：
- 打开连接
- 关闭连接
- 写入数据
- 刷新数据

事件处理的程序接口：ChannelHandler, ChannelOutboundHandler, ChannelInboundHandler
适配器（空实现，需要继承使用）
- ChannelInboundHandlerAdapter
- ChannelOutboundHanlerAdapter

Netty应用的组成：
- 网络事件
- 应用程序逻辑事件
- 事件处理程序


## Netty网络程序优化
### 粘包和拆包
都是人为造成的问题，TCP底层机制不会造成这种问题。需要规范好怎样使个完成的数据包，定义好数据的边界
Netty的ByteToMessageDecoder提供一些常用的实现：
- FixedLengthFrameDecoder: 定长协议解码器，指定固定长度的字节数算一个完整的报文
- LineBasedFrameDecoder: 行分隔，遇到\n或者\r\n为完整报文
- DelimiterBasedFrameDecoder: 分隔符解码
- LengthFieldBasedFrameDecoder: 长度编码解码器，将报文划分为报文头/报文体
- JsonObjectDecoder: Json格式编码器

### Nagle与TCP_NODELAY
网络拥堵与Nagle算法优化
send和recv不是真正发送或接受数据，只是发送到内核或者从内核读数据
Nagle算法把发送的数据在缓冲区堆满后再发送，减少发送数据的次数，尽量发送大的数据包，提高网络效率
关掉算法就每次send都发出去

### 连接优化
TCP三次握手建立连接
TCP关闭连接（4次握手）：(客户端)主动关闭发起Fin，客户端进入Fin-WAIT-1，服务端响应Ack，客户端进入Fin-Wait-2，服务端进入close-wait，服务端发送完最后的数据后，发送Fin给客户端，服务端进入last-ack，客户端收到后进入time-wait，并发送ack到服务端，服务端收到ack就关闭，客户端等待2msl的时间才关闭，这是客户端还是占用端口。2msl根据不同的操作系统由不同。2msl客户端才能关闭，释放端口。
优化的话，减少msl的时间

### Netty优化
1. 不要阻塞EventLoop
2. 系统参数优化  ulimit -a /proc/sys/net/ipv4/tcp_fin_timeout, TcpTimedWaitDelay
3. 缓冲区优化 SO_RCVBUF/SO_SNDBUF/SO_BACKLOG/REUSEXXX
4. 心跳周期优化：心跳机制与短线重连
网络如果比较繁忙，可以考虑停掉心跳周期
5. 内存与ByteBuffer优化
DirectBuffer与HeapBuffer，减少内核态和用户态数据拷贝，使用DirectBuffer映射到程序里面（堆外内存）；HeapBuffer是堆内内存
6. 其他优化
- ioRatio
- waterMark
- TrafficShaping


## 网关API
API网关的只能：
1. 请求接入：所有API接口服务请求的接入点
2. 业务聚合：所有后端业务服务的聚合
3. 中介策略：实现安全、验证、路由、过滤、流量控制策略
4. 统一管理：对所有API服务和策略进行统一管理

网关分类：
1. 流量网关
关注稳定和安全：全局性流控、日志统计、防止SQL注入、防止Web攻击、黑白IP、证书\解密处理（如ngnix，OpenResty）
2. 业务网关
提供更好的服务：服务级别的流量控制、服务降级和熔断、路由与负载均衡、灰度策略、服务过滤、聚合发现、权限验证和用户等级策略、业务规则和参数校验、多级缓存策略

### 业务网关
#### Zuul
大部分服务网关的结构都差不多：请求进来->PreFilter->Router->服务—>PostFilter->请求返回
Zuul主要有三种过滤器：PreFilters，Router，PostFilter
Zuul2，核心用Netty内核重构，接入有个Netty Server Handlers，在访问真是后端，有个Netty Client Handler
请求进来Inbound Filter，到Endpoint Filter
Spring Cloud Gateway刚开始用Zuul，低层基于Netty改造

