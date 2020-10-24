package com.auto.interview.algorithm.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/10/24
 * @description :
 */
public class MinClips {

    private int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        MinClips minClips = new MinClips();
        /**
         * [[0,5],[1,6],[2,7],[3,8],[4,9],[5,10],[6,11],[7,12],[8,13],[9,14],[10,15],[11,16],[12,17],[13,18],[14,19],[15,20],[16,21],[17,22],[18,23],[19,24],[20,25],[21,26],[22,27],[23,28],[24,29],[25,30],[26,31],[27,32],[28,33],[29,34],[30,35],[31,36],[32,37],[33,38],[34,39],[35,40],[36,41],[37,42],[38,43],[39,44],[40,45],[41,46],[42,47],[43,48],[44,49],[45,50],[46,51],[47,52],[48,53],[49,54]]
         * 50
         */
//        String s = "[[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]]";
//        String replace = s.replace("[", "{").replace("]", "}");
//        System.out.println(replace);
//
//        int i1 = minClips.videoStitching2(new int[][]{{0,2},{4,6},{8,10},{1,9},{1,5},{5,9}}, 6);
//        System.out.println(i1);
        A a = new B();
        A.say();
        B.say();
    }

    public int videoStitching2(int[][] clips, int T) {
        int[] dp = new int[T + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        for (int i = 1; i <= T; i++) {
            for (int[] clip : clips) {
                if (clip[0] < i && i <= clip[1]) {
                    dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
                }
            }
        }
        return dp[T] == Integer.MAX_VALUE - 1 ? -1 : dp[T];
    }


    public int videoStitching(int[][] clips, int T) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0;i < clips.length;i ++) {
            list.add(i);
        }
        if (! isFull(list, clips, T)) {
            return -1;
        }
        minChips(clips, T, 0, new ArrayList<>());
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    // start开始递归组合，找到满足全片的最小组合长度
    public void minChips(int[][] clips, int T, int start, List<Integer> indexList) {
        if (indexList.size() >= min) return ;
        for (Integer i = start;i < clips.length;i ++) {
            if (isMoreMin(indexList, clips, i)) continue;
            indexList.add(i);
            if (isFull(indexList, clips, T)) {
                System.out.println("full : " + indexList);
                min = Math.min(indexList.size(), min);
            }
//            System.out.println(indexList);
            minChips(clips, T, start + 1, indexList);
            indexList.remove(i);
        }
    }

    public boolean isMoreMin(List<Integer> list, int[][] clips, int index) {
        int[] arr = clips[index];
        double s = arr[0];
        while (s <= arr[1]) {
            boolean find = false;
            for (int i : list) {
                if (clips[i][0] <= s && clips[i][1] >= s) {
                    find = true;
                    break;
                }
            }
            if (! find) return false;
            s += 0.5;
        }
        System.out.println("skip : " + list);
        return true;
    }

    public boolean isFull(List<Integer> list, int[][] clips, int T) {
        if (list.size() >= min) return false;
        double d = Double.valueOf(T);
        double s = 0;
        while (s <= d) {
            boolean find = false;
            for (int i : list) {
                int[] clip = clips[i];
                if (clip[0] <= s && s <= clip[1]) {
                    find = true;
                    break;
                }
            }
            if (! find) return false;
            s += 0.5;
        }
        return true;
    }
}
class A {
    public static void say() {
        System.out.println("A");
    }
}
class B extends A {
    public static void say() {
        System.out.println("B");
    }
}
