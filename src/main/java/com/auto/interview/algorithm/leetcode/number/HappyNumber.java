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


    public int getNext2(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    /**
     * 快慢指针法
     * @param n
     * @return
     */
    public boolean isHappy2(int n) {
        int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }

}
