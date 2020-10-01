package com.auto.interview.algorithm.leetcode.dp;

/**
 * @author : jihai
 * @date : 2020/10/1
 * @description :
 */
public class LeavesCollect {

    public int minimumOperations(String leaves) {
        // i为长度为i，j为状态，最小值, j 为红，黄，红 0 1 2
        int[][] dp = new int[leaves.length() + 1][3];
        dp[0][0] = leaves.charAt(0) == 'y' ? 1 : 0;
        dp[0][1] = dp[0][2] = dp[1][2] = Integer.MAX_VALUE;
        for (int i = 1;i < leaves.length();i ++) {
            int isYellow = leaves.charAt(i) == 'y' ? 1 : 0;
            int isRed = leaves.charAt(i) == 'r' ? 1 : 0;
            dp[i][0] = dp[i - 1][0] + isYellow;
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + isRed;
            if (i >= 2) {
                dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][2]) + isYellow;
            }
        }
        return dp[leaves.length() - 1][2];
    }
}
