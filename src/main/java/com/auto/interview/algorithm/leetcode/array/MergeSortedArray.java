package com.auto.interview.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * @author : jihai
 * @date : 2020/8/22
 * @description :
 */
public class MergeSortedArray {

    public static void main(String[] args) {
        MergeSortedArray mergeSortedArray = new MergeSortedArray();
        mergeSortedArray.merge2(new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3);
    }

    /**
     * 方法1 ：
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums2 == null || nums2.length == 0) return ;
        int index = 0;
        for (int i = m;i < m + n;i ++) {
            nums1[i] = nums2[index ++];
        }
        Arrays.sort(nums1);
    }

    /*
     * 插入排序
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        if (nums2 == null || nums2.length == 0) return ;
        for (int i = 0;i < n;i ++) {
            int t = m - 1;
            while (t >= 0 && nums1[t] > nums2[i]) {
                nums1[t + 1] = nums1[t];
                t --;
            }
            nums1[++ t] = nums2[i];
            m ++;
        }
    }


    /**
     * 方法3：
     * 双指针法
     */
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        if (nums2 == null || nums2.length == 0) return ;
        int[] mergeArr = new int[m + n];
        int p1 = 0, p2 = 0;
        int index = 0;
        while (p1 < m && p2 < n) {
            if (nums1[p1] <= nums2[p2]) {
                mergeArr[index ++] = nums1[p1];
                p1 ++;
            } else {
                mergeArr[index ++] = nums2[p2];
                p2 ++;
            }
        }
        while (p1 < m) {
            mergeArr[index ++] = nums1[p1 ++];
        }
        while (p2 < n) {
            mergeArr[index ++] = nums2[p2 ++];
        }
        // copy 2 nums1
        for (int i = 0;i < mergeArr.length;i ++) {
            nums1[i] = mergeArr[i];
        }
    }
}
