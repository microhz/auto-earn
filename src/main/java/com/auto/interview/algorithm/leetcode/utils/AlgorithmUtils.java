package com.auto.interview.algorithm.leetcode.utils;

import java.util.function.Supplier;

/**
 * @author : jihai
 * @date : 2020/8/7
 * @description :
 */
public class AlgorithmUtils {

    /**
     * 统计算法耗时
     * cycleCount 循环次数
     */
    public static <T> void costTime(Supplier<T> supplier, int cycleCount) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < cycleCount; i++) {
            supplier.get();
        }
        long end = System.currentTimeMillis();
        System.out.println("costTime -> " + (end - start) + "ms");
    }
}
