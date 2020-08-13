package com.auto.test;

import com.auto.interview.algorithm.leetcode.utils.Assert;

/**
 * @author : jihai
 * @date : 2020/8/13
 * @description :
 *
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 *
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class SearchMidNumber {

    public static void main(String[] args) {
        SearchMidNumber searchMidNumber = new SearchMidNumber();
        Assert.assertTrue(searchMidNumber.searchMidNumber(new int[]{1, 3}, new int[]{2}) == 2);
        Assert.assertTrue(searchMidNumber.searchMidNumber2(new int[]{1, 3}, new int[]{2}) == 2);
        Assert.assertTrue(searchMidNumber.searchMidNumber(new int[]{1, 2}, new int[]{3, 4}) == 2.5D);
    }

    /**
     *
     * 方法1
     * 合并的中位数等于两个中位数相加/2
     *
     * 数组直接下标找到中位数
     * 时间复杂度 1
     * 空间复杂度 1
     */
    public double searchMidNumber(int[] nums1, int[] nums2) {
        double a = getMidNumber(nums1);
        double b = getMidNumber(nums2);
        return (a + b) / 2;
    }

    /**
     * 二分查找
     * @param nums1
     * @return
     */
    private double getMidNumber(int[] nums1) {
        int length = nums1.length;
        if (length % 2 == 1) {
            return nums1[length / 2];
        } else {
            return Double.valueOf(((nums1[length / 2] + nums1[length / 2 - 1])) / 2);
        }
    }

    /**
     *
     * 方法2
     * 合并两个数组
     * 归并两个数组
     * 双指针找到中位数
     *
     * 时间复杂度: m + n
     * 空间复杂度 m + n
     */
    public double searchMidNumber2(int[] nums1, int[] nums2) {
        int[] mergeArray = new int[nums1.length + nums2.length];
        int first = 0, second = 0;
        int index = 0;
        while (first < nums1.length && second < nums2.length) {
            if (nums1[first] <= nums2[second]) {
                mergeArray[index ++] = nums1[first];
                first ++;
            } else {
                mergeArray[index ++] = nums2[second];
                second ++;
            }
        }
        while (first < nums1.length) {
            mergeArray[index ++] = nums1[first];
            first ++;
        }
        while (second < nums2.length) {
            mergeArray[index ++] = nums2[second];
            second ++;
        }
        int length = mergeArray.length;
        if (length % 2 == 0) {
            return Double.valueOf(mergeArray[length / 2] + mergeArray[length / 2 - 1]) / 2;
        } else {
            return Double.valueOf(mergeArray[length / 2]);
        }
    }
}
