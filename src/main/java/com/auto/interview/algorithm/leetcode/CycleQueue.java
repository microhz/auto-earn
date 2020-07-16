package com.auto.interview.algorithm.leetcode;

/**
 * @author : jihai
 * @date : 2020/7/16
 * @description :
 *
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 *
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 *
 * 你的实现应该支持如下操作：
 *
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * Front: 从队首获取元素。如果队列为空，返回 -1 。
 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满。
 *  
 *
 * 示例：
 *
 * MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
 * circularQueue.enQueue(1);  // 返回 true
 * circularQueue.enQueue(2);  // 返回 true
 * circularQueue.enQueue(3);  // 返回 true
 * circularQueue.enQueue(4);  // 返回 false，队列已满
 * circularQueue.Rear();  // 返回 3
 * circularQueue.isFull();  // 返回 true
 * circularQueue.deQueue();  // 返回 true
 * circularQueue.enQueue(4);  // 返回 true
 * circularQueue.Rear();  // 返回 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-circular-queue
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CycleQueue {

    public static void main(String[] args) {
         CycleQueue circularQueue = new CycleQueue(3); // 设置长度为 3
         System.out.println(circularQueue.enQueue(1));; // 返回 true
         System.out.println(circularQueue.enQueue(2)); // 返回 true
         System.out.println(circularQueue.enQueue(3)); // 返回 true
         System.out.println(circularQueue.enQueue(4)); // 返回 false，队列已满
         System.out.println(circularQueue.Rear()); // 返回 3
//        circularQueue.deQueue();
//        circularQueue.deQueue();
//        circularQueue.deQueue();
//        circularQueue.deQueue();
//        circularQueue.deQueue();
//        circularQueue.deQueue();

        // 0 -> 5 -> 9 - 3 ---

        System.out.println(circularQueue.isEmpty());
         System.out.println(circularQueue.isFull()); // 返回 true
         System.out.println(circularQueue.deQueue()); // 返回 true
         System.out.println(circularQueue.enQueue(4)); // 返回 true
         System.out.println(circularQueue.Rear());// 返回 4
    }

    private Integer[] cycleQueue;

    /*
     1. inital a array
     2. front is last element that not null, rear is first element
     3. isEmpty is array length either zero
     4. [3] -> [2] -> [1] -> null
     */

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public CycleQueue(int k) {
        // init array
        cycleQueue = new Integer[k];
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        if (isEmpty() && cycleQueue.length > 0) {
            cycleQueue[0] = value;
            return true;
        }

        // move that element to next value
        moveOneElement2Next();
        cycleQueue[0] = value;
        return true;
    }

    // set next element as currnent element
    private void moveOneElement2Next() {
        for (int i = cycleQueue.length - 1; i > 0; i --) {
            cycleQueue[i] = cycleQueue[i - 1];
            cycleQueue[i - 1] = null;
        }
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        // remove the last element
        for (int i = cycleQueue.length - 1; i >= 0; i --) {
            if (cycleQueue[i] != null) {
                cycleQueue[i] = null;
                return true;
            }
        }
        return false;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (isFull()) {
            // full
            return cycleQueue[cycleQueue.length - 1];
        }
        if (isEmpty()) {
            return -1;
        }
        for (int i = cycleQueue.length - 1; i >= 0; i --) {
            if (cycleQueue[i] != null) {
                return cycleQueue[i];
            }
        }
        // error
        return -1;
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return cycleQueue[0];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        for (Integer e : cycleQueue) {
            if (e != null) {
                return false;
            }
        }
        return true;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        for (Integer e : cycleQueue) {
            if (e == null) {
                return false;
            }
        }
        return true;
    }
}

/**
 * 解题思路没啥大的问题，但是很多边界值没考虑清楚，例如>= 0 , > 0 , >= 1 等问题没考虑清楚，还有就是为0或者为空等等情况.
 */
