package com.auto.interview.algorithm.leetcode.pass.sort;

import com.auto.interview.algorithm.leetcode.AssertUtils;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author : jihai
 * @date : 2020/8/5
 * @description : 排序的测试用例
 */
public class SortAssert {

    public static void main(String[] args) {
        List<Sort> allSortList = getAllOrderList();
        allSortList.forEach(e -> {
            System.out.println("开始排序方式：" + e.getClass().getName());
            assertOrderResult(e);
        });
    }

    /**
     * 所有排序算法
     */
    private static List<Sort> getAllOrderList() {
        return Lists.newArrayList(new BubbleSort(),
                new ChoiseSort(),
//                new CountSort(),
                new InsertSort(),
                new MergeIntoSort(),
                new QuickSort(),
                new ShellSort());
    }

    /**
     * 测试用例
     */
    private static void assertOrderResult(Sort sort) {
        // case 1
        AssertUtils.assertArray(sort.order(new int[]{4, 7, 6, 5, 3, 2, 8, 1}), 1, 2, 3, 4, 5, 6, 7, 8);
        // case 2
        AssertUtils.assertArray(sort.order(new int[]{5, 2, 3, 7, 1, 5, 10, 3, 4}), 1, 2, 3, 3, 4, 5, 5, 7, 10);
        // case 3
        AssertUtils.assertArray(sort.order(new int[]{3, 2, 1, 3, 4, 5, -1}), -1, 1, 2, 3, 3, 4, 5);
        // case 4
        AssertUtils.assertArray(sort.order(new int[]{22, 3, 5, 4, 12, 34, 11, 96, 34}), 3, 4, 5, 11, 12, 22, 34, 34, 96);

    }
}
