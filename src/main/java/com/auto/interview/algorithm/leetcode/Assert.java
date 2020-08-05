package com.auto.interview.algorithm.leetcode;

import java.util.function.Supplier;

/**
 * @author : jihai
 * @date : 2020/8/3
 * @description :
 */
public class Assert {

    public static void assertTrue(boolean expression) {
        if (! expression) {
            throw new RuntimeException("assert error");
        }
    }

    public static void assertTrue(String msg, boolean expression) {
        if (! expression) {
            throw new RuntimeException("assert error : " + msg);
        }
    }

    public static void assertResult(Supplier<Boolean> supplier) {
        if (!supplier.get()) {
            throw new RuntimeException("assert error");
        }
    }
}
