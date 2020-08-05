package com.auto.interview.algorithm.leetcode.amazing;

import com.auto.interview.algorithm.leetcode.Assert;

/**
 * @author : jihai
 * @date : 2020/8/4
 * @description :
 * 204. 计数质数
 * 统计所有小于非负整数 n 的质数的数量。
 *
 * 示例:
 *
 * 输入: 10
 * 输出: 4
 * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 */
public class PrimeNumber {

    public static void main(String[] args) {
        PrimeNumber primeNumber = new PrimeNumber();
        Assert.assertResult(() -> primeNumber.countPrimes(10) == 4);
    }

    public int countPrimes(int n) {
        int count = 0;
        boolean[] notPrim = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (notPrim[i] == false) {
                count ++;
                for (int j = 2; j * i < n; j++) {
                    notPrim[i * j] = true;
                }
            }
        }
        return count;
    }

}
