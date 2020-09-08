package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.ListNode;

/**
 * @author : jihai
 * @date : 2020/9/8
 * @description :
 *
 *
 * 86. 分隔链表
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 示例:
 *
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 */
public class ParitionLink {


    /**
     * 自己写的，判断的条件比较多，通过不断的修改原链表的指向，比较复杂
     */
    public ListNode partition(ListNode head, int x) {
        // 非空处理
        if (head == null) return null;
        // 哨兵节点，避免单个节点判断
        ListNode dummyNode = new ListNode(0);
        ListNode p = head;
        dummyNode.next = p;
        // 需要插入的前一个节点位置，即大于等于x的前一个节点
        ListNode pre = dummyNode;
        // 找到第一个pre节点
        while (p != null) {
            if (p.val >= x) break;
            pre = p;
            p = p.next;
        }
        // pre2为大于等于x的节点右边第一个小于x节点的前驱节点
        ListNode pre2 = null;
        while (p != null) {
            if (p.val >= x) {
                pre2 = p;
                p = p.next;
            } else {
                // 把找到的节点插入到pre节点前
                ListNode next = p.next;
                ListNode findNode = p;
                findNode.next = pre.next;
                pre.next = findNode;
                pre2.next = next;
                p = pre2.next;
                // 不要忘记移动pre节点
                pre = pre.next;
            }
        }
        return dummyNode.next;
    }


    /**
     * 官方的答案，明显更好
     * 把小于x的构造一个链表，大于等于x的构造一个链表，最后连接起来
     */
    public ListNode partition2(ListNode head, int x) {
        // 非空处理
        if (head == null) return null;
        ListNode beforeDummyNode = new ListNode(0);
        ListNode afterDummyNode2 = new ListNode(0);
        ListNode p1 = beforeDummyNode, p2 = afterDummyNode2;

        ListNode p = head;
        while (p != null) {
            if (p.val < x) {
                p1.next = new ListNode(p.val);
                p1 = p1.next;
            } else {
                p2.next = new ListNode(p.val);
                p2 = p2.next;
            }
            p = p.next;
        }
        p1.next = afterDummyNode2.next;
        return beforeDummyNode.next;
    }
}
