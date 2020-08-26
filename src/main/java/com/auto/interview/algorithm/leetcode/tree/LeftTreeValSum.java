package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;

/**
 * @author : jihai
 * @date : 2020/8/25
 * @description :
 *
 * 404. 左叶子之和
 * 计算给定二叉树的所有左叶子之和。
 *
 * 示例：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 *
 *
 * 通过次数33,735提交次数60,789
 */
public class LeftTreeValSum {

    public static void main(String[] args) {

    }

    /**
     * 自己写的
     * 方法1：
     * 递归计算，如果满足条件就累加，不满足就递归就好了
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        int sum = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) sum += root.left.val;
        sum += sumOfLeftLeaves(root.left);
        sum += sumOfLeftLeaves(root.right);
        return sum;
    }
}
