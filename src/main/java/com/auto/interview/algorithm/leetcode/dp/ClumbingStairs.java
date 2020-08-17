package com.auto.interview.algorithm.leetcode.dp;

import com.auto.interview.algorithm.leetcode.utils.Assert;

/**
 * @author : jihai
 * @date : 2020/8/15
 * @description :
 *
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class ClumbingStairs {

    public static void main(String[] args) {
        ClumbingStairs clumbingStairs = new ClumbingStairs();
        Assert.assertTrue(clumbingStairs.climbStairs(1) == 1);
        Assert.assertTrue(clumbingStairs.climbStairs(3) == 3);

        Assert.assertTrue(clumbingStairs.climbStairs2(1) == 1);
        Assert.assertTrue(clumbingStairs.climbStairs2(3) == 3);
    }

    /**
     * DP算法
     * 本质是一个斐波那契数列
     * 方法1 ：递归法
     */
    public int climbStairs(int n) {
        return n > 2 ? climbStairs(n - 1) + climbStairs(n - 2) : ((n == 0 || n == 1) ? 1 : 2);
    }

    /**
     * 方法2：迭代法
     * fn = fn-1 + fn-2
     * 1  1  2  3  ... n
     *
     * 时间复杂度 n
     * 空间复杂度 1
     */
    public int climbStairs2(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 0; i < n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
}
