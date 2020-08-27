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

    public TreeNode build(int x) {
        return new TreeNode(x);
    }

    public  TreeNode left(int x) {
        this.left = new TreeNode(x);
        return this.left;
    }

    public TreeNode right(int x) {
        this.right = new TreeNode(x);
        return this.right;
    }


    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
