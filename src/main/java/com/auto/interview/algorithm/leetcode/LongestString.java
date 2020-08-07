package com.auto.interview.algorithm.leetcode;

/**
 * @author : jihai
 * @date : 2020/8/6
 * @description :
 *
 * 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class LongestString {

    public static void main(String[] args) {

    }

    /**
     * 暴力法虽然可以求解，但是复杂度n的平方
     *
     * 动态规划的思路
     * 动态规划是初始化一个二维来保存相同字符串的长度
     *
     * 中心扩展法：根据字符串有偶数和奇数，可以找n + n - 1 个中心左右扩展
     */
    public String longestPalindrome(String s) {

        return null;
    }

}
