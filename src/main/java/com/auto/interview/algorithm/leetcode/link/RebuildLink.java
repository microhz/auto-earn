package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.ListNode;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

/**
 * @author : jihai
 * @date : 2020/8/28
 * @description :
 */
public class RebuildLink {

    public static void main(String[] args) {

        RebuildLink rebuildLink = new RebuildLink();
        rebuildLink.reorderList(NodeUtils.buildListNode(1, 2, 3, 4));
    }

    // 本质这是一个递归的问题
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return ;
        ListNode p = head;
        while (p.next.next != null) {
            p = p.next;
        }
        ListNode insertNode = p.next;
        p.next = null;
        insertNode.next = head.next;
        head.next = insertNode;
        reorderList(insertNode.next);
    }
}
