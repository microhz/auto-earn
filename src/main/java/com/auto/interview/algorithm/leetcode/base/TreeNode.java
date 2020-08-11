package com.auto.interview.algorithm.leetcode.base;

/**
 * @author : jihai
 * @date : 2020/8/11
 * @description : 二叉树定义
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
