package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;
import com.auto.interview.algorithm.leetcode.utils.Assert;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author : jihai
 * @date : 2020/8/21
 * @description :
 *
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2.
 */
public class MiniDepth {

    public static void main(String[] args) {
        TreeNode treeNode = NodeUtils.buildTreeNode(3, 9, 20, null, null, 15, 7);
        MiniDepth miniDepth = new MiniDepth();
        Assert.assertTrue(miniDepth.minDepth(treeNode) == 2);
        Assert.assertTrue(miniDepth.minDepth2(treeNode) == 2);
        Assert.assertTrue(miniDepth.minDepth3(treeNode) == 2);
    }

    /**
     * 方法1：
     * 我知道大部分人都采用dfs之类的解法，
     * 我采用另一种解法，dfs找到所有子节点，然后再向上找父节点的个数
     * 其实这样效率并不高，空间复杂度过高
     * 维护一个父指针
     */
    private Map<TreeNode, TreeNode> parentMap;
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        parentMap = new HashMap<>();
        // dfs
        dfs(root, null);
        return getMiniDepth();
    }

    private void dfs(TreeNode root, TreeNode parent) {
        if (root != null) {
            parentMap.put(root, parent);
            dfs(root.left, root);
            dfs(root.right, root);
        }
    }

    private int getMiniDepth() {
        // 遍历
        int min = Integer.MAX_VALUE;
        for (Map.Entry<TreeNode, TreeNode> entry : parentMap.entrySet()) {
            // 判断是否叶子节点
            if (entry.getKey().left == null && entry.getKey().right == null) {
                int count = 1;
                TreeNode p = entry.getKey();
                while ((p = parentMap.get(p)) != null) {
                    count ++;
                }
                min = min < count ? min : count;
            }
        }
        return min;
    }

    /**
     * 方法2：
     * 深度搜索
     */
    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        Integer min = Integer.MAX_VALUE;
        if (root.left != null) {
            min = minDepth2(root.left) + 1;
        }
        if (root.right != null) {
            min = Math.min(min, minDepth2(root.right) + 1);
        }
        return min;
    }


    /**
     * 方法3：
     * 广度搜索
     */
    public int minDepth3(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 0;
        while (! queue.isEmpty()) {
            int len = queue.size();
            count ++;
            for (int i = 0;i < len;i ++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return count;
                }
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return -1;
    }


}
