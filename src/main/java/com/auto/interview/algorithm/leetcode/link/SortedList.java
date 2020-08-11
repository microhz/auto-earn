package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.ListNode;
import com.auto.interview.algorithm.leetcode.utils.AssertUtils;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

/**
 * @author : jihai
 * @date : 2020/8/10
 * @description :
 *
 * 148. 排序链表
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 示例 1:
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 */
public class SortedList {

    public static void main(String[] args) {
        SortedList sortedList = new SortedList();
        AssertUtils.assertListNode(sortedList.sortList(NodeUtils.buildListNode(4, 1, 2, 5, -2, -1, 0)), -2,-1,0,1,2,4,5);
    }


    /**
     * 双指针法，找到中间位置
     * 切分成两个链表, 左链表排序， 右链表排序
     * 最后归并
     *
     * 切分
     * 排序
     * 合并
     *
     * 时间复杂度 nLogn
     * 空间复杂度 如果不考虑栈帧 1 复杂度
     */
    public ListNode sortList(ListNode head) {
        if (head == null) return null;
        // 双指针法找到中间点
        ListNode pre = head;
        if (pre.next == null) {
            return pre;
        }
        ListNode p1 = pre, p2 = pre;
        while (p2.next != null && p2.next.next != null) {
            p2 = p2.next.next;
            p1 = p1.next;
        }
        ListNode nextNode = p1.next;
        p1.next = null;
        ListNode leftNode = sortList(pre);
        ListNode rightNode = sortList(nextNode);
        return mergeList(leftNode, rightNode);
    }


    private ListNode mergeList(ListNode leftNode, ListNode rightNode) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        while (leftNode != null && rightNode != null) {
            if (leftNode.val < rightNode.val) {
                p.next = leftNode;
                leftNode = leftNode.next;
            } else {
                p.next = rightNode;
                rightNode = rightNode.next;
            }
            p = p.next;
        }
        while (leftNode != null) {
            p.next = leftNode;
            p = p.next;
            leftNode = leftNode.next;
        }
        while (rightNode != null) {
            p.next = rightNode;
            p = p.next;
            rightNode = rightNode.next;
        }
        return head.next;
    }


    /**
     *
     * 归并排序
     */
    public ListNode sortList2(ListNode head) {
        ListNode min = head, p = head;
        while (p != null) {
            if (p.val < min.val) {
                min = p;
            }
            p = p.next;
        }
        recurisve(head);
        return min;
    }

    /**
     * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
     * 就是快排的算法
     *
     * 分治法
     * 取第一个节点，把链表小的移动到左边，大的移动到右边
     * 然后分别递归左边和右边
     */
    public ListNode sortList3(ListNode head) {
        ListNode min = head, p = head;
        while (p != null) {
            if (p.val < min.val) {
                min = p;
            }
            p = p.next;
        }
        recurisve(head);
        return min;
    }

    /**
     * 递归
     */
    private void recurisve(ListNode head) {
        if (head == null) {
            return ;
        }
        // 三个指针，左边链表，右边链表，当前
        ListNode p = head;
        while (p.next != null) {
            /*
            如果下一个节点比当前节点小，那就位置交换
            如果下一个节点比当前节点大，用一个二级指针滑动，找到下一个更小的
             */
            if (p.next.val < p.val) {
                ListNode f = p;
                ListNode s = p.next;
                ListNode t = p.next.next;

                f.next = t;
                s.next = f;
            } else {
                /**
                 *  *  * 输入: 4->1->2->5->0->0->0
                 *  *   *  * 输入: 1->2->4->5->0->0->0
                 */
                /*ListNode subNode = p.next;
                while (subNode != null) {
                    if (subNode.val < p.val) {
                        p.next = subNode.next;
                        break;
                    } else {
                        subNode = subNode.next;
                    }
                }*/
            }
        }

//        recurisve(left);
//        recurisve(right);
    }
}
