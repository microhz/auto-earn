package com.auto.interview.algorithm.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : jihai
 * @date : 2020/10/17
 * @description :
 */
public class Huanghou {

    public static void main(String[] args) {
        Huanghou huanghou = new Huanghou();
        huanghou.totalNQueens(4);
    }

    int count = 0;
    public int totalNQueens(int n) {
        if (n <= 0) return -1;
        Map<Integer, Integer> traceMap = new HashMap<>();
        total(n, 0, traceMap);
        return count;
    }

    public void total(int n, int j, Map<Integer, Integer> traceMap) {
        if (traceMap.size() == n) {
            count ++;
            System.out.println(traceMap);
            return ;
        }
        for (Integer i = 0;i < n;i ++) {
            if (traceMap.keySet().contains(i) || isExits(j, i, n, traceMap)) continue;
            traceMap.put(j, i);
            total(n, j + 1, traceMap);
            traceMap.remove(j);
        }
    }

    public boolean isExits(int i, int j, int n, Map<Integer, Integer> traceMap) {
        for (int a = i,b = j;a >= 0 && b < n;a --,b ++) {
            if (traceMap.get(a) != null && traceMap.get(a) == b) return true;
        }
        for (int a = i,b = j;a >= 0 && b >= 0;a --,b --) {
            if (traceMap.get(a) != null && traceMap.get(a) == b) return true;
        }
        return false;
    }
}
