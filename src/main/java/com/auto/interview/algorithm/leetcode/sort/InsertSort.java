package com.auto.interview.algorithm.leetcode.sort;

/**
 * @author : jihai
 * @date : 2020/8/5
 * @description : 插入排序需要用一个额外的空间来
 * 时间复杂度 n平方
 */
public class InsertSort implements Sort {

    /**
     * 核心思想就是默认第一个元素已经排好序，从第一个元素开始往前移动，移动到一个合适的位置
     * @param array
     * @return
     */
    @Override
    public int[] sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int insertVal = array[i];
            for (int j = i; j >= 0 ; j--) {
                if (array[j] > insertVal) {
                    array[j + 1] = array[j];
                    array[j] = insertVal;
                }
            }
        }
        return array;
    }
}
