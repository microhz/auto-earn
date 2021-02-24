zookeeper,znode的数据存储信息,zk适合读多写少，每个节点最大不能1M.
zk集群，更新leader，再更新follower，读取可以任何机器.
一致性采用了ZAB(zk automic broadcast)协议.类似于paxos和raft
zk节点类型，临时节点，临时顺序节点，永久节点，永久顺序节点
zab定义的节点三个状态：looking,  following, leading, observing
选举节点：选举、发现、同步
zxid包含最大事务编号和epoch。
zab : discover->sync->broadcast
顺序一致性：https://segmentfault.com/a/1190000022248118
zk 并不适合做注册中心，会有不一致性的短暂时间负载不够均衡，而且不同机房分区后不能调同机房的机器不合理。
leader挂了，整个集群对外的服务停止开始选举。
3.4 后保留fastLeaderElection，分两个情况，启动集群已经有leader直接同步即可， 没leader就开始zxid和sid的投票信息进行交换，第一轮是投自己然后进行对比，如果收到比自己的大，那第二轮就拿大的继续投票，直到选出leader，由looking变更为following状态,投票的报文里还有队列或则第几轮和状态的标志，避免消息紊乱。 为了避免重复连接tcp，都是sid大的连接sid小的，建立连接就会判断这个sid如果比自己大直接断开 。
zk的不可用体现在可能丢掉部分请求，在选举期间，不提供服务。
脑裂问题是两个集群被分区了，因为有过半机制，所以分区的后，小的分区不会选出leader因为所有机器达成一致也不可能过半。(总机器数量为配置的，所以是直到集群的总量的)
client写请求 -> server1 (如果不是leader就转发给leader) -> leader写入并广播其他follower -> 一半能够ack -> 返回给server1 -> client 成功
