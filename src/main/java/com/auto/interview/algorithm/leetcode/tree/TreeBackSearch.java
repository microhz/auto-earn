package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.utils.Assert;

/**
 * @author : jihai
 * @date : 2020/8/16
 *
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 *
 *
 *
 * 参考以下这颗二叉搜索树：
 *
 *      5
 *     / \
 *    2   6
 *   / \
 *  1   3
 * 示例 1：
 *
 * 输入: [1,6,3,2,5]
 * 输出: false
 * 示例 2：
 *
 * 输入: [1,3,2,6,5]
 * 输出: true
 */
public class TreeBackSearch {

    public static void main(String[] args) {
        TreeBackSearch treeBackSearch = new TreeBackSearch();
        Assert.assertTrue(treeBackSearch.verifyPostorder(new int[]{1,3,2,6,5}));
    }

    /**
     *
     * [1,6,3,2,5]
     *
     * 递归算法
     * 如果找到左子树和右子树
     * 分别判断是二叉搜索树即可
     *
     * 递归出口：
     * 如果是一个节点，即可返回肯定平衡
     *
     * 找到左子树，第一个小于根的值
     *
     * new int[]{1,3,2,6,5}
     */
    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length == 0) return true;
        return recursiv(postorder, 0, postorder.length - 1);
    }

    private boolean recursiv(int[] postorder, int start, int end) {
        if (start >= end) return true;
        int p = end;
        while (p > start && postorder[p - 1] > postorder[end]) p --;
        int mid = p;
        // 还要判断的左边是否都小于根
        while (p > start && postorder[p - 1] < postorder[end]) p --;
        return p == start && recursiv(postorder, start, mid - 1) && recursiv(postorder, mid, end - 1);
    }
}
