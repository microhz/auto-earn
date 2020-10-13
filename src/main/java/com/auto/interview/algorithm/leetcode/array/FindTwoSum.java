package com.auto.interview.algorithm.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : jihai
 * @date : 2020/10/3
 * @description :
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 *
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class FindTwoSum {

    /**
     * 两层循环固然可以解决，但是也可以使用哈希表去解决，能够让空间换时间的复杂度
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[0];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0;i < nums.length;i ++) {
            if (map.get(target - nums[i]) == null) {
                map.put(nums[i], i);
            } else {
                int[] ret = new int[2];
                ret[0] = map.get(target - nums[i]);
                ret[1] = i;
                return ret;
            }
        }
        return new int[0];
    }
}
