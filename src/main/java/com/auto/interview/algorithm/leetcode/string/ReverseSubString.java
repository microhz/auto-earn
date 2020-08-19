package com.auto.interview.algorithm.leetcode.string;

import com.auto.interview.algorithm.leetcode.utils.Assert;

/**
 * @author : jihai
 * @date : 2020/8/19
 * @description :
 * 647. 回文子串
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 *
 *
 * 示例 1：
 *
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 *
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *
 *  dggaaer
 * 提示：
 *
 * 输入的字符串长度不会超过 1000 。
 */
public class ReverseSubString {

    public static void main(String[] args) {
        ReverseSubString reverseSubString = new ReverseSubString();
        Assert.assertTrue(reverseSubString.countSubstrings("aaa") == 6);
        Assert.assertTrue(reverseSubString.countSubstrings("abc") == 3);

        Assert.assertTrue(reverseSubString.countSubstrings2("aaa") == 6);
        Assert.assertTrue(reverseSubString.countSubstrings2("abc") == 3);
    }

    /**
     * 方法1：
     * dp 动态规划
     * dp[i] 为字符串第i位组成的回文
     * dp[i] = dp[i - 1] + k  其中k为(0~i)位组成的回文个数
     * 初始值 dp[0] = 1
     *
     * 可以发现存在很多重复计算，时间复杂度很高 接近与n3
     * 空间复杂度 n
     */
    public int countSubstrings(String s) {
        if (s == "" || s == null) return 0;
        int[] dp = new int[s.length()];
        dp[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            int k = count(s, i);
            dp[i] = dp[i - 1] + k;
        }
        return dp[s.length() - 1];
    }

    private int count(String s, int i) {
        int count = 0;
        for (int j = i; j >= 0; j --) {
            if (reverse(s, j, i)) {
                count ++;
            }
        }
        return count;
    }

    /**
     * 判断是否是回文
     */
    private boolean reverse(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                start ++;
                end --;
            } else {
                return false;
            }
        }
        return true;
    }


    /**
     * 方法2：
     * 中心扩展法
     * 从可能的中心左右扩展
     * n2复杂度能解决
     *
     * 0 1 2 3 4 5 6 7 8
     */
    public int countSubstrings2(String s) {
        if (s == "" || s == null) return 0;
        int count = 0;
        for (int i = 0; i < s.length() * 2 + 1; i++) {
            // 区分下标是奇数还是偶数
            // n如果是偶数，则 n / 2直接可以得到中心，左右均从此开始
            // n如果是奇数，则 (n + 1) / 2 为左 和 (n + 1) / 2 - 1为右
            int left = 0, right = 0;
            if (i % 2 == 0) {
                left = i / 2;
                right = left;
            } else {
                right = (i + 1) / 2;
                left = right - 1;
            }
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                count ++;
                left --;
                right ++;
            }
        }
        return count;
    }
}
