多线程用于商品查询组装，并发去查询.
分布式锁退款，取消 

spring的循环依赖解决方案，1. 重新设计依赖关系 2.懒加载 3.setter加载
Spring的构造一个对象是先是实例化依赖的对象再设置属性值。实例化了对象就会放到缓存里面，不会再继续递归去实例化。三级缓存不同成品的bean对象.


nio就是避免了bio的一个连接保持一个服务器的内存消耗。采用不停轮询的方式，具体实现java使用了客户端的buffer 和一个双工的channel和服务端的selector去操作的。selector不停的轮询多个channel把数据发送给客户端的buffer
steam是单向，channel是双向的
同步就是要拿结果，无论是等待或则不断的轮询都是同步。异步就是不用等待，通知我就好了。
阻塞就是数据就绪后，采用的方式是等待还是立即返回。

TCP协议的滑动窗口避免阻塞发生。不用每个包都等待。可以等待一批再等待，这样提高网络吞吐量。
Dubbo的SPI机制，就是有个ExtensionLoader可以根据情况加载接口的不同实现。
CopyOnWriteArrayList的思想就是对于并发容器读写分离，但是写入加锁也会阻塞读，这样不好，所以采取复制一个对象写入完成再把原先引用指向来解决，这个可能存在脏读的风险。


循环依赖问题
AQS
redis的优化
引用类型
项目亮点和难题
hashMap死锁
StringBuffer 相比StringBuilder的优势
每日算法 
零拷贝技术
消息队列的高可用，顺序性
kafka的消费者于partition是固定的么
kafka的磁盘处理
分布式事务超时
B+结构
表分区具体怎么操作
日活，量级
treeMap与treeSet区别
各个排序算法
线程池参数
redis事务协调
意向锁
流量控制
mybatis事务
https原理
nio bio io
2pc和3pc
mapreduce介绍下

内存屏障

zk的顺序一致性
redis 集群的proxy高可用和高性能问题
java对象创建过程
对象访问定位有几种
双亲委派
dump文件
嵌入式tomcat
RequestMapping和GetMapping区别

对Mike Cohn的测试金字塔了解多少?
Mock或Stub有什么区别?
什么是OAuth ?
什么是CopyOnWrite 
redis怎么实现分布式锁
redis高并发和高可用
redis数据类型和应用
Dubbo SPI机制
redis和zk分布锁对比
redis集群如何做分片
事务的隔离级别如何控制 

自我介绍要谈项目和角色

Java反射原理
Concurrent包实现


项目亮点：
tcp半连接,在服务端维护了一个sync的队列，就是半连接队列，从收到请求到第三次握手成established中间会放里面，默认会又50，溢出直接丢掉，因为jit还没触发，导致才部署的机器连接剧增，客户端在第二次连接之后就established了，所以结果就是客户端认为连接成功，服务端没连接，导致正式传输数据失败。
stock库存高并发优化 : 机器排队，case when sql，日志和扣减的事务的顺序，避免死锁：排序.数据一致性，最终一致性：上游消息与幂等。
持有锁时间优化、合并redis刷新、异步化、skuId扣减顺序避免死锁问题。
接口测试的维护
设计模式的应用
binlog异步刷缓存