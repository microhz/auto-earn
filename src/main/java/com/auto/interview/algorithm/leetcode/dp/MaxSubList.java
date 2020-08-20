package com.auto.interview.algorithm.leetcode.dp;

import com.auto.interview.algorithm.leetcode.utils.Assert;

/**
 * @author : jihai
 * @date : 2020/8/20
 * @description :
 *
 * 面试题 16.17. 连续数列
 * 给定一个整数数组，找出总和最大的连续数列，并返回总和。
 *
 * 示例：
 *
 * 输入： [-2,1,-3,4,-1,2,1,-5,4]
 * 输出： 6
 * 解释： 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶：
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class MaxSubList {

    public static void main(String[] args) {
        MaxSubList maxSubList = new MaxSubList();
        Assert.assertTrue(maxSubList.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}) == 6);
    }
    /**
     *
     * dp[i] 表示第i位连续最大的和
     * dp[i] = max(dp[i - 1] + nums[i], nums[i])
     * dp[0] = nums[0]
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        int max = dp[0];
        for (int i : dp) {
            max = i > max ? i : max;
        }
        return max;
    }
}
