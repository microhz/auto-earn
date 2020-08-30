package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.ListNode;

/**
 * @author : jihai
 * @date : 2020/8/30
 * @description :
 *
 * 25. K 个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 *
 *
 * 示例：
 *
 * 给你这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 *
 *
 * 说明：
 *
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * 通过次数97,335提交次数155,098
 */
public class KGroupReverse {

    public static void main(String[] args) {

    }
    /**hard级别，关键在于反转链表的方式用头插法
     * 利用头插法
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        ListNode p = head;
        int count = 0;
        while (p != null && count < k) {
            p = p.next;
            count ++;
        }
        if (p == null && count < k) return head;

        ListNode dummpyNode = new ListNode(0);
        dummpyNode.next = head;
        for (int i = 0;i < k - 1;i ++) {
            ListNode insertNode = head.next;
            head.next = head.next.next;
            insertNode.next = dummpyNode.next;
            dummpyNode.next = insertNode;
        }
        head.next = reverseKGroup(head.next, k);
        return dummpyNode.next;
    }
}
