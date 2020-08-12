package com.auto.interview.algorithm.leetcode.sort;

import com.auto.interview.algorithm.leetcode.utils.AssertUtils;

/**
 * @author : jihai
 * @date : 2020/8/12
 * @description :
 *
 * 时间复杂度：nlgn
 * 空间复杂度 1
 */
public class HeapSort implements Sort {

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        AssertUtils.assertArray(heapSort.sort(new int[]{3, 2, 1, 5}), 1, 2, 3, 5);
    }

    /**
     * 遍历数组，构造堆
     * 构造完成根和最后一个元素交换
     *
     * 剩下的n - 1个元素继续构造堆
     *
     *    3
     *   2 1
     *  5
     *
     *  第一次
     *    5
     *
     */
    @Override
    public int[] sort(int[] array) {
        if (array == null || array.length == 0) return new int[]{};
        for (int i = 0; i < array.length; i++) {
            constractHeap(array, array.length - i - 1);
            exchange(array, array.length - i - 1);
        }
        return array;
    }

    private void exchange(int[] array, int i) {
        int temp = array[0];
        array[0] = array[i];
        array[i] = temp;
    }

    // 左子树 2*n + 1, 2*n + 2
    // 构造大根堆，升序排列
    private void constractHeap(int[] array, int length) {
        int parent = length / 2;
        for (int i = parent; i >= 0; i --) {
            int right = i * 2 + 2;
            int left = i * 2 + 1;
            if (right <= length) {
                // 右孩纸
                if (array[right] > array[i]) {
                    swap(array, right, i);
                }
            }
            if (left <= length) {
                // 左孩纸
                if (array[left] > array[i]) {
                    swap(array, left, i);
                }
            }
        }
    }

    private void swap(int[] array, int v, int v2) {
        int temp = array[v];
        array[v] = array[v2];
        array[v2] = temp;
    }
}
