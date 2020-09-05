package com.auto.interview.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author : jihai
 * @date : 2020/9/5
 * @description :
 *
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *
 *
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 */
public class AllValidString {

    public static void main(String[] args) {
        AllValidString allValidString = new AllValidString();
        allValidString.generateParenthesis2(3);
    }

    public void change(StringBuilder sb) {
        sb.append("b");
    }


    public List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max){
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }

        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open+1, close, max);
            // 回溯法关键，这里会把递归拼接的都删除
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close+1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    /**
     * 全排列再判断是否是有效括号
     */
    List<String> list;
    public List<String> generateParenthesis(int n) {
        list = new ArrayList<>();
        if (n <= 0) return list;
        generate("", n * 2);
        return list;
    }

    public void generate(String s, int len) {
        if (len == 0) {
            if (isValid(s)) list.add(s);
            return ;
        }
        generate(s + '(', len - 1);
        generate(s + ')', len - 1);
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0;i < s.length();i ++) {
            if (s.charAt(i) == ')') {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
            else stack.push('(');
        }
        return stack.isEmpty();
    }
}
