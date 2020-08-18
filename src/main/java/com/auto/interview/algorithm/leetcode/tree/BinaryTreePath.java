package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/8/17
 * @description :
 *
 * 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 */
public class BinaryTreePath {

    public static void main(String[] args) {
        BinaryTreePath binaryTreePath = new BinaryTreePath();
        System.out.println(binaryTreePath.binaryTreePaths(NodeUtils.buildTreeNode(1, 2, 3, null, 5, null, null)));
    }

    /**
     * 方法1：递归
     * 保存一个列表，如果有左右子树，就分裂为两个列表
     */
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        recursion(root, list, result);
        return printResult(result);
    }

    private List<String> printResult(List<List<Integer>> result) {
        List<String> list = new ArrayList<>();
        for (List<Integer> path : result) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < path.size(); i++) {
                sb.append(path.get(i));
                 if (i < path.size() - 1) {
                     sb.append("->");
                 }
            }
            list.add(sb.toString());
        }
        return list;
    }

    private void recursion(TreeNode root, List<Integer> list, List<List<Integer>> result) {
        if (list.size() == 0) {
            list.add(root.val);
        }
        if (root.left == null && root.right == null) {
            result.add(list);
            return ;
        }
        if (root.left != null) {
            ArrayList<Integer> leftList = new ArrayList<>(list);
            leftList.add(root.left.val);
            recursion(root.left, leftList, result);
        }
        if (root.right != null) {
            ArrayList<Integer> rightList = new ArrayList<>(list);
            rightList.add(root.right.val);
            recursion(root.right, rightList, result);
        }
    }
}
