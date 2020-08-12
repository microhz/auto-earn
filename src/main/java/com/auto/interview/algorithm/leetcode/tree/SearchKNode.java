package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;
import com.auto.interview.algorithm.leetcode.utils.Assert;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/8/12
 * @description :
 *
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 *
 *
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 4
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 */
public class SearchKNode {

    public static void main(String[] args) {
        SearchKNode searchKNode = new SearchKNode();
        Assert.assertTrue(searchKNode.kthLargest(NodeUtils.buildTreeNode(5, 3, 6, 2, 4, null, null), 3) == 4);

        Assert.assertTrue(searchKNode.kthLargest(NodeUtils.buildTreeNode(1, null, 2, null, null, null, null), 2) == 1);

    }

    /**
     * 中序遍历拿到排序
     * 拿到第k个索引
     */
    public int kthLargest(TreeNode root, int k) {
        if (root == null) return -1;
        List<TreeNode> list = new ArrayList<>();
        searchK(root, list);
        return getMaxN(list, k);
    }

    /**
     * 得到数组中第k大的数字
     * @param list
     * @param k
     * @return
     */
    private int getMaxN(List<TreeNode> list, int k) {
        int index = 1;
        int max = list.get(list.size() - 1).val;
        if (k == 1) return max;
        for (int i = list.size() - 1; i >= 0; i --) {
            if (max > list.get(i).val) {
                max = list.get(i).val;
                index ++;
            }
            if (index == k) {
                return max;
            }
        }
        return -1;
    }

    /**
     * 中序遍历二叉搜索树
     */
    private void searchK(TreeNode root, List<TreeNode> list) {
        if (root.left != null) {
            searchK(root.left, list);
        }
        list.add(root);
        if (root.right != null) {
            searchK(root.right, list);
        }
    }
}
