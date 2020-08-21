package com.auto.interview.algorithm.leetcode.array;

import com.auto.interview.algorithm.leetcode.utils.AssertUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/8/21
 * @description :
 *
 * 989. 数组形式的整数加法
 * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 *
 * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 *
 *
 *
 * 示例 1：
 *
 * 输入：A = [1,2,0,0], K = 34
 * 输出：[1,2,3,4]
 * 解释：1200 + 34 = 1234
 * 示例 2：
 *
 * 输入：A = [2,7,4], K = 181
 * 输出：[4,5,5]
 * 解释：274 + 181 = 455
 * 示例 3：
 *
 * 输入：A = [2,1,5], K = 806
 * 输出：[1,0,2,1]
 * 解释：215 + 806 = 1021
 * 示例 4：
 *
 * 输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
 * 输出：[1,0,0,0,0,0,0,0,0,0,0]
 * 解释：9999999999 + 1 = 10000000000
 *
 *
 * 提示：
 *
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 9
 * 0 <= K <= 10000
 * 如果 A.length > 1，那么 A[0] != 0
 */
public class ArrayAdd {

    public static void main(String[] args) {
        ArrayAdd arrayAdd = new ArrayAdd();
        AssertUtils.assertList(arrayAdd.addToArrayForm2(new int[]{1, 2, 0, 0}, 34), 1, 2, 3, 4);
        AssertUtils.assertList(arrayAdd.addToArrayForm2(new int[]{2,7,4}, 181), 4, 5, 5);

        // [9,9,9,9,9,9,9,9,9,9]
        //1
//        AssertUtils.assertList(arrayAdd.addToArrayForm2(new int[]{9,9,9,9,9,9,9,9,9,9}, 1), 4, 5, 5);


        AssertUtils.assertList(arrayAdd.addToArrayForm3(new int[]{1, 2, 0, 0}, 34), 1, 2, 3, 4);
        AssertUtils.assertList(arrayAdd.addToArrayForm3(new int[]{2,7,4}, 181), 4, 5, 5);

        // [9,9,9,9,9,9,9,9,9,9]
        //1
//        AssertUtils.assertList(arrayAdd.addToArrayForm3(new int[]{9,9,9,9,9,9,9,9,9,9}, 1), 4, 5, 5);

    }

    /**
     * 官方答案：进位
     */
    public List<Integer> addToArrayForm3(int[] A, int K) {
        int N = A.length;
        int cur = K;
        List<Integer> ans = new ArrayList();

        int i = N;
        while (--i >= 0 || cur > 0) {
            if (i >= 0)
                cur += A[i];
            ans.add(cur % 10);
            cur /= 10;
        }

        Collections.reverse(ans);
        return ans;
    }


    /***
     * 方法1：转换成数字相加，再转换为数组
     * 空间复杂度 n
     * 时间复杂度 n
     */
    public List<Integer> addToArrayForm2(int[] A, int K) {
        List<Integer> kList = new ArrayList<>();
        while (K > 0) {
            kList.add(K % 10);
            K /= 10;
        }
        for (int i = 0, j = kList.size() - 1; i < j; i++, j --) {
            int temp = kList.get(i);
            kList.set(i, kList.get(j));
            kList.set(j, temp);
        }
        int aLen = A.length - 1, kLen = kList.size() - 1;
        int carr = 0;
        List<Integer> list = new ArrayList<>();
        while (aLen >= 0 && kLen >= 0) {
            int sum = A[aLen] + kList.get(kLen) + carr;
            if (sum > 9) {
                carr = 1;
                list.add(sum % 10);
            } else {
                list.add(sum);
                carr = 0;
            }
            aLen --;
            kLen --;
        }
        while (aLen >= 0) {
            int sum = carr + A[aLen];
            if (sum > 9) {
                carr = 1;
                list.add(sum % 10);
            } else {
                list.add(sum);
                carr = 0;
            }
            aLen --;
        }

        while (kLen >= 0) {
            int sum = carr + kList.get(kLen);
            if (sum > 9) {
                carr = 1;
                list.add(sum % 10);
            } else {
                list.add(sum);
                carr = 0;
            }
            kLen --;
        }
        if (carr > 0) list.add(carr);
        for (int i = 0,j = list.size() - 1;i < j;i ++,j --) {
            int temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
        return list;
    }

    /***
     * 方法1：转换成数字相加，再转换为数组
     * 空间复杂度 n
     * 时间复杂度 n
     */
    public List<Integer> addToArrayForm(int[] A, int K) {
        int n = (int)Math.pow(10, (A.length - 1));
        int B = 0;
        for (int i = 0;i < A.length;i ++) {
            B += A[i] * n;
            n /= 10;
        }
        int sum = B + K;
        List<Integer> list = new ArrayList<>();

        while (sum > 0) {
            int temp = sum % 10;
            list.add(temp);
            sum /= 10;
        }
        for (int i = 0, j = list.size() - 1; i < j;i ++,j --) {
            int temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
        return list;
    }
}
