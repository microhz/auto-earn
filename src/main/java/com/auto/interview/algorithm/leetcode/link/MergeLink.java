package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.ListNode;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

/**
 * @author : jihai
 * @date : 2021/4/2
 * @description :
 */
public class MergeLink {

    public static void main(String[] args) {
        ListNode listNode = NodeUtils.buildListNode(1, 2, 3);
        ListNode listNode1 = NodeUtils.buildListNode(1, 2, 3);

        mergeKLists(new ListNode[]{listNode, listNode1});
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        while (true) {
            ListNode minNode = null;
            for (ListNode node : lists) {
                if (node != null) {
                    if (minNode == null) minNode = node;
                    if (node.val <= minNode.val) minNode = node;
                }
            }
            if (minNode != null) {
                p.next = minNode;
                p = p.next;
            } else break;
        }
        return head.next;
    }
}
