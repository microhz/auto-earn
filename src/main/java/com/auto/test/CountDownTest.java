package com.auto.test;

import java.util.concurrent.CountDownLatch;

/**
 * @author : jihai
 * @date : 2021/3/9
 * @description :
 */
public class CountDownTest {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(() -> {
                try {
                    Thread.sleep(999999999);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " ready");
                countDownLatch.countDown();
            });
            t.start();

        }

        countDownLatch.await();
        System.out.println("all ready");

    }
}
