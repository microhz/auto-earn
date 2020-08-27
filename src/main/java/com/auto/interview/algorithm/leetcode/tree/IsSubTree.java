package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;
import com.auto.interview.algorithm.leetcode.utils.Assert;
import com.auto.interview.algorithm.leetcode.utils.NodeUtils;

/**
 * @author : jihai
 * @date : 2020/8/26
 * @description :
 */
public class IsSubTree {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(4);
        TreeNode left = treeNode.left(1).left(1);
        left.left = new TreeNode(6);
        left.right = new TreeNode(7);

        TreeNode treeNode1 = NodeUtils.buildTreeNode(4, 1, null, 6, 7, null, null);
        IsSubTree isSubTree = new IsSubTree();
        Assert.assertTrue(!isSubTree.isSubtree(treeNode, treeNode1));
    }

    /**
     * 运行时间：743ms
     *
     * 占用内存：248020k
     *
     * leetcode超时，牛客通过
     */
    // 都不为空
    TreeNode rootVal;
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (rootVal == null) rootVal = t;
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        boolean isTree = false;
        if (s.val == t.val) {
            isTree = isSubtree(s.left, t.left) && isSubtree(s.right, t.right);
        } else if (rootVal.val != t.val) {
            return false;
        }
        return isTree || isSubtree(s.left, rootVal) || isSubtree(s.right, rootVal);
    }


    /**
     * 参考别人答案
     * 这才是正常的递归
     * 运行时间：721ms
     *
     * 占用内存：241632k
     */
    // 都不为空
    public boolean isSubtree2(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        return isSubtree2(s.right, t) || isSubtree2(s.left, t) || sameSubTreefs(s, t);
    }

    public boolean sameSubTreefs(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.val == t.val) return sameSubTreefs(s.left, t.left) && sameSubTreefs(s.right, t.right);
        return false;
    }
}
