redis集群方案有redis cluster类似于节点redirect， redis sharding客户端进行路由、proxy代理。几种分片和扩容方式对比。
redis按存储模式可以分为内存和硬盘，硬盘模式是在内存提供索引，内存模式是不依赖硬盘IO
redis压缩的二进制文件， save和bgsave命令，分别阻塞和不阻塞客户进程.可以通过配置来控制频率刷新写入RDB文件.
10w qps
redis快，内存，数据结构，单线程CPU不是瓶颈 ，多路复用IO，非阻塞。
Sorted set（zsort） 实现是使用的 skiplist表是分层链表，加快查询速度，不用遍历了，写入和删除都是随机层，不用动太多指针
redis String的 SDS一系列对空间的优化
rehash渐进式，在重新hash的过程操作两个表，这样避免一次性耗费大量时间处理.
zset 有序链表 使用skipList 
RDB和AOF的备份方式,AOF又分三种模式，主要是频率的控制 ,RDB更多适合大文件和恢复要更快， AOF适合数据的完整性.
redis 事务ACID的A原子性并不能总是保证，因为其没回滚特性，作者认为客户端的代码没问题就不应该报错。
redis 主从复制保持高可用，slave先sync拉取RDB文件，这个时候主master写到缓冲区不写RDB，RDB拉取完了加载到内存，master再同步缓冲区的命令给slave，后续建立连接进行
redis 主从同步，2.8版本之前都是全量同步，但是只是 redis失去连接或则停止运行恢复没必要全部重新拉取，所以slave会发送psync发送id标志和offset，master确认是之前连过就从offset开始同步，否则还是拉全量。
集群数据分布方式：节点取摸，一致性哈希(可以虚拟节点增加均匀，把一个真实节点拆成多个节点均匀分布到环上)，哈希槽(redis-cluster)。
集群的一些坑：批量操作，事务，限制为一个节点
哨兵是新引入一个进程高可用方案，哨兵也是集群，就是监控master是否存活，自动切换当前slave为master, 独立进程，也会互相监控.如果主机挂了，哨兵主观认为其master下线，等待其他哨兵也认为它下线然后进行投票选举新的master
sentinel也是一个集群，会连接所有master和slave redis节点，如果某台机器下线了，先主观下线，再根据其他sentinel节点的信息汇总，超过阀值就会判定为客观下线，进行投票选择新的。其中很多阀值可以进行配置 。
sentinel 会对master进行cmd连接通信，与其他sentinal节点用pub/sub进程通信。 
redis的淘汰策略，就是在有限内存内，如果继续写入，根据策略进行淘汰kv，否则超出了会与磁盘进行频繁交互影响性能，allkeys-lru算法淘汰，volatile-lru:设置过期时间的淘汰，随机淘汰等策略.
lru是根据最近使用的频率来看，用的很少就淘汰
4.0增加LRU，就是访问的频率
主要是从是否设置过过期时间的 key去淘汰，可以根据最先过期，或则通过访问次数和频率.还有比较暴力的随机淘汰策略。
redis的SDS直接存放了字符串的长度，避免了C语言的遍历计算长度和扩容开销，因为拼接一次之后会有个free长度去缓冲，避免每次拼接都扩容带来开销。缩容不会马上删除。 这就是两个解决方案：空间预分配和惰性空间释放.
redis的事务单线程不会中断所以天然保证隔离性,本质是一个队列把命令放进去一起执行。
redis的watch就是在事务开启之前监听某个key变化，乐观锁机制，如果变化了执行就会返回失败
String、Hash  List Set SortedSet HyperLog  GeoHash  Pub/Sub Redis Module  BloomFilter  RedisSearch Redis-ML
redis setnx可以复杂指令把setnx与expire合成为一个指令，原子操作
scan 可以避免 keys的匹配导致的单线程的阻塞，但是可能存在重复的，在客户端进行去重即可，增量式迭代命令可能存在老数据？
list  做异步队列，rpush生产消息，lpop消费消息，没消息就sleep一下或则可以blpop阻塞。
1次消费多次，用pub/sub...pub/sub有些情况消息会丢失，得使用rocketMQ来保证.
redis实现延迟队列的方式, sortedSet，时间戳score,消息内容zadd,消费者zrangebyscore获取n秒前的数据轮训处理。
AOF方式突然掉电，看sync配置，如果不求性能，就每次都sync，如果追求性能就1s一次，最多丢失1s数据。
RDB原理就是fork和cow,fork一个子进程，然后copy on write写入。
pipline可以多个IO合并为一次。
布隆过滤器的缺点是删除比较困难，可能存在误判。
redis的list采用ziplist或则双向链表来实现
hash采用ziplist和哈希表来实现
set用inset和哈希表
zset使用ziplist与skipList实现
缓存穿透（布隆过滤器），缓存击穿（一个线程拿到锁去家在数据库，另一个线程休眠重试查缓存）、缓存雪崩（过期时间离散一些）
skiplist严格的是上层是下层的1/2个节点，但是这样很容易写入一个数据破坏比例，因此redis底层使用的是每一个节点的写入会随机定义一个层数，这样保证了写入的性能. 如果是平衡树写入还要调整比较复杂 ，skiplist就只需要修改相邻的指针，简单快速.
查找单个值Hash更快，查找范围就需要skipList或则树了，但是skipList更灵活一些，写入性能更好。 
redis的zset是一个有序集合，节点数小于128、或则member小于 64（可配置）使用ziplist压缩列表,否则用跳跃表和哈希表去，查询为O(n)复杂度，修改由于首先要定位所以大于O(n)
zset在哈希和跳跃表两个结构维护，可以很快根据member去查询score,也可以很快遍历，但是他们的节点是一个节点，只是引用指向的同一个内存节省空间。
关于zset的结构这里有比较详细的图解：https://redisbook.readthedocs.io/en/latest/datatype/sorted_set.html
redlock是redis自己实现的一套分布式锁算法，平时我们在用的单机版的分布式锁，机器挂了就不可靠了，而且在主从的时候也可能导致锁丢失，所以redis自己实现，原理就是分别获取集群每个节点的锁（设置一个较短的超时时间，避免阻塞），获取锁的耗时必须小于需要锁定的时间 并且 获取到节点的大于一半的节点的锁 即可认为成功，最后再释放。
分布式锁要求的三个特性：安全性(只有一个客户端获取)、无死锁（持有锁的客户端分区崩溃也可以）、容错（redis大多数节点正常即可）
aof机制每次都是追加命令到aof_buf ，通过参数appendfSync配置同步刷磁盘周期，另外其实这样的文件非常大，可以定期进行AOF重写，但是重写的时候新的命令怎么办呢，可以fork一个子进程搞个重写缓冲区aof_rewrite_buf,重写完了再合并.
redis使用的Reactor单线程模型。

