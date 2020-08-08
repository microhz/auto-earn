package com.auto.interview.algorithm.leetcode;

/**
 * @author : jihai
 * @date : 2020/8/8
 * @description :
 *
 * 11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 *
 *
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 *
 * 示例：
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 */
public class ContainerWithMostWater {


    /**
     * 动态规划
     *
     * 两次遍历，外层循环左边，内存循环长方形右边，计算最大值
     * n方复杂度
     * @param args
     */
    public static void main(String[] args) {
        ContainerWithMostWater containerWithMostWater = new ContainerWithMostWater();
        Assert.assertTrue(containerWithMostWater.maxArea2(new int[]{1,8,6,2,5,4,8,3,7}) == 49);

//        Assert.assertTrue(containerWithMostWater.maxArea2(new int[]{5, 10, 0, 1, 1, 1, 1, 4}) == 24);
    }

    /**
     * 动态规划
     *
     * 两次遍历，外层循环左边，内存循环长方形右边，计算最大值
     * n方复杂度
     */
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            if (height[i] == 0) {
                continue;
            }
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] == 0) {
                    continue;
                }

                int container = (j - i) * (height[i] < height[j] ? height[i] : height[j]);
                if (max < container) {
                    max = container;
                }
            }
        }
        return max;
    }


    /**
     * 两个指针
     * 移动小的那个值，因为不可能再以这个边作为容器边界了，因为如果还以这个边，因为距离在减小，但是高仍然没变肯定更小了
     */
    public int maxArea2(int[] height) {
        int left = 0,right = height.length - 1, max = 0;
        while (left < right) {
            int container = (height[left] < height[right] ? height[left] : height[right]) * (right - left);
            if (max < container) max = container;
            if (height[left] < height[right]) left ++;
            else right --;
        }
        return max;
    }

    /**
     *   3, 10, 0, 1, 1, 1, 1, 2
     */


}
