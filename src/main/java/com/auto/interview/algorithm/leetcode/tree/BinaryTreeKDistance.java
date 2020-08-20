package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : jihai
 * @date : 2020/8/20
 * @description :
 *
 * 863. 二叉树中所有距离为 K 的结点
 * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
 *
 * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * 输出：[7,4,1]
 * 解释：
 * 所求结点为与目标结点（值为 5）距离为 2 的结点，
 * 值分别为 7，4，以及 1
 *
 *
 *
 * 注意，输入的 "root" 和 "target" 实际上是树上的结点。
 * 上面的输入仅仅是对这些对象进行了序列化描述。
 *
 *
 * 提示：
 *
 * 给定的树是非空的。
 * 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
 * 目标结点 target 是树上的结点。
 * 0 <= K <= 1000.
 */
public class BinaryTreeKDistance {

    /**
     * 维护一个父节点的引用
     *
     *         1
     *        2 3
     *       4 5 6 7
     */
    public static void main(String[] args) {
        TreeNode treeNode = NodeUtils.buildTreeNode(1, 2, 3, 4, 5, 6, 7);
        BinaryTreeKDistance binaryTreeKDistance = new BinaryTreeKDistance();
//        System.out.println(binaryTreeKDistance.distanceK(treeNode, treeNode.left, 1));
//        System.out.println(binaryTreeKDistance.distanceK(treeNode, treeNode.left, 2));


        /**
         * [0,1,null,3,2]
         * 2
         * 1
         *
         *    0
         *   1 null
         *  3 2
         */
        TreeNode root = NodeUtils.buildTreeNode(0, 1, null, 3, 2, null, null);
        System.out.println(binaryTreeKDistance.distanceK(root, root.left.right, 1));

    }

    /**
     * 维护节点对父亲节点的引用
     */
    Map<TreeNode, TreeNode> parentNodeMap = new HashMap<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        if (root == null) return new ArrayList<>();
        // 深度搜索构建父节点关系
        dfs(root, null);
        // 获取距离为k的节点
        return getKDistance(target, K);
    }

    private void dfs(TreeNode root, TreeNode parent) {
        if (root != null) {
            parentNodeMap.put(root, parent);
            dfs(root.left, root);
            dfs(root.right, root);
        }
    }

    private List<Integer> getKDistance(TreeNode target, int k) {
        List<Integer> list = new ArrayList<>();
        // 此处递归去寻找距离为k的 父节点，左节点，右节点
        getNode(target, k, list, new TreeNode(0));
        return list;
    }

    // preNode表示前一个节点，避免走回头路
    private void getNode(TreeNode target, int k, List<Integer> list, TreeNode preNode) {
        if (k == 0) {
            list.add(target.val);
            return ;
        }
        TreeNode parent = parentNodeMap.get(target);
        if (parent != null && preNode != parent) {
            getNode(parent, k - 1, list, target);
        }
        if (target.left != null && preNode != target.left) {
            getNode(target.left, k - 1, list, target);
        }
        if (target.right != null && preNode != target.right) {
            getNode(target.right, k - 1, list, target);
        }
    }
}
