package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;
import com.auto.interview.algorithm.leetcode.utils.Assert;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author : jihai
 * @date : 2020/8/17
 * @description :
 *
 * 107. 二叉树的层次遍历 II
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class BinaryTreeLevel {

    public static void main(String[] args) {
        BinaryTreeLevel binaryTreeLevel = new BinaryTreeLevel();
        // TODO assert
        System.out.println(binaryTreeLevel.levelOrderBottom(NodeUtils.buildTreeNode(3, 9, 20, null, null, 15, 7)));


    }

    /**
     * 方法1：迭代法
     * 利用队列，获取层放到结果里
     * 反转结果
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (! queue.isEmpty()) {
            List<Integer> levelList = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                levelList.add(poll.val);
                if (poll.left != null) queue.offer(poll.left);
                if (poll.right != null) queue.offer(poll.right);
            }
            list.add(levelList);
        }
        return reverse(list);
    }

    private List<List<Integer>> reverse(List<List<Integer>> list) {
        int left = 0, right = list.size() - 1;
        while (left < right) {
            List<Integer> temp = list.get(left);
            list.set(left, list.get(right));
            list.set(right, temp);
            left ++;right --;
        }
        return list;
    }
}
