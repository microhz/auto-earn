package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.ListNode;

/**
 * @author : jihai
 * @date : 2020/8/28
 * @description :
 *
 * 面试题 02.05. 链表求和
 * 给定两个用链表表示的整数，每个节点包含一个数位。
 *
 * 这些数位是反向存放的，也就是个位排在链表首部。
 *
 * 编写函数对这两个整数求和，并用链表形式返回结果。
 *
 *
 *
 * 示例：
 *
 * 输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
 * 输出：2 -> 1 -> 9，即912
 * 进阶：假设这些数位是正向存放的，请再做一遍。
 *
 * 示例：
 *
 * 输入：(6 -> 1 -> 7) + (2 -> 9 -> 5)，即617 + 295
 * 输出：9 -> 1 -> 2，即912
 */
public class LinkSum {


    /**
     * 自己写的, 进位
     * 方法1：
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dumyNode = new ListNode(0);
        ListNode p = dumyNode;
        int carr = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carr;
            carr = sum > 9 ? sum / 10 : 0;
            p.next = new ListNode(sum % 10);
            p = p.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int sum = l1.val + carr;
            carr = sum > 9 ? sum / 10 : 0;
            p.next = new ListNode(sum % 10);
            p = p.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            int sum = l2.val + carr;
            carr = sum > 9 ? sum / 10 : 0;
            p.next = new ListNode(sum % 10);
            p = p.next;
            l2 = l2.next;
        }
        if (carr > 0) {
            p.next = new ListNode(carr);
        }
        return dumyNode.next;
    }


    /**
     * 自己基于上面的代码进行的优化
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dumyNode = new ListNode(0);
        ListNode p = dumyNode;
        int carr = 0;

        while (l1 != null || l2 != null) {
            int sum = 0;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            sum += carr;
            carr = sum > 9 ? sum / 10 : 0;
            p.next = new ListNode(sum % 10);
            p = p.next;
        }
        if (carr > 0) p.next = new ListNode(carr);
        return dumyNode.next;
    }
}
