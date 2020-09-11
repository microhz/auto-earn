package com.auto.interview.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/9/11
 * @description :216. 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombineSum3 {
    /**
     * 自己写的回溯
     */
    List<List<Integer>> ansList;
    public List<List<Integer>> combinationSum3(int k, int n) {
        ansList = new ArrayList<>();
        if (n < 0 || n > 81) return ansList;
        combinationSum3(1, k, n, new ArrayList<>());
        return ansList;
    }

    public void combinationSum3(int start, int k, int n, List<Integer> list) {
        if (list.size() > k) return ;
        if (list.size() == k) {
            int sum = 0;
            for (int i : list) {
                sum += i;
            }
            if (sum == n) ansList.add(new ArrayList<>(list));
            return ;
        }
        for (int i = start;i <= 9;i ++) {
            list.add(i);
            combinationSum3(i + 1, k, n, list);
            list.remove(list.size() - 1);
        }
    }
}
