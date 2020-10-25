package com.auto.interview.algorithm.leetcode.array;

/**
 * @author : jihai
 * @date : 2020/10/25
 * @description :
 *
 * 845. 数组中的最长山脉
 * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 *
 * B.length >= 3
 * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
 *
 * 给出一个整数数组 A，返回最长 “山脉” 的长度。
 *
 * 如果不含有 “山脉” 则返回 0。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[2,1,4,7,3,2,5]
 * 输出：5
 * 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
 * 示例 2：
 *
 * 输入：[2,2,2]
 * 输出：0
 * 解释：不含 “山脉”。
 */
public class MaxMontains {

    public int longestMountain(int[] A) {
        int n = A.length;
        int max = 0;
        for (int i = 1;i < n - 1;i ++) {
            int len = 1;
            int m = i;
            while (m - 1 >= 0 && A[m - 1] < A[m]) {
                len ++;
                m --;
            }
            int x = i;
            while (x + 1 < n && A[x + 1] < A[x]) {
                len ++;
                x ++;
            }
            if (len > 2 && m < i && x > i) max = Math.max(max, len);
        }
        return max;
    }
}
