package com.auto.interview.algorithm.leetcode.array;

/**
 * @author : jihai
 * @date : 2020/9/14
 * @description :
 *
 * 136. 只出现一次的数字
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 *
 * 输入: [4,1,2,1,2]
 * 输出: 4
 */
public class OneNumber {

    public static void main(String[] args) {
        System.out.println(1056389759 > Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE + 1);
    }


    /**
     * 其他方式不用说了
     * 异或运算是一个比较好的方式，线性时间复杂度，空间复杂度常数
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int i : nums) {
            single ^= i;
        }
        return single;
    }
}
