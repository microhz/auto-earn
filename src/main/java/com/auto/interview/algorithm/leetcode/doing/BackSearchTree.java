package com.auto.interview.algorithm.leetcode.doing;

import com.auto.interview.algorithm.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author : jihai
 * @date : 2020/8/16
 * @description :
 *
 * 145. 二叉树的后序遍历
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 *              1
 *            /  \
 *           2    3
 *          / \  / \
 *         4  5 6  7
 */
public class BackSearchTree {

    public static void main(String[] args) {

    }

    /**
     * 方法1 ：递归算法
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        recursion(list, root);
        return list;
    }

    /**
     * 左右根
     */
    private void recursion(List<Integer> list, TreeNode root) {
        if (root.left != null) {
            recursion(list, root.left);
        }
        if (root.right != null) {
            recursion(list, root.right);
        }
        list.add(root.val);
    }

    /**
     * 方法2:迭代算法
     * 左右根
     *
     * 利用栈存放根，当右子树为null的时候，出栈
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null) {
            while (p.left != null) {
                stack.push(p);
                p = p.left;
            }
            // 到达左子树底部
            list.add(p.val);
            while (! stack.isEmpty()) {
                TreeNode pop = stack.pop();
                list.add(pop.val);
                if (pop.right == null) {
                    p = p.right;
                    break;
                } else {
                    p = null;
                }
            }
        }
        return list;
    }
}
