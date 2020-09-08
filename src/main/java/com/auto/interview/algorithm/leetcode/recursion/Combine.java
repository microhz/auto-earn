package com.auto.interview.algorithm.leetcode.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/9/8
 * @description :
 *
 * 77. 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class Combine {

    public static void main(String[] args) {

    }

    /**
     * 回溯递归算法
     */
    List<List<Integer>> retList;
    public List<List<Integer>> combine(int n, int k) {
        retList = new ArrayList<>();
        if (n < 1 || n < k) return retList;
        combine(n, 1, k, new ArrayList<Integer>());
        return retList;
    }

    public void combine(int n, int cur, int k, List<Integer> list) {
        if (k == 0) {
            retList.add(new ArrayList<>(list));
            return ;
        }
        for (int i = cur;i <= n - k + 1;i ++) {
            list.add(i);
            combine(n, i + 1, k - 1, list);
            // 这里关键回溯点，内层递归也会把末尾的值移除
            list.remove(list.size() - 1);
        }
    }

}
