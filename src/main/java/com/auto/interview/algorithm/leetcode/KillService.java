package com.auto.interview.algorithm.leetcode;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : jihai
 * @date : 2020/8/5
 * @description : 实现一个应用层排队秒杀逻辑
 */
public class KillService {

    /**
     * 模拟MySQL的库存记录
     */
    public static Stock stock;

    /**
     * 秒杀的skuId
     */
    private static Long killSkuId = 1L;

    public static void main(String[] args) {
        // 初始化库存数量
        stock = new Stock(1);

        // 模拟10个并发
        CountDownLatch countDownLatch = new CountDownLatch(10);
        KillService killService = new KillService();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 2, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10), new ThreadFactoryBuilder().setNameFormat("user-request-%d").build(), new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    /**
                     * countDownLatch模拟多个线程并发
                     */
                    countDownLatch.countDown();
                    countDownLatch.await();
                    System.out.println(Thread.currentThread().getName() + " -> 开始并发抢购");
                    long start = System.currentTimeMillis();
                    // 发送并发请求
                    Result result = killService.commitRequest(killSkuId, 1);
                    long end = System.currentTimeMillis();
                    System.out.println(Thread.currentThread().getName() + " 秒杀结果 : " + result.isSuccess() + "，耗时:" + (end - start) + "ms");
                    stock.showOperateNumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * 并发计数
     */
    Map<Long, AtomicInteger> counter = new ConcurrentHashMap<>();

