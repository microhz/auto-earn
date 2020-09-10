package com.auto.interview.algorithm.leetcode.link;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/9/9
 * @description :
 */
public class CombineSum {

    /**
     * 回溯法
     * 自己写的
     */
    List<List<Integer>> retList;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        retList = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return retList;
        List<Integer> list = new ArrayList<>();

        combinationSum(candidates, 0, target, list);
        return retList;
    }

    public void combinationSum(int[] candidates, int start, int target, List<Integer> list) {
        if (start == candidates.length) return ;
        // target递减，为负数直接终止
        if (target < 0) return ;
        if (target == 0) {
            // 这里复制当前列表
            retList.add(new ArrayList<>(list));
            return ;
        }
        for (int i = start; i < candidates.length;i ++) {
            list.add(candidates[i]);
            combinationSum(candidates, i, target - candidates[i], list);
            // 回溯
            list.remove(list.size() - 1);
        }
    }
}
