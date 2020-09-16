package com.auto.interview.algorithm.leetcode.number;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : jihai
 * @date : 2020/9/16
 * @description :
 */
public class HappyNumber {

    public boolean isHappy(int n) {
        if (n <= 0) return false;
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    public int getNext(int n) {
        int sum = 0;
        while (n != 0) {
            int d = n % 10;
            n /= 10;
            sum += d * d;
        }
        return sum;
    }
}
