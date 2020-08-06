package com.auto.interview.algorithm.leetcode.pass;

import com.auto.interview.algorithm.leetcode.AssertUtils;
import com.auto.interview.algorithm.leetcode.ListNode;
import com.auto.interview.algorithm.leetcode.ListNodeUtils;

/**
 * @author : jihai
 * @date : 2020/8/5
 * @description :
 *
 * 2. 两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 *
 *
 *  * 输入：(1 -> 4 -> 3) + (1 -> 6 -> 4)
 *  * 输出：8 -> 0 -> 2
 *  * 原因：341 + 461 = 802
 *
 *
 *   *  * 输入：(2 -> 7 -> 3) + (1 -> 6)
 *  *  * 输出：3 -> 3 -> 3
 *  *  * 原因：61 + 372 = 433
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode listNode = ListNodeUtils.buildListNode(2, 7, 3);
        ListNode listNode2 = ListNodeUtils.buildListNode(1, 6);
        ListNode resultListNode = addTwoNumbers.addTwoNumbers(listNode, listNode2);
        AssertUtils.assertListNode(resultListNode, 4, 3, 3);


        ListNode listNode3 = ListNodeUtils.buildListNode(2, 4, 3);
        ListNode listNode4 = ListNodeUtils.buildListNode(5, 6, 4);
        ListNode resultListNode2 = addTwoNumbers.addTwoNumbers(listNode3, listNode4);
        AssertUtils.assertListNode(resultListNode2, 8, 0, 7);

    }

    /**
     * 两个链表同时遍历，按位数，头节点为*1 + 第二个节点*10 + 第三个节点 * 100 + 第n个节点* 10的(n - 1)次方
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int n = 1;
        int result = 0;
        while (l1 != null || l2 != null) {
            int l1Number = 0, l2Number = 0;
            if (l1 != null) {
                l1Number = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                l2Number = l2.val;
                l2 = l2.next;
            }
            result += (l1Number + l2Number) * n;
            n *= 10;
        }

        // 807
//        n = 1;
//        ListNode p = null;
//        ListNode head = null;
//        while (result )

        /*n /= 10;
        ListNode p = null;
        ListNode head = null;
        while (result / 10 >= 0 && result > 0) {
            int x = result / n;
            result -= x * n;
            n /= 10;
            if (p == null) {
                head = new ListNode(x);
                p = head;
            } else {
                p.next = new ListNode(x);
                p = p.next;
            }
        }
        return head;*/
        return null;
    }
}
