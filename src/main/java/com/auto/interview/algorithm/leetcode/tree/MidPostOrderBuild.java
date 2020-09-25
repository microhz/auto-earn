package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;

/**
 * @author : jihai
 * @date : 2020/9/25
 * @description :
 * 106. 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class MidPostOrderBuild {

    /**
     * 参考别人的算法写出来的
     * @param inorder
     * @param postorder
     * @return
     */
    // 找到根和左右子树进行构造
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length == 0 || postorder.length == 0) return null;
        if (inorder.length != postorder.length) return null;
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode buildTree(int[] inorder, int a, int b, int[] postorder, int c, int d) {
        if (a > b || c > d) return null;
        int i = a;
        while (inorder[i] != postorder[d]) {
            i ++;
        }
        TreeNode root = new TreeNode(postorder[d]);
        root.left = buildTree(inorder, a, i - 1, postorder, c, c + (i - a) - 1);
        root.right = buildTree(inorder, i + 1, b, postorder, c + (i - a), d - 1);
        return root;
    }
}
