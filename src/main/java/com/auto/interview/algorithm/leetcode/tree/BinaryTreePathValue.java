package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/8/25
 * @description :
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 *
 *
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 *
 * 提示：
 *
 * 节点总数 <= 10000
 */
public class BinaryTreePathValue {


    /**
     * 方法1：
     * 每一层new一个list，到叶子节点然后判断list的和是否等于sum
     * 定义递归，计算和
     */
    List<List<Integer>> list;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        list = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        if (root == null) return list;
        getPathSum(root, subList, sum);
        return list;
    }

    public void getPathSum(TreeNode root, List<Integer> pathList, int sum) {
        if (root == null) return ;
        pathList.add(root.val);
        if (root.left == null && root.right == null) {
            int s = 0;
            for (int i = 0;i < pathList.size();i ++) {
                s += pathList.get(i);
            }
            if (sum == s) list.add(pathList);
            return ;
        }
        getPathSum(root.left, new ArrayList<>(pathList), sum);
        getPathSum(root.right, new ArrayList<>(pathList), sum);
    }
}
