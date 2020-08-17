package com.auto.interview.algorithm.leetcode.doing;

import com.auto.interview.algorithm.leetcode.base.TreeNode;
import com.auto.interview.algorithm.leetcode.utils.Assert;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

/**
 * @author : jihai
 * @date : 2020/8/14
 * @description :
 *
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 */
public class BalanceTree {
    public static void main(String[] args) {
        BalanceTree balanceTree = new BalanceTree();
        Assert.assertTrue(balanceTree.isBalanced(NodeUtils.buildTreeNode(3,9,20,null,null,15,7)));
        Assert.assertTrue(balanceTree.isBalanced2(NodeUtils.buildTreeNode(3,9,20,null,null,15,7)));
    }

    /**
     * 递归遍历，分别判断左右两个子树的高度差
     *
     * 时间复杂度过高，因为有很多冗余计算
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int left = height(root.left);
        int right = height(root.right);
        return Math.abs(left - right) < 2 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    /**
     * 方法2，自底向上递归
     * @param root
     * @return
     */
    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return findIsBalanced(root).getBalanced();
    }

    private TreeInfo findIsBalanced(TreeNode root) {
        if (root == null) {
            return new TreeInfo(true, 0);
        }
        TreeInfo leftTreeInfo = findIsBalanced(root.left);
        TreeInfo rightTreeInfo = findIsBalanced(root.right);
        return new TreeInfo(Math.abs(leftTreeInfo.getHeight() - rightTreeInfo.getHeight()) < 2
                && leftTreeInfo.getBalanced() && rightTreeInfo.getBalanced(),
                Math.max(leftTreeInfo.getHeight(), rightTreeInfo.getHeight()) + 1);
    }

}
class TreeInfo {
    private Boolean isBalanced;
    private int height;

    public TreeInfo(Boolean isBalanced, int height) {
        this.isBalanced = isBalanced;
        this.height = height;
    }

    public Boolean getBalanced() {
        return isBalanced;
    }

    public void setBalanced(Boolean balanced) {
        isBalanced = balanced;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
