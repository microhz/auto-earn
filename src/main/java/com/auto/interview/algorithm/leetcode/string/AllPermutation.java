package com.auto.interview.algorithm.leetcode.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author : jihai
 * @date : 2020/9/11
 * @description :
 *
 * 567. 字符串的排列
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 示例1:
 *
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *
 *
 * 示例2:
 *
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *
 *
 * 注意：
 *
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 */
public class AllPermutation {

    public static void main(String[] args) {
        AllPermutation allPermutation = new AllPermutation();
        allPermutation.checkInclusion2("adc", "dcda");
    }


    /**
     * 滑动窗口思想
     */
    public boolean checkInclusion2(String s1, String s2) {
        if (s1 == null || s1.trim().equals("")) return true;
        if (s2 == null || s1.length() > s2.length()) return false;
        int len = s1.length();
        Map<Character, Integer> countMap = countMap(s1);
        int index = 0;
        while (index + len <= s2.length()) {
            if (valid(index, len, s2, countMap)) return true;
            index ++;
        }
        return false;
    }

    public  Map<Character, Integer> countMap(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            int count = 0;
            for (char c2 : s.toCharArray()) {
                if (c2 == c) count ++;
            }
            map.put(c, count);
        }
        return map;
    }

    public boolean valid(int index, int len, String s2, Map<Character, Integer> map) {
        char[] arr = s2.toCharArray();
        for (int i = index;i < index + len;i ++) {
            if (map.get(arr[i]) == null) return false;
            int count = 0;
            for (int j = index;j < index + len;j ++) {
                if (arr[j] == arr[i]) count ++;
            }
            if (count != map.get(arr[i])) return false;
        }
        return true;
    }


    /**
     * 超时，虽然是一个不错的思想
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s1.trim().equals("")) return true;
        if (s2 == null || s1.length() > s2.length()) return false;
        if (! checkCount(s1, s2)) return false;
        return checkInclusion(s1, s2, new StringBuilder());
    }

    public boolean checkCount(String s1, String s2) {
        Map<Character,Integer> countMap = new HashMap<>();
        for (int i = 0;i < s1.length();i ++) {
            char c = s1.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            char c = entry.getKey();
            int count = 0;
            for (char c2 : s2.toCharArray()) {
                if (c2 == c) count ++;
            }
            if (count < entry.getValue()) return false;
        }
        return true;
    }

    Set<Integer> traceSet = new HashSet<>();
    public boolean checkInclusion(String s1, String s2, StringBuilder sb) {
        if (sb.length() == s1.length()) return true;
        for (int i = 0;i < s1.length();i ++) {
            if (traceSet.contains(i)) continue;
            traceSet.add(i);
            sb.append(s1.charAt(i));
            if (s2.contains(sb.toString()) && checkInclusion(s1, s2, sb)) return true;
            sb.deleteCharAt(sb.length() - 1);
            traceSet.remove(i);
        }
        return false;
    }
}
