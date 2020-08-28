package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.ListNode;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

/**
 * @author : jihai
 * @date : 2020/8/28
 * @description :
 */
public class SwapNodeLink {

    public static void main(String[] args) {

        SwapNodeLink swapNodeLink = new SwapNodeLink();
        swapNodeLink.swapPairs(NodeUtils.buildListNode(1, 2, 3, 4));
    }

    /**
     * 递归思路，注意边界
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode nextNode = head.next.next;
        // 第一种写法清晰
        // ListNode t = head.next;
        // t.next = head;
        // head.next = swapPairs(nextNode);
        // return t;

         head.next.next = head;
         ListNode t = head.next;
         head.next = swapPairs(nextNode);
        return t;
    }
}
