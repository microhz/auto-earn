package com.auto.test;

import java.util.concurrent.*;

/**
 * @author : jihai
 * @date : 2021/3/9
 * @description :
 */
public class ThreadLocalTest {

    static InheritableThreadLocal requestIdLocal = new InheritableThreadLocal();

    public static void main(String[] args) {

        /*long requestId = System.currentTimeMillis();
        requestIdLocal.set(requestId);

        System.out.println("main get request Id : " + requestIdLocal.get());

        Thread thread = new Thread(() -> {
            System.out.println("sub thread get request id : " + requestIdLocal.get());
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end");*/


        /*long requestId = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        InheritableThreadLocal requestLocal = new InheritableThreadLocal();
        requestLocal.set(requestId);

        CyclicBarrier barrier = new CyclicBarrier(1);
        executorService.submit(() -> {
            System.out.println("thread 1 : " + requestLocal.get());
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        requestLocal.set(System.currentTimeMillis() + 10);
        barrier.reset();
        executorService.submit(() -> {
            System.out.println("thread 2 : " + requestLocal.get());
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        System.out.println("main end");
        executorService.shutdown();*/


        ThreadLocal<byte[]> threadLocal = new ThreadLocal();
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        executorService.submit(() -> {
            while (true) {
                System.out.println("添加");
                threadLocal.set(new byte[1024 * 1024 * 1024]);
            }
        });

    }
}

