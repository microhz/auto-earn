package com.auto.interview.algorithm.leetcode.array;

import com.auto.interview.algorithm.leetcode.base.Review;

/**
 * @author : jihai
 * @date : 2020/8/21
 * @description :
 *
 * 1480. 一维数组的动态和
 * 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
 *
 * 请返回 nums 的动态和。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,6,10]
 * 解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。
 * 示例 2：
 *
 * 输入：nums = [1,1,1,1,1]
 * 输出：[1,2,3,4,5]
 * 解释：动态和计算过程为 [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1] 。
 * 示例 3：
 *
 * 输入：nums = [3,1,2,10,1]
 * 输出：[3,4,6,16,17]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * -10^6 <= nums[i] <= 10^6
 */
@Review(1)
public class DymaticArraySum {

    public static void main(String[] args) {

    }

    /**
     * 方法1
     * 时间复杂度n2
     */
    public int[] runningSum(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        int[] returnNums = new int[nums.length];
        for (int i = 0;i < nums.length;i ++) {
            int sum = 0;
            for (int j = 0;j <= i;j ++) {
                sum += nums[j];
            }
            returnNums[i] = sum;
        }
        return returnNums;
    }

    /**
     * 方法2：时间复杂度为n
     * @param nums
     * @return
     */
    public int[] runningSum2(int[] nums) {
        int sum = 0;
        int[] returnNums = new int[nums.length];
        for (int i = 0;i < nums.length;i ++) {
            sum += nums[i];
            returnNums[i] = sum;
        }
        return returnNums;
    }
}
