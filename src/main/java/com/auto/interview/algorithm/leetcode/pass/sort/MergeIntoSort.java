package com.auto.interview.algorithm.leetcode.pass.sort;

import com.auto.interview.algorithm.leetcode.AssertUtils;

/**
 * @author : jihai
 * @date : 2020/8/10
 * @description :
 * 归并排序算法
 *
 * 分解
 * 解决
 * 合并
 *
 * 适合局部有序的算法
 * 稳定的
 *
 * 四季奶清
 */
public class MergeIntoSort implements Order {

    public static void main(String[] args) {
        MergeIntoSort mergeIntoSort = new MergeIntoSort();
        AssertUtils.assertArray(mergeIntoSort.order(new int[]{2, 3, 1, 2, -1}), -1, 1, 2, 2, 3);
    }

    /**
     * 把数组切分为两个有序的部分
     * 再进行合并排序
     */
    @Override
    public int[] order(int[] array) {
        return mergeInto(array, 0, array.length);
    }

    private int[] mergeInto(int[] array, int start, int end) {
        if (end <= start + 1) return new int[]{};
        int mid = (end + start) / 2;
        if (mid == 0) {
            return new int[]{array[start]};
        }
        int[] result = new int[end - start];
        int[] left = mergeInto(array, 0, mid);
        int[] right = mergeInto(array, mid, end);

        int k = 0;
        for (int i = 0, j = 0; i < left.length || j < right.length; ) {
            if (i >= left.length) {
                result[k ++] = right[j];
                j ++;
            } else if (j >= right.length) {
                result[k ++] = left[i];
                i ++;
            }else if (left[i] < right[j]) {
                result[k ++] = left[i];
                i ++;
            } else {
                result[k ++] = right[i];
                j ++;
            }
        }
        return result;
    }

}
