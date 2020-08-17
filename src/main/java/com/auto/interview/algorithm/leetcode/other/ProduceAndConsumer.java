package com.auto.interview.algorithm.leetcode.other;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author : jihai
 * @date : 2020/8/15
 * @description :
 */
public class ProduceAndConsumer {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        Consumer consumer = new Consumer(linkedBlockingQueue);
        executorService.submit(consumer);
        Producer producer = new Producer(linkedBlockingQueue);
        for (int i = 0; i < 100; i++) {
            Thread.sleep(4000);
            executorService.submit(
                    () -> {
                        producer.addTask(() -> {
                            System.out.println(Thread.currentThread().getName() + " executed !");
                        });
                    }
            );
        }
    }
}

class Producer {

    private LinkedBlockingQueue<Runnable> linkedBlockingQueue;

    public Producer(LinkedBlockingQueue<Runnable> linkedBlockingQueue) {
        this.linkedBlockingQueue = linkedBlockingQueue;
    }

    public void addTask(Runnable runnable) {
        linkedBlockingQueue.add(runnable);
    }
}

class Consumer implements Runnable {

    private LinkedBlockingQueue<Runnable> linkedBlockingQueue;

    public Consumer(LinkedBlockingQueue<Runnable> linkedBlockingQueue) {
        this.linkedBlockingQueue = linkedBlockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                Runnable take = linkedBlockingQueue.take();
                take.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
