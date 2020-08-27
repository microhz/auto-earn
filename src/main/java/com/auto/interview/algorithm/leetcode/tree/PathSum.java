package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;

/**
 * @author : jihai
 * @date : 2020/8/27
 * @description :
 *
 * 437. 路径总和 III
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 *
 * 找出路径和等于给定数值的路径总数。
 *
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 *
 * 示例：
 *
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * 返回 3。和等于 8 的路径有:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 */
public class PathSum {


    /**
     * dfs每个节点，每个节点的计算值又需要dfs计算
     * 因此双层递归即可解决
     */
    int allPath = 0;
    // 边界处理、每个节点DFS累加
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        dfs(root, sum);
        return allPath;
    }

    public void dfs(TreeNode root, int sum) {
        if (root == null) return ;
        allPath += dfsSum(root, sum, 0);
        if (root.left != null) dfs(root.left, sum);;
        if (root.right != null) dfs(root.right, sum);
    }

    public int dfsSum(TreeNode root, int sum, int count) {
        if (root == null) return 0;
        int ret = 0;
        if (root.val + count == sum) ret += 1;
        if (root.left != null) ret += dfsSum(root.left, sum, root.val + count);
        if (root.right != null) ret += dfsSum(root.right, sum, root.val + count);
        return ret;
    }
}
