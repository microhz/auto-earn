## 系统设计
分布式id生成
https://zhuanlan.zhihu.com/p/107939861

限流算法
https://segmentfault.com/a/1190000023552181


降级算法
压测方法、平台、框架
领域驱动设计
流程引擎实现
tmf与nbf
负载均衡算法
一行记录一个字段可能包含多种，可以用位运算
arthas实现原理
LRU算法手写
zset 可以做延迟,score为时间戳，消费者拉取处理再删除，原子性保证可以用lua
大数据去重：1.布隆过滤器（存在误判），2.可以用bitmap,两个bit就可以判断。 3.hash分组，重复的数字一定在每个文件


2PC和3PC，3PC多一步确认是否能提交事物，降低了锁持有的时间。
rocketMQ，可以有多个leader/master，每个master有slave,默认slave只做高可用，在延迟比较大的情况可以让slave消费
rocketMQ同步和异步刷盘保证高可用，
分布式锁的原子性

雪花算法、redlock
key

压测、限流、java文件转换为字节码.

限流方案：技术的方案方案，但是存在请求峰值的问题，可以用令牌桶（入的速度一定）和漏斗（出的速度一定）的方式，也可以用单机并发来限流。
1.漏斗算法，可以用一个线程池固定的速率来处理，多余的请求可以拒绝策略，2.令牌桶是起一个Timmer定时向桶里添加  参考RateLimiter，3. 时间窗口，4. 并发限流AOP, 可能窗口边界分布不均匀峰值。分布式的话可能用redis和sentinel.