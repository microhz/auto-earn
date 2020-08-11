package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.ListNode;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;
import com.auto.interview.algorithm.leetcode.utils.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : jihai
 * @date : 2020/8/9
 * @description :
 * 141. 环形链表
 * 给定一个链表，判断链表中是否有环。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 *
 *
 * 示例 1：
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 *
 * 示例 2：
 *
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 *
 * 示例 3：
 *
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 *
 *
 *
 *
 * 进阶：
 *
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 */
public class LinkedListCycle {

    public static void main(String[] args) {
        ListNode listNode = NodeUtils.buildListNode(3, 2, 0, -4);
        listNode.next.next.next.next = listNode.next;
        LinkedListCycle linkedListCycle = new LinkedListCycle();
        Assert.assertTrue(linkedListCycle.hasCycle2(listNode));
    }

    /**
     * 方法1：
     * hash表法
     * 维护一个哈希表，保存遍历过的节点
     * 如果某个节点的下一个节点在哈希表内，则存在循环链表
     *
     * 时间复杂度 n 方
     * 空间复杂度 n 方
     */
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }


    /**
     * 方法2:
     * 定义两个指针，如果有循环列表，一个快一个慢，总会相遇
     */
    public boolean hasCycle2(ListNode head) {
        if (head == null) return false;
        ListNode p1 = head, p2 = head.next;
        while (p1 != p2) {
            if (p2 == null || p1.next == null || p2.next == null || p2.next.next == null) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return true;
    }
}
