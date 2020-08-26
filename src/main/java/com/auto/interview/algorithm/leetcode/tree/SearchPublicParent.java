package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : jihai
 * @date : 2020/8/25
 * @description :
 *
 * 剑指 Offer 68 - II. 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 *
 *
 *
 *
 * 示例 1:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 示例 2:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *
 *
 * 说明:
 *
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 */
public class SearchPublicParent {



    // 保持父节点的指针，然后用双指针遍历
    /**
     * 自己写的
     * 保持一个父节点，然后用双指针双层遍历
     */
    Map<TreeNode, TreeNode> parent;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        parent = new HashMap<>();
        // dfs
        dfs(root, null);

        while (p != null) {
            TreeNode search = q;
            while (search != null) {
                if (p == search) {
                    return search;
                }
                search = parent.get(search);
            }
            p = parent.get(p);
        }
        return null;
    }

    public void dfs(TreeNode root, TreeNode parentNode) {
        if (root != null) {
            parent.put(root, parentNode);
            if (root.left != null) dfs(root.left, root);
            if (root.right != null) dfs(root.right, root);
        }
    }


    /**
     * 参考别人的
     * 方法2：更好的一种递归解法
     * 看p,q两个节点是否都在左右两侧
     * 可能在两侧
     * 可能都在左侧
     * 可能在又侧
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        // root为null
        if (root == null) return null;
        // 找到了一个节点
        if (root == p || root == q) return root;
        /*
        找到一个节点，另一个节点一定在另一侧
         */
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        // 如果分别在两侧，该节点为目标
        return root;
    }
}
