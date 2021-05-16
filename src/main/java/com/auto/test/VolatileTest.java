package com.auto.test;

import java.util.concurrent.CountDownLatch;

/**
 * @author : jihai
 * @date : 2021/3/29
 * @description :
 */
public class VolatileTest {

    private static volatile  boolean isStop = false;

    static int a = 0;

    public static void main(String[] args) throws InterruptedException {


        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread thread1 = new Thread(() -> {
            try {
                countDownLatch.await();
                Thread.sleep(1000);
                a = 1;
                isStop = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thirad 1 finished");
        });
        thread1.start();
        Thread thread = new Thread(() -> {
            countDownLatch.countDown();
            while (!isStop) {

            }
            System.out.println("thread 2 stopped, a = " + a);
        });
        thread.start();

        thread1.join();
        thread.join();
        System.out.println("main end");
    }
}
