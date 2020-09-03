package com.auto.interview.algorithm.leetcode.string;

import com.auto.interview.algorithm.leetcode.utils.Assert;

/**
 * @author : jihai
 * @date : 2020/9/3
 * @description :
 */
public class MaxReverseString {

    public static void main(String[] args) {
        MaxReverseString maxReverseString = new MaxReverseString();
        Assert.assertTrue(maxReverseString.longestPalindrome("babad").equals("bab"));
        Assert.assertTrue(maxReverseString.longestPalindrome("cbbd").equals("bb"));
        Assert.assertTrue(maxReverseString.longestPalindrome("aaaa").equals("aaaa"));


    }


    /**
     * 参考了答案写的
     * 最大回文字符串
     * dp算法和中心扩展法
     */
    public String longestPalindrome(String s) {
        if (s == null || s.equals("")) return s;
        String ret = s.substring(0, 1);
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int len = 0; len < s.length(); len++) {
            for (int i = 0; i + len < s.length(); i++) {
                int j = i + len;
                if (len == 0) {
                    dp[i][j] = true;
                } else if (len == 1 && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                } else if (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)){
                    dp[i][j] = true;
                }
                if (dp[i][j] && len + 1 > ret.length()) {
                    ret = s.substring(i, j + 1);
                }
            }
        }
        return ret;
    }
}
