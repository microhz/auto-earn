package com.auto.interview.algorithm.leetcode.link;

import com.auto.interview.algorithm.leetcode.base.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : jihai
 * @date : 2020/8/28
 * @description :
 */
public class ComplexLinkCopy {

    public static void main(String[] args) {

    }

    /**
     * 用哈希表去处理这个问题
     */
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Map<Node, Node> map = new HashMap<>();
        Node p = head;
        while (p != null) {
            map.put(p, new Node(p.val));
            p = p.next;
        }
        p = head;
        while (p != null) {
            map.get(p).next = map.get(p.next);
            map.get(p).random = map.get(p.random);
            p = p.next;
        }
        return map.get(head);
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}

