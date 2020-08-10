package com.auto.interview.algorithm.leetcode.pass.sort;

/**
 * @author : jihai
 * @date : 2020/8/5
 * @description : 选择排序
 * 时间复杂度： n方
 * 一般人最快能想到的方法
 */
public class ChoiseSort implements Sort {


    /**
     * 遍历数组取最小的放到最前面
     * 每次取剩下的数组最小的放到最前面
     * @param array
     * @return
     */
    @Override
    public int[] order(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            // 遍历结束
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }

}
