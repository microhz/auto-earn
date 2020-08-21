package com.auto.interview.algorithm.leetcode.array;

import com.auto.interview.algorithm.leetcode.utils.Assert;

/**
 * @author : jihai
 * @date : 2020/8/21
 * @description :
 *
 * 717. 1比特与2比特字符
 * 有两种特殊字符。第一种字符可以用一比特0来表示。第二种字符可以用两比特(10 或 11)来表示。
 *
 * 现给一个由若干比特组成的字符串。问最后一个字符是否必定为一个一比特字符。给定的字符串总是由0结束。
 *
 * 示例 1:
 *
 * 输入:
 * bits = [1, 0, 0]
 * 输出: True
 * 解释:
 * 唯一的编码方式是一个两比特字符和一个一比特字符。所以最后一个字符是一比特字符。
 * 示例 2:
 *
 * 输入:
 * bits = [1, 1, 1, 0]
 * 输出: False
 * 解释:
 * 唯一的编码方式是两比特字符和两比特字符。所以最后一个字符不是一比特字符。
 * 注意:
 *
 * 1 <= len(bits) <= 1000.
 * bits[i] 总是0 或 1.
 */
public class BitArray {

    public static void main(String[] args) {
        BitArray bitArray = new BitArray();
        Assert.assertTrue(bitArray.isOneBitCharacter(new int[]{1, 0, 0}));
        Assert.assertTrue(! bitArray.isOneBitCharacter(new int[]{1, 1, 1, 0}));

    }

    /**
     * 采用分治法
     */
    public boolean isOneBitCharacter(int[] bits) {
        if (bits.length == 1) return true;
        return isOneBitCharacter(bits, 0);
    }

    public boolean isOneBitCharacter(int[] bits, int start) {
        if (start > bits.length - 1) return false;
        if (start == bits.length - 1) return true;
        boolean c1 = true, c2 = true;
        if (bits[start] == 0) {
            c1 = isOneBitCharacter(bits, start + 1);
        } else {
            if (start < bits.length - 2) {
                c2 = bits[start] == bits[start + 1] ? isOneBitCharacter(bits, start + 2) : isOneBitCharacter(bits, start + 1);
            } else {
                return false;
            }
        }
        return c1 && c2;
    }


    /**
     * 官方解法： 线性扫描
     * 遇到1 i就跳2下，因为肯定是2比特的，
     * 遇到0就跳1下，看最后是不是一定在bits最后位置
     */
    public boolean isOneBitCharacter2(int[] bits) {
        if (bits.length == 1) return true;
        int i = 0;
        while (i < bits.length - 1) {
            if (bits[i] == 0) i ++;
            else i += 2;
        }
        return i == bits.length - 1;
    }

    /**
     * 贪心算法
     * 只需要关心最后一位前面有多少个连续的1，如果是偶数个成立，奇数个不成立
     */
    public boolean isOneBitCharacter3(int[] bits) {
        int i = bits.length - 1;
        while (i >= 0 && bits[i] > 0) {
            i --;
        }
        return i % 2 == 0;
    }
}
