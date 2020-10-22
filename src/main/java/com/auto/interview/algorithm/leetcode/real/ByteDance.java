package com.auto.interview.algorithm.leetcode.real;

/**
 * @author : jihai
 * @date : 2020/10/15
 * @description :
 * 任务
 * 面试官提出的问题将出现在这里。
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 * 由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水:
 *
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 *
 * 输出: 6
 */
public class ByteDance {
    public static void main(String[] args) {
        //
        //System.out.println(a);
        System.out.println(sumRains(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));

    }

    public static int sumRains(int[] array) {
        if (array == null || array.length == 0 || array.length == 1) return 0;

        int sum = 0;
        for (int i = 1;i < array.length - 1;i ++) {
            // 判断每个节点能接多少雨水
            int cur = array[i];
            int pre = array[i - 1];
            int after = array[i + 1];
            for (int j = i;j > 0;j --) {
                pre = Math.max(pre, array[j]);
            }
            for (int m = i;m < array.length;m ++) {
                after = Math.max(after, array[m]);
            }
            if (pre > cur && after > cur) {
                sum += (Math.min(pre, after) - cur);
            }
        }
        return sum;
    }
}
