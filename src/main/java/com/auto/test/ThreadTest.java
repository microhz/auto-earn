package com.auto.test;

import com.auto.interview.algorithm.leetcode.utils.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.*;

/**
 * @author : jihai
 * @date : 2020/11/22
 * @description :
 */
public class ThreadTest {

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));

    public static void main(String[] args) {

//        long t1 = System.currentTimeMillis();
//
//        queryItem();
//
//        long t2 = System.currentTimeMillis();
//        System.out.println("同步查询耗时" + (t2 - t1));
//
//        queryItem2();
//
//
//        long t3 = System.currentTimeMillis();
//        System.out.println("多线程查询耗时" + (t3 - t2));


//        new Thread().start();
//        Integer[] a = new Integer[]{};
//        Arrays.sort(a, (o1, o2) -> o2 - o1);

        //[1,3,7,5,10,3]
        //3
        Assert.assertTrue(maxProfit(new int[]{1,3,7,5,10,3}, 3) == 8);
    }


    public static int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int buy = prices[0] + fee;
        int profit = 0;
        for (int i = 1; i < n; ++i) {
            if (prices[i] + fee < buy) {
                buy = prices[i] + fee;
            } else if (prices[i] > buy) {
                profit += prices[i] - buy;
                buy = prices[i];
            }
        }
        return profit;
    }

    private static void queryItem() {
        String s = queryStock();

        String s1 = queryImage();

        String s2 = queryCommentary();
        System.out.println(s + ", " + s1 + ", " + s2);
    }



    private static String queryCommentary()  {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "commentary";
    }

    private static String queryImage()  {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "image";
    }



    private static String queryStock() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "stock";
    }

    private static void queryItem2() {
        try {
            Future<String> submit = executor.submit(() -> queryCommentary());
            Future<String> submit1 = executor.submit(() -> queryImage());
            Future<String> submit2 = executor.submit(() -> queryCommentary());

            System.out.println(submit.get(3,TimeUnit.SECONDS));
            System.out.println(submit1.get(3,TimeUnit.SECONDS));
            System.out.println(submit2.get(3,TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }


}
