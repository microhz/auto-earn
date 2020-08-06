package com.auto.interview.algorithm.leetcode.pass.sort;

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

//        Order insertOrder = new InsertOrder();
//        insertOrder.order(array);
//        Assert.assertResult(() -> assertOrder(array, 1, 2, 3, 3, 4, 5, 5, 7, 10));

//        Order shellSort = new ShellSort();
//        shellSort.order(array);
//        Assert.assertResult(() -> assertOrder(array, 1, 2, 3, 3, 4, 5, 5, 7, 10));

        Order quickSort = new QuickSort();
        int[] array2 = new int[]{4, 7, 6, 5, 3, 2, 8, 1};
        quickSort.order(array2);
        Assert.assertResult(() -> assertOrder(array2, 1, 2, 3, 4, 5, 6, 7, 8));
        quickSort.order(array);
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