redis与zk分布式锁对比,redis不断的抢锁，性能开销会大些，客户端挂了只能等超时。zk不太适合高并发，客户端挂了锁自动失效。

主从复制过程：1.保存主节点信息 2.主从建立socket连接 3.发送ping 4.权限验证 5. 同步数据集 6. 命令持续复制
repl-disable-tcp-nodelay的用途？    yes为合并多个命令，节省带宽，但是一致性延迟大， no为立即同步，没有延迟.
区分全量复制与部分复制
1. slave主动要求全量复制或则master判断发现只能全量复制
主节点执行bgsave RDB + 一个复制缓冲区记录开始执行的所有写命令
先恢复RDB，然后同步复制缓冲区命令
如果从节点开启AOF，也会触发bgrewriteaof保证AOF最新
RDB的传输非常消耗带宽，从节点清除老数据，载入RDB过程是阻塞无法相应命令，bgrewriteaof也带来额外消耗
2. 偏移量、复制缓冲区、runid
主和从都有一个自己的offset偏移量，但是如果主的偏移量大于这个队列的固定长度，说明被挤压的命令存在丢失，只能全量同步了。所以可以通过repl-backlog-size控制缓冲区大小。
从节点网络隔离后恢复可能主节点机器已经变了，所以从节点也会有个主节点的id，如果id不同也会直接全量同步.
从节点slaveof命令，首先判断是否是第一次同步（全量）然后psync {runid}{offset}
心跳检测主要网络状态，min-slaves,检测命令丢失。
主：从节点状态，offset便宜，心跳延迟
    min-slaves-to-write 心跳检测的从的数量低于这个配置的值，那就拒绝写命令
    min-slaves-max-lag 从服务器延迟大于10s，写命令拒绝
    如果检测到从节点offset丢失，重发丢失的数据。
    
主从延迟比较大，可以优化网络，slave-serve-stale-data 如果yes延迟大也可服务，如果no延迟太大只能执行slaveof等少数命令
