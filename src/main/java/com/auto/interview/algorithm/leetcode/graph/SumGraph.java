package com.auto.interview.algorithm.leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : jihai
 * @date : 2020/10/6
 * @description :
 */
public class SumGraph {

    public static void main(String[] args) {
        SumGraph sumGraph = new SumGraph();
        int[] ints = sumGraph.sumOfDistancesInTree(4, new int[][]{
                {1, 2},
                {2, 0},
                {0, 3}
        });
        for (int i : ints) {
            System.out.println(i);
        }
    }

    private Map<Integer, List<Integer>> map = new HashMap<>();
    List<Integer> reList;
    Map<Integer, Integer> retMap = new HashMap<>();
    int[] ret;
    // hash表+递归
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        // 构造哈希表

        for (int[] arr : edges) {
            List<Integer> list = map.get(arr[0]);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(arr[1]);
            map.put(arr[0], list);


            List<Integer> list2 = map.get(arr[1]);
            if (list2 == null) {
                list2 = new ArrayList<>();
            }
            list2.add(arr[0]);
            map.put(arr[1], list2);
        }

        // 递归
        ret = new int[N];
        reList = new ArrayList<>();
        getSumDistance(N);
        // 转换结果
        return ret;
    }

    public void getSumDistance(int N) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0;i < N;i ++) {
            list.add(i);
            getSumDistance(i, list, 0, 0);
            list.clear();
        }
    }

    public void getSumDistance(int i, List<Integer> list, int count, int sum) {
        if (map.get(i) == null) return ;
        for (Integer nextVal : map.get(i)) {
            if (! list.contains(nextVal)) {
                count ++;
                sum += count;
                list.add(nextVal);
                retMap.put(list.get(0), retMap.getOrDefault(list.get(0), 0) + list.size() - 1);
                ret[list.get(0)] = retMap.get(list.get(0));
                getSumDistance(nextVal, list, count, sum);
                count --;
                sum -= count;
                list.remove(list.size() - 1);
            }
        }
    }
}
