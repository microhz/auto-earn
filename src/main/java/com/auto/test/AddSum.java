package com.auto.test;

import com.auto.interview.algorithm.leetcode.base.ListNode;
import com.auto.interview.algorithm.leetcode.utils.Assert;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

/**
 * @author : jihai
 * @date : 2020/8/13
 * @description :
 *
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 * 提示：
 *
 * num1 和num2 的长度都小于 5100
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
 */
public class AddSum {

    public static void main(String[] args) {
        ListNode listNode = NodeUtils.buildListNode(1, 2, 3, 4);

        ListNode listNode1 = NodeUtils.buildListNode(5, 6);
        listNode1.next.next = listNode.next;

        System.out.println(getIntersectionNode(listNode, listNode1).val);;

    }

    /**
     * "3456"
     * "79232"
     *
     * 字符串转换为字符，字符ascii码转换成数字
     *
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode p1 = headA, p2 = headB;
        boolean flag1 = true, flag2 = true;
        while (true) {
            if (p1 == p2) return p1;
            p1 = p1.next;
            p2 = p2.next;
            if (p1 == null) {
                if (flag1) {
                    p1 = headB;
                    flag1 = false;
                } else {
                    p1 = headA;
                    flag1 = true;
                }
            }
            if (p2 == null) {
                if (flag2) {
                    p2 = headA;
                    flag2 = false;
                } else {
                    p2 = headB;
                    flag2 = true;
                }
            }
        }
    }
}
