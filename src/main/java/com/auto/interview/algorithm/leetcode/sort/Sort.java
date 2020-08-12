package com.auto.interview.algorithm.leetcode.sort;

/**
 * @author : jihai
 * @date : 2020/8/5
 * @description :
 *
 * 比较堆排序与快速排序
 * 堆排序的空间复杂度比快速排序低，而且只取前n的情况下堆排序更省空间，两个都是不稳定的算法
 * 要稳定的化可以考虑归并排序, 但是空间复杂度比快排要慢一些
 */
public interface Sort {

    /**
     * 排序定义
     * @param array
     * @return
     */
    int[] sort(int[] array);
}
