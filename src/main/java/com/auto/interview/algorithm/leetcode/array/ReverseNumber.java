package com.auto.interview.algorithm.leetcode.array;

import com.auto.interview.algorithm.leetcode.utils.Assert;

/**
 * @author : jihai
 * @date : 2020/9/23
 * @description :
 */
public class ReverseNumber {

    public static void main(String[] args) {
        ReverseNumber reverseNumber = new ReverseNumber();

        Assert.assertTrue(! reverseNumber.isPalindrome2(1234));
        Assert.assertTrue(reverseNumber.isPalindrome2(121));
        Assert.assertTrue(! reverseNumber.isPalindrome2(-121));
        Assert.assertTrue(! reverseNumber.isPalindrome2(10));
        Assert.assertTrue(! reverseNumber.isPalindrome2(1234));
    }


    /**
     * 自己想出来的递归方式
     * @param x
     * @return
     */
    public boolean isPalindrome2(int x) {
        if (x < 0) return false;
        sum = 0;
        return x == getReverse2(x);
    }
    int sum = 0; // 1234
    public int getReverse2(int x) {
        if (x == 0) return 0;
        sum = x % 10 + sum * 10;
        getReverse2(x / 10);
        return sum;
    }

    /*int n = 1;
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int origin = x;
        while (x > 9) {
            x /= 10;
            n *= 10;
        }
        return getReverse(origin) == origin;
    }

    public int getReverse(int x) {
        if (x == 0) return 0;
        int a = n * (x % 10);
        n /= 10;
        return a + getReverse(x / 10);
    }*/
}
