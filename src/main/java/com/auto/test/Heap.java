package com.auto.test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author : jihai
 * @date : 2020/10/25
 * @description :
 */
public class Heap {

    public static void main(String[] args) {
        Heap heap = new Heap();
        List<Integer> list = heap.findK(new int[]{4, 1, 3, 2}, 2);
        for (int i : list) {
            System.out.println(i);
        }
    }

    public List<Integer> findK(int[] array, int k) {
        List<Integer> list = new ArrayList<>();
        if (array == null || array.length == 0) return list;
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i : array) {
            queue.add(i);
        }
        for (int i = 0;i < k;i ++) {
            list.add(queue.poll());
        }
        return list;
    }
}
