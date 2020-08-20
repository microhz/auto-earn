package com.auto.interview.algorithm.leetcode.dp;

import com.auto.interview.algorithm.leetcode.utils.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : jihai
 * @date : 2020/8/20
 * @description :
 *
 * 剑指 Offer 14- I. 剪绳子
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 示例 1：
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 * 提示：
 *
 * 2 <= n <= 58
 */
public class CutRope {

    public static void main(String[] args) {
        CutRope cutRope = new CutRope();
        Assert.assertTrue(cutRope.cuttingRope(10) == 36);
        Assert.assertTrue(cutRope.cuttingRope(3) == 2);
        Assert.assertTrue(cutRope.cuttingRope(4) == 4);
        Assert.assertTrue(cutRope.cuttingRope(2) == 1);


    }

    /**
     * dp一般解决，是否存在，计数，最大最小等问题，根据题意可以看到最大的关键字，优先考虑dp算法
     * dp[i] 定义为长度为i的绳子最大乘积
     * n = k[0] +...k[m - 1]
     * 递归写法
     */
    // 备忘录降低重复计算
    Map<Integer, Integer> map = new HashMap<>();
    public int cuttingRope(int n) {
        if (n == 2) return 1;
        int[] max = new int[]{Integer.MIN_VALUE};
        getMax(n, max);
        return max[0];
    }

    private void getMax(int n, int[] max) {
        if (map.get(n) != null) {
            max[0] = map.get(n);
            return ;
        }
        if (n == 1) {
            max[0] = n;
            return ;
        }
        for (int i = 2; i < n; i++) {
            int[] temp = new int[]{n - i};
            getMax(n - i, temp);
            max[0] = Math.max(max[0], temp[0] * i);
        }
        map.put(n, max[0]);
    }
}
