package com.auto.interview.algorithm.leetcode;

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
 *  * 输入: 4, 1, 2, 5, -2, -1, 0
 *   *  * 输入: 1->2->4->5->0->0->0
 */
public class SortedList {

    public static void main(String[] args) {
        SortedList sortedList = new SortedList();
        AssertUtils.assertListNode(sortedList.sortList(ListNodeUtils.buildListNode(4, 1, 2, 5, -2, -1, 0)), -2,-1,0,1,2,4,5);
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
    public ListNode sortList(ListNode head) {
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
