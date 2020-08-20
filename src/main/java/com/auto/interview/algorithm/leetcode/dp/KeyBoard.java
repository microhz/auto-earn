package com.auto.interview.algorithm.leetcode.dp;

import com.auto.interview.algorithm.leetcode.utils.Assert;

/**
 * @author : jihai
 * @date : 2020/8/19
 * @description :
 *
 * 650. 只有两个键的键盘
 * 最初在一个记事本上只有一个字符 'A'。你每次可以对这个记事本进行两种操作：
 *
 * Copy All (复制全部) : 你可以复制这个记事本中的所有字符(部分的复制是不允许的)。
 * Paste (粘贴) : 你可以粘贴你上一次复制的字符。
 * 给定一个数字 n 。你需要使用最少的操作次数，在记事本中打印出恰好 n 个 'A'。输出能够打印出 n 个 'A' 的最少操作次数。
 *
 * 示例 1:
 *
 * 输入: 3
 * 输出: 3
 * 解释:
 * 最初, 我们只有一个字符 'A'。
 * 第 1 步, 我们使用 Copy All 操作。
 * 第 2 步, 我们使用 Paste 操作来获得 'AA'。
 * 第 3 步, 我们使用 Paste 操作来获得 'AAA'。
 * 说明:
 *
 * n 的取值范围是 [1, 1000] 。
 */
public class KeyBoard {

    public static void main(String[] args) {
        KeyBoard keyBoard = new KeyBoard();
        Assert.assertTrue(keyBoard.minSteps(3) == 3);
        Assert.assertTrue(keyBoard.minSteps(4) == 4);
        Assert.assertTrue(keyBoard.minSteps(6) == 5);
        Assert.assertTrue(keyBoard.minSteps(18) == 8);


        Assert.assertTrue(keyBoard.minSteps2(3) == 3);
        Assert.assertTrue(keyBoard.minSteps2(4) == 4);
        Assert.assertTrue(keyBoard.minSteps2(6) == 5);
        Assert.assertTrue(keyBoard.minSteps2(18) == 8);
        // 8 cpppcp  cppppcp  cppppppp
        // 6 cppcp  cpppcp
    }

    /**
     * 方法1：
     * n 步区分两种情况
     * 如果n是素数，只能一个一个粘贴i个就需要i次操作
     * 如果n是非素数，n = m * n最快就是先粘贴出来m再粘贴出来n, 粘贴出来m需要m次，复制1次+(n - 1)次 = n 次，因此m + n
     *
     * 至于为什么m * n 一定大于一个一个粘贴，最开始我也觉得需要证明:
     * 现在证明这种分割方式使用的操作最少。原本需要 pq 步操作，分解后需要 p+q 步。因为 p+q <= pq，等价于 1 <= (p-1)(q-1)，当 p >= 2 且 q >= 2 时上式永远成立。
     *
     */
    public int minSteps(int n) {
        // 递归出口1
        if (n == 1) return 0;
        for (int i = 2; i < n / 2; i++) {
            if (n % i == 0) {
                // 递归找到最小
                return minSteps(n / i) + i;
            }
        }
        // 递归一旦找到素数出口2
        return n;
    }


    /**
     * 方法2：动态规划
     * dp[i] 表示i个字符需要的次数
     */
    public int minSteps2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            Integer min = i;
            for (int j = 2; j < i; j++) {
                // 可能多个除数，选个小的
                if (i % j == 0) {
                    min = Math.min(dp[j] + i / j, min);
                }
            }
            dp[i] = min;
        }
        return dp[n];
    }

}
