package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;
import com.auto.interview.algorithm.leetcode.utils.Assert;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/8/24
 * @description :
 */
public class TreeSymetric {

    public static void main(String[] args) {
        TreeNode treeNode = NodeUtils.buildTreeNode(1, 2, 2, 3, 2, 2, 3);
        TreeSymetric treeSymetric = new TreeSymetric();
        Assert.assertTrue(treeSymetric.isSymmetric2(treeNode));

    }

    /**
     * 每一层层序遍历，然后判断每一层是否对称，注意null节点
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        while (! list.isEmpty()) {
            List<TreeNode> lay = new ArrayList<>();
            if (list.size() > 1) {
                for (int i = 0,j = list.size()-1;i < j;i ++,j --) {
                    if (list.get(i) == null && list.get(j) == null) continue;
                    if (list.get(i) != null && list.get(j) != null && list.get(i).val == list.get(j).val) continue;
                    return false;
                }
            }
            for (int i = 0;i < list.size();i ++) {
                if (list.get(i) != null) {
                    lay.add(list.get(i).left);
                    lay.add(list.get(i).right);
                }
            }
            list.clear();
            list.addAll(lay);
        }
        return true;
    }

    /**
     * 需要话题加深理解，左右相等，并且Left.left == Right.right && left.right == right.left;
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null || left.val != right.val) return false;
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
}
