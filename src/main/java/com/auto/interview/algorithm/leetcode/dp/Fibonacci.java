package com.auto.interview.algorithm.leetcode.dp;

import com.auto.interview.algorithm.leetcode.utils.Assert;

/**
 * @author : jihai
 * @date : 2020/8/18
 * @description : 斐波那契数列 动态规划思想
 *
 * 步骤一：定义dp数组的含义 步骤二：定义状态转移方程 步骤三：初始化过程转移的初始值 步骤四：可优化点(可选)
 */
public class Fibonacci {

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        for (int i = 0; i < 50; i++) {
            System.out.println((i + 1) + ":" + fibonacci.fib(i + 1));
            System.out.println((i + 1) + ":" + fibonacci.fib2(i + 1));
        }
        Assert.assertTrue(fibonacci.fib(45) == 134903163);

    }


    /**
     * 动态规划
     * 避免传统递归的重复计算
     */
    public int fib(int n) {
        if (n < 2) return n;
        int dp1 = 0, dp2 = 1;
        for (int i = 1; i < n; i++) {
            int q = dp2;
            dp2 = (dp2 + dp1) % (int)(1e9 + 7);
            dp1 = q;
        }
        return dp2;
    }

    /**
     * 正常递归算法
     */
    public int fib2(int n) {
        return n < 2 ? n : (fib(n - 1) + fib(n - 2)) % (int)(1e9 + 7);
    }
}
