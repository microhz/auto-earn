rocketMQ是开源版本，metaQ notify 和aliware MQ都是以rocketMQ为内核开发出来的产品，metaQ主要是pull,解决顺序消息和海量堆积问题， Notify是推模型，解决事务消息， Aliware MQ则是商业化的版本.
rocketMQ : nameServer, broker, producer, consumer
nameServer和zk的功能差不多，但是更轻量
producer只是向master发送消息，但是consumer可以从master和slave订阅消息 
rocket参考的kafka设计的,都采用了零拷贝技术,在可靠性和事务性上做了优化，性能略降低。
吞吐量在十万级别，kafka达到百万级别
kafka 消息还没到broker就返回成功，rocketMQ会保证发送到再返回，因此kafka可能会丢数据 （刚好broker宕机）
因为只需要一个比较轻量高可用的协调集群，所以没引入zk
零拷贝，就是避免CPU把数据从磁盘读取到内核空间缓存和socket缓存的两次复制，用一些技术让出了CPU资源。
消息重复一般都是让消费者控制的，可以redis判断唯一id控制
rocketMQ在4.5之前master-slave是没有故障自动转移，需要人工介入，4.5之后支持故障切换，因为引入了DLedger记录commitLog，可以用raft选举出新的leader
nameServer检查broker存活是有延迟的，因此如何规避broker的问题，一般就是重试和--
rocketMQ一个区别就是采用全局的一个commitlog来记录producer顺序写入的消息， kafka每个partition都一个顺序写入commitlog，在大量partition或则topics会导致IO剧增性能下降。
消息不丢失，发送，broker存储，消费三个阶段。1.发送主要利用刷盘或则主从的，四个状态，成功、刷盘超时、同步slave超时、slave不可用。2. 存储可以开启刷盘成功再返回，默认是5s.
消息不重复消费，consumer消费定期会提交offset给mq,因此存在部分消息重复的可能，幂等保证。
每20s会来重新进行负载均衡
新上了消费节点，节点会心跳通知broker, broker再通知老的节点去开始rebalance. 默认 AllocateMessageQueueAveragely， 例如三个节点消费5个队列，那就 {1,2} {3,4} {5}



高可用的核心就是冗余副本（partition）
leader是partition的维度
创建topic可以设置复制因子，决定副本的个数
一个topics有多个partition分布在不同的机器上，每个partition有多个副本在不同的机器
kafka的写入写入性能取决于ISR集合中最慢的broker接受消息的性能, 运维可以最好识别出来最慢的broker干掉
这个 ISR怎样才算跟得上，这个可以通过参数配置，可以是落后的消息数量也可以是follower主要fetch（也是配置）的延迟时间
replica.lag.time.max.ms就是落后的数量，但是这样可能在峰值的时候频繁删除ISR和恢复，所以kafka是优化了落后数量判断并且下个fetch时间内没追上，才会移除ISR
每个partition（leader patition）都有个一个ISR(In-Sync Replicas)，维护着跟自己partition同步的副本
leader有两个指针，指向committed和logEndOffset，分别指向已经提交的和还没同步完成的.(https://zhuanlan.zhihu.com/p/56440807)
acks参数在生产者里，有三个选项0,1,all.  0-客户端发送出去就不管了，性能最高但是可靠性最差，1-leader保存还没同步就返回成功，all-必须要同步ISR里的机器成功再返回，性能最差
kafka消费者记录offset
Kafka HW:high waterbark,最小的ISR机器的水位 leo:logEndOffset   hw和leo都是最后一条的下一条
顺序性，parition内是顺序写入的，所以消费也是有序，如果要topic有序，那就partitio的个数设置为1，当然这样就没高可用了
kafka consumer如果超过了partition个数，那多余的机器不会消费到topics,所以这里没法线性扩展.
kafka 的高性能，partition的并行处理能力, 磁盘的顺序写能力，把partition分为多个segment，segment对应一个文件，按文件删除.
生产和消费差不多，Page cache避免写入磁盘的等待，直接消费，但是可能宕机丢失，不过可以用partition冗余解决这个问题。
零拷贝 kafka节点投递给消费者的时候避免了内核态到用户态的复制过程 https://www.jianshu.com/p/835ec2d4c170
批量发送消息，和数据压缩，broker发送搞得时候再解压，整个过程磁盘的数据写入写出都降低了