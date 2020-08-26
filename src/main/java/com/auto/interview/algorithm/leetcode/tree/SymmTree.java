package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

/**
 * @author : jihai
 * @date : 2020/8/24
 * @description :
 */
public class SymmTree {

    public static void main(String[] args) {
        TreeNode treeNode = NodeUtils.buildTreeNode(4, 2, 7, 1, 3, 6, 9);
        SymmTree symmTree = new SymmTree();
        symmTree.mirrorTree(treeNode);
    }

    /**
     *  方法1：
     *  递归，不改变原树
     *  把右节点挂在左边。左节点挂在右边，然后递归树的左右
     */
    public TreeNode mirrorTree(TreeNode root) {
        TreeNode newTreeNode = new TreeNode(root.val);
        getMirror(root.left, root.right, newTreeNode);
        return newTreeNode;
    }

    public void getMirror(TreeNode left, TreeNode right, TreeNode node) {
        if (node == null) return ;
        if (right != null) {
            node.left = new TreeNode(right.val);
            getMirror(right.left, right.right, node.left);
        }
        if (left != null) {
            node.right = new TreeNode(left.val);
            getMirror(left.left, left.right, node.right);
        }
    }
}
