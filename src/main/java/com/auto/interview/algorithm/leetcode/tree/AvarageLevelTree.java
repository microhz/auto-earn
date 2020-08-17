package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;
import com.auto.interview.algorithm.leetcode.utils.AssertUtils;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author : jihai
 * @date : 2020/8/17
 * @description :
 *
 * 637. 二叉树的层平均值
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
 *
 *
 *
 * 示例 1：
 *
 * 输入：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 输出：[3, 14.5, 11]
 * 解释：
 * 第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
 *
 *
 * 提示：
 *
 * 节点值的范围在32位有符号整数范围内。
 */
public class AvarageLevelTree {

    public static void main(String[] args) {
        AvarageLevelTree avarageLevelTree = new AvarageLevelTree();
        AssertUtils.assertDoubleList(avarageLevelTree.averageOfLevels(NodeUtils.buildTreeNode(3, 9, 20, null, null, 15, 7))
        , 3, 14.5, 11);
        AssertUtils.assertDoubleList(avarageLevelTree.averageOfLevels2(NodeUtils.buildTreeNode(3, 9, 20, null, null, 15, 7))
                , 3, 14.5, 11);
        AssertUtils.assertDoubleList(avarageLevelTree.averageOfLevels3(NodeUtils.buildTreeNode(3, 9, 20, null, null, 15, 7))
                , 3, 14.5, 11);
    }

    /**
     * 方法1
     * 广度遍历
     * 用一个数组维护当前层的节点不断的遍历
     * 每以层遍历结束计算平均数放到结果数组
     */
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Double> list = new ArrayList<>();
        List<TreeNode> layTreeList = new ArrayList<>();
        layTreeList.add(root);
        while (! layTreeList.isEmpty()) {
            List<Double> tempList = new ArrayList<>();
            List<TreeNode> tempNodeList = new ArrayList<>();
            for (TreeNode treeNode : layTreeList) {
                if (treeNode.left != null) tempNodeList.add(treeNode.left);
                if (treeNode.right != null) tempNodeList.add(treeNode.right);
                tempList.add(Double.valueOf(treeNode.val));
            }
            layTreeList.clear();
            list.add(getAvg(tempList));
            layTreeList.addAll(tempNodeList);
        }
        return list;
    }

    private Double getAvg(List<Double> tempList) {
        double sum = 0;
        for (Double d : tempList) {
            sum += d;
        }
        return sum / tempList.size();
    }


    /**
     * 方法2：
     * 队列实现, 在poll的时候自动移除了元素，
     * 最开始队列的长度刚好就是当前层的数量，
     * 因此代码可以更加简洁
     */
    public List<Double> averageOfLevels2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        List<Double> list = new ArrayList<>();
        queue.offer(root);
        while (! queue.isEmpty()) {
            double sum = 0;
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) queue.offer(poll.left);
                if (poll.right != null) queue.offer(poll.right);
                sum += poll.val;
            }
            list.add(sum / len);
        }
        return list;
    }


    /**
     * 方法3：树的递归
     * 递归入参数：当前层的和，当前层的数量
     * 时间复杂度 n
     * 空间复杂度 n + h
     */
    public List<Double> averageOfLevels3(TreeNode root) {
        // n 层的数
        List<Double> sum = new ArrayList<>();
        // n 层的节点数
        List<Integer> count = new ArrayList<>();
        avgTree(root, sum, count, 0);
        // 统计
        return avgLayer(sum, count);
    }

    private void avgTree(TreeNode root, List<Double> sum, List<Integer> count, int high) {
        if (root == null) return ;
        if (high > sum.size() - 1) {
            sum.add(high, Double.valueOf(root.val));
            count.add(high, 1);
        } else {
            sum.set(high, sum.get(high) + Double.valueOf(root.val));
            count.set(high, count.get(high) + 1);
        }
        avgTree(root.left, sum, count, high + 1);
        avgTree(root.right, sum, count, high + 1);
    }

    private List<Double> avgLayer(List<Double> sum, List<Integer> count) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < sum.size(); i++) {
            result.add(sum.get(i) / Double.valueOf(count.get(i)));
        }
        return result;
    }
}
