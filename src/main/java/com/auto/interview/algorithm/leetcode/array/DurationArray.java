package com.auto.interview.algorithm.leetcode.array;

/**
 * @author : jihai
 * @date : 2020/8/4
 * @description :
 *
 * 给定一个数组，将数组中的元素向*右移动 k 个位置，其中 k 是*非负数。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 *
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 说明:
 *
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DurationArray {

    public static void main(String[] args) {
        DurationArray durationArray = new DurationArray();
        int[] arr1 = new int[]{1, 2, 3};
        durationArray.rotate(arr1, 1);
        assertArray(arr1, new int[]{3, 1, 2});


        int[] arr2 = new int[]{1};
        durationArray.rotate(arr2, 1);
        assertArray(arr2, new int[]{1});
    }

    private static void assertArray(int[] arr1, int[] ints) {
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != ints[i]) {
                throw new RuntimeException("assert error");
            }
        }
    }

    public void rotate(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            move2Next(nums);
        }
    }

    private void move2Next(int[] nums) {
        int temp = nums[nums.length - 1];
        for (int i = nums.length - 1; i > 0; i --) {
            nums[i] = nums[i - 1];
        }
        nums[0] = temp;
    }
}
