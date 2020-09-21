package com.auto.interview.algorithm.leetcode.array;

/**
 * @author : jihai
 * @date : 2020/9/19
 * @description :
 *
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
 *
 *
 *
 * 示例：
 *
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *
 *
 * 进阶：
 *
 * 如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 */
public class LongMinSubArray {


    /**
     * 双指针, 自己写的
     */
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int start = 0, end = 0;
        int min = Integer.MAX_VALUE;
        int sum = nums[0];
        if (sum >= s) return 1;
        while (start <= end && end < nums.length) {
            if (sum < s) {
                end ++;
                if (end >= nums.length) break;
                sum += nums[end];
            } else if (sum > s) {
                sum -= nums[start];
                start ++;
            }

            if (sum >= s) {
                min = Math.min(end - start + 1, min);
                sum -= nums[start];
                start ++;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }


    /**
     * 官方的双指针写法，貌似要好很多
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen2(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int start = 0, end = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        while (end < nums.length) {
            sum += nums[end];
            while (sum >= s) {
                min = Math.min(min, end - start + 1);
                sum -= nums[start];
                start ++;
            }
            end ++;
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
