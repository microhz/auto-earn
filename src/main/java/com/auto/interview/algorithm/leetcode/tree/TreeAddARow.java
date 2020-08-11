package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/7/16
 * @description :
 * 623. 在二叉树中增加一行
 * 给定一个二叉树，根节点为第1层，深度为 1。在其第 d 层追加一行值为 v 的节点。
 *
 * 添加规则：给定一个深度值 d （正整数），针对深度为 d-1 层的每一非空节点 N，为 N 创建两个值为 v 的左子树和右子树。
 *
 * 将 N 原先的左子树，连接为新节点 v 的左子树；将 N 原先的右子树，连接为新节点 v 的右子树。
 *
 * 如果 d 的值为 1，深度 d - 1 不存在，则创建一个新的根节点 v，原先的整棵树将作为 v 的左子树。
 *
 * 示例 1:
 *
 * 输入:
 * 二叉树如下所示:
 *        4
 *      /   \
 *     2     6
 *    / \   /
 *   3   1 5
 *
 * v = 1
 *
 * d = 2
 *
 * 输出:
 *        4
 *       / \
 *      1   1
 *     /     \
 *    2       6
 *   / \     /
 *  3   1   5
 *
 * 示例 2:
 *
 * 输入:
 * 二叉树如下所示:
 *       4
 *      /
 *     2
 *    / \
 *   3   1
 *
 * v = 1
 *
 * d = 3
 *
 * 输出:
 *       4
 *      /
 *     2
 *    / \
 *   1   1
 *  /     \
 * 3       1
 * 注意:
 *
 * 输入的深度值 d 的范围是：[1，二叉树最大深度 + 1]。
 * 输入的二叉树至少有一个节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-one-row-to-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TreeAddARow {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
//        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);

//        treeNode.left.left = new TreeNode(4);
//        treeNode.left.right = new TreeNode(5);

        treeNode.right.left = new TreeNode(6);
        treeNode.right.right = new TreeNode(7);

        TreeAddARow treeAddARow = new TreeAddARow();
        List<TreeNode> treeNodes = treeAddARow.searchTargetNode(treeNode, 3);
        treeNodes.forEach(e -> {
            System.out.println(e.val);
        });
    }

    /**
      1. consider the target node left tree is null
      2. d is between [1, max length + 1]
     */
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode vNode = new TreeNode(v);
            vNode.left = root;
            return vNode;
        }
        List<TreeNode> targetNode = searchTargetNode(root, d);
        for (TreeNode treeNode : targetNode) {
            convertTreeNode(treeNode, v);
        }
        return root;
    }

    /**
     * key steps :
     * search the target node from tree
     * @param root
     * @param d
     * @return
     */
    private List<TreeNode> searchTargetNode(TreeNode root, int d) {
        List<TreeNode> targetNodeList = new ArrayList<>();
        searchNode(targetNodeList, root, 1, d);
        return targetNodeList;
    }

    private void searchNode(List<TreeNode> targetNodeList, TreeNode root, int concurent, int d) {
        if (concurent == (d - 1)) {
            targetNodeList.add(root);
            return ;
        }
        /*
        concursive this child
         */
        if (root.left != null) {
            searchNode(targetNodeList, root.left, concurent + 1, d);
        }
        if (root.right != null) {
            searchNode(targetNodeList, root.right, concurent + 1, d);
        }
    }

    /**
     * child add the v node
     * add left and right node
     * @param treeNode
     * @param v
     */
    private void convertTreeNode(TreeNode treeNode, int v) {
        TreeNode oldLeft = treeNode.left;
        TreeNode oldRight = treeNode.right;

        TreeNode vLeft = new TreeNode(v);
        vLeft.left = oldLeft;
        treeNode.left = vLeft;

        TreeNode vRight = new TreeNode(v);
        vRight.right = oldRight;
        treeNode.right = vRight;

    }
}