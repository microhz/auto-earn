package com.auto.interview.algorithm.leetcode.string;

/**
 * @author : jihai
 * @date : 2020/8/7
 * @description :
 *
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 *
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 *
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 *
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 */
public class RegularExpressionMatching {


    public static void main(String[] args) {
        RegularExpressionMatching regularExpressionMatching = new RegularExpressionMatching();
        regularExpressionMatching.isMatch2("ads", ".*ads");
    }

    /**
     * 官方解答
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                }
                else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    /**
     * 使用动态规划的方法
     * 从左向右匹配s
     *
     * s1 匹配，然后匹配p，如果能够联系匹配完s与p即可
     *
     * 拿s的第一个字符来匹配 p第一个字符，如果p是.则可以算匹配
     * 如果匹配的下一个是*，则可以匹配s1多个
     *
     */
    public boolean isMatch(String s, String p) {
        // 特殊的先判断
        if (s == "" && p == "") {
            return true;
        }

        if (s == "" || p == "") {
            return false;
        }

        /**
         * 分类去分析
         *
         * 2.   s    匹配一个字符串
         * 4.   s*   匹配字符串n个
         * 3.   .*   匹配任意一个字符串n个
         * 5.   .    匹配任意一个字符串
         */
        int match = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {

                String tempMatch;
                char curChar = p.charAt(j);
                if (curChar == '.') {
                    if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
                        tempMatch = ".*";
                        // 可以匹配任何一个字符串0~多个   .*ab  ab

                    } else {
                        tempMatch = ".";
                    }
                } else if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
                    tempMatch = p.charAt(j) + "*";
                } else {
                    tempMatch = String.valueOf(p.charAt(j));
                }
                // 四种case



                /*if (p.charAt(j) == '.') {
                    // 随机匹配1个
                    match ++;
                } else if (p.charAt(j) == s.charAt(i)) {
                    // 字符串精确匹配
                    if (j < p.length() - 1 && p.charAt(j) == '*') {
                        // 可以匹配多个
                        if (i < s.length() - 1 && s.charAt(i + 1) == s.charAt(i)) {
                            i ++;
                            match ++;
                        }
                        // 这里的*不用单独匹配
                        j ++;
                    } else {
                        // 只匹配一个

                    }
                } else if (j < p.length() - 1 && p.charAt(j) == '*') {
                    // 可以0匹配
                }*/
            }
        }
        return false;
    }
}
