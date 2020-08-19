package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;
import com.auto.interview.algorithm.leetcode.utils.Assert;
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
//        Assert.assertTrue(binaryTreeMaxValue.maxPathSum(NodeUtils.buildTreeNode(-2, -3, -1, null, null, null, null)) == -1);
//        Assert.assertTrue(binaryTreeMaxValue.maxPathSum(NodeUtils.buildTreeNode(-10, 9, 20, null, null, 15, 7)) == 42);
//        Assert.assertTrue(binaryTreeMaxValue.maxPathSum(NodeUtils.buildTreeNode(1, null, null, null, null, null, null)) == 1);
//        Assert.assertTrue(binaryTreeMaxValue.maxPathSum(NodeUtils.buildTreeNode(2, -1, null, null, null, null, null)) == 2);
//        Assert.assertTrue(binaryTreeMaxValue.maxPathSum(NodeUtils.buildTreeNode(2, -1, null, null, null, null, null)) == 2);

        //[1,-2,-3,1,3,-2,null,-1]
        /**
         *    1
         *   -2 -3
         *  1 3 -2 null
         *-1
         */
        Assert.assertTrue(binaryTreeMaxValue.maxPathSum(NodeUtils.buildTreeNode(1,-2,-3,1,3,-2,null)) == 3);

        // [1,2,null,3,null,4,null,5]
        /**
         *               1
         *             2
         *          3  null
         *        4
         *      5
         */
        Assert.assertTrue(binaryTreeMaxValue.maxPathSum(NodeUtils.buildTreeNode(1,2,3,null,4,5,null)) == 15);

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
        boolean isLeftExits = false, isRightExits = false;
        if (p.left != null) {
            isLeftExits = true;
            searchTree(p.left, list);
        }
//        if (isLeftExits) list.add(Integer.MIN_VALUE);
        list.add(p.val);
        if (p.right != null) {
            isRightExits = true;
            searchTree(p.right, list);
        }
        if (isLeftExits) list.add(Integer.MIN_VALUE);
    }

    /**
     *
     * dp 算法
     * dp[i] 为前i最大
     * dp[i] = max(dp[i - 1] + list.get(i),dp[i - 1])
     * 初始值为dp[0] = list.get(0), dp[1] = max(list.get(0), list.get(1), list.get(1) + list.get(0))
     * 时间复杂度 n
     * 空间复杂度 1
     */
    // -2, -3, -1, 1, -2, 3, -1
    private int getMaxSub(List<Integer> list) {
        int[] dp = new int[list.size()];
        dp[0] = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) == Integer.MIN_VALUE) {
                dp[i] = Integer.MIN_VALUE;
                continue;
            }
            dp[i] = Math.max(dp[i - 1] + list.get(i), list.get(i));
        }
        Integer max = dp[0];
        for (int i : dp) {
            max = i > max ? i : max;
        }
        return max;
    }
}
