package com.auto.interview.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : jihai
 * @date : 2020/9/11
 * @description :
 */
public class Cube {

    public static void main(String[] args) {
        Cube cube = new Cube();
        cube.makesquare(new int[]{5,5,5,5,4,4,4,4,3,3,3,3});

        cube.isPalindrome(10);
    }


    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        return new StringBuilder(x).toString().equals(new StringBuilder(x).reverse().toString());
    }

    List<List<Integer>> ansList;
    Set<Integer> ansTraceSet;
    int len;
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 4 != 0) return false;
        this.len = sum / 4;
        ansList = new ArrayList<>();
        ansTraceSet = new HashSet<>();
        makesquare(nums, 0, new ArrayList<Integer>(), new HashSet<Integer>());
        return ansList.size() == 4;
    }

    public void makesquare(int[] nums, int start, List<Integer> list, Set<Integer> traceSet) {
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        if (sum == len) {
            ansTraceSet.addAll(new HashSet<>(traceSet));
            ansList.add(new ArrayList<>(list));
            return ;
        }

        if (start == nums.length || sum > len) return ;
        for (int i = start;i < nums.length;i ++) {
            if (ansTraceSet.contains(i)) continue;
            list.add(nums[i]);
            traceSet.add(i);
            makesquare(nums, start + 1, list, traceSet);
            list.remove(list.size() - 1);
            traceSet.remove(i);
        }
    }
}
