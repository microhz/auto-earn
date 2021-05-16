package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.ListNode;
import com.auto.interview.algorithm.leetcode.base.TreeNode;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

/**
 * @author : jihai
 * @date : 2021/4/12
 * @description :
 */
public class MergeLink2 {

    public static void main(String[] args) {
        /*ListNode listNode = NodeUtils.buildListNode(4, 2, 1, 3);
        MergeLink2 mergeLink2 = new MergeLink2();
        mergeLink2.sortList(listNode);*/

        /**
         * [1,4,3,2,5,2]
         * 3
         */
       /* ListNode listNode = NodeUtils.buildListNode(1, 4, 3, 2, 5, 2);
        ListNode partition = partition(listNode, 3);*/


        /*ListNode listNode = NodeUtils.buildListNode(1, 2, 3, 4);
        System.out.println(oddEvenList(listNode));

        System.out.println(NodeUtils.buildListNode(1,2,3,4,5));*/

        /*MergeLink2 mergeLink2 = new MergeLink2();
        mergeLink2.reverse(1534236469);*/


        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20);

        MergeLink2 mergeLink2 = new MergeLink2();
        mergeLink2.maxDepth(treeNode);
    }

    int max = 0;
    public int maxDepth(TreeNode root) {
        maxDepth(root, 0);
        return max;
    }

    public void maxDepth(TreeNode root, int depth) {
        if (root == null) {
            max = Math.max(max, depth);
            return ;
        }
        maxDepth(root.left, depth + 1);
        maxDepth(root.right, depth + 1);
    }


    int sum = 0;
    int n = 1;
    boolean over = false;
    public int reverse(int x) {
        int symbol = x >= 0 ? 1 : -1;
        int ret = reverseNumber(x >= 0 ? x : -x);
        ret *= symbol;
        return ret;
    }

    private int reverseNumber(int x) {
        int t = x % 10;
        if (x > 9) {
            sum += reverseNumber(x / 10);
        }
        if (sum > Integer.MAX_VALUE - (n * t)) {
            over = true;
            return 0;
        }
        if (over) return 0;
        sum += (n * t);
        n *= 10;
        return sum;
    }


    public static ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode p1 = head, dum = new ListNode(0), p2 = dum;
        while (p1 != null && p1.next != null) {
            ListNode next = p1.next;
            p1.next = p1.next.next;
            p1 = p1.next;

            p2.next = next;
            p2 = p2.next;
        }
        if (p2.next == p1) p2.next = null;
        p1.next = dum.next;
        return head;
    }


    public static ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        ListNode preNode = new ListNode(Integer.MIN_VALUE);
        ListNode p1 = preNode;
        preNode.next = head;
        while (p1.next != null && p1.next.val < x) {
            p1 = p1.next;
        }
        ListNode p2 = p1.next;
        while (p2 != null && p2.next != null) {
            if (p2.next.val >= x) {
                p2 = p2.next;
            } else {
                ListNode n = p2.next;
                p2.next = p2.next.next;
                n.next = p1.next;
                p1.next = n;
            }
        }
        return preNode.next;
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode preNode1 = new ListNode(0);
        preNode1.next = head;
        ListNode fast = head, slow = preNode1;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode next = slow.next;
        slow.next = null;
        ListNode first = sortList(head);
        ListNode second = sortList(next);

        ListNode preNode = new ListNode(0);
        ListNode p = preNode;

        while (first != null && second != null) {
            if (first.val <= second.val) {
                p.next = first;
                first = first.next;
            } else {
                p.next = second;
                second = second.next;
            }
            p = p.next;
        }

        while (first != null) {
            p.next = first;
            first = first.next;
            p = p.next;
        }
        while (second != null) {
            p.next = second;
            second = second.next;
            p = p.next;
        }
        return preNode.next;
    }
}
