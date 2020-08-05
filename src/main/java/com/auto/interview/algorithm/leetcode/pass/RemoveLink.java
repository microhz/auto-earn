package com.auto.interview.algorithm.leetcode.pass;

import com.auto.interview.algorithm.leetcode.Assert;
import com.auto.interview.algorithm.leetcode.ListNode;
import com.auto.interview.algorithm.leetcode.ListNodeUtils;

/**
 * @author : jihai
 * @date : 2020/8/4
 * @description :
 * 删除链表中等于给定值 val 的所有节点。
 *
 * https://leetcode-cn.com/problems/remove-linked-list-elements/submissions/
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
public class RemoveLink {

    public static void main(String[] args) {
        RemoveLink removeLink = new RemoveLink();

        ListNode listNode = ListNodeUtils.buildListNode(1, 2, 6, 3, 4, 5, 6);
        assertLinkNode(listNode, 1, 2, 6, 3, 4, 5, 6);
        removeLink.removeElements(listNode, 6);
        assertLinkNode(listNode, 1, 2, 3, 4, 5);


        ListNode listNode2 = ListNodeUtils.buildListNode(1);
        listNode2 = removeLink.removeElements(listNode2, 1);
        Assert.assertTrue(listNode2 == null);
    }

    private static void assertLinkNode(ListNode listNode, int ... vals) {
        ListNode p = listNode;
        for (int i = 0; i < vals.length; i++) {
            if (p.val != vals[i]) {
                new RuntimeException("assert error");
            }
            p = p.next;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        // 移动头不是 val
        while (head.val == val) {
            head = head.next;
            if (head == null) {
                return null;
            }
        }

        ListNode p = head;
        while (p.next != null) {
            if (p.next.val == val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return head;
    }
}
// pass

