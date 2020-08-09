package com.auto.interview.algorithm.leetcode;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : jihai
 * @date : 2020/8/8
 * @description :
 *
 * 442. 数组中重复的数据
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 *
 * 找到所有出现两次的元素。
 *
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 *
 * 示例：
 *
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [2,3]
 */
public class FindAllDuplicatesInAnArray {

    public static void main(String[] args) {
        FindAllDuplicatesInAnArray findAllDuplicatesInAnArray = new FindAllDuplicatesInAnArray();
        Assert.assertTrue(findAllDuplicatesInAnArray.findDuplicates2(new int[]{1, 2, 3, 3, 2}).equals(Lists.newArrayList(3, 2)));
    }

    /**
     * 方法2，n复杂度，但是用了额外空间
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                list.add(nums[i]);
            }
            set.add(nums[i]);
        }
        return list;
    }


    /**
     * 方法1，n方复杂度
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int count = 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == nums[i]) {
                    count ++;
                }
            }
            if (count == 2) {
                list.add(nums[i]);
            }
        }
        return list;
    }
}
