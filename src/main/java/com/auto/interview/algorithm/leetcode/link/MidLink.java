package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.ListNode;

/**
 * @author : jihai
 * @date : 2020/8/29
 * @description :
 * 876. 链表的中间结点
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
 *
 * 如果有两个中间结点，则返回第二个中间结点。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[1,2,3,4,5]
 * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
 * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
 * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
 * 示例 2：
 *
 * 输入：[1,2,3,4,5,6]
 * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
 * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
 */
public class MidLink {

    /**
     * 先计算出链表的长度，
     * 再遍历找到中间位置
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode p = head;
        int count = 0;
        while (p != null) {
            count ++;
            p = p.next;
        }
        int target = count / 2 + 1;
        p = head;
        count = 0;
        while (p != null) {
            count ++;
            if (count == target) return p;
            p = p.next;
        }
        return null;
    }


    /**
     * 比较优雅的解法，快慢指针
     * 快的指针走两步，走到尾巴的时候，慢指针就是要的节点
     */
    public ListNode middleNode2(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
