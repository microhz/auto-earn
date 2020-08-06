package com.auto.interview.algorithm.leetcode.pass.sort;

/**
 * @author : jihai
 * @date : 2020/8/5
 * @description : 希尔排序，第一个突破n方复杂度 低于n平方
 */
public class ShellSort implements Order {


    /**
     * 根据一个步长，逐渐缩小去排序
     * @param array
     * @return
     */
    @Override
    public int[] order(int[] array) {
        int step = array.length / 2;
        for (int i = step; i > 0; i --) {
            for (int j = 0; j + i < array.length; j++) {
                if (array[j] > array[j + i]) {
                    int temp = array[j + i];
                    array[j + i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }
}
