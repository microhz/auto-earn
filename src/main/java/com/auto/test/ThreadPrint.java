package com.auto.test;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : jihai
 * @date : 2020/8/23
 * @description :
 */
public class ThreadPrint {
        Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();

    public static void main(String[] args) {
        ThreadPrint threadPrint = new ThreadPrint();
        threadPrint.test();
    }
        public void test() {

            AtomicInteger state = new AtomicInteger(1);

            new Thread(() -> {
                for (int i = 0; i < 10; ) {
                    lock.lock();
                    if (1 == state.get()) {
                        System.out.println("A");
                        state.incrementAndGet();
                        conditionB.signal();
                        try {
                            conditionA.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        i++;
                        lock.unlock();
                    } else {
                        try {
                            conditionA.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            new Thread(() -> {
                try {
                    for (int i = 0; i < 10; ) {
                        lock.lock();
                        if (2 == state.get()) {
                            System.out.println("B");
                            state.incrementAndGet();
                            conditionC.signal();
                            conditionB.await();
                            i++;
                            lock.unlock();
                        } else {
                            conditionB.await();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                try {
                    for (int i = 0; i < 10; ) {
                        lock.lock();
                        if (3 == state.get()) {
                            System.out.println("C");
                            state.set(1);
                            conditionA.signal();
                            conditionC.await();
                            i++;
                            lock.unlock();
                        } else {
                            conditionC.await();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            java.util.concurrent.locks.LockSupport.park();

        }
}
