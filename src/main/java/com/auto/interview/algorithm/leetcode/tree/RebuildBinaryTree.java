package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;

/**
 * @author : jihai
 * @date : 2020/8/26
 * @description :
 *
 *
 * 剑指 Offer 07. 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 *
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 */
public class RebuildBinaryTree {

    public static void main(String[] args) {

    }

    /**
     * 方法1：
     * 自己写的
     * 边界、找出左子树，右子树，进行递归处理
     * 递归入口，递归出口
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) return null;
        int root = preorder[0];

        // 寻找左右子树的中序序列
        int count = 0;
        for (int i = 0;i < inorder.length;i ++) {
            if (inorder[i] == root) {
                count = i;
                break;
            }
        }
        int[] leftInorder = new int[count];
        for (int i = 0;i < count;i ++) {
            leftInorder[i] = inorder[i];
        }
        int[] rightInorder = new int[inorder.length - count - 1];
        for (int i = 1 + count, x = 0;x < inorder.length - count - 1;i ++, x ++) {
            rightInorder[x] = inorder[i];
        }

        // 寻找左右子树的前序列
        int[] leftPre = new int[count];
        for (int i = 1, y = 0;y < count;i ++, y ++) {
            leftPre[y] = preorder[i];
        }
        int[] rightPre = new int[preorder.length - count - 1];
        for (int i = 1 + count, k = 0;i < preorder.length;i ++,k ++) {
            rightPre[k] = preorder[i];
        }

        TreeNode node = new TreeNode(preorder[0]);
        if (leftPre.length != 0) node.left = buildTree(leftPre, leftInorder);
        if (rightPre.length != 0) node.right = buildTree(rightPre, rightInorder);
        return node;
    }
}
