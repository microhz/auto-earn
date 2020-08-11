package com.auto.interview.algorithm.leetcode.number;

import com.auto.common.Self;
import com.auto.interview.algorithm.leetcode.utils.AssertUtils;
import com.auto.interview.algorithm.leetcode.base.ListNode;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

/**
 * @author : jihai
 * @date : 2020/8/10
 * @description :
 *
 * 445. 两数相加 II
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 *
 *
 * 进阶：
 *
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *
 *
 *
 * 示例：
 *
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 */
public class AddSum {

    public static void main(String[] args) {
        AddSum addSum = new AddSum();
        AssertUtils.assertListNode(addSum.addTwoNumbers(NodeUtils.buildListNode(7, 2, 4, 3),
                NodeUtils.buildListNode(5, 6, 4)), 7, 8, 0, 7);
    }

    /**
     * 反转链表
     * 进位
     */
    @Self
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = reverseLink(l1), p2 = reverseLink(l2);
        int carry = 0;
        ListNode head = null, p = new ListNode(0);
        while (p1 != null || p2 != null) {
            int sum = (p1 != null ? p1.val : 0) + (p2 != null ? p2.val : 0) + carry;
            if (sum >= 10) {
                carry = sum / 10;
                sum %= 10;
            } else {
                carry = 0;
            }
            p.next = new ListNode(sum);
            if (head == null) {
                head = p.next;
            }
            if (p1 != null) p1 = p1.next;
            if (p2 != null) p2 = p2.next;
            p = p.next;
        }
        if (carry > 0) {
            p.next = new ListNode(carry);
        }
        return reverseLink(head);
    }

    private ListNode reverseLink(ListNode node) {
        ListNode pre = null, cur = node;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

}
