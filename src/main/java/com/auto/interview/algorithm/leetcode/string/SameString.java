package com.auto.interview.algorithm.leetcode.string;

import com.auto.interview.algorithm.leetcode.utils.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : jihai
 * @date : 2020/8/4
 * @description :
 * 205. 同构字符串
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 *
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 *
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 *
 * 示例 1:
 *
 * 输入: s = "egg", t = "add"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * 示例 3:
 *
 * 输入: s = "paper", t = "title"
 * 输出: true
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 *
 */
public class SameString {

    public static void main(String[] args) {
        SameString sameString = new SameString();
        Assert.assertTrue(sameString.isIsomorphic("paper", "title"));
        Assert.assertTrue(!sameString.isIsomorphic("ab", "aa"));

    }

    /**
     * 判断哪些字符串一样
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        char[] chars = s.toCharArray();
        char[] chars2 = t.toCharArray();

        Set<Character> set = new HashSet<Character>();
        for (int i = 0; i < chars.length; i++) {
            if (set.contains(chars[i])) {
                continue;
            }
            List<Integer> index = new ArrayList<Integer>();
            index.add(i);
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j]) {
                    index.add(j);
                }
            }
            char sameChar = chars2[i];

            List<Integer> targetIndex = new ArrayList<>();
            for (int j = 0; j < chars2.length; j++) {
                if (chars2[j] == sameChar) {
                    targetIndex.add(j);
                }
            }
            boolean equals = index.equals(targetIndex);
            set.add(chars[i]);
            if (equals) {
                continue;
            }
            return false;
        }
        return true;
    }
}
// pass
