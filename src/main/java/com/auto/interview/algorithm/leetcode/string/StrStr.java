package com.auto.interview.algorithm.leetcode.string;

/**
 * @author : jihai
 * @date : 2020/9/3
 * @description :
 *
 * 28. 实现 strStr()
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 *
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 *
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 */
public class StrStr {

    /**
     * 自己写的，用例过了，但是超时
     */
    public int strStr(String haystack, String needle) {
        if (haystack == null || haystack.length() < needle.length()) return -1;
        if (needle == null || needle.equals("")) return 0;
        for (int i = 0;i < haystack.length();i ++) {
            int j;
            int k = i;
            for (j = 0;j < needle.length() && k < haystack.length();j ++) {
                if (haystack.charAt(k ++) != needle.charAt(j)) break;
            }
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 参考了官方的答案
     */
    public int strStr2(String haystack, String needle) {
        if (haystack == null || haystack.length() < needle.length()) return -1;
        if (needle == null || needle.equals("")) return 0;
        int len = needle.length();
        for (int i = 0;i + len <= haystack.length();i ++) {
            if (haystack.substring(i, i + len).equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}
