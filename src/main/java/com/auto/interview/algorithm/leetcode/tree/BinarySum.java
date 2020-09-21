package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/9/21
 * @description :
 *
 *
 */
public class BinarySum {

    public static void main(String[] args) {
        BinarySum binarySum = new BinarySum();
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(13);

        binarySum.convertBST(treeNode);
    }

    /**
     * 多思考一下!!
     *
     * 这个算法辣鸡n3
     */
    List<Integer> list;
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        list = new ArrayList<>();
        dfs(root);
        updateValue(root);
        return root;
    }

    public void dfs(TreeNode root) {
        if (root.left != null) dfs(root.left);
        list.add(root.val);
        if (root.right != null) dfs(root.right);
    }

    public void updateValue(TreeNode root) {
        int sum = 0;
        for (int i : list) {
            if (i >= root.val) sum += i;
        }
        root.val = sum;
        if (root.left != null) updateValue(root.left);
        if (root.right != null) updateValue(root.right);
    }


    /**
     * 方法2：n2
     */
    int sum = 0;
    public TreeNode convertBST2(TreeNode root) {
        if (root == null) return null;
        // list = new ArrayList<>();
        sumAll(root);
        updateValue2(root);
        return root;
    }

    public void sumAll(TreeNode root) {
        sum += root.val;
        if (root.left != null) sumAll(root.left);
        if (root.right != null) sumAll(root.right);
    }

    public void updateValue2(TreeNode root) {
        if (root.left != null) updateValue(root.left);
        sum -= root.val;
        root.val = root.val + sum;
        if (root.right != null) updateValue(root.right);
    }


    /**
     * 方法3：中序遍历反过来即可
     */
    int sum2 = 0;
    public TreeNode convertBST3(TreeNode root) {
        if (root == null) return null;
        updateValue3(root);
        return root;
    }

    public void updateValue3(TreeNode root) {
        // int sum = 0;
        if (root.right != null) updateValue(root.right);
        sum2 += root.val;
        root.val = sum2;
        if (root.left != null) updateValue(root.left);
    }
}
