package com.auto.interview.algorithm.leetcode.number;

/**
 * @author : jihai
 * @date : 2020/9/14
 * @description :
 *
 *
 * 7. 整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 */
public class NumberParse {

    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }


    public int reverse2(int x) {
        int signal = x < 0 ? -1 : 1;
        int ret = reverseNumber(x >= 0 ? x : -x);
        if (ret - 1 == Integer.MAX_VALUE && signal == -1) return 0;
        return signal * ret;
    }

    int n = 1;
    public int reverseNumber(int x) {
        if (x < 10) return x;
        int k = x % 10;
        int a = reverseNumber(x / 10);
        if (a > Integer.MAX_VALUE / 10 || n > Integer.MAX_VALUE / 10 || k > Integer.MAX_VALUE / 10) return 0;
        n *= 10;
        int b = (k * n);
        return a > Integer.MAX_VALUE - b ? 0 : a + b;
    }
}
