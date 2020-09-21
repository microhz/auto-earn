package com.auto.interview.algorithm.leetcode.array;

import com.auto.interview.algorithm.leetcode.base.Review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/9/10
 * @description :
 * 40. 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */
@Review(1)
public class MaxTargetCombine {

    public static void main(String[] args) {
        MaxTargetCombine maxTargetCombine = new MaxTargetCombine();
        System.out.println(maxTargetCombine.combinationSum2(new int[]{1,1,2}, 3));
    }


    /**
     * 自己的写法，效率不高
     */
    List<List<Integer>> ansList;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ansList = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return ansList;
        combinationSum(candidates, 0, target, new ArrayList<Integer>());
        return ansList;
    }

    public void combinationSum(int[] candidates, int start, int target, List<Integer> list) {
        if (target == 0) {
            // 去重
            List<Integer> temp = new ArrayList<>(list);
            Collections.sort(temp);
            if (!ansList.contains(temp)) ansList.add(temp);
            return ;
        }
        if (target < 0) return ;
        for (int i = start;i < candidates.length;i ++) {
            list.add(candidates[i]);
            combinationSum(candidates, i + 1, target - candidates[i], list);
            list.remove(list.size() - 1);
        }
    }


    /**
     * 别人的解法，关键在于去重，这个去重的逻辑，可以理解一下
     */
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, new ArrayList<>(), 0, target);
        return res;
    }

    private void dfs(int[] candidates, List<Integer> path, int index, int target) {
        if(target < 0) return;

        if(target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i = index; i < candidates.length; i++) {
            if(i > index && candidates[i] == candidates[i-1]) continue;
            path.add(candidates[i]);
            dfs(candidates, path, i+1, target-candidates[i]);
            path.remove(path.size()-1);
        }
    }
}
