package com.auto.interview.algorithm.leetcode.number;

import com.auto.interview.algorithm.leetcode.utils.Assert;

import java.util.Arrays;

/**
 * @author : jihai
 * @date : 2020/8/12
 * @description :
 *
 * 16. 最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 *
 *
 * 示例：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 *
 * 提示：
 *
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 */
public class ThreeNumberSum {

    public static void main(String[] args) {
        ThreeNumberSum threeNumberSum = new ThreeNumberSum();
        Assert.assertTrue(threeNumberSum.threeSumClosest(new int[]{-1, 2, 1, -4}, 4) == 2);
        Assert.assertTrue(threeNumberSum.threeSumClosest2(new int[]{-1, 2, 1, -4}, 4) == 2);

        /**
         * [-3,-2,-5,3,-4]
         * -1
         */
        Assert.assertTrue(threeNumberSum.threeSumClosest(new int[]{-3,-2,-5,3,-4},  -1) == -2);
        Assert.assertTrue(threeNumberSum.threeSumClosest2(new int[]{-3,-2,-5,3,-4},  -1) == -2);
        //[1,2,4,8,16,32,64,128]
        //82
        Assert.assertTrue(threeNumberSum.threeSumClosest2(new int[]{1,2,4,8,16,32,64},  82) == 82);

    }


    /**
     * 排序
     * 双指针法
     * a b c 三个数字之和最接近target
     * a + b + c = target
     * a + b = target - c
     * 遍历c，然后a + b用双指针移动
     * 小于了就目标，b ++, 大于目标a --
     */
    public int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int k = nums[0] + nums[1] + nums[2];
        int d = target - k;
        for (int i = 0; i < nums.length - 2; i++) {
            int c = nums[i];
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int ret = nums[left] + nums[right] - (target - c);
                int temp = Math.abs(ret);
                if (d > temp) {
                    d = temp;
                    k = nums[left] + nums[right] + c;
                } else if (temp == 0) {
                    return nums[left] + nums[right] + c;
                }
                if (ret > 0) {
                    right --;
                } else if (ret < 0) {
                    left ++;
                } else {
                    return nums[left] + nums[right] + c;
                }
            }
        }
        return k;
    }

    /**
     * 暴力法
     *
     * 时间复杂度 n3
     * 空间复杂度 1
     */
    public int threeSumClosest(int[] nums, int target) {
        int n1 = nums[0], n2 = nums[1], n3= nums[2];
        int result = n1 + n2 + n3;
        int d = Math.abs(result - target);
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (d > Math.abs(target - (nums[k] + nums[j] + nums[i]))) {
                        result = nums[k] + nums[j] + nums[i];
                        d = Math.abs(target - result);
                    }
                }
            }
        }
        return result;
    }
}
