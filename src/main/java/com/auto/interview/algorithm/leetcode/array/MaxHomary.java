package com.auto.interview.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : jihai
 * @date : 2020/9/10
 * @description :
 * 和谐数组是指一个数组里元素的最大值和最小值之间的差别正好是1。
 *
 * 现在，给定一个整数数组，你需要在所有可能的子序列中找到最长的和谐子序列的长度。
 *
 * 示例 1:
 *
 * 输入: [1,3,2,2,5,2,3,7]
 * 输出: 5
 * 原因: 最长的和谐数组是：[3,2,2,2,3].
 * 说明: 输入的数组长度最大不超过20,000.
 */
public class MaxHomary {


    /**
     * 超时, 这个算法的确垃圾
     */
    int maxRet = 0;
    public int findLHS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        findLongest(nums, 0, new ArrayList<Integer>());
        return maxRet;
    }

    public void findLongest(int[] nums, int start, List<Integer> list) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i : list) {
            if (i < min) min = i;
            if (i > max) max = i;
        }
        if (max - min == 1) maxRet = list.size() > maxRet ? list.size() : maxRet;
        if (max > min + 1 || start == nums.length) return ;
        for (int i = start;i < nums.length;i ++) {
            list.add(nums[i]);
            findLongest(nums, i + 1, list);
            list.remove(list.size() - 1);
        }
    }


    /**
     * 官方答案
     * @param nums
     * @return
     */
    public int findLHS2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int ans = 0;
        for (int i : nums) {
            int count = 0;
            boolean flag = false;
            for (int e : nums) {
                if (e == i) count ++;
                else if (e == (i + 1)) {
                    count ++;
                    flag = true;
                }
            }
            if (flag) ans = Math.max(count, ans);
        }
        return ans;
    }



    public int findLHS3(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int ret = 0;
        for (int i : nums) {
            if (map.getOrDefault(i + 1, 0) > 0)
                ret = Math.max(ret, map.get(i) + map.getOrDefault(i + 1, 0));
        }
        return ret;
    }
}
