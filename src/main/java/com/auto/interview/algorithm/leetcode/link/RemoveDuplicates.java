package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : jihai
 * @date : 2020/9/8
 * @description :
 */
public class RemoveDuplicates {

    Set<Integer> set;
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) return null;
        set = new HashSet<>();
        return removeDuplicate(head);
    }

    /**
     * 递归法
     * 时间复杂度n
     * 空间复杂度n
     */
    public ListNode removeDuplicate(ListNode node) {
        if (node == null) return null;
        if (! set.contains(node.val)) {
            set.add(node.val);
            node.next = removeDuplicate(node.next);
            return node;
        }
        return removeDuplicate(node.next);
    }


    /**
     * 迭代法
     * 时间复杂度n
     * 空间复杂度n
     */
    public ListNode removeDuplicateNodes2(ListNode head) {
        if (head == null) return null;
        set = new HashSet<>();
        ListNode dummyNode = new ListNode(0);
        ListNode p = head;
        dummyNode.next = p;
        while (p != null) {
            set.add(p.val);
            ListNode cur = p.next;
            while (cur != null && set.contains(cur.val)) {
                cur = cur.next;
            }
            p.next = cur == null ? null : cur;
            p = p.next;
        }
        return dummyNode.next;
    }
}
