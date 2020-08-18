package com.auto.interview.algorithm.leetcode.dp;

import com.auto.interview.algorithm.leetcode.utils.Assert;

/**
 * @author : jihai
 * @date : 2020/8/18
 *
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 *
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 */
public class HouseRobber {

    public static void main(String[] args) {
        HouseRobber houseRobber = new HouseRobber();
        Assert.assertTrue(houseRobber.rob(new int[]{1, 2, 3, 1}) == 4);
        Assert.assertTrue(houseRobber.rob(new int[]{2, 7, 9, 3, 1}) == 12);
        Assert.assertTrue(houseRobber.rob(new int[]{1, 1}) == 1);


        Assert.assertTrue(houseRobber.rob2(new int[]{1, 2, 3, 1}) == 4);
        Assert.assertTrue(houseRobber.rob2(new int[]{2, 7, 9, 3, 1}) == 12);
        Assert.assertTrue(houseRobber.rob2(new int[]{1, 1}) == 1);

    }

    /**
     * 动态规法解法
     * 1. 确定状态定义，d[i]为第i间房子的最大金额，第i间房偷与否
     *  不偷的话： d[i] = d[i - 1]
     *  偷的话：d[i] = nums[i] + d[i - 2]
     * 2. 确定状态转移方程
     *   d[i] = max(d[i - 1], nums[i] + d[i - 2])
     * 3. 初始化值  d[1],d[0]
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        return dp[nums.length - 1];
    }

    /**
     * 上面的方法1 时间复杂度为n, 空间复杂度也为n
     * 优化：滚动数组 可以把空间复杂度优化为常数级别
     */
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        int p = nums[0];
        int q = Math.max(nums[0], nums[1]);
        if (nums.length <= 2) return Math.max(p, q);
        for (int i = 2; i < nums.length; i++) {
            int temp = q;
            dp[i] = Math.max(q, nums[i] + p);
            q = dp[i];
            p = temp;
        }
        return dp[nums.length - 1];
    }
}
