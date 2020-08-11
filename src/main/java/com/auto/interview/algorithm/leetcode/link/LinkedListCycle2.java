package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.ListNode;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;
import com.auto.interview.algorithm.leetcode.utils.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : jihai
 * @date : 2020/8/9
 * @description :
 */
public class LinkedListCycle2 {
    public static void main(String[] args) {
        ListNode listNode = NodeUtils.buildListNode(-3, -2 ,-1, 0, 1, 2, 3, 4);
        listNode.next.next.next.next.next.next.next.next = listNode.next.next.next;
        LinkedListCycle2 linkedListCycle = new LinkedListCycle2();
        Assert.assertTrue(linkedListCycle.detectCycle2(listNode) == listNode.next.next.next);


        ListNode head = NodeUtils.buildListNode(1, 2);
        head.next.next = head;
        Assert.assertTrue(linkedListCycle.detectCycle2(head) == head);

    }

    /**
     * 方法1：
     * hash表法
     * 维护一个哈希表，保存遍历过的节点
     * 如果某个节点的下一个节点在哈希表内，则存在循环链表
     *
     * 时间复杂度 n 方
     * 空间复杂度 n 方
     */
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode p = head;
        while (p != null) {
            if (set.contains(p)) {
                return p;
            }
            set.add(p);
            p = p.next;
        }
        return null;
    }


    /**
     * 方法2: floyd算法
     * 定义两个指针，一个快一个慢，如果是循环链表，相遇的位置记下来
     * 第一个指针返回head开始，第二个指针从记下的位置继续开始，两个指针分别移动，交叉点就为循环链表起点
     *
     * 本题目需要通过数学进行一定的推算，存在一定的复杂性
     */
    public ListNode detectCycle2(ListNode head) {
        ListNode p1 = head, p2 = head;
        int index = 0;
        while (p1 != p2 || index == 0) {
            if (p2 == null || p1.next == null || p2.next == null || p2.next.next == null) {
                return  null;
            }
            p1 = p1.next;
            p2 = p2.next.next;
            index ++;
        }
        ListNode t1 = p1, t2 = head;
        while (t1 != t2) {
            t1 = t1.next;
            t2 = t2.next;
        }
        return t1;
    }

}
