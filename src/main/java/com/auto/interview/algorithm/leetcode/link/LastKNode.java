package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.ListNode;

/**
 * @author : jihai
 * @date : 2020/8/28
 * @description :
 * 剑指 Offer 22. 链表中倒数第k个节点
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
 *
 *
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 *
 * 返回链表 4->5.
 */
public class LastKNode {


    /**
     * 自己写的，时间复杂度为n
     * 一个慢指针慢k步走就行了
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) return null;
        ListNode p1 = head, p2 = head;
        int i = 1;
        while (p2.next != null) {
            if (i >= k) p1 = p1.next;
            p2 = p2.next;
            i ++;
        }
        return p1;
    }
}
