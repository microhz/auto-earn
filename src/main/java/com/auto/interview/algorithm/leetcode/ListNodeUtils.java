package com.auto.interview.algorithm.leetcode;

/**
 * @author : jihai
 * @date : 2020/8/4
 * @description :
 */
public class ListNodeUtils {

    public static ListNode buildListNode(int ... vals) {
        ListNode head = new ListNode(vals[0]);
        ListNode p = head;
        for (int i = 1; i < vals.length; i++) {
            p.next = new ListNode(vals[i]);
            p = p.next;
        }
        return head;
    }
}
