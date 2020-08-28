package com.auto.interview.algorithm.leetcode.tree;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/8/28
 * @description :
 *
 * 589. N叉树的前序遍历
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 *
 * 例如，给定一个 3叉树 :
 *
 *
 *
 *
 *
 *
 *
 * 返回其前序遍历: [1,3,5,6,2,4]。
 *
 *
 *
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 */
public class NTree {

    public static void main(String[] args) {
        NTree nTree = new NTree();
        Node node = new Node(1);
        Node node1 = new Node(3);
        node.children = Lists.newArrayList(node1, new Node(2), new Node(4));
        node1.children = Lists.newArrayList(new Node(5), new Node(6));

        nTree.preorder2(node);
    }


    /**
     * 递归法
     */
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        dfs(root, list);
        return list;
    }

    public void dfs(Node root, List<Integer> list) {
        if (root == null) return ;
        list.add(root.val);
        for (Node node : root.children) {
            dfs(node, list);
        }
    }


    /**
     *
     */
    public List<Integer> preorder2(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        // 初始化一个链表
        LinkedList<Node> linkedList = new LinkedList<>();
        linkedList.add(root);
        while (! linkedList.isEmpty()) {
            // 拿链表头
            Node node = linkedList.pollLast();
            list.add(node.val);
            // 反转子节点
            Collections.reverse(node.children);
            for (Node n : node.children) {
                linkedList.add(n);
            }
        }
        return list;
    }

}


class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", children=" + children +
                '}';
    }
}
