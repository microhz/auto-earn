package com.auto.interview.algorithm.leetcode.pass.sort;

import com.auto.interview.algorithm.leetcode.AssertUtils;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author : jihai
 * @date : 2020/8/5
 * @description :
 */
public class OrderAssert {

    public static void main(String[] args) {
        List<Order> allOrderList = getAllOrderList();
        allOrderList.forEach(e -> {
            System.out.println("开始排序方式：" + e.getClass().getName());
            assertOrderResult(e);
        });
    }

    private static List<Order> getAllOrderList() {
        return Lists.newArrayList(new BubbleOrder(),
                new ChoiseOrder(),
//                new CountSort(),
                new InsertOrder(),
                new MergeIntoSort(),
                new QuickSort(),
                new ShellSort());
    }

    private static void assertOrderResult(Order order) {
        int[] array = new int[]{5, 2, 3, 7, 1, 5, 10, 3, 4};
        int[] array2 = new int[]{4, 7, 6, 5, 3, 2, 8, 1};
        int[] result = order.order(array2);
        // case 1
        AssertUtils.assertArray(result, 1, 2, 3, 4, 5, 6, 7, 8);
        result = order.order(array);
        // case 2
        AssertUtils.assertArray(result, 1, 2, 3, 3, 4, 5, 5, 7, 10);
        // case 3
        AssertUtils.assertArray(order.order(new int[]{3, 2, 1, 3, 4, 5, -1}), -1, 1, 2, 3, 3, 4, 5);
    }

    private static Order getOrder() {
        return new MergeIntoSort();
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
