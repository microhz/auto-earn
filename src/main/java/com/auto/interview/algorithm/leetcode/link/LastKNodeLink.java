package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.ListNode;

/**
 * @author : jihai
 * @date : 2020/8/28
 * @description :
 *
 * 面试题 02.02. 返回倒数第 k 个节点
 * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
 *
 * 注意：本题相对原题稍作改动
 *
 * 示例：
 *
 * 输入： 1->2->3->4->5 和 k = 2
 * 输出： 4
 * 说明：
 *
 * 给定的 k 保证是有效的。
 */
public class LastKNodeLink {


    /**
     * 自己写的，快慢指针法
     */
    public int kthToLast(ListNode head, int k) {
        if (head == null) return -1;
        ListNode fast = head, slow = head;
        int step = 1;
        while (fast != null) {
            if (step ++ > k) {
                slow = slow.next;
            }
            fast = fast.next;
        }
        return slow.val;
    }
}
