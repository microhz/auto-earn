package com.auto.test;

import com.auto.interview.algorithm.leetcode.utils.Assert;

/**
 * @author : jihai
 * @date : 2020/8/13
 * @description :
 *
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 * 提示：
 *
 * num1 和num2 的长度都小于 5100
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
 */
public class AddSum {

    public static void main(String[] args) {
        AddSum addSum = new AddSum();
        Assert.assertTrue(addSum.addSum("123", "321").equals("444"));
        Assert.assertTrue(addSum.addSum("754", "321").equals("1075"));
    }

    /**
     * "3456"
     * "79232"
     *
     * 字符串转换为字符，字符ascii码转换成数字
     *
     */
    public String addSum(String num1, String num2) {
        StringBuffer reverse1 = new StringBuffer(num1).reverse();
        StringBuffer reverse2 = new StringBuffer(num2).reverse();
        char[] chars1 = reverse1.toString().toCharArray();
        char[] chars2 = reverse2.toString().toCharArray();

        int carr = 0;
        int first = 0, second = 0;
        int sum = 0, n = 1;
        while (first < chars1.length || second < chars2.length) {
            int temp = 0;
            if (carr > 0) {
                temp += carr;
            }
            if (first < chars1.length) {
                temp += char2Num(chars1[first ++]);
            }
            if (second < chars2.length) {
                temp += char2Num(chars2[second ++]);
            }
            sum +=  (temp % 10) * n;
            carr = temp / 10;
            n *= 10;
        }
        if (carr >= 1) {
            sum += carr * n;
        }
        return new StringBuffer(sum).reverse().toString();
    }

    private int char2Num(char c) {
        return (int)c  - 48;
    }
}
