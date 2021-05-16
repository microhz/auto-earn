package com.auto.interview.algorithm.leetcode.doing;

/**
 * @author : jihai
 * @date : 2021/3/3
 * @description :
 */
public class AddSum {

    public static void main(String[] args) {
        System.out.println(add(8, 5));
    }

    public static int add(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;
        int sum1 = a ^ b;
        int sum2 = (a & b) << 1;
        return add(sum1, sum2);
    }
}
