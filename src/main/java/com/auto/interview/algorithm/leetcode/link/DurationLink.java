package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.ListNode;

/**
 * @author : jihai
 * @date : 2020/8/28
 * @description :
 *
 * 61. 旋转链表
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 *
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 */
public class DurationLink {

    /**
     * 感觉是字节跳动当初考的那道题
     * 每一步都是上一步的重复操作，就很容易想到递归了，顺带用了个快慢指针把最后一个节点移动到头来
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) return head;
        ListNode fast = head, slow = head;
        int step = 1;
        while (fast.next != null) {
            if (step ++ > 1) slow = slow.next;
            fast = fast.next;
        }
        k = k % step;
        if (k == 0) return head;
        slow.next = null;
        fast.next = head;
        return rotateRight(fast, k - 1);
    }
}
