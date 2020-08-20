package com.auto.interview.algorithm.leetcode.dp;

import com.auto.interview.algorithm.leetcode.utils.Assert;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/8/19
 * @description :
 *
 * 网页题目： https://www.cxyxiaowu.com/6781.html
 *
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class MinTravel {

    public static void main(String[] args) {
        MinTravel minTravel = new MinTravel();
        List<List<Integer>> arrayLists = Lists.newArrayList(Lists.newArrayList(2), Lists.newArrayList(3, 4), Lists.newArrayList(6, 5, 7), Lists.newArrayList(4, 1, 8, 3));
//        int i = minTravel.minTravel(new int[][]{
//                {2},
//                {3, 4},
//                {6, 5, 7},
//                {4, 1, 8, 3}
//        });
        int i = minTravel.minTravel(arrayLists);
        Assert.assertTrue(i == 11);

        // [[-1],[2,3],[1,-1,-3]]
        Assert.assertTrue(minTravel.minTravel(Lists.newArrayList(
                Lists.newArrayList(-1),
                Lists.newArrayList(2, 3),
                Lists.newArrayList(1, -1, -3)
        )) == -1);
    }

    /**
     * dp[i] = dp[i - 1] + min(k), min(k)为下一层的相邻最小
     * dp[0] = array[0][0]
     * 应该错误
     */
    public int minTravel(List<List<Integer>> list) {
        int p = 0;
        int sum = list.get(0).get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).get(p) < list.get(i).get(p + 1)) {
                sum += list.get(i).get(p);
            } else {
                sum += list.get(i).get(p + 1);
                p ++;
            }
        }
        return sum;
    }
}
