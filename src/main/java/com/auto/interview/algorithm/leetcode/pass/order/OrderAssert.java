package com.auto.interview.algorithm.leetcode.pass.order;

import com.auto.interview.algorithm.leetcode.Assert;

/**
 * @author : jihai
 * @date : 2020/8/5
 * @description :
 */
public class OrderAssert {

    public static void main(String[] args) {

        int[] array = new int[]{5, 2, 3, 7, 1, 5, 10, 3, 4};
//        Order bubbleOrder = new BubbleOrder();
//        bubbleOrder.order(array);
//        Assert.assertResult(() -> assertOrder(array, 1, 2, 3, 3, 4, 5, 5, 7, 10));

//        Order choiseOrder = new ChoiseOrder();
//        choiseOrder.order(array);
//        Assert.assertResult(() -> assertOrder(array, 1, 2, 3, 3, 4, 5, 5, 7, 10));

        Order insertOrder = new InsertOrder();
        insertOrder.order(array);
        Assert.assertResult(() -> assertOrder(array, 1, 2, 3, 3, 4, 5, 5, 7, 10));
    }

    private static Boolean assertOrder(int[] array, int ... params) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != params[i]) {
                throw new RuntimeException("assert error");
            }
        }
        return true;
    }
}
