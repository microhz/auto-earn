package com.auto.interview.algorithm.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : jihai
 * @date : 2020/9/1
 * @description :
 *
 * 剑指 Offer 50. 第一个只出现一次的字符
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 *
 * 示例:
 *
 * s = "abaccdeff"
 * 返回 "b"
 *
 * s = ""
 * 返回 " "
 *
 *
 * 限制：
 *
 * 0 <= s 的长度 <= 50000
 */
public class FirstNotDuplcates {

    public char firstUniqChar(String s) {
        if (s == null || s.trim().equals("")) return ' ';
        Map<Character, Boolean> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, !map.containsKey(c));
        }
        for (char c : s.toCharArray()) {
            if (map.get(c)) return c;
        }
        return ' ';
    }
}
