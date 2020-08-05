package com.auto.interview.algorithm.leetcode.pass;

import com.auto.interview.algorithm.leetcode.AssertUtils;
import com.auto.interview.algorithm.leetcode.ListNode;
import com.auto.interview.algorithm.leetcode.ListNodeUtils;

/**
 * @author : jihai
 * @date : 2020/8/4
 * @description :
 * 206. 反转链表
 * 反转一个单链表。
 *
 * 示例:
 *
 *
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class ReverseLink {

    public static void main(String[] args) {
        ListNode listNode = ListNodeUtils.buildListNode(1, 2, 3);
        ReverseLink reverseLink = new ReverseLink();
        ListNode listNode1 = reverseLink.reverseList(listNode);
        AssertUtils.assertListNode(listNode1, 3, 2, 1);

        ListNode listNode2 = ListNodeUtils.buildListNode(1, 2, 3, 4, 5);

        ListNode listNode3 = reverseLink.reverseList2(listNode2);
        AssertUtils.assertListNode(listNode3, 5, 4, 3, 2, 1);
    }


    /**
     * 用两个指针，一个指向前一个，一个指向后一个
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        // 迭代
        ListNode p1 = null;
        ListNode p2 = head;
        while (p2 != null) {
            ListNode nextNode = p2.next;
            ListNode curNode = p2;
            p2.next = p1;
            p1 = curNode;
            p2 = nextNode;
        }
        return p1;
    }

    /**
     * 递归
     * 思路同理
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode p1 = null;
        ListNode p2 = head;
        return recursion(p1 ,p2);
    }

    private ListNode recursion(ListNode p1, ListNode p2) {
        if (p2 == null) {
            return p1;
        }
        ListNode next = p2.next;
        p2.next = p1;
        p1 = p2;
        p2 = next;
        return recursion(p1, p2);
    }

}

// pass
