package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;
import com.auto.interview.algorithm.leetcode.utils.AssertUtils;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

import java.util.*;

/**
 * @author : jihai
 * @date : 2020/8/11
 * @description :
 *
 * 94. 二叉树的中序遍历
 * 给定一个二叉树，返回它的中序 遍历。
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
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 *      1
 *     2 3
 *   4 5 6 7
 */
public class BinaryTreeInorderTraversal {

    public static void main(String[] args) {
        TreeNode treeNode = NodeUtils.buildTreeNode(1, 2, 3, 4, 5, 6, 7);
        BinaryTreeInorderTraversal binaryTreeInorderTraversal = new BinaryTreeInorderTraversal();

        AssertUtils.assertList(binaryTreeInorderTraversal.inorderTraversal(treeNode), 4, 2, 5, 1, 6, 3, 7);
        AssertUtils.assertList(binaryTreeInorderTraversal.inorderTraversal2(treeNode), 4, 2, 5, 1, 6, 3, 7);
        AssertUtils.assertList(binaryTreeInorderTraversal.midOrderTree(treeNode), 4, 2, 5, 1, 6, 3, 7);
        AssertUtils.assertList(binaryTreeInorderTraversal.midOrderTree(treeNode), 4, 2, 5, 1, 6, 3, 7);


        TreeNode treeNode2 = NodeUtils.buildTreeNode(1, null, 2, null, null, 3, null);
        AssertUtils.assertList(binaryTreeInorderTraversal.inorderTraversal(treeNode2), 1, 3, 2);
        AssertUtils.assertList(binaryTreeInorderTraversal.midOrderTree(treeNode2), 1, 3, 2);


        /**
         *    2
         *   3
         *  1
         */
        // 2, 3, null, 1
        // 1, 3, 2
        TreeNode treeNode3 = NodeUtils.buildTreeNode(2, 3, null, 1, null, null, null);
        AssertUtils.assertList(binaryTreeInorderTraversal.inorderTraversal(treeNode3), 1, 3, 2);
    }

    /**
     * 非递归解法
     * 最优雅写法
     * 时间复杂度: n
     * 空间复杂度: 1
     */
    public List<Integer> midOrderTree(TreeNode root) {
        if (root == null) return new ArrayList<>();
        TreeNode curNode = root;
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        while (curNode != null || ! stack.isEmpty()) {
            if (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            } else {
                TreeNode pop = stack.pop();
                list.add(pop.val);
                curNode = pop.right;
            }
        }
        return list;
    }





    /**
     * 中序遍历
     *
     * 根节点开始遍历
     * 寻找左节点，如果存在就把当前节点放在栈上，
     * 一直找到最左放进list然后拿出栈顶元素放进去，
     * 指针指向栈顶元素的右子树，继续上面操作
     */


    /**
     * 迭代法
     *
     * 栈
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        while (curNode != null || !stack.isEmpty()) {
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }
            TreeNode pop = stack.pop();
            list.add(pop.val);
            curNode = pop.right;
        }
        return list;

    }

    /**
     * 方法：递归
     * 时间复杂度 n2
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        searchTree(list, root);
        return list;
    }

    private void searchTree(List<Integer> list, TreeNode root) {
        if (root.left == null && root.right == null) {
            list.add(root.val);
            return ;
        }
        if (root.left != null) {
            searchTree(list, root.left);
        }
        list.add(root.val);
        if (root.right != null) {
            searchTree(list, root.right);
        }
    }
}
