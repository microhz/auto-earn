package com.auto.interview.algorithm.leetcode.array;

import com.auto.interview.algorithm.leetcode.utils.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/8/21
 * @description :
 *
 * 18. 四数之和
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：
 *
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 *
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 *
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 */
public class FourNumberAdd {

    public static void main(String[] args) {
        FourNumberAdd fourNumberAdd = new FourNumberAdd();
        System.out.println(fourNumberAdd.fourSum(new int[]{0, 0, 0, 0}, 0));
    }

    /**
     * 双指针法
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        // 先排序
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0;i < nums.length - 3;i ++) {
            for (int j = i + 1;j < nums.length - 2 ; j ++) {
                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum > target) right --; // 右边大了，右指针向左移动
                    else if (sum < target) left ++; // 左边小了，左指针向右移动
                    else if (sum == target) {
                        List<Integer> tempList = new ArrayList<>();
                        tempList.add(nums[i]);
                        tempList.add(nums[j]);
                        tempList.add(nums[left]);
                        tempList.add(nums[right]);
                        if (! list.contains(tempList)) list.add(tempList);
                        left ++;
                        right --;
                    }
                }
            }
        }
        return list;
    }
}
