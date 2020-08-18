package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;
import com.auto.interview.algorithm.leetcode.utils.AssertUtils;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

import java.util.*;

/**
 * @author : jihai
 * @date : 2020/8/18
 * @description :
 *
 * 1305. 两棵二叉搜索树中的所有元素
 * 给你 root1 和 root2 这两棵二叉搜索树。
 *
 * 请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root1 = [2,1,4], root2 = [1,0,3]
 * 输出：[0,1,1,2,3,4]
 * 示例 2：
 *
 * 输入：root1 = [0,-10,10], root2 = [5,1,7,0,2]
 * 输出：[-10,0,0,1,2,5,7,10]
 * 示例 3：
 *
 * 输入：root1 = [], root2 = [5,1,7,0,2]
 * 输出：[0,1,2,5,7]
 * 示例 4：
 *
 * 输入：root1 = [0,-10,10], root2 = []
 * 输出：[-10,0,10]
 * 示例 5：
 *
 *
 *
 * 输入：root1 = [1,null,8], root2 = [8,1]
 * 输出：[1,1,8,8]
 *
 *
 * 提示：
 *
 * 每棵树最多有 5000 个节点。
 * 每个节点的值在 [-10^5, 10^5] 之间。
 */
public class AllElementsInBinaryTree {


    public static void main(String[] args) {
        AllElementsInBinaryTree allElementsInBinaryTree = new AllElementsInBinaryTree();
        AssertUtils.assertList(allElementsInBinaryTree.getAllElements(NodeUtils.buildTreeNode(1, 2, 3, null, null, null, null),
                NodeUtils.buildTreeNode(1, 2, 3, 4,null ,null, null)), 1,1,2,2,3,3,4);

        AssertUtils.assertList(allElementsInBinaryTree.getAllElements2(NodeUtils.buildTreeNode(2, 1, 3, null, null, null, null),
                NodeUtils.buildTreeNode(4, 2, 5, 1,null ,null, null)), 1,1,2,2,3,4,5);
    }

    /**
     * 方法1：简单粗暴的，递归
     * 遍历合并再排序
     * 时间复杂度 > m + n 依赖排序算法
     * 空间复杂度 m + n
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        // 递归
        List<Integer> list = new ArrayList<>();
        searchTree(root1, list);
        searchTree(root2, list);
        Collections.sort(list);
        return list;
    }

    private void searchTree(TreeNode root1, List<Integer> list) {
        if (root1 == null) {
            return ;
        }
        list.add(root1.val);
        if (root1.left != null) searchTree(root1.left, list);
        if (root1.right != null) searchTree(root1.right, list);
    }


    /**
     * 方法2：
     * 定义两个指针，分别指向两个树
     * 因为是二叉搜索树，根据性质，中序遍历即是排序
     * 时间复杂度 m + n
     * 空间复杂度 m + n
     */
    public List<Integer> getAllElements2(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        dfs(root1, list1);
        dfs(root2, list2);
        // 归并排序
        int first = 0, second = 0;
        List<Integer> mergeList = new ArrayList<>();
        while (first < list1.size() && second < list2.size()) {
            if (list1.get(first) <= list2.get(second)) {
                mergeList.add(list1.get(first));
                first ++;
            } else {
                mergeList.add(list2.get(second));
                second ++;
            }
        }
        while (first < list1.size()) {
            mergeList.add(list1.get(first));
            first ++;
        }
        while (second < list2.size()) {
            mergeList.add(list2.get(second));
            second ++;
        }
        return mergeList;
    }

    private void dfs(TreeNode root, List<Integer> list) {
        if (root == null) return ;
        if (root.left != null) {
            dfs(root.left, list);
        }
        list.add(root.val);
        if (root.right != null) {
            dfs(root.right, list);
        }
    }

}
