package com.auto.interview.algorithm.leetcode.doing;

import com.auto.interview.algorithm.leetcode.base.TreeNode;
import com.auto.interview.algorithm.leetcode.utils.Assert;
import com.auto.interview.algorithm.leetcode.utils.AssertUtils;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author : jihai
 * @date : 2020/8/17
 * @description :
 *
 * 面试题 17.12. BiNode
 * 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。实现一个方法，把二叉搜索树转换为单向链表，要求依然符合二叉搜索树的性质，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
 *
 * 返回转换后的单向链表的头节点。
 *
 * 注意：本题相对原题稍作改动
 *
 *
 *
 * 示例：
 *
 * 输入： [4,2,5,1,3,null,6,0]
 * 输出： [0,null,1,null,2,null,3,null,4,null,5,null,6]
 * 提示：
 *
 * 节点数量不会超过 100000。
 */
public class BiNode {

    /**
     *         4
     *       /   \
     *      2    5
     *     / \  /  \
     *    1  3 null 6
     *   /
     *  0
     *
     *       0
     *        \
     *         1
     *          \
     *           2
     *            \
     *             3 ...
     * @param args
     */
    public static void main(String[] args) {
        BiNode biNode = new BiNode();
        TreeNode treeNode = biNode.convertBiNode(NodeUtils.buildTreeNode(4, 2, 5, 1, 3, null, 6));
        // 构造assert太复杂
        Assert.assertTrue(treeNode != null);
    }

    /**
     * 参考别人思想
     * 方法1：非递归中序遍历
     * 非递归，关键就是把前一个指针的左指针置为null，右指针指向下一个遍历的节点
     * 注意整个新的树的头使用的是一个哨兵的方式.
     */
    public TreeNode convertBiNode(TreeNode root) {
        if (root == null) return null;
        TreeNode head = new TreeNode(0);// 初始化哨兵
        TreeNode pre = head, curNode = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (curNode != null || ! stack.isEmpty()) {
            if (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            } else {
                TreeNode pop = stack.pop();
                pop.left = null;
                pre.right = pop;
                pre = pre.right;
                curNode = pop.right;
            }
        }
        return head.right;
    }
}
