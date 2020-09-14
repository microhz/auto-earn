package com.auto.interview.algorithm.leetcode.array;

/**
 * @author : jihai
 * @date : 2020/9/14
 * @description :
 *
 * 739. 每日温度
 * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 * 通过次数102,691提交次数158,851
 */
public class DailyTemperature {


    /**
     * 参考别人写出来的，比自己写的优雅
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        for (int i = 0;i < T.length;i ++) {
            int j = 1;
            while (j + i < T.length && T[j + i] <= T[i]) {
                j ++;
            }
            ans[i] = j + i == T.length ? 0 : j;
        }
        return ans;
    }
}
