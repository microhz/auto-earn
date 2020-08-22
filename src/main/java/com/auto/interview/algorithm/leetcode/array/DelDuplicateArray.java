package com.auto.interview.algorithm.leetcode.array;

import com.auto.interview.algorithm.leetcode.utils.Assert;

/**
 * @author : jihai
 * @date : 2020/8/22
 * @description :
 */
public class DelDuplicateArray {

    public static void main(String[] args) {
        DelDuplicateArray delDuplicateArray = new DelDuplicateArray();
        Assert.assertTrue(delDuplicateArray.removeDuplicates(new int[]{1, 1, 2}) == 2);

        // 0,0,1,1,1,2,2,3,3,4
        Assert.assertTrue(delDuplicateArray.removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}) == 2);

    }

    // 因为是排序数组，所以累计每个数字重复，再用总数组-重复数字
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int count = 0;
        for (int i = 0;i < nums.length - 1;) {
            if (nums[i] == Integer.MAX_VALUE) {
                break;
            }
            if (nums[i] == nums[i + 1]) {
                count ++;
                for (int j = i; j < nums.length - 1; j++) {
                    if (nums[j] != Integer.MAX_VALUE) {
                        nums[j] = nums[j + 1];
                    }
                }
                nums[nums.length - 1] = Integer.MAX_VALUE;
            } else {
                i ++;
            }
        }
        return nums.length - count;
    }
}
