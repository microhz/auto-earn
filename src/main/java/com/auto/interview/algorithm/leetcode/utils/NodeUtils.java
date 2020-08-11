package com.auto.interview.algorithm.leetcode.utils;

import com.auto.interview.algorithm.leetcode.base.ListNode;
import com.auto.interview.algorithm.leetcode.base.TreeNode;

/**
 * @author : jihai
 * @date : 2020/8/4
 * @description :
 */
public class NodeUtils {

    public static ListNode buildListNode(int ... vals) {
        ListNode head = new ListNode(vals[0]);
        ListNode p = head;
        for (int i = 1; i < vals.length; i++) {
            p.next = new ListNode(vals[i]);
            p = p.next;
        }
        return head;
    }

    /**
     * 构造二叉树
     * @param params
     * @return 3层
     */
    public static TreeNode buildTreeNode(Integer ... params) {
        TreeNode treeNode = new TreeNode(params[0]);
        if (params[1] != null) {
            treeNode.left = new TreeNode(params[1]);
        }
        if (params[2] != null) {
            treeNode.right = new TreeNode(params[2]);
        }
        if (params[3] != null) {
            treeNode.left.left = new TreeNode(params[3]);
        }
        if (params[4] != null) {
            treeNode.left.right = new TreeNode(params[4]);
        }
        if (params[5] != null) {
            treeNode.right.left = new TreeNode(params[5]);
        }
        if (params[6] != null) {
            treeNode.right.right = new TreeNode(params[6]);
        }
        return treeNode;
        /*if (params[7] != null) {
            treeNode.left.left.left = new TreeNode(params[7]);
        }
        if (params[8] != null) {
            treeNode.left.left.right = new TreeNode(params[8]);
        }
        if (params[9] != null) {
            treeNode.left.right.left = new TreeNode(params[9]);
        }
        if (params[7] != null) {
            treeNode.left.right.right = new TreeNode(params[10]);
        }
        if (params[7] != null) {
            treeNode.left.left.left = new TreeNode(params[7]);
        }
        if (params[7] != null) {
            treeNode.left.left.left = new TreeNode(params[7]);
        }*/
    }
}
