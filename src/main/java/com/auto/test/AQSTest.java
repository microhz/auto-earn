package com.auto.test;

import com.auto.interview.algorithm.leetcode.base.ListNode;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : jihai
 * @date : 2021/3/9
 * @description :
 */
@SpringBootApplication
@RestController
@RequestMapping("aqs")
public class AQSTest {
    static LockObject lockObject = new LockObject();

    public static void main(String[] args) throws InterruptedException {
        /*Thread thread = new Thread(() -> {
            try {
                lockObject.testLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();

        Thread thread2 = new Thread(() -> {
            try {
                lockObject.testLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread2.start();

//        thread.join();
//        thread2.join();
//        System.out.println("main end.");

        new SpringApplication(AQSTest.class).run(args);*/

        AQSTest aqsTest = new AQSTest();
        aqsTest.deleteDuplicates(NodeUtils.buildListNode(1,2,2,3));
    }


    @RequestMapping("/setStatus/{status}")
    public Object setStatus(@PathVariable("status")Integer status) {
        lockObject.setStatus(status);
        return "ok";
    }


    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        Map<Integer, Integer> countMap = new HashMap<>();
        ListNode preNode = new ListNode(Integer.MIN_VALUE);
        preNode.next = head;
        ListNode p = preNode;
        while (p != null) {
            countMap.put(p.val, countMap.getOrDefault(p.val, 0) + 1);
            p = p.next;
        }

        p = preNode;
        while (p != null) {
            ListNode next = p.next;
            while (countMap.get(next.val) > 1) {
                next = next.next;
            }
            p = next;
        }
        return preNode.next;
    }

}

class LockObject {

    ReentrantLock reentrantLock = new ReentrantLock();

    private volatile int status;

    public void testLock() throws InterruptedException {

        try {
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName() + " blocking");
            while (status != 1) {
                Thread.sleep(1000);
            }
//            Thread.sleep(1000000000);
            System.out.println(Thread.currentThread().getName() + " end blocking");
        } finally {
            reentrantLock.unlock();
        }

    }

    public void setStatus(int status) {
        this.status = status;
    }
}
