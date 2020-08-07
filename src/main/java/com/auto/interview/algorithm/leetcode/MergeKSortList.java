package com.auto.interview.algorithm.leetcode;

/**
 * @author : jihai
 * @date : 2020/8/7
 * @description :
 * 23. 合并K个排序链表
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 *
 *    1->4->5,
 *    1->3->4,
 *    5->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 */
public class MergeKSortList {

    public static void main(String[] args) {
        MergeKSortList mergeKSortList = new MergeKSortList();
        ListNode listNode = mergeKSortList.mergeKLists(new ListNode[]{ListNodeUtils.buildListNode(1, 4, 5),
                ListNodeUtils.buildListNode(1, 3, 4),
                ListNodeUtils.buildListNode(2, 6)});
        AssertUtils.assertListNode(listNode, 1, 1, 2, 3, 4, 4, 5, 6);
    }

    /**
     *
     * 分治法
     * 每次寻找下一个节点，添加进来
     * 再递归，直到所有节点都为null
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        /*
        递归出口
         */
        ListNode p = null;
        for (ListNode node : lists) {
            if (node != null) {
                p = node;
                break;
            }
        }
        if (p == null) {
            return null;
        }

        int index = 0;
        for (int i = 0; i < lists.length; i++) {
            if (lists[index] == null && lists[i] != null) {
                index = i;
            }
            if (lists[i] != null && lists[i].val < p.val) {
                p = lists[i];
                index = i;
            }
        }
        lists[index] = lists[index].next;
        // 寻找下个节点
        p.next = mergeKLists(lists);
        return p;
    }
}
