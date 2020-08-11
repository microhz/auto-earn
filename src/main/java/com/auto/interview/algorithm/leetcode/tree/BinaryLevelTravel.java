package com.auto.interview.algorithm.leetcode.tree;

import com.auto.common.Learn;
import com.auto.common.Self;
import com.auto.interview.algorithm.leetcode.base.TreeNode;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

import java.util.*;

/**
 * @author : jihai
 * @date : 2020/8/11
 * @description :
 *
 * 102. 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class BinaryLevelTravel {

    public static void main(String[] args) {
        TreeNode treeNode = NodeUtils.buildTreeNode(1, 2, 3, 4, null, null, null);
        BinaryLevelTravel binaryLevelTravel = new BinaryLevelTravel();
        System.out.println(binaryLevelTravel.levelOrder3(treeNode));
    }


    /**
     * 遍历多层
     * 每层遍历
     */
    @Self
    public List<List<Integer>> levelOrder3(TreeNode treeNode) {
        List<List<Integer>> list = new ArrayList<>();
        List<TreeNode> levelList = new ArrayList<>();
        levelList.add(treeNode);
        List<TreeNode> tempList = levelList;
        while (!tempList.isEmpty()) {
            List<Integer> layList = new ArrayList<>();
            List<TreeNode> tempNodeList = new ArrayList<>();
            for (TreeNode node : tempList) {
                layList.add(node.val);
                if (node.left != null) {
                    tempNodeList.add(node.left);
                }
                if (node.right != null) {
                    tempNodeList.add(node.right);
                }
            }
            list.add(layList);
            tempList = tempNodeList;
        }
        return list;
    }


    /**
     * BFS 搜索
     */
    @Learn
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if(root == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int levelSize = queue.size();
            List<Integer> curLevel = new ArrayList<>(levelSize);
            for(int i = 0; i < levelSize; i++){
                TreeNode curNode = queue.poll();
                curLevel.add(curNode.val);
                if(curNode.left != null){
                    queue.add(curNode.left);
                }
                if(curNode.right != null){
                    queue.add(curNode.right);
                }
            }
            result.add(curLevel);
        }
        return result;
    }

    /**
     * 定义一个哈希表
     * k为层数
     * v为节点
     *
     * 递归拿到每个层的节点
     */
    @Self
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        travel(root, 0, map);
        return new ArrayList<>(map.values());
    }

    private void travel(TreeNode root, int level, Map<Integer, List<Integer>> map) {
        List<Integer> list = map.get(level);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(root.val);
        map.put(level, list);

        if (root.left != null) {
            travel(root.left, level + 1, map);
        }
        if (root.right != null) {
            travel(root.right, level + 1, map);
        }
    }
}
