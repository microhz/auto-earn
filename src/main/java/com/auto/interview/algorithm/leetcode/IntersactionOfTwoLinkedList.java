package com.auto.interview.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : jihai
 * @date : 2020/8/8
 * @description :
 *
 * 160. 相交链表
 * 编写一个程序，找到两个单链表相交的起始节点。
 *
 * 如下面的两个链表：
 *
 *
 *
 * 在节点 c1 开始相交。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *
 *
 * 示例 2：
 *
 *
 *
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 *
 *
 * 示例 3：
 *
 *
 *
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 *
 *
 * 注意：
 *
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 */
public class IntersactionOfTwoLinkedList {

    public static void main(String[] args) {
        IntersactionOfTwoLinkedList intersactionOfTwoLinkedList = new IntersactionOfTwoLinkedList();
        ListNode headA = ListNodeUtils.buildListNode(4, 1, 8, 4, 5);

        ListNode headB = ListNodeUtils.buildListNode(5, 0, 1);
        headB.next = headA.next.next;
        Assert.assertTrue(intersactionOfTwoLinkedList.getIntersectionNode3(headA, headB).val == 8);


        ListNode headA2 = ListNodeUtils.buildListNode(1, 2, 3);

        ListNode headB2 = ListNodeUtils.buildListNode(4);
        Assert.assertTrue(intersactionOfTwoLinkedList.getIntersectionNode3(headA2, headB2) == null);
    }

    /**
     * 分治法
     * 定义两个指针
     * p1, p2分别指向第一个链表和第二个链表
     *
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        while (p1 != null) {
            while (p2 != null) {
                if (p1 == p2) {
                    return p1;
                }
                p2 = p2.next;
            }
            p1 = p1.next;
            p2 = headB;
        }
        return null;
    }


    /**
     * Hash表
     * 复杂度 m + n
     * 空间复杂度 m
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        Set<ListNode> contains = new HashSet<>();
        while (p1 != null) {
            contains.add(p1);
            p1 = p1.next;
        }

        while (p2 != null) {
            if (contains.contains(p2)) {
                return p2;
            }
            p2 = p2.next;
        }
        return null;
    }


    /**
     * 这个算法比较巧妙
     * 两个指针，分别遍历两个链表
     * 当A链表到尾巴在换到B链表
     */
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        ListNode tail = null;
        if (headA == null || headB == null) {
            return null;
        }
        while (true) {
            if (p1 == p2) {
                return p1;
            }
            if (p1.next == null) {
                if (tail == null) tail = p1;
                if (tail != null && p1 != tail) {
                    return null;
                }
                p1 = headB;
            } else {
                p1 = p1.next;
            }
            if (p2.next == null) {
                if (tail == null) tail = p2;
                if (tail != null && p2 != tail) {
                    return null;
                }
                p2 = headA;
            } else {
                p2 = p2.next;
            }
        }
    }
}
