package com.auto.interview.algorithm.leetcode.amazing;


import com.auto.interview.algorithm.leetcode.Assert;

/**
 * @author : jihai
 * @date : 2020/8/3
 * @description :
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * 示例 1:
 *
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 *
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-ways
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DecodeMethod {

    // 2342446923
    public static void main(String[] args) {
//        System.out.println((int)Character.valueOf('A'));

        DecodeMethod decodeMethod = new DecodeMethod();
        Assert.assertTrue(decodeMethod.numDecodings("30") == 0);
        Assert.assertTrue(decodeMethod.numDecodings("230") == 0);
        Assert.assertTrue(decodeMethod.numDecodings("611") == 2);
        Assert.assertTrue(decodeMethod.numDecodings("20") == 1);
        Assert.assertTrue(decodeMethod.numDecodings("21") == 2);


        Assert.assertTrue(decodeMethod.numDecodings("110") == 1);
        Assert.assertTrue(decodeMethod.numDecodings("226") == 3);
        Assert.assertTrue(decodeMethod.numDecodings("010") == 0);

        Assert.assertTrue(decodeMethod.numDecodings("301") == 0);
        Assert.assertTrue(decodeMethod.numDecodings("101") == 1);

    }

    public int numDecodings(String s) {
        final int length = s.length();
        if(length == 0)
            return 0;
        if(s.charAt(0) == '0')
            return 0;

        int[] dp = new int[length+1];
        dp[0] = 1;

        for(int i=0;i<length;i++){
            dp[i+1] = s.charAt(i)=='0'?0:dp[i];
            if(i > 0 && (s.charAt(i-1) == '1' || (s.charAt(i-1) == '2' && s.charAt(i) <= '6'))){
                dp[i+1] += dp[i-1];
            }
        }

        return dp[length];
    }
}
