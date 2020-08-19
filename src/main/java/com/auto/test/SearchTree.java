package com.auto.test;

import com.auto.interview.algorithm.leetcode.base.TreeNode;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author : jihai
 * @date : 2020/8/13
 * @description :
 *
 * 中序遍历二叉树，要求使用非递归方式
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 */
public class SearchTree {

    public static void main(String[] args) {
        TreeNode treeNode = NodeUtils.buildTreeNode(1, null, 2, null, null, 3, null);
        SearchTree searchTree = new SearchTree();
        List<Integer> list = searchTree.searchTree(treeNode);
        System.out.println(list);
    }

    /**
     *
     * 左根右
     * 非递归，那只能用迭代的方式
     * 利用栈，后进先出遍历
     *
     *     1
     *   2  3
     *  4 5 6 7

     */
    public List<Integer> searchTree(TreeNode root) {
        TreeNode p = root;
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
                continue;
            }
            TreeNode pop = stack.pop();
            list.add(pop.val);
            p = pop.right;
        }
        return list;
    }
}