    /**
     * 秒杀核心排队逻辑
     * @param count
     */
    private Result commitRequest(Long skuId, int count) {
        Result result = new Result();
        try {
            // 累计阈值
            incr(skuId);
            // 判断是否达到并发阈值
            if (killConcurrent(skuId)) {
                KillPromise killPromise = addOrCreateSkuQueue(skuId, count);
                try {
                    System.out.println(Thread.currentThread().getName() + " 挂起等待排队");
                    // 用户线程最多等待200ms
                    killPromise.waitPromise(200);
                    System.out.println(Thread.currentThread().getName() + " 被唤醒");
                    if (killPromise.isOk()) {
                        result.setSuccess(true);
                    } else {
                        result.setSuccess(false);
                        if (killPromise.getException() != null) {
                            result.setMsg(killPromise.getException().getMessage());
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    result.setSuccess(false);
                    result.setMsg("超时");
                }
                return result;
            }
            // 如果没到阈值执行这里
            // 普通单线程直接访问DB
            if (stock.duductStock(skuId, count)) {
                result.setMsg("库存不足");
                result.setSuccess(false);
                return result;
            }
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        } finally {
            // 释放阈值
            decr(skuId);
        }
        return result;
    }

    private void decr(Long skuId) {
        counter.get(skuId).decrementAndGet();
    }

    /**
     * 避免并发递增
     * @param skuId
     */
    private synchronized void incr(Long skuId) {
        AtomicInteger atomicInteger = counter.get(skuId);
        if (atomicInteger == null) {
            counter.put(skuId, new AtomicInteger(1));
        } else {
            atomicInteger.incrementAndGet();
        }
    }


    /**
     * sku-扣减库存请求
     */
    Map<Long, MergeRequestWorker> mergeRequestWorkerMap = new ConcurrentHashMap<>();

    private Object lock = new Object();

    /**
     * 如果秒杀队列不存在就创建，否则直接加入队列
     */
    private KillPromise addOrCreateSkuQueue(Long skuId, int count) {
        StockParam stockParam = new StockParam();
        KillPromise killPromise = new KillPromise();
        killPromise.setRequestThread(Thread.currentThread());
        stockParam.setKillPromise(killPromise);
        stockParam.setSkuId(skuId);
        stockParam.setCount(count);

        // 避免并发创建
        synchronized (lock) {
            MergeRequestWorker mergeRequestWorker = mergeRequestWorkerMap.get(skuId);
            if (mergeRequestWorker == null) {
                System.out.println(Thread.currentThread().getName() + " : ->创建新队列");
                mergeRequestWorker = new MergeRequestWorker();
                mergeRequestWorkerMap.put(skuId, mergeRequestWorker);
            } else {
                System.out.println(Thread.currentThread().getName() + ", 已存在队列，加入队列");
            }
            mergeRequestWorker.createOrAddStockParam(stockParam);
        }
        return killPromise;
    }

    /**
     * 判断是否创建队列的阈值
     */
    private boolean killConcurrent(Long skuId) {
        return Optional.ofNullable(counter.get(skuId)).orElse(new AtomicInteger(0)).get() > 2;
    }

}

/**
 * 队列封装
 * 单线程轮训合并用户请求并执行
 */
class MergeRequestWorker  implements Runnable {

    /**
     * 用户线程队列
     */
    LinkedBlockingQueue<StockParam> stockParamQueue = new LinkedBlockingQueue<>(10);;

    public void createOrAddStockParam(StockParam stockParam) {
        stockParamQueue.add(stockParam);
        // 起步执行轮训队列
        new Thread(this).start();
    }

    @Override
    public void run() {
        List<StockParam> stockParamList = new ArrayList<>();
        while (true) {
            try {
                // 队列为空等待100ms
                if (stockParamQueue.isEmpty()) {
                    Thread.sleep(100);
                    if (stockParamQueue.isEmpty()) {
                        return ;
                    }
                }

                int size = 0;
                while (true) {
                    StockParam poll = stockParamQueue.poll();
                    // 10个请求一起合并
                    if (poll != null && size <= 10) {
                        stockParamList.add(poll);
                    } else {
                        // 队列为空或则达到最大批量，先执行
                        break;
                    }
                }

                System.out.println(Thread.currentThread().getName() + " , 合并线程 : " + stockParamList);
                mergeOperateStock(stockParamList);
                stockParamList.clear();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 合并执行用户多个线程
     * @param stockParamList
     */
    private void mergeOperateStock(List<StockParam> stockParamList) {
        Long skuId = stockParamList.get(0).getSkuId();
        // 合并数量
        Integer count = stockParamList.stream().mapToInt(StockParam::getCount).sum();
        boolean success = KillService.stock.duductStock(skuId, count);
        if (success) {
            // 合并成功
            System.out.println(Thread.currentThread().getName() + " 合并扣减成功，通知用户线程");
            stockParamList.forEach(e -> e.getKillPromise().notifyRequest());
        } else {
            // 合并不成功，可以拆分用户线程分别执行
            // 按照扣减数量依次扣减
            System.out.println(Thread.currentThread().getName() + " 开始合并后的单独扣减");

            // 排序，让扣减数量少的先扣减，尽量满足最多请求量成功
            stockParamList.sort((s1, s2) -> {
                if (s1.getCount() < s2.getCount()) {
                    return -1;
                } else if (s1.getCount() > s2.getCount()) {
                    return 1;
                }
                return 0;
            });

            /*
             逐个执行用户线程，退化为单线程
             */
            for (StockParam stockParam : stockParamList) {
                if (KillService.stock.duductStock(stockParam.getSkuId(), stockParam.getCount())) {
                    stockParam.getKillPromise().notifyRequest();
                } else {
                    KillPromise killPromise = stockParam.getKillPromise();
                    killPromise.setException(new Exception("合并逐序扣减库存不足"));
                    killPromise.setOk(false);
                    System.out.println(Thread.currentThread().getName() + " 合并逐序扣减库存不足 : " + stockParam);
                    stockParam.getKillPromise().notifyRequest();
                }
            }
        }
    }

}

/**
 * 队列元素
 */
@Data
class StockParam {
    /**
     * 用户线程引用
     */
    private KillPromise killPromise;

    /**
     * 目标skuId
     */
    private Long skuId;

    /**
     * 扣减数量
     */
    private Integer count;

    @Override
    public String toString() {
        return "StockParam{" +
                "killPromise=" + killPromise +
                ", skuId=" + skuId +
                ", count=" + count +
                '}';
    }
}

/**
 * 合并线程对用户线程的返回
 */
@Data
class KillPromise {

    private Exception exception;

    private boolean ok = true;

    private Thread requestThread;

    public synchronized void notifyRequest() {
        super.notify();
    }

    @Override
    public String toString() {
        return "KillPromise{" +
                "exception=" + exception +
                ", ok=" + ok +
                ", requestThread=" + requestThread +
                '}';
    }

    /**
     * 用户线程阻塞等待
     */
    public synchronized void waitPromise(int maxWaitTime) throws InterruptedException {
        wait(maxWaitTime);
    }
}

/**
 * 封装秒杀扣减库存的结果
 */
@Data
class Result {

    private boolean success;

    private String msg;
}

/**
 * 模拟MySQL的InnoDB索引更新库存的行锁
 */
@Data
class Stock {

    private Long skuId = 1L;

    /**
     * 剩余库存
     */
    private Integer availCount;

    /**
     * 操作次数
     */
    private int operteNumer;

    public Stock(Integer availCount) {
        this.availCount = availCount;
    }

    /**
     * 扣减库存， synchronized 关键字模拟行锁
     * 模拟只有库存不足才会返回false
     */
    public synchronized boolean duductStock(Long skuId, Integer count) {
        System.out.println(Thread.currentThread().getName() + " : 剩余库存 ： " + this.availCount + ",扣减：" + count);
        if (this.availCount >= count && this.skuId.equals(skuId)) {
            this.availCount = availCount - count;
            operteNumer ++;
            return true;
        }
        return false;
    }

    public void showOperateNumer() {
        System.out.println(Thread.currentThread().getName() + " : 一共操作了:" + operteNumer + "次库存记录, 剩余库存 : " + this.availCount);
    }
}