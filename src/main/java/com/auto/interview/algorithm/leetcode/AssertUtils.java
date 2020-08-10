package com.auto.interview.algorithm.leetcode;

/**
 * @author : jihai
 * @date : 2020/8/4
 * @description :
 */
public class AssertUtils {

    /**
     * 断言链表
     * @param listNode
     * @param params
     */
    public static void assertListNode(ListNode listNode, int ... params) {
        ListNode p = listNode;
        for (int i = 0; i < params.length; i++) {
            if (p.val != params[i]) {
                throw new RuntimeException("assert error");
            }
            p = p.next;
        }
    }

    /**
     * 断言数组
     * @param order
     * @param params
     */
    public static void assertArray(int[] order, int ... params) {
        if (order.length != params.length) {
            throw new RuntimeException("assert error");
        }
        for (int i = 0; i < order.length; i++) {
            if (order[i] != params[i]) {
                throw new RuntimeException("assert error");
            }
        }
    }
}
