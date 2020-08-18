package com.auto.interview.algorithm.leetcode.dp;

import com.auto.interview.algorithm.leetcode.utils.Assert;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    }


    /**
     * 动态规划
     * 1. 确定状态dp[i]表示金额为i的最小个数
     * 2. 状态转移: dp[i] = dp[i - coin[k]] + 1
     * 3. 初始值: coin k 只能枚举
     */
    public int coinChange(int[] coins, int amount) {
        ArrayList<Integer> list = new ArrayList<>();
        int i = coinChange2(coins, amount, list, 0);
        return i == Integer.MAX_VALUE ? -1 : i;
    }

    Map<Integer, Integer> map = new HashMap<>();
    private int coinChange2(int[] coins, int amount, List<Integer> list, int layer) {
        if (map.get(amount) != null) {
            return map.get(amount);
        }
        if (amount < 0) {
//            System.out.println(layer + " no ok: " + list);
            list.add(-1);
            return -1;
        }
        if (amount == 0) {
//            System.out.println(layer + " ok : " + list);
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            ArrayList<Integer> list1 = new ArrayList<>(list);
            list1.add(coins[i]);
            int ret = coinChange2(coins, amount - coins[i], list1, layer + 1);
            if (list1.contains(-1)) {
                continue;
            }
            if (ret != Integer.MAX_VALUE) {
                ret = ret + 1;
            }
            min = ret < min ? ret : min;
        }
        map.put(amount, min);
        return min;
    }


}
