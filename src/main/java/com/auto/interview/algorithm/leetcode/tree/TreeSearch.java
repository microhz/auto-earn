package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;
import com.auto.interview.algorithm.leetcode.utils.AssertUtils;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author : jihai
 * @date : 2020/8/16
 *
 * 144. 二叉树的前序遍历
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 *  示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,2,3]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * [3,1,null,null,2]
 *  *     3
 *  *    /  \
 *  *   1   null
 *  *  /  \
 *  *null 2
 */
public class TreeSearch {

    public static void main(String[] args) {
        TreeNode treeNode = NodeUtils.buildTreeNode(1, null, 2, null, null, 3, null);
        TreeSearch treeSearch = new TreeSearch();

        AssertUtils.assertList(treeSearch.preorderTraversal(treeNode), 1, 2, 3);
        AssertUtils.assertList(treeSearch.preorderTraversal(NodeUtils.buildTreeNode(3, 1, null, null, 2, null, null)), 3, 1, 2);
        AssertUtils.assertList(treeSearch.preorderTraversal(NodeUtils.buildTreeNode(1, 2, null, null, null, null, null)), 1, 2);
        AssertUtils.assertList(treeSearch.preorderTraversal(NodeUtils.buildTreeNode(1, null, 2, null, null, null, null)), 1, 2);

        AssertUtils.assertList(treeSearch.preorderTraversal2(treeNode), 1, 2, 3);
        AssertUtils.assertList(treeSearch.preorderTraversal2(NodeUtils.buildTreeNode(3, 1, null, null, 2, null, null)), 3, 1, 2);
        AssertUtils.assertList(treeSearch.preorderTraversal2(NodeUtils.buildTreeNode(1, 2, null, null, null, null, null)), 1, 2);
        AssertUtils.assertList(treeSearch.preorderTraversal2(NodeUtils.buildTreeNode(1, null, 2, null, null, null, null)), 1, 2);


        AssertUtils.assertList(treeSearch.preorderTraversal3(treeNode), 1, 2, 3);
        AssertUtils.assertList(treeSearch.preorderTraversal3(NodeUtils.buildTreeNode(3, 1, null, null, 2, null, null)), 3, 1, 2);
        AssertUtils.assertList(treeSearch.preorderTraversal3(NodeUtils.buildTreeNode(1, 2, null, null, null, null, null)), 1, 2);
        AssertUtils.assertList(treeSearch.preorderTraversal3(NodeUtils.buildTreeNode(1, null, 2, null, null, null, null)), 1, 2);

    }


    /**
     * 方法1
     * 递归算法
     * 时间复杂度:n
     * 空间复杂度:n
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        recur(list, root);
        return list;
    }

    private void recur(List<Integer> list, TreeNode root) {
        if (root == null) return ;
        list.add(root.val);
        if (root.left != null) recur(list, root.left);
        if (root.right != null) recur(list, root.right);
    }

    /**
     * 方法2：
     * 为了回溯，利用栈
     *
     * 思路非常重要:
     * 一路找左子树，遍历完了移动到右子树，关键右子树可能为空，这个时候要用栈循环取到一个非空的右子树
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null) {
            list.add(p.val);
            stack.push(p);
            while (p.left != null) {
                p = p.left;
                list.add(p.val);
                stack.push(p);
            }
            if (!stack.isEmpty()) {
                // 找到第一个右子树为空的
                while (! stack.isEmpty()) {
                    TreeNode pop = stack.pop();
                    if (pop.right != null) {
                        p = pop.right;
                        break;
                    }
                    p = null;
                }
            }
        }
        return list;
    }

    /**
     * 方法3
     *
     * 利用栈，分别压右，左
     * 循环取stack
     */
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (! stack.isEmpty()) {
            TreeNode pop = stack.pop();
            list.add(pop.val);
            if (pop.right != null) stack.push(pop.right);
            if (pop.left != null) stack.push(pop.left);
        }
        return list;
    }
}
