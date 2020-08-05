package com.auto.interview.algorithm.leetcode.pass.order;

/**
 * @author : jihai
 * @date : 2020/8/5
 * @description :
 */
public class BubbleOrder implements Order {

    /**
     * 两两相互比较
     * 时间复杂度 n方
     * @param array
     * @return
     */
    @Override
    public int[] order(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }
}
