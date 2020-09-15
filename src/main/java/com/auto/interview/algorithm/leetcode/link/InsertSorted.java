package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.ListNode;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

/**
 * @author : jihai
 * @date : 2020/9/15
 * @description :
 *
 * 147. 对链表进行插入排序
 * 对链表进行插入排序。
 *
 *
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 *
 *
 *
 * 插入排序算法：
 *
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 *
 *
 * 示例 1：
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2：
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 */
public class InsertSorted {

    public static void main(String[] args) {

        InsertSorted insertSorted = new InsertSorted();
        insertSorted.insertionSortList2(NodeUtils.buildListNode(4, 2, 1, 3));
    }


    /**
     * 自己写的迭代法
     * @param head
     * @return
     */
    public ListNode insertionSortList3(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        while (head != null && head.next != null) {
            if (head.next.val >= head.val) {
                head = head.next;
                continue;
            }
            ListNode curr = head.next;
            while (pre.next != null && pre.next.val <= curr.val) {
                pre = pre.next;
            }
            head.next = curr.next;
            curr.next = pre.next;
            pre.next = curr;

//            head = curr.next; 这一行直接导致耗时增加不少，其实没必要，仔细品
            pre = dummyNode;
        }
        return dummyNode.next;
    }

    public ListNode insertionSortList2(ListNode head) {
        ListNode dummy = new ListNode(0), pre;
        dummy.next = head;

        while(head != null && head.next != null) {
            if(head.val <= head.next.val) {
                head = head.next;
                continue;
            }
            pre = dummy;

            while (pre.next.val < head.next.val) pre = pre.next;

            ListNode curr = head.next;

            head.next = curr.next;
            curr.next = pre.next;
            pre.next = curr;
        }
        return dummy.next;
    }

    /**
     * 方法1，递归
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null) return null;
        return insertSort(head);
    }

    public ListNode insertSort(ListNode head) {
        if (head == null) return null;
        ListNode p = head;
        ListNode insertNode = p, pre = null, pre2 = null;
        while (p != null) {
            if (p.val < insertNode.val) {
                pre = pre2;
                insertNode = p;
            }
            pre2 = p;
            p = p.next;
        }
        if (insertNode != head) {
            ListNode next = insertNode.next;
            insertNode.next = head;
            pre.next = next;
        }

        insertNode.next = insertSort(insertNode.next);
        return insertNode;
    }
}
