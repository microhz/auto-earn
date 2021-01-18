package com.auto.interview.algorithm.leetcode.string;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author : jihai
 * @date : 2020/11/30
 * @description :
 */
public class RebuildString {

    public static void main(String[] args) {
        System.out.println(reorganizeString("aab"));;
    }

    public static String reorganizeString(String S) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) > (S.length() + 1) / 2) return "";
        }
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue((o1, o2) -> ( (Map.Entry<Character, Integer>) o2).getValue() - ( (Map.Entry<Character, Integer>) o1).getValue());
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            queue.add(entry);
        }

        StringBuffer sb = new StringBuffer();
        while (queue.size() >= 2) {
            Map.Entry<Character, Integer> e1 = queue.poll();
            Map.Entry<Character, Integer> e2 = queue.poll();

            sb.append(e1.getKey()).append(e2.getKey());
            e1.setValue(e1.getValue() - 1);
            e2.setValue(e2.getValue() - 1);

            if (e1.getValue() > 0) {
                queue.add(e1);
            }
            if (e2.getValue() > 0) {
                queue.add(e2);
            }
        }
        if (! queue.isEmpty()) {
            sb.append(queue.poll().getKey());
        }
        return sb.toString();
    }
}
