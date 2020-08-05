package com.auto.interview.algorithm.leetcode;

/**
 * @author : jihai
 * @date : 2020/8/4
 * @description :
 */
public class AssertUtils {
    public static void assertListNode(ListNode listNode, int ... params) {
        ListNode p = listNode;
        for (int i = 0; i < params.length; i++) {
            if (p.val != params[i]) {
                throw new RuntimeException("assert error");
            }
            p = p.next;
        }
    }
}
