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
        Assert.assertTrue(maxSubList.maxSubArray2(new int[]{-2,1,-3,4,-1,2,1,-5,4}) == 6);
        Assert.assertTrue(maxSubList.maxSubArray2(new int[]{1, 2}) == 3);
        Assert.assertTrue(maxSubList.maxSubArray3(new int[]{-2,1,-3,4,-1,2,1,-5,4}) == 6);
        Assert.assertTrue(maxSubList.maxSubArray3(new int[]{1, 2}) == 3);

    }
    /**
     * 方法1：
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

    /**
     * 方法2：分治法
     * 分别获取子数组的最大子数组
     */
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int[] max = new int[]{nums[0]};
        getMaxSubArray(nums, max);
        return max[0];
    }

    private int getMaxSubArray(int[] nums, int[] max) {
        return getMaxSubArray(nums, nums.length, max);
    }

    private int getMaxSubArray(int[] nums, int length, int[] max) {
        int ret;
        if (length - 1 == 0) {
            ret = nums[0];
        } else {
            int pre = getMaxSubArray(nums, length - 1, max);
            ret = pre <= 0 ? nums[length - 1] : (pre + nums[length - 1]);
        }
        max[0] = max[0] > ret ? max[0] : ret;
        return ret;
    }


    /**
     * 方法3：
     * 当连续数组为负数，临时求和为0即可
     * 相比动态规划，空间复杂度降为常数
     */
    public int maxSubArray3(int[] nums) {
        int sum = Integer.MIN_VALUE, temp = 0;
        for (int i = 0; i < nums.length; i++) {
            temp += nums[i];
            sum = Math.max(temp, sum);
            if (temp < 0) temp = 0;
        }
        return sum;
    }
}
