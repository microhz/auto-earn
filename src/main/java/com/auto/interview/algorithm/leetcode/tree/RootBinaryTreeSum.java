package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;

/**
 * @author : jihai
 * @date : 2020/8/26
 * @description :
 *
 * 1022. 从根到叶的二进制数之和
 * 给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
 *
 * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
 *
 * 以 10^9 + 7 为模，返回这些数字之和。
 *
 *
 *
 * 示例：
 *
 *
 *
 * 输入：[1,0,1,0,1,0,1]
 * 输出：22
 * 解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 *
 *
 * 提示：
 *
 * 树中的结点数介于 1 和 1000 之间。
 * node.val 为 0 或 1 。
 */
public class RootBinaryTreeSum {


    /**
     * 方法1：dfs递归写法
     * 利用了String是不变对象的特性
     * 注意三个点：边界、递归入参、出口
     */
    int sum = 0;
    public int sumRootToLeaf(TreeNode root) {
        if (root == null) return 0;
        sumRootToLeaf(root, "");
        return sum;
    }

    public void sumRootToLeaf(TreeNode root, String str) {
        str += root.val;
        if (root.left == null && root.right == null) {
            sum += Integer.valueOf(str, 2);
        }
        if (root.left != null) sumRootToLeaf(root.left, str);
        if (root.right != null) sumRootToLeaf(root.right, str);
    }


    /**
     * 方法2：二进制的本质进行累加
     */
    int sum2=0;
    public int sumRootToLeaf2(TreeNode root) {
        if(root==null) return -1;
        sumRoot(root,0);
        return sum;
    }

    public void sumRoot(TreeNode root, int num) {
        if (root == null) return ;
        num += root.val;
        if (root.left == null && root.right == null) {
            sum += num;
        }
        if (root.left != null) sumRoot(root.left, num * 2);
        if (root.right != null) sumRoot(root.right, num * 2);
    }
}
