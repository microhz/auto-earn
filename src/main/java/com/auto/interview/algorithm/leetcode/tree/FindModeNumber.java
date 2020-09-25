package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : jihai
 * @date : 2020/9/24
 * @description :
 * 501. 二叉搜索树中的众数
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 *
 * 假定 BST 有如下定义：
 *
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 *
 *    1
 *     \
 *      2
 *     /
 *    2
 * 返回[2].
 *
 * 提示：如果众数超过1个，不需考虑输出顺序
 *
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 */
public class FindModeNumber {


    Map<Integer, Integer> map = new HashMap<>();
    int max = 0;
    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];
        dfs(root);
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(max)) {
                list.add(entry.getKey());
            }
        }
        int[] arr = new int[list.size()];
        for (int i = 0;i < list.size();i ++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public void dfs(TreeNode root) {
        if (root == null) return ;
        int m = map.getOrDefault(root.val, 0) + 1;
        max = Math.max(m, max);
        map.put(root.val, m);
        dfs(root.left);
        dfs(root.right);
    }
}
