package com.auto.interview.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * @author : jihai
 * @date : 2020/10/11
 * @description :
 */
public class SplitArray {

    public static void main(String[] args) {
        int[] array = new int[]{4,4,4,4,4,4,4,4,8,8,8,8,8,8,8,8,12,12,12,12,12,12,12,12,16,16,16,16,16,16,16,16,20,20,20,20,20,20,20,20,24,24,24,24,24,24,24,24,28,28,28,28,28,28,28,28,32,32,32,32,32,32,32,32,36,36,36,36,36,36,36,36,40,40,40,40,40,40,40,40,44,44,44,44,44,44,44,44,48,48,48,48,48,48,48,48,52,52,52,52,52,52,52,52,56,56,56,56,56,56,56,56,60,60,60,60,60,60,60,60,64,64,64,64,64,64,64,64,68,68,68,68,68,68,68,68,72,72,72,72,72,72,72,72,76,76,76,76,76,76,76,76,80,80,80,80,80,80,80,80,84,84,84,84,84,84,84,84,88,88,88,88,88,88,88,88,92,92,92,92,92,92,92,92,96,96,96,96,96,96,96,96,97,99};
        SplitArray splitArray = new SplitArray();
        splitArray.canPartition2(new int[]{1, 1, 2, 2});

        int a = 1;
    }


    public boolean canPartition2(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }


        boolean[][] dp = new boolean[n][target + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][target];
    }

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 != 0) return false;
        Arrays.sort(nums);
        return canPartition(sum / 2, 0, nums);
    }

    public boolean canPartition(int target, int index, int[] nums) {
        if (target == 0) return true;
        if (target < 0) return false;
        for (int i = index;i < nums.length;i ++) {
            if (canPartition(target - nums[i], i + 1, nums)) return true;
            while (i < nums.length - 1 && nums[i + 1] == nums[i]) i ++;
            System.out.println(i);
        }
        return false;
    }
}
