package com.auto.interview.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/9/11
 * @description :
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class AllPermutations {

    /**
     * 自己写的，全排列，主要还是回溯思想
     */
    List<List<Integer>> ansList;
    public List<List<Integer>> permute(int[] nums) {
        ansList = new ArrayList<>();
        if (nums == null || nums.length == 0) return ansList;
        permute(nums, nums.length, new ArrayList<>());
        return ansList;
    }

    public void permute(int[] nums, int len, List<Integer> list) {
        if (len == list.size()) {
            ansList.add(new ArrayList<>(list));
            return ;
        }
        for (int i : nums) {
            if (list.contains(i)) continue;
            list.add(i);
            permute(nums, len, list);
            list.remove(list.size() - 1);
        }
    }
}
