package com.auto.interview.algorithm.leetcode.number;

import com.auto.interview.algorithm.leetcode.utils.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/8/12
 * @description :
 *
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *
 *
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        System.out.println(threeSum.threeSum(new int[]{0,0,0,0,0,0}));
        System.out.println(threeSum.threeSum(new int[]{-1, 0, 2, 3, 2, 1}));

        System.out.println(threeSum.threeSum(new int[]{-1,0,1,2,-1,-4}));
    }


    /**
     * 排序
     * 拿一个数字出来遍历
     * 另外两个用双指针法
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        Integer pre = null;
        for (int i = 0; i < nums.length - 2; i++) {
            int a = nums[i];
            if (pre != null && a == pre) {
                continue;
            }
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int ret = a + nums[left] + nums[right];
                if (ret == 0) {
                    List<Integer> subList = new ArrayList<>();
                    subList.add(a);
                    subList.add(nums[left]);
                    subList.add(nums[right]);
                    list.add(subList);
                    pre = a;
                    // 随便走一个
                    while (left < right && nums[left + 1] == nums[left]) {
                        left ++;
                    }
                    left ++;
                } else if (ret > 0) {
                    right --;
                } else {
                    left ++;
                }
            }
        }
        return list;
    }
}
