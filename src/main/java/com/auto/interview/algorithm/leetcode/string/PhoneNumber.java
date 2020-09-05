package com.auto.interview.algorithm.leetcode.string;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/9/3
 * @description :
 *
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 图略了!!!!!!!
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 */
public class PhoneNumber {

    public static void main(String[] args) {
//        ImmutableMultimap.builder().put().put()
    }


    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (digits == null || digits.equals("")) return list;
        return getCombinations(digits, list);
    }

    public List<String> getCombinations(String s, List<String> retList) {
        if (s == null || s.equals("")) return retList;
        List<Character> list = new ArrayList<>();
        if (s.charAt(0) == '2') {
            list.add('a');
            list.add('b');
            list.add('c');
        } else if (s.charAt(0) == '3') {
            list.add('d');
            list.add('e');
            list.add('f');
        } else if (s.charAt(0) == '4') {
            list.add('g');
            list.add('h');
            list.add('i');
        } else if (s.charAt(0) == '5') {
            list.add('j');
            list.add('k');
            list.add('l');
        } else if (s.charAt(0) == '6') {
            list.add('m');
            list.add('n');
            list.add('o');
        } else if (s.charAt(0) == '7') {
            list.add('p');
            list.add('q');
            list.add('r');
            list.add('s');
        } else if (s.charAt(0) == '8') {
            list.add('t');
            list.add('u');
            list.add('v');
        } else if (s.charAt(0) == '9') {
            list.add('w');
            list.add('x');
            list.add('y');
            list.add('z');
        }
        List<String> newList = new ArrayList<>();
        if (retList.size() == 0) {
            for (Character c : list) {
                newList.add(String.valueOf(c));
            }
        } else {
            for (String s1 : retList) {
                for (Character c : list) {
                    newList.add(s1 + c);
                }
            }
        }
        return getCombinations(s.substring(1, s.length()), newList);
    }
}
