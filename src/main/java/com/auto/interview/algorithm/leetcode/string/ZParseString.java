package com.auto.interview.algorithm.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/9/1
 * @description :
 */
public class ZParseString {

    public static void main(String[] args) {
        ZParseString zParseString = new ZParseString();
        zParseString.convert("LEETCODEISHIRING", 3);
    }


    /**
     * 参考官方答案写出来的
     * 巧用了行数的切换 flag 让 z字可以切换
     */
    public String convert2(String s, int numRows) {
        if (numRows <= 1 || s == null) return s;
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0;i < numRows;i ++) {
            list.add(new StringBuilder());
        }
        int flag = -1;
        int i = 0;
        for (char c : s.toCharArray()) {
            list.get(i).append(c);
            if (i == 0 || i == numRows - 1) flag = - flag;
            i += flag;
        }
        StringBuilder sb = new StringBuilder();
        for (StringBuilder s1 : list) {
            sb.append(s1);
        }
        return sb.toString();
    }

    public String convert(String s, int numRows) {
        if(numRows < 2) return s;
        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for(int i = 0; i < numRows; i++) rows.add(new StringBuilder());
        int i = 0, flag = -1;
        for(char c : s.toCharArray()) {
            rows.get(i).append(c);
            if(i == 0 || i == numRows -1) flag = - flag;
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for(StringBuilder row : rows) res.append(row);
        return res.toString();
    }

}
