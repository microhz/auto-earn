package com.auto.interview.algorithm.leetcode.doing;

import com.auto.interview.algorithm.leetcode.utils.Assert;

/**
 * @author : jihai
 * @date : 2020/8/14
 * @description :
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
 *       [1, 2, 6, 5]
 *       [3, 2, 5, 6]
 * 输出: true
 *
 * 中等难度
 *
 * // TODO
 */
public class VerifyPosterOrder {

    public static void main(String[] args) {
        VerifyPosterOrder verifyPosterOrder = new VerifyPosterOrder();
        Assert.assertTrue(verifyPosterOrder.verifyPostorder(new int[]{1, 2, 6, 5}));
        Assert.assertTrue(verifyPosterOrder.verifyPostorder(new int[]{1, 3, 2, 6, 5}));
        Assert.assertTrue(verifyPosterOrder.verifyPostorder(new int[]{3, 2, 5, 6}));
        // [1,2,5,10,6,9,4,3]
        /**
         *       10
         *      5
         *     2
         *    1
         */
        Assert.assertTrue(! verifyPosterOrder.verifyPostorder(new int[]{1,2,5,10,6,9,4,3}));

    }


    /**
     * 后序遍历：左右根
     * 又因为是搜索二叉树
     * 数组遍历，找到左右根，然后递归处理
     * 如果比后面两个都大，那只能是最大根了
     */
    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length == 0) return false;
        return recurs(postorder, postorder.length);
    }

    /**
     * 递归出口 都是一个节点
     * 划分左子树和右子树
     * 递归
     */
    private boolean recurs(int[] postorder, int i) {
//        while ()
        return false;
    }

  /*  private boolean verify(int[] postorder, int start, int length, int max) {
        if (start + 1 >= length) {
            return true;
        }
        // 三种情况，左右子树都有，只有左子树，只有右子树
        boolean ok1 = false, ok2 = false, ok3 = false;
        if (length - start > 1) {
            if (postorder[start + 1] > postorder[start]) {
                // 左子树
                ok1 = verify(postorder, start + 1, length, postorder[start + 1]);
            } else if (postorder[start + 1] < postorder[start]) {
                // 右子树
                ok2 = verify(postorder, start + 1, length, postorder[start + 1]);
            }
        }
        if (length - length > 2) {
            // 可以构造两个子树
            ok3 = verify(postorder, start + 2, length, postorder[start + 2]);
        }
        return ok1 || ok2 || ok3;
    }*/
}
