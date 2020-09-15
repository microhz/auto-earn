package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.ListNode;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

/**
 * @author : jihai
 * @date : 2020/8/27
 * @description :
 *
 * 92. 反转链表 II
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 */
public class ChangeLink {

    public static void main(String[] args) {
        ListNode listNode = NodeUtils.buildListNode(1, 2, 3, 4, 5);
        ChangeLink changeLink = new ChangeLink();
//        Assert.assertTrue(changeLink.reverseBetween3(listNode, 1, 4) != null);


        int[] array = new int[6];
        for (int i = 0; i < 6; i++) {
            array[i] = i;
        }

        // 反转
        for (int i = 1; i < 6; i++) {
            int temp = array[i];
            for (int j = i;j >= 1;j --) {
                array[j] = array[j - 1];
            }
            array[0] = temp;
        }
        System.out.println(array);
    }

    public ListNode reverseBetween2(ListNode head, int m, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode g = dummyHead;
        ListNode p = dummyHead.next;

        int step = 0;
        while (step < m - 1) {
            g = g.next; p = p.next;
            step ++;
        }
        // 1, 2, 3, 4, 5   1, 3, 2, 4, 5    1, 4, 3, 2, 5
        for (int i = 0; i < n - m; i++) {
            ListNode removed = p.next;
            p.next = p.next.next;

            removed.next = g.next;
            g.next = removed;
        }

        return dummyHead.next;
    }


    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m == n) return head;
        ListNode p1 = new ListNode(0), p3 = new ListNode(0);// 哨兵不用单独处理头节点
        ListNode p2 = head, p4 = head;
        p1.next = p2;
        p3.next = p4;
        ListNode t1 = null,t2 = null;
        int index = 1;
        while (p2 != null || p4 != null || t1 == null || t2 == null) {
            if (t1 == null) {
                if (index == m) {
                    t1 = p2;
                    p2 = p2.next;
                } else {
                    p1 = p2;
                    p2 = p2.next;
                }
            }
            if (t2 == null) {
                if (index == n) {
                    t2 = p4;
                    p4 = p4.next;
                } else {
                    p3 = p4;
                    p4 = p4.next;
                }
            }
            if (t1 != null && t2 != null) {
                break;
            }
            index ++;
        }
        p1.next = t2;
        t2.next = p2;

        p3.next = t1;
        t1.next = p4;
        if (m == 1) {
            return t2;
        }
        return head;
    }



    // 1 ,2 , 3, 4, 5, 6
    /**
     *
     * 2 1 3 4 5 6
     * 3 2 1 4 5 6
     * 4 3 2 1 5 6
     * 5 4 3 2 1 6
     * 6 5 4 3 2 1
     */
    /**
     * 1 2 3 4 5 6
     * 1 3 2 4 5 6
     * 1 4 3 2 5 6
     * 1 5 4 3 2 6
     * 1 6 5 4 3 2
     */

    public ListNode reverseBetween3(ListNode head, int m, int n) {
        if (head == null || m == n) return head;
        ListNode dumyHead = new ListNode(0);
        ListNode p1 = dumyHead, p2 = head;
        dumyHead.next = p2;
        int index = 1;
        while (index < m) {
            p1 = p2;
            p2 = p2.next;
        }

        for (int i = 0; i < n - m; i++) {
            ListNode insertNode = p2.next;
            p2.next = p2.next.next;
            p1.next = insertNode;
            insertNode.next = p1.next;
        }
        return dumyHead.next;

    }
}
