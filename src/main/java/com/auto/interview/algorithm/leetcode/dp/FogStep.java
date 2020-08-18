package com.auto.interview.algorithm.leetcode.dp;

import com.auto.interview.algorithm.leetcode.utils.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : jihai
 * @date : 2020/8/18
 * @description :
 *
 * 剑指 Offer 10- II. 青蛙跳台阶问题
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：n = 7
 * 输出：21
 * 示例 3：
 *
 * 输入：n = 0
 * 输出：1
 * 提示：
 *
 * 0 <= n <= 100
 */
public class FogStep {

    public static void main(String[] args) {
        FogStep fogStep = new FogStep();
        Assert.assertTrue(fogStep.numWays(3) == 3);
        Assert.assertTrue(fogStep.numWays2(3) == 3);
        Assert.assertTrue(fogStep.numWays3(3) == 3);

    }

    /**
     * 方法1
     * 动态规划
     * 1. dp[i] 为i阶的跳法
     * 2. dp[i] = dp[i - 1] + dp[i - 2]
     * 3. 初始值：dp[1] = 1, dp[0] = 1;
     *
     * 方法栈可以抽象成二叉树的遍历，可以发现很多子树是重复的
     * 递归存在大量重复计算问题，可以用哈希表存储计算过的节点，本质通过空间换时间
     */
    private Map<Integer, Integer> map = new HashMap<>();
    public int numWays(int n) {
        if (map.get(n) != null) return map.get(n);
        if (n < 2) return 1;
        map.put(n, (numWays(n - 1) + numWays(n - 2)) % (int)(1e9+7));
        return map.get(n);
    }

    /**
     * 方法2
     * 迭代算法
     * 根据动态规法状态转移方程
     * 本质是斐波那契数列
     */
    public int numWays2(int n) {
        if (n == 0) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1; dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % (int)(1e9+7);
        }
        return dp[n];
    }

    /**
     * 方法3 时间复杂度n 空间复杂n
     * 可以用滚动数组 把空间复杂度优化为常数级别
     */
    public int numWays3(int n) {
        if (n == 0 || n == 1) return 1;
        int[] dp = new int[n + 1];
        int p = 1, q = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (p + q) % (int)(1e9+7);
            int temp = q;
            q = dp[i];
            p = temp;
        }
        return dp[n];
    }
}
