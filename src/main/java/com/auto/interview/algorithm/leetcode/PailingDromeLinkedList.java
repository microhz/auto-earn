package com.auto.interview.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/8/9
 * @description :
 *
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class PailingDromeLinkedList {

    public static void main(String[] args) {
        ListNode listNode = ListNodeUtils.buildListNode(1, 2, 2, 1);
        PailingDromeLinkedList pailingDromeLinkedList = new PailingDromeLinkedList();
        Assert.assertTrue(pailingDromeLinkedList.isPalindrome2(listNode));

        Assert.assertTrue(! pailingDromeLinkedList.isPalindrome2(ListNodeUtils.buildListNode(1, 1, 2, 1)));

        Assert.assertTrue(pailingDromeLinkedList.isPalindrome2(ListNodeUtils.buildListNode(1, 0, 1)));

    }

    /**
     * 方法1：
     * 生成一个链表反转
     * 一起遍历两个链表，看每个节点向减是否为0
     * 时间复杂度 n
     * 空间复杂度 n
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode reverseLink =  reverseLink(head);
        ListNode p1 = head, p2 = reverseLink;
        while (p1 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    // 生成一个链表
    private ListNode reverseLink(ListNode head) {
        ListNode listNode = copyList(head);

        // 反转链表
        ListNode p1 = null, p2 = listNode;
        while (p2 != null) {
            ListNode preNode = p2;
            p2 = p2.next;
            preNode.next = p1;
            p1 = preNode;
        }
        return p1;
    }

    private ListNode copyList(ListNode head) {
        ListNode newHead = new ListNode(head.val);
        ListNode p = head.next;
        ListNode p2 = newHead;
        while (p != null) {
            p2.next = new ListNode(p.val);
            p2 = p2.next;
            p = p.next;
        }
        return newHead;
    }

    /**
     * 方法2：
     * 构造一个数组
     * 双向指针比较
     *
     * 时间复杂度 n
     * 空间复杂度 n
     */
    public boolean isPalindrome2(ListNode head) {
        List<ListNode> array = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            array.add(p);
            p = p.next;
        }
        if (array.size() == 1) return true;
        int left = 0, right = array.size() - 1;
        while (left < right) {
            if (array.get(left).val != array.get(right).val) {
                return false;
            }
            left ++;
            right --;
        }
        return true;
    }


    /**
     * 递归
     */
    public boolean isPalindrome3(ListNode head) {
        return false;
    }
}
