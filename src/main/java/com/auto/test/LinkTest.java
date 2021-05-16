package com.auto.test;

import com.auto.interview.algorithm.leetcode.base.ListNode;

/**
 * @author : jihai
 * @date : 2021/3/15
 * @description :
 */
public class LinkTest {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);

        reverseList(node);
    }


    public static ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        return reverseList(pre, cur);
    }

    public static ListNode reverseList(ListNode pre, ListNode cur) {
        if (cur == null || cur.next == null) return cur;
        ListNode next = cur.next;
        cur.next = pre;
        return reverseList(cur, next);
    }
}
