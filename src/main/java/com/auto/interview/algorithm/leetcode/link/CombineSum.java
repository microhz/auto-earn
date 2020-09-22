package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/9/9
 * @description :
 * 39. 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2：
 *
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 *
 * 提示：
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 */
@Review(1)
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
