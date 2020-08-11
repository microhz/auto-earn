package com.auto.interview.algorithm.leetcode.recursion;

import com.auto.interview.algorithm.leetcode.utils.AlgorithmUtils;
import com.auto.interview.algorithm.leetcode.utils.Assert;
import org.apache.commons.lang3.RandomUtils;

/**
 * @author : jihai
 * @date : 2020/8/7
 * @description :
 *
 * 55. 跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 *
 * 示例 1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 *        0 1 2 3 4
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置
 *
 *
 * [100,2,...0]
 */
public class JumpGame {

    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();

        Assert.assertTrue(jumpGame.canJump2(new int[]{2, 3, 1, 1, 4}));

        int[] array = new int[10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = RandomUtils.nextInt(1, 1000);
        }
        AlgorithmUtils.costTime(() -> jumpGame.canJump(array), 1000000);

        Assert.assertTrue(! jumpGame.canJump2(new int[]{3,2,1,0,4}));

        Assert.assertTrue(jumpGame.canJump2(new int[]{2, 5, 0, 0}));

        Assert.assertTrue(! jumpGame.canJump2(new int[]{3, 3, 1, 0, 1, 0, 1}));

        Assert.assertTrue(jumpGame.canJump2(new int[]{2, 0, 0}));

    }


    /**
     * 发现只要能跳过所有的0 （非数组最后一个索引）即可完成跳跃
     * 问题转换为：
     * 每个0的索引的前面是否存在能跳过的值
     * 是否能跳过就看索引+数值的最大值是否大于0位置的索引
     */
    public boolean canJump2(int[] nums) {
        if (nums.length == 1) return true;
        int max = nums[0];
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0 && max <= nums[i] + i) return false;
            else if (max < nums[i] + i) max = nums[i] + i;
        }
        return true;
    }

    /**
     * 分治法思路
     *
     * 每个位置都有对应数字的跳法
     * 跳一次，剩下的又是一个同样的问题
     * 递归出口在于能到最后一步么，也就是数组在最后一个下标
     */
    public boolean canJump(int[] nums) {
        return jump2(nums, 0);
    }

    private boolean jump2(int[] nums, int start) {
        // 递归出口
        if (start >= nums.length - 1) {
            return true;
        }
        int jumpCount = nums[start];
        // 不从短的开始跳，而是从远的开始跳
        int index = jumpCount;
        while (index >= 1) {
            if (jump2(nums, start + index)) {
                return true;
            }
            index --;
        }
        return false;
    }

    private boolean jump(int[] nums, int start) {
        // 递归出口
        if (start == nums.length - 1) {
            return true;
        }
        int jumpCount = nums[start];
        // 从短开始跳
        int index = 0;
        while (index <= jumpCount) {
            if (jump(nums, start + index)) {
                return true;
            }
            index ++;
        }
        return false;
    }
}
