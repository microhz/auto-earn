package com.auto.interview.algorithm.leetcode.array;

import com.auto.interview.algorithm.leetcode.base.Review;

import java.util.Arrays;

/**
 * @author : jihai
 * @date : 2020/8/22
 * @description :
 *
 * 面试题 10.01. 合并排序的数组
 * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 *
 * 初始化 A 和 B 的元素数量分别为 m 和 n。
 *
 * 示例:
 *
 * 输入:
 * A = [1,2,3,0,0,0], m = 3
 * B = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 * 说明:
 *
 * A.length == n + m
 */
@Review(1)
public class MergeArray {

    public static void main(String[] args) {
        MergeArray mergeArray = new MergeArray();
        mergeArray.merge(new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3);
    }

    /**
     * 方法1：
     */
    // 两个指针，比较，如果小于就不动，大于就移动一个距离放进来
    public void merge(int[] A, int m, int[] B, int n) {
        if (B == null || B.length == 0) return;
        for (int i = m;i < A.length;i ++) {
            // 这里要设置一个最大值，否则不是一个递增的合并有问题
            A[i] = Integer.MAX_VALUE;
        }
        for (int i = 0, j = 0;i < A.length && j < B.length;) {
            if (A[i] <= B[j]) {
                i ++;
                continue;
            }
            for (int k = A.length - 1;k > i;k --) {
                A[k] = A[k - 1];
            }
            A[i] = B[j];
            j ++;
        }
    }

    /**
     * 方法2
     * 时间复杂度n
     */
    // 直接合并排序
    public void merge2(int[] A, int m, int[] B, int n) {
        if (B == null || B.length == 0) return;
        for (int i = m,j = 0;i < A.length;i ++) {
            A[i] = B[j ++];
        }
        Arrays.sort(A);
    }

    /**
     * 方法3：
     * 双指针 归并思想
     * 时间复杂度m + n
     * 空间复杂度m + n
     */
    public void merge3(int[] A, int m, int[] B, int n) {
        if (B == null || B.length == 0) return;
        int[] mergeArray = new int[m + n];
        int a = 0, b = 0;
        int i = 0;
        while (a < m && b < n) {
            if (A[a] <= B[b]) {
                mergeArray[i ++] = A[a];
                a ++;
            } else {
                mergeArray[i ++] = B[b];
                b ++;
            }
        }
        while (a < m) {
            mergeArray[i ++] = A[a ++];
        }
        while (b < n) {
            mergeArray[i ++] = B[b ++];
        }
        for (int j = 0;j < A.length;j ++) {
            A[j] = mergeArray[j];
        }
    }
}
