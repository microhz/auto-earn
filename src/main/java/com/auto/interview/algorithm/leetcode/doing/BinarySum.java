package com.auto.interview.algorithm.leetcode.doing;

/**
 * @author : jihai
 * @date : 2020/8/14
 * @description :
 *
 * 67. 二进制求和
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 *
 * 输入为 非空 字符串且只包含数字 1 和 0。
 *
 *
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *
 *
 * 提示：
 *
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 */
public class BinarySum {

    public static void main(String[] args) {

    }

    /**
     * 转换为二进制
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        return Integer.toBinaryString(
                Integer.parseInt(a, 2) + Integer.parseInt(b, 2)
        );
    }


    public String addBinary2(String a, String b) {
       /* String s = new StringBuffer(a).reverse().toString();
        String s2 = new StringBuffer(b).reverse().toString();
        int first = 0, second = 0;
        int carray = 0;
        StringBuffer result = new StringBuffer();
        char[] chars1 = s.toCharArray();
        char[] chars2 = s2.toCharArray();
        while (first < s.length() && second < s2.length()) {
            int temp = Integer.valueOf(chars1[first]) + Integer.valueOf(chars2[second]) + carray;
            temp
        }*/
       return null;

    }
}
