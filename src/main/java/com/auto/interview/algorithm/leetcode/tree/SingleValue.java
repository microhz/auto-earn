package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;

/**
 * @author : jihai
 * @date : 2020/8/25
 * @description :
 *
 * 965. 单值二叉树
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 *
 * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：[1,1,1,1,1,null,1]
 * 输出：true
 * 示例 2：
 *
 *
 *
 * 输入：[2,2,2,5,2]
 * 输出：false
 *
 *
 * 提示：
 *
 * 给定树的节点数范围是 [1, 100]。
 * 每个节点的值都是整数，范围为 [0, 99]
 */
public class SingleValue {

    public static void main(String[] args) {

    }

    /**
     * 递归
     */
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return true;
        int val = root.val;
        return dfs(root, val);
    }

    public boolean dfs(TreeNode root, int val) {
        if (root == null) return true;
        return root.val == val && dfs(root.left, val) && dfs(root.right, val);
    }
}
