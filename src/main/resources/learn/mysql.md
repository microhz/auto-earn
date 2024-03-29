mysql innoDB如果没定义primary key，就选择第一个唯一键的索引作为pk。
为什么要用自增主键好，因为顺序插入，数据紧凑不需要挪动数据。非自增的话会为了保证B+树的顺序，所以可能频繁移动造成碎片，不紧凑性能降低。
索引，有序，二分查找快
B+是有序的，叶子节点有指针指向。
哈希是无序的，单key查找不需要遍历树，速度极快，但是不擅长范围查询和排序，联合作引左匹配也不支持。
B+查询的时候引擎会分析查询自动触发自适应查询构建哈希索引。客户端不可控制，隐式的。 
表分区是把单表的物理存储拆分成多个，逻辑上还是一个，分表是按某个业务字段进行分表。分区可以存储更多数据，需要并行查询汇总，删数据更容易。
MVCC 提高了读的并发度
limit 1找到一条就停止，可以是一个优化点
MRR(multi range read) 就是读取了索引一起然后排序再去磁盘上拿数据，避免频繁IO磁盘非顺序读。因为mysql都是一页一页的查询，顺序读后一页的缓存不会丢失导致非顺序读重复读某页，会找到需要数据结束后再失效缓存。
B+主要是避免了B树的遍历排序问题
innoDB 存在缓冲管理，索引和数据全部缓存起来，加快查询速度
innoDB相比MySAM最大特点就是支持事务，损失效率换来的
explain的index是索引全部扫描，ref是更快的查找
联合索引的的b+树都是根据第一个索引字段去构建的 
mysql执行:连接器->查询缓存->分析器->优化器，查询缓存是缓存整张表.除非是静态表缓存价值不大。
意向锁是表锁，与其互斥的也是普通表锁，一个数据要对某行加锁，就要先获取表的意向锁再获取表的行锁。意向锁增加了并发度，表和行锁共存。
乐观锁、悲观锁，共享锁和排他锁都是悲观锁。
mysql大量update的时候，就算走的索引也会升级为表锁，因为行锁的开销大。不走索引的话也是直接表锁.
间隙锁，给不存在的数据区间加锁，避免幻读(因此在写多的场景，尽量避免范围加锁, 和大范围加锁)。如果使用条件记录给不存在的记录加锁，也会间隙锁。
update和delete 索引区分度不高的场景，引发锁表。
innoDB有监控线程主动检测死锁并通知，回滚代价最小的那个事务。
show OPEN TABLES where In_use > 0查看是否锁表.
死锁文章https://juejin.im/post/5b82e0196fb9a019f47d1823
内存三大模块：缓冲池、重做缓存池(Redo Log Buffer)和额外内存池. 
innoDB为了抵抗高并发，抵消CPU与IO的速度差异，引入了缓冲池，读就是先读缓存池读不到再读磁盘然后写入到缓存池，跟缓存一样一样的，写的话也是修改缓存池再去刷盘，降低IO交互。当然如果全表扫描的话会导致缓存池被占满，这个采用了LRU算法淘汰避免。
MySQL都是日志先行，写数据前都会Redo Log,定期CheckPoint将RedoLog刷入磁盘。
写入都是批量写入缓存量达到某个值再刷盘。但是这里如果宕机就没办法RedoLog了，所以要用两次写入，不仅写入内存缓冲池，还要在磁盘共享空间也写个副本，避免丢失。
访问热点的页进行hash自适应，类似于JIT。
重做日志缓冲，写RedoLog然后更改缓冲区，更改的页叫脏页，根据Checkpoint将脏页刷到磁盘。
MySQL日志：错误日志、二进制文件、查询日志、慢查询日志。InnoDB日志：Redo Log和Undo Log
Mysql主从复制，master会在启动后开启一个dump线程写入日志并通知slave, slave会启动IO线程和 SQL线程读取和relay日志.
磁盘寻找一次会寻道，寻点，复制到内存。一次IO加在一页，这个一页的大小跟操作系统的bit有关系，可能是8byte或则4byte,加载一页性能高。
MyISAM不需要支持事务，快速返回count(*), innoDB执行count(*)遍历全表，性能差。
在InnoDB下count(id)遍历全表，拿到id累加，count(1)遍历但是不取值直接+1，因此更快。
InnoDB由于MVVC导致可见性，所以无法像MyISAM一样直接缓存行数。count(*) count(1) 会选择最小的二级索引进行遍历。
缓冲池只针对普通索引写入优化（写缓存再写个 redo log），因为非唯一索引还是要去查磁盘无法避免。
change_buffer可以设置大小，优化写入。
行级锁都是基于索引的，如果一条SQL语句用不到索引是不会使用行级锁的，会使用表级锁。
change_buffer对于普通索引和唯一索引的处理还不太一样，普通索引在更新的时候缓存页存在就更新缓存页，不存在也把操作写入缓存页，下次读取到内存再进行merge，唯一索引的话change_buffer不存在就得去查询出来更新。
非唯一键，写多读少的表适合使用changeBuffer，因为多次写入可以进行合并一次merge（当然在关机的时候也会触发merge）
change_buffer写入缓存区存在与否，缓存区存在就直接更新缓存区k1，不存在就写个k2操作在change_buffer，并且顺序写入redolog磁盘.两次内存操作，一次磁盘操作，如果此时崩溃，因为redolog持久化了，所以可以恢复。
读取的时候的时候k1可以直接缓冲区读出来，k2可以在脏页的进行change_buffer的合并得到正确的结果。这个change_buffer会定期刷到磁盘覆盖脏页面。 
redolog顺序写提升，changeBuffer降低IO次数。 
changeBuffer参考：https://www.jb51.net/article/155737.htm
redolog是解决事务的一致性(写入缓冲)、undolog保证事务的原子性(delete对应insert..)
binlog是数据变更，有三种格式:row、statement、mix
explain 语句, const 返回一个性能高 （system是特殊的const），type比较重要,index就是扫描全部索引，如果extra Using index就是走了索引覆盖， row越少越好，说明扫描的少，extra: Using filesort就是按照非B+树的顺序的字段排序了，可以优化掉。
B树和B+树的区别就是 1.B树的非叶子节点也存放了数据，B+树没有，这样就可以让非叶子节点只做索引，可以加载更多索引在内存进行索引，降低IO次数, 查询效率更稳定，数据都存在叶子节点。 2.B+在叶子节点更方便的进行范围查询，叶子节点之间会有指针，内存存储基本也连续。
mysql update如果不走索引是首先加全表加行锁，再过滤把不符合的行锁释放掉，但是这样的开销是很大的。
DML太久可能是purge阻塞导致，由于大事务导致的undo log太多,由于某些大事务一直要读一些需要删除的数据，一直没提交，导致unpurge无法清理，当unpurge list大于某个值时就会阻塞DML请求。
SHOW ENGINE innoDB status;
SHOW global variables LIKE "%lag%"


# 相关深度文章
mysql buffer pool 详解：https://juejin.cn/post/6923373860469506062
链表的作用重点关注


# 索引相关理论
https://juejin.cn/post/6923788859712995336
索引下推减少回表
大表加索引的方案？

# https://juejin.cn/post/6918908595778093070
pt-osc大表DDL不锁表方案

# mysql与es索引
https://juejin.cn/post/6881298078201036807