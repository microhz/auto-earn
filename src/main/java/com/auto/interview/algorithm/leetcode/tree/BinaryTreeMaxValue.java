package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/8/13
 *
 * 124. 二叉树中的最大路径和
 * 给定一个非空二叉树，返回其最大路径和。
 *
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 *
 *        1
 *       / \
 *      2  3
 *
 * 输出: 6
 * 示例 2:
 *
 * 输入: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15  7
 *
 * 输出: 42
 */
public class BinaryTreeMaxValue {

    public static void main(String[] args) {
        BinaryTreeMaxValue binaryTreeMaxValue = new BinaryTreeMaxValue();
        System.out.println(binaryTreeMaxValue.maxPathSum(NodeUtils.buildTreeNode(-2, -3, -1, null, null, null, null)));
        System.out.println(binaryTreeMaxValue.maxPathSum(NodeUtils.buildTreeNode(-10, 9, 20, null, null, 15, 7)));

    }

    /**
     * 中序遍历
     * 累计连续最大
     */
    public int maxPathSum(TreeNode root) {
        List<Integer> list = searchTree(root);
        return getMaxSub(list);
    }

    /**
     * 中序遍历
     * @param root
     * @return
     */
    private List<Integer> searchTree(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        searchTree(root, list);
        return list;
    }

    private void searchTree(TreeNode root, List<Integer> list) {
        TreeNode p = root;
        if (p.left != null) {
            searchTree(p.left, list);
        }
        list.add(p.val);
        if (p.right != null) {
            searchTree(p.right, list);
        }
    }
    // -3, -2, -1
    private int getMaxSub(List<Integer> list) {
        return 0;
    }
}
