package com.auto.interview.algorithm.leetcode.dp;

import com.auto.interview.algorithm.leetcode.utils.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/8/20
 * @description :
 *
 * 413. 等差数列划分
 * 如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 *
 * 例如，以下数列为等差数列:
 *
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * 以下数列不是等差数列。
 *
 * 1, 1, 2, 5, 7
 *
 *
 * 数组 A 包含 N 个数，且索引从0开始。数组 A 的一个子数组划分为数组 (P, Q)，P 与 Q 是整数且满足 0<=P<Q<N 。
 *
 * 如果满足以下条件，则称子数组(P, Q)为等差数组：
 *
 * 元素 A[P], A[p + 1], ..., A[Q - 1], A[Q] 是等差的。并且 P + 1 < Q 。
 *
 * 函数要返回数组 A 中所有为等差数组的子数组个数。
 *
 *
 *
 * 示例:
 *
 * A = [1, 2, 3, 4]
 *
 * 返回: 3, A 中有三个子等差数组: [1, 2, 3], [2, 3, 4] 以及自身 [1, 2, 3, 4]。
 */
public class SliceAglorith {

    public static void main(String[] args) {
        SliceAglorith sliceAglorith = new SliceAglorith();
        Assert.assertTrue(sliceAglorith.numberOfArithmeticSlices(new int[]{1, 2, 3, 4}) == 3);

        // [1,2,3,8,9,10]
        Assert.assertTrue(sliceAglorith.numberOfArithmeticSlices(new int[]{1,2,3,8,9,10}) == 2);

        // 2,1,3,4,2,3
        Assert.assertTrue(sliceAglorith.numberOfArithmeticSlices(new int[]{2,1,3,4,2,3}) == 0);

    }

    /**
     * 返回个数，一般会考虑dp算法
     * dp[i]定义为第i项的最多等差数列
     * 如果dp[i] 不是等差了, dp[i] = dp[i - 1], 如果dp[i]依旧是等差数列, dp[i] = dp[i] + 新增的等差数列
     * dp[0] = 0, dp[1] = 0, dp[2] =
     */
    public int numberOfArithmeticSlices2(int[] A) {
        return 0;
    }


    /**
     * 设置一个list保存当前遍历的等差数列
     */
    public int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3) return 0;
        int d = (A[2] - A[1] == A[1] - A[0]) ? (A[2] - A[1]) : Integer.MAX_VALUE;
        List<Integer> list = new ArrayList<>();
        int count = 0;
        if (d != Integer.MAX_VALUE) {
            list.add(A[0]);
            list.add(A[1]);
            list.add(A[2]);
        }
        for (int i = 2; i < A.length - 1; i++) {
            // 1,2,3,8,9,10
            if (A[i + 1] - A[i] != d) {
                if (list.size() > 2) {
                    count += countSubSlices(list);
                    list.clear();
                    list.add(A[i + 1]);
                }
            } else {
                list.add(A[i + 1]);
            }
        }
        if (list.size() >= 3) {
            count += countSubSlices(list);
        }
        return count;
    }

    private int countSubSlices(List<Integer> list) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 2; j < list.size(); j++) {
                count ++;
            }
        }
        return count;
    }
}
