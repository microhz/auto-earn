package com.auto.interview.algorithm.leetcode.string;

import com.auto.interview.algorithm.leetcode.utils.Assert;

/**
 * @author : jihai
 * @date : 2020/9/2
 * @description :
 *
 * 680. 验证回文字符串 Ⅱ
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 *
 * 示例 1:
 *
 * 输入: "aba"
 * 输出: True
 * 示例 2:
 *
 * 输入: "abca"
 * 输出: True
 * 解释: 你可以删除c字符。
 * 注意:
 *
 * 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
 */
public class ValidateString {


    public static void main(String[] args) {
        ValidateString validateString = new ValidateString();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            Assert.assertTrue(validateString.validPalindrome2("bddb"));

            Assert.assertTrue(! validateString.validPalindrome2("addb"));
            Assert.assertTrue(! validateString.validPalindrome2("tav"));
            Assert.assertTrue(validateString.validPalindrome2("aba"));
            Assert.assertTrue(validateString.validPalindrome2("abca"));
        }
        System.out.println(System.currentTimeMillis() - start + " ms");
    }

    public boolean validPalindrome2(String s) {
        return valid(s.toCharArray(), 0, s.toCharArray().length - 1, false);
    }

    private boolean valid(char[] array, int start, int end, boolean skipCount) {
        if (end - start <= 0) return true;
        if (array[start] != array[end]) {
            if (! skipCount) {
                return valid(array, start + 1, end, true) || valid(array, start, end - 1, true);
            }
            return false;
        }
        return end - start >= 1 && valid(array, start + 1, end - 1, skipCount);
    }

//    public boolean valid(StringBuilder s, boolean isSkip) {
//        if (s.equals("") || s.length() == 1) return true;
//        if (s.charAt(0) != s.charAt(s.length() - 1)) {
//            if (! isSkip) {
//                return valid(s.substring(1), true) || valid(s.substring(0, s.length() - 1), true);
//            }
//            return false;
//        }
//        return s.length() > 1 && valid(s.substring(1, s.length() - 1), isSkip);
//    }


    /**
     * 超出时间限制
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        char[] array = s.toCharArray();
        boolean noSyme = false;
        for (int i = 0, j = array.length - 1;i < j;i ++, j --) {
            if (array[i] != array[j]) {
                noSyme = true;
                break;
            }
        }
        if (noSyme) {
            for (int i = 0;i < array.length;i ++) {
                int m = 0, n = array.length - 1;
                boolean sym = true;
                while (m < n) {
                    if (m == i) {
                        m ++;
                        continue;
                    }
                    if (n == i) {
                        n --;
                        continue;
                    }
                    if (array[m] != array[n]) {
                        sym = false;
                        break;
                    }
                    m ++;
                    n --;
                }
                if (sym) return true;
            }
            return false;
        }
        return true;
    }
}
