package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.ListNode;

/**
 * @author : jihai
 * @date : 2020/10/23
 * @description :
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class IsReverseLink {

    // 因为考虑常数空间复杂度，主要思路是反转前面一半的节点为反转链表，再同时从中间向两头遍历判断
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        // 记录节点的数量，因为奇偶性需要特殊处理
        int count = 0;
        ListNode p = head;
        while (p != null) {
            p = p.next;
            count ++;
        }
        // 计算反转数量
        int moveCount = count / 2;
        ListNode pre = null, next = head;
        // 进行反转前面一半节点
        int index = 0;
        while (index < moveCount) {
            ListNode nextNode = next.next;
            next.next = pre;
            pre = next;
            next = nextNode;
            index ++;
        }
        // 如果是奇数个，右指针需要再走一步
        if (count % 2 == 1) next = next.next;
        // 双指针遍历
        while (pre != null && next != null) {
            if (pre.val != next.val) return false;
            pre = pre.next;
            next = next.next;
        }
        // 同时到达末端
        return pre == null && next == null;
    }
}
