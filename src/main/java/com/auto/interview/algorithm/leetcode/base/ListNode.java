package com.auto.interview.algorithm.leetcode.base;

/**
 * @author : jihai
 * @date : 2020/8/4
 * @description :
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
