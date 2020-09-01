package com.auto.interview.algorithm.leetcode.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : jihai
 * @date : 2020/9/1
 * @description :
 */
public class MosCipher {

    public static void main(String[] args) {
        Arrays.asList(".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--..");
    }

    /**
     * 摩斯密码
     * @param words
     * @return
     */
    public int uniqueMorseRepresentations(String[] words) {
        String[] array = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        Set<String> set = new HashSet<>();
        for (String word : words) {
            StringBuffer sb = new StringBuffer();
            for (char c : word.toCharArray()) {
                sb.append(array[(int) c - 'a']);
            }
            set.add(sb.toString());
        }
        return set.size();
    }
}
