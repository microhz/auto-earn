package com.auto.interview.algorithm.leetcode.link;

import com.auto.common.Learn;
import com.auto.common.Self;
import com.auto.interview.algorithm.leetcode.base.ListNode;
import com.auto.interview.algorithm.leetcode.utils.Assert;
import com.auto.interview.algorithm.leetcode.utils.AssertUtils;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

/**
 * @author : jihai
 * @date : 2020/8/9
 * @description :
 *
 * 19. 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 *
 * 你能尝试使用一趟扫描实现吗？
 */
public class RemoveNNode {

    public static void main(String[] args) {
        RemoveNNode removeNNode = new RemoveNNode();
        AssertUtils.assertListNode(removeNNode.removeNthFromEnd2(NodeUtils.buildListNode(1, 2, 3, 4, 5), 2), 1, 2, 3, 5);
        Assert.assertTrue(removeNNode.removeNthFromEnd2(NodeUtils.buildListNode(1), 1) == null);
        AssertUtils.assertListNode(removeNNode.removeNthFromEnd2(NodeUtils.buildListNode(1, 2), 2), 2);
    }


    /**
     * 方法2：
     *
     * 官方答案：amazing
     *
     * 可以保持两个指针间距为n
     * 然后移动即可
     *
     * 时间复杂度 n
     * 空间复杂度 1
     */
    @Learn
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head.next == null) return null;
        ListNode p1 = head, p2 = head;
        int index = 0;
        while (index < n) {
            p2 = p2.next;
            index ++;
        }
        if (p2 == null) return head.next;
        while (p2.next != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        p1.next = p1.next.next;
        return head;
    }

    /**
     * 动态规划
     *
     * 遍历一遍计算链表的长度
     * 遍历第二个遍找出倒数第n个节点的前驱节点, 用指针跳过
     *
     * 时间复杂度 n
     * 空间复杂度 1
     */
    @Self
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = 0;
        ListNode p = head;
        while (p != null) {
            size ++;
            p = p.next;
        }
        int target = size - n - 1;
        if (target == -1) {
            head = head.next;
            return head;
        }
        int index = 0;
        p = head;
        while (index < target) {
            p = p.next;
            index ++;
        }
        p.next = p.next.next;
        return head;
    }
}
