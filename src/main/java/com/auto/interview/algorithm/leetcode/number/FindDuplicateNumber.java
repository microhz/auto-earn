package com.auto.interview.algorithm.leetcode.number;

import com.auto.interview.algorithm.leetcode.utils.Assert;

/**
 * @author : jihai
 * @date : 2020/8/19
 * @description :
 * 剑指 Offer 03. 数组中重复的数字
 * 找出数组中重复的数字。
 *
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *
 *
 * 限制：
 *
 * 2 <= n <= 100000
 */
public class FindDuplicateNumber {

    public static void main(String[] args) {
        FindDuplicateNumber findDuplicateNumber = new FindDuplicateNumber();
        Assert.assertTrue(findDuplicateNumber.findRepeatNumber(new int[] {2, 3, 1, 0, 2, 5, 3}) == 2);
        Assert.assertTrue(findDuplicateNumber.findRepeatNumber(new int[] {1, 1, 1}) == 1);

    }

    /**
     * 利用关键信息  0～n-1
     * 对每个数字按长度n取模+n，该下标的位置超过2n则重复
     * 时间复杂度 n
     * 空间复杂度 1
     */
    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[nums[i] % nums.length] += nums.length;
            if (nums[nums[i] % nums.length] > 2 * nums.length) return nums[i] % nums.length;
        }
        return -1;
    }
}
