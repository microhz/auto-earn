package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.ListNode;

/**
 * @author : jihai
 * @date : 2020/8/29
 * @description :
 *
 * 82. 删除排序链表中的重复元素 II
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 *
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 */
public class DeleteLinkDuplcates {

    public static void main(String[] args) {

    }


    /**
     * 自己写出来的，比较慢
     * 利用递归的思路
     */
    public ListNode deleteDuplicates(ListNode head) {
        // 边界处理
        if (head == null) return null;
        ListNode curNode = head;
        int count = 0;
        // 跳过重复的节点
        while (curNode.next != null && curNode.next.val == curNode.val) {
            curNode = curNode.next;
            count ++;
        }
        if (count > 0) {
            // 如果重复的数字在脸链表的结尾，直接返回null即可
            if (curNode.next == null) return null;
            // 存在重复的数字直接跳过
            return deleteDuplicates(curNode.next);
        }
        // 不存在重复的，下一个节点接续递归
        curNode.next = deleteDuplicates(curNode.next);
        return curNode;
    }
}
