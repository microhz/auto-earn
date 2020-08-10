package com.auto.interview.algorithm.leetcode.pass.sort;

/**
 * @author : jihai
 * @date : 2020/8/6
 * @description : 快速排序
 * nlogn的复杂度，最快的方式
 */
public class QuickSort implements Sort {

    /**
     * 分治思想
     * 选择一个基准
     * 把比基准小的数字放左边，比基准大的放右边
     * 递归左右
     */
    @Override
    public int[] order(int[] array) {
        order(array, 0, array.length - 1);
        return array;
    }

    /**
     * 核心逻辑就是用分治法，递归去解决
     * 随机选一个基准数字，把整个数组比它小的元素放左边，大的放右边
     * 然后左右再递归
     *
     * 其中值得一提的是左右划分，是采用三个指针，一个指向坑，一个左指针，一个右指针
     * 从坑的相反端开始遍历，如果需要交换，就换坑
     * 这里有图文：https://www.sohu.com/a/246785807_684445
     * @param array
     * @param start
     * @param end
     */
    private void order(int[] array, int start, int end) {
        if (end <= start) {
            // 递归出口
            return ;
        }
        int pivot = array[start];
        int left = start;
        int right = end;
        // 坑
        int index = start;

        while (left < right) {
            if (index == left) {
                // 坑在左边
                // 从右边开始扫描
                if (array[right] < pivot) {
                    // 坑移动到右边
                    array[left] =  array[right];
                    left ++;
                    index = right;
                } else {
                    right --;
                }
            } else {
                // 坑在右边
                // 左向右
                if (array[left] > pivot) {
                    // 坑移动到左
                    array[right] = array[left];
                    right --;
                    index = left;
                } else {
                    left ++;
                }
            }
            // 从左边开始扫描
        }
        array[index] = pivot;

        order(array, start, index - 1);
        order(array, index + 1, end);
    }

}
