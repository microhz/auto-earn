package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;

/**
 * @author : jihai
 * @date : 2020/9/9
 * @description :
 * 98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 */
public class ValidSearchBinaryTree {

    /**
     * 自己写的
     *  二次递归
     *  第一次递归判断左右子数是否每个节点对于当前节点的值符合平衡
     *  第二次递归当前节点本身
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (root.left != null && !validLeft(root.val, root.left)) {
            return false;
        }
        if (root.right != null && !validRight(root.val, root.right)) {
            return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

    public boolean validLeft(int val, TreeNode node) {
        if (node == null) return true;
        if (node.val >= val) return false;
        if (node.left != null && !validLeft(val, node.left)) return false;
        if (node.right != null && !validLeft(val, node.right)) return false;
        return true;
    }

    public boolean validRight(int val, TreeNode node) {
        if (node == null) return true;
        if (node.val <= val) return false;
        if (node.left != null && !validRight(val, node.left)) return false;
        if (node.right != null && !validRight(val, node.right)) return false;
        return true;
    }


    /**
     * 方法2：中序遍历判断是否递增
     */
    int last = Integer.MIN_VALUE;
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (isValidBST2(root.left)) {
            if (last < root.val) {
                last = root.val;
                return isValidBST2(root.right);
            }
        }
        return false;
    }
}
