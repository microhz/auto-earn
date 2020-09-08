package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.ListNode;

/**
 * @author : jihai
 * @date : 2020/9/8
 * @description :
 *
 * 328. 奇偶链表
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 *
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 *
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 */
public class OddEvenLinkedList {



    /**
     * 构造两个链表，再连接起来
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode dummyNode1 = new ListNode(0);
        ListNode dummyNode2 = new ListNode(0);
        int index = 1;
        ListNode p = head, p1 = dummyNode1, p2 = dummyNode2;
        while (p != null) {
            if (index % 2 == 1) {
                p1.next = p;
                p1 = p1.next;
            } else {
                p2.next = p;
                p2 = p2.next;
            }
            p = p.next;
            index ++;
        }
        p2.next = null; // 注意此处如果是奇数个节点很容易行程循环链表，需要把后驱节点置为null
        p1.next = dummyNode2.next;
        return dummyNode1.next;
    }


    /**
     * 官方的更加简洁
     */
}
