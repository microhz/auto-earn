package com.auto.interview.algorithm.leetcode.dp;

import com.auto.interview.algorithm.leetcode.utils.Assert;

import java.util.ArrayList;

/**
 * @author : jihai
 * @date : 2020/8/17
 * @description :
 *
 * 322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 *
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *
 *
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 */
public class CoinChange {

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        Assert.assertTrue(coinChange.coinChange(new int[]{1, 2, 5}, 11) == 3);
        Assert.assertTrue(coinChange.coinChange(new int[]{2}, 3) == -1);

        Assert.assertTrue(coinChange.coinChange2(new int[]{1}, 1) == 1);
        Assert.assertTrue(coinChange.coinChange2(new int[]{1, 2, 5}, 11) == 3);
        Assert.assertTrue(coinChange.coinChange2(new int[]{2}, 3) == -1);

    }


    public int coinChange2(int[] coins, int amount) {
        int i = coinChange2(coins, amount, new int[amount + 1]);
        return i == Integer.MAX_VALUE ? -1 : i;
    }

    /**
     * 递归求解至少n个硬币
     */
    private int coinChange2(int[] coins, int amount, int[] amounts) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        if (amounts[amount] != 0) return amounts[amount];
        // 默认最大值
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int ret = coinChange2(coins, amount - coins[i], amounts);
            if (ret >= 0 && ret < Integer.MAX_VALUE) {
                ret ++;
                min = ret < min ? ret : min;
            }
        }
        amounts[amount] = min;
        return min;
    }


    /**
     * 动态规划
     * 1. 确定状态dp[i]表示金额为i的最小个数
     * 2. 状态转移: dp[i] = dp[i - coin[k]] + 1
     * 3. 初始值: coin k 只能枚举
     */
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        // 备忘录
        if (count[rem - 1] != 0) return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }


//    public int coinChange(int[] coins, int amount) {
//        return coinChange(coins, amount, new int[amount]);
////        return i == Integer.MAX_VALUE ? -1 : i;
//    }
//
//    private int coinChange(int[] coins, int amount, int[] amounts) {
//        if (amount < 0) {
//            return -1;
//        }
//        if (amount == 0) {
//            return 0;
//        }
//        int min = Integer.MAX_VALUE;
//        for (int i = 0; i < coins.length; i++) {
//            int ret = coinChange(coins, amount - coins[i], new int[amount]);
//            if (ret != Integer.MAX_VALUE) {
//                ret = ret + 1;
//            }
//            min = ret < min ? ret : min;
//        }
//        amounts[amount] = min;
//        return min;
//    }


}
