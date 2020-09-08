package com.auto.interview.algorithm.leetcode.array;

import com.auto.interview.algorithm.leetcode.utils.Assert;

/**
 * @author : jihai
 * @date : 2020/8/22
 * @description :
 *
 * 26. 删除排序数组中的重复项
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 *
 *
 * 示例 1:
 *
 * 给定数组 nums = [1,1,2],
 *
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 *
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 *
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 *
 * 说明:
 *
 * 为什么返回数值是整数，但输出的答案是数组呢?
 *
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *
 * 你可以想象内部操作如下:
 *
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 *
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 */
public class DelDuplicateArray {

    public static void main(String[] args) {
        DelDuplicateArray delDuplicateArray = new DelDuplicateArray();
        Assert.assertTrue(delDuplicateArray.removeDuplicates(new int[]{1, 1, 2}) == 2);

        // 0,0,1,1,1,2,2,3,3,4
        Assert.assertTrue(delDuplicateArray.removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}) == 5);
        Assert.assertTrue(delDuplicateArray.removeDuplicates2(new int[]{0,0,1,1,1,2,2,3,3,4}) == 5);


    }

    /**
     * 方法1：遇到重复的，一个一个移动重复数组
     * 时间复杂度: n> && <n^2
     * 空间复杂度 1
     */
    // 因为是排序数组，所以累计每个数字重复，再用总数组-重复数字
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int count = 0;
        for (int i = 0;i < nums.length - 1;) {
            if (nums[i] == Integer.MAX_VALUE) break;
            if (nums[i] == nums[i + 1]) {
                count ++;
                for (int j = i; j < nums.length - 1; j++) {
                    if (nums[j] != Integer.MAX_VALUE) nums[j] = nums[j + 1];
                }
                nums[nums.length - 1] = Integer.MAX_VALUE;
            } else {
                i ++;
            }
        }
        return nums.length - count;
    }

    /**
     * 双指针!!
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                nums[++ i] = nums[j];
            }
        }
        return i + 1;
    }
}
