package com.auto.interview.algorithm.leetcode.bytedance;

import com.auto.interview.algorithm.leetcode.ListNode;
import com.auto.interview.algorithm.leetcode.ListNodeUtils;

/**
 * @author : jihai
 * @date : 2020/8/5
 * @description :
 *
 * 给定单链表的头结点 head，实现一个调整链表的函数，
 * 从链表尾部开始，以 K 个结点为一组进行逆序翻转，
 * 头部剩余结点不足一组时，不需要翻转。（不能使用队列或者栈作为辅助）
 *
 * 链表:1->2->3->4->5->6->7->8->null, K = 3。
 * 那么 6->7->8，3->4->5，1->2各位一组。
 * 调整后：1->2->5->4->3->8->7->6->null。其中 1，2不调整，因为不够一组。
 */
public class LinkReverse {

    public static void main(String[] args) {
        LinkReverse linkReverse = new LinkReverse();
        ListNode listNode = ListNodeUtils.buildListNode(1, 2, 3, 4, 5, 6, 7, 8);
        ListNode listNode1 = linkReverse.reverseLink(listNode, 3);
        System.out.println(listNode1);
    }

    /**
     *  1->2->3->4->5->6->7->null
     *  3
     *
     * @param head
     */
    public ListNode reverseLink(ListNode head, int k) {
        // 计算从哪个节点开始要反转
        // 循环去拼接反转链表
        int linkSize = getLinkSize(head);
        int startIndex = linkSize % k;

        ListNode tail = getReverseIndex(head, startIndex);
        ListNode pHead = tail.next;
        while (pHead != null) {
            // 获取下一个反转链表
            ListNode curNode = pHead;
            pHead = getReverseIndex(pHead, k + 1);
            // 反转当前这个链表
            ListNode listNode = getReverseLink(curNode, k);
            tail.next = listNode;
            // 获取到反转后链表尾巴
            tail = getReverseIndex(listNode, k);
            if (pHead == null) {
                break;
            }
        }
        return head;
    }

    /**
     * 反转链表
     * @param head
     * @param k
     * @return 1->2->3 -> null
     * 3->2->1-> null
     */
    private ListNode getReverseLink(ListNode head, int k) {
        ListNode p1 = null;
        ListNode p2 = head;
        int count = 1;
        while (count <= k) {
            ListNode nextNode = p2;
            p2 = p2.next;
            nextNode.next = p1;
            p1 = nextNode;
            count ++;
        }
        return p1;
    }

    private ListNode getReverseIndex(ListNode head, int startIndex) {
        ListNode p = head;
        int count = 1;
        while (count < startIndex) {
            p = p.next;
            count ++;
        }
        return p;
    }

    private int getLinkSize(ListNode head) {
        int size = 0;
        ListNode p = head;
        while (p != null) {
            size ++;
            p = p.next;
        }
        return size;
    }
}
