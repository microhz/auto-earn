package com.auto.interview.algorithm.leetcode.array;

import com.auto.interview.algorithm.leetcode.utils.Assert;

/**
 * @author : jihai
 * @date : 2020/8/12
 * @description : 二分查找
 *
 * 704. 二分查找
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 *
 *
 * 示例 1:
 *
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * 示例 2:
 *
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 *
 *
 * 提示：
 *
 * 你可以假设 nums 中的所有元素是不重复的。
 * n 将在 [1, 10000]之间。
 * nums 的每个元素都将在 [-9999, 9999]之间。
 */
public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        Assert.assertTrue(binarySearch.search(new int[]{1, 2, 3, 4, 5, 6, 7}, 6) == 5);
        Assert.assertTrue(binarySearch.search(new int[]{1, 2, 3, 4, 5, 6, 7}, 7) == 6);
        // [-1,0,3,5,9,12]
        //2

        Assert.assertTrue(binarySearch.search(new int[]{-1,0,3,5,9,12}, 2) == -1);


    }

    /**
     * 二分查找思路：
     * 动态规划
     *
     * 定义一个中间的值k，目标比k大，遍历右边，比k小找左边
     */
    public int search(int[] nums, int target) {
        if (nums == null) return -1;
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    private int binarySearch(int[] nums, int start, int end, int target) {
        if (start >= end && nums[start] != target) {
            // 递归出口
            return -1;
        }
        int mid = (end + start) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[mid] > target) {
            return binarySearch(nums,0, mid, target);
        } else {
            return binarySearch(nums, mid + 1, end, target);
        }
    }
}
