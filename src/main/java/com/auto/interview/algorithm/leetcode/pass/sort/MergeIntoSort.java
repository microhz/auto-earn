package com.auto.interview.algorithm.leetcode.pass.sort;

import com.auto.interview.algorithm.leetcode.AssertUtils;
import org.apache.commons.lang.ArrayUtils;

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
 */
public class MergeIntoSort implements Sort {

    public static void main(String[] args) {
        MergeIntoSort mergeIntoSort = new MergeIntoSort();
        AssertUtils.assertArray(mergeIntoSort.order(new int[]{3, 2, 1}), 1, 2, 3);

        AssertUtils.assertArray(mergeIntoSort.order(new int[]{6,5,4,3,2,1,0}), 0, 1, 2, 3, 4, 5, 6);

        AssertUtils.assertArray(mergeIntoSort.order(new int[]{4, 2, 4, 1, 7, 11, -1}), -1, 1, 2, 4, 4, 7, 11);
    }

    /**
     * 把数组切分为左右两个部分
     * 分为左右都只剩一个
     * 构造新数组，有序合并左右
     * 不断的归并左右
     *
     * 时间复杂度 n log n
     * 空间复杂度 n
     *
     * 6,5,4,3,2,1,0
     */
    @Override
    public int[] order(int[] array) {
        return splitMerge(array, 0, array.length);
    }

    private int[] splitMerge(int[] array, int start, int end) {
        if (array.length == 0) {
            return new int[0];
        }
        if (array.length == 1) {
            return new int[]{array[0]};
        }
        int[] leftArr = splitMerge(ArrayUtils.subarray(array, 0, (start + end) / 2), 0, (start + end) / 2);
        int[] rightArr = splitMerge(ArrayUtils.subarray(array, (start + end) / 2, end), (start + end) / 2, end);
        int[] result = merge(leftArr, rightArr);
        return result;
    }

    private int[] merge(int[] leftArr, int[] rightArr) {
        int[] result = new int[leftArr.length + rightArr.length];
        int left = 0, right = 0, index = 0;
        while (left < leftArr.length || right < rightArr.length) {
            int val = 0;
            if (left >= leftArr.length) {
                val = rightArr[right];
                right ++;
            } else if (right >= rightArr.length) {
                val = leftArr[left];
                left ++;
            } else if (leftArr[left] <= rightArr[right]) {
                val = leftArr[left];
                left ++;
            } else if (leftArr[left] > rightArr[right]) {
                val = rightArr[right];
                right ++;
            }

            result[index ++] = val;
        }
        return result;
    }

    /**
     * 把数组切分为两个有序的部分
     * 再进行合并排序
     */
//    @Override
    public int[] order2(int[] array) {
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
