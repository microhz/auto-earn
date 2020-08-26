package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.ListNode;

import java.util.Stack;

/**
 * @author : jihai
 * @date : 2020/8/24
 * @description :
 *
 * 剑指 Offer 06. 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 */
public class ReversePrintArray {

    /**
     * 直接利用栈的性质
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        if (head == null) return new int[0];
        Stack<Integer> stack = new Stack<>();
        ListNode p = head;
        while (p != null) {
            stack.push(p.val);
            p = p.next;
        }
        int[] arr = new int[stack.size()];
        int len = stack.size();
        for (int i = 0;i < len;i ++) {
            arr[i] = stack.pop();
        }
        return arr;
    }
}
