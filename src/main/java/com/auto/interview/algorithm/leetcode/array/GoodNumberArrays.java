package com.auto.interview.algorithm.leetcode.array;

import com.auto.interview.algorithm.leetcode.utils.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : jihai
 * @date : 2020/8/22
 * @description :
 *
 * 1512. 好数对的数目
 * 给你一个整数数组 nums 。
 *
 * 如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。
 *
 * 返回好数对的数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1,1,3]
 * 输出：4
 * 解释：有 4 组好数对，分别是 (0,3), (0,4), (3,4), (2,5) ，下标从 0 开始
 * 示例 2：
 *
 * 输入：nums = [1,1,1,1]
 * 输出：6
 * 解释：数组中的每组数字都是好数对
 * 示例 3：
 *
 * 输入：nums = [1,2,3]
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 */
public class GoodNumberArrays {

    public static void main(String[] args) {
        GoodNumberArrays goodNumberArrays = new GoodNumberArrays();
        Assert.assertTrue(goodNumberArrays.numIdenticalPairs(new int[]{1,2,3,1,1,3}) ==4);
    }

    /**
     *
     * 大佬思路，初始化一个数组A，记录每个nums的值所在的A对应nums值下标累计并+1
     */
    public int numIdenticalPairs(int[] nums) {
        int ans = 0;
        int[] temp = new int[100];
        for (int num : nums) {
            ans += temp[num - 1]++;
        }
        return ans;
    }

    /**
     * 用map替换上面temp数组
     * @param nums
     * @return
     */
    public int numIdenticalPairs2(int[] nums) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0;i < nums.length;i ++) {
            if (map.get(nums[i]) == null) {
                map.put(nums[i], 1);
            } else {
                ans += map.get(nums[i]);
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }
        return ans;
    }


    /**
     * 暴力法
     * @param nums
     * @return
     */
    public int numIdenticalPairs3(int[] nums) {
        int ans = 0;
        for (int i = 0;i < nums.length - 1;i ++) {
            for (int j = i + 1;j < nums.length;j ++) {
                if (nums[i] == nums[j]) {
                    ans ++;
                }
            }
        }
        return ans;
    }

}
