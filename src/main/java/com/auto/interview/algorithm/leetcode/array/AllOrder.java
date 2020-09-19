package com.auto.interview.algorithm.leetcode.array;

import java.util.*;

/**
 * @author : jihai
 * @date : 2020/9/18
 * @description :
 *
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 */
public class AllOrder {


    /**
     * 回溯算法，自己写的，但是效率很低
     * 这个方法本质就是通过list和路径的一个回溯
     */
    List<List<Integer>> ansList;
    public List<List<Integer>> permuteUnique(int[] nums) {
        ansList = new ArrayList<>();
        if (nums == null || nums.length == 0) return ansList;
        List<Integer> startList = new ArrayList<>();
        Set<Integer> traceSet = new HashSet<>();
        permuteUnique(nums, startList, traceSet);
        return ansList;
    }

    public void permuteUnique(int[] nums, List<Integer> list, Set<Integer> traceSet) {
        if (traceSet.size() == nums.length) {
            if (ansList.contains(list)) return ;
            ansList.add(new ArrayList<>(list));
            return ;
        }

        for (int i = 0;i < nums.length;i ++) {
            if (traceSet.contains(i)) continue;
            list.add(nums[i]);
            traceSet.add(i);
            permuteUnique(nums, list, traceSet);
            list.remove(list.size() - 1);
            traceSet.remove(i);
        }
    }


    // 这里记录已经添加过的位置
    boolean[] visited;
    public List<List<Integer>> permuteUnique2(int[] nums) {
        ansList = new ArrayList<>();
        if (nums == null || nums.length == 0) return ansList;
        visited = new boolean[nums.length];
        Arrays.sort(nums); // 首先要排序
        permuteUnique(nums, new ArrayList<>());
        return ansList;
    }

    public void permuteUnique(int[] nums, List<Integer> list) {
        if (list.size() == nums.length) {
            // 这里不再需要判断，因为都是不会重复的
            ansList.add(new ArrayList<>(list));
            return ;
        }
        for (int i = 0;i < nums.length;i ++) {
            // 关键的剪枝逻辑，!visited[i - 1]是因为判断是回溯后的第一层剪枝,前一个节点一定是撤销了选择
            if (visited[i] || ( i > 0 && nums[i - 1] == nums[i] && !visited[i - 1])) continue;
            list.add(nums[i]);
            visited[i] = true;
            permuteUnique(nums, list);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }



}
