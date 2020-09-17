package com.auto.interview.algorithm.leetcode.array;

import com.auto.interview.algorithm.leetcode.utils.Assert;

/**
 * @author : jihai
 * @date : 2020/8/19
 * @description :
 *
 * 剑指 Offer 42. 连续子数组的最大和
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 *
 * 要求时间复杂度为O(n)。
 *
 *
 *
 * 示例1:
 *
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 */
public class MaxSubArray {

    public static void main(String[] args) {
        MaxSubArray maxSubArray = new MaxSubArray();
        Assert.assertTrue(maxSubArray.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}) == 6);
        Assert.assertTrue(maxSubArray.maxSubArray(new int[]{-1}) == -1);
        Assert.assertTrue(maxSubArray.maxSubArray(new int[]{-2, -1}) == -1);

        Assert.assertTrue(maxSubArray.maxSubArray2(new int[]{-2,1,-3,4,-1,2,1,-5,4}) == 6);
        Assert.assertTrue(maxSubArray.maxSubArray2(new int[]{-1}) == -1);
        Assert.assertTrue(maxSubArray.maxSubArray2(new int[]{-2, -1}) == -1);

    }

    /**
     *
     * 方法1：dp 算法
     * dp[i] 为前i最大子数组
     * dp[i] = max(dp[i - 1] + nums[i], nums[i])
     * 初始值为dp[0] = nums[0]
     * 时间复杂度 n
     * 空间复杂度 1
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1;i < nums.length;i ++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(dp[i], max);
        }
        return max;
    }

    /**
     * 方法2：
     * sum 累计，如果小于0设置为0
     * 求出max最大值即可
     */
    public int maxSubArray2(int[] nums) {
        int max = nums[0], sum = 0;
        for (int i : nums) {
            sum += i;
            max = sum > max ? sum : max;
            if (sum < 0) sum = 0;
        }
        return max;
    }

}
