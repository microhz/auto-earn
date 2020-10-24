package com.auto.interview.algorithm.leetcode.other;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : jihai
 * @date : 2020/10/24
 * @description :
 */
public class Lru<K, V> extends LinkedHashMap<K, V> {
    private int capacity;

    public Lru(int initialCapacity) {
        // accessOrder就是排序模式，核心参数
        super(initialCapacity, 0.75f, true);
        capacity = initialCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        Lru<Integer, Integer> lru = new Lru<>(3);

        lru.put(1, 1);
        lru.put(2, 2);
        lru.put(3, 3);

        lru.get(3);
        lru.get(1);

        lru.put(4, 4);

        System.out.println(lru);
    }
}
