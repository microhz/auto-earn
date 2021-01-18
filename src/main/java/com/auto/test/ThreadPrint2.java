package com.auto.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : jihai
 * @date : 2021/1/15
 * @description :题目：有A，B，C三个线程,，A线程输出A，B线程输出B， C线程输出C，要求，同时启动三个线程,，按顺序输出ABC，循环10次
 */
public class ThreadPrint2 {

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();

        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();

        PrintThread aPrint = new PrintThread('A', conditionB, conditionA, lock);
        PrintThread bPrint = new PrintThread('B', conditionC, conditionB, lock);
        PrintThread cPrint = new PrintThread('C', conditionA, conditionC, lock);

        new Thread(aPrint).start();
//        Thread.sleep(1000);
        new Thread(bPrint).start();
//        Thread.sleep(1000);
        new Thread(cPrint).start();
//        Thread.sleep(1000);

        try {
            lock.lock();
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    static class PrintThread implements Runnable {

        private char c;

        private Condition nextCondition;

        private Condition curCondition;

        private Lock lock;

        int count = 0;

        public PrintThread(char c, Condition nextCondition, Condition curCondition, Lock lock) {
            this.c = c;
            this.nextCondition = nextCondition;
            this.curCondition = curCondition;
            this.lock = lock;

        }

        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    curCondition.await();
                    System.out.println(c);
                    nextCondition.signal();
                    if (++ count == 10) break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
