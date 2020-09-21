package com.auto.interview.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/9/20
 * @description :
 *
 * 78. 子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class SubSets {


    /**
     * 自己写的，注意 剪枝的判断即可
     */
    List<List<Integer>> ansList;
    boolean[] visited;
    public List<List<Integer>> subsets(int[] nums) {
        ansList = new ArrayList<>();
        if (nums == null || nums.length == 0) return ansList;
        visited = new boolean[nums.length];
        Arrays.sort(nums);
        subsets(nums, 0, new ArrayList<>());
        return ansList;
    }

    public void subsets(int[] nums, int index, List<Integer> list) {
        ansList.add(new ArrayList<>(list));
        if (index == nums.length) return ;
        for (int i = index;i < nums.length;i ++) {
            if (i + 1 < nums.length && nums[i + 1] == nums[i] && !visited[i - 1]) continue;
            list.add(nums[i]);
            visited[i] = true;
            subsets(nums, i + 1, list);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}
