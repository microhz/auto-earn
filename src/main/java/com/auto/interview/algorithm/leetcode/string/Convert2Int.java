package com.auto.interview.algorithm.leetcode.string;

/**
 * @author : jihai
 * @date : 2020/8/30
 * @description :
 */
public class Convert2Int {

    public static void main(String[] args) {

        Convert2Int convert2Int = new Convert2Int();

//        System.out.println(Integer.MAX_VALUE + Integer.MAX_VALUE > Integer.MAX_VALUE);
        System.out.println(convert2Int.strToInt("-91283472332"));
        System.out.println(Integer.MAX_VALUE);
    }


    public void test() {
        // 214748364     2147483647 - 8
//        if (sum > (Integer.MAX_VALUE - (array[i] - '0')) / 10) {
//            // 别忘了判断符号
//            return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
//        }
//        sum = sum * 10 + (array[i] - '0');
    }

    public int strToInt(String str) {
        if (str == null || str.trim().length() == 0) return 0;
        // 处理正负号
        int sign = 1;
        // 累计
        int sum = 0;
        // 第一个开始值
        int first = 0;
        char[] array = str.trim().toCharArray();
        // 判断正负，并且移动第一个数字
        if (array[0] == '-') {
            sign = -1;
            first ++;
        } else if (array[0] == '+') {
            first ++;
        }
        // 跳过开头的0
        while (first < array.length && array[first] == '0') {
            first ++;
        }
        // 非数字开头
        if (first < array.length && (array[first] - '0' < 0 || array[first] - '0' > 9)) return 0;

        for (int i = first;i < array.length;i ++) {
            if (array[i] - '0' >= 0 && array[i] - '0' < 10) {
                // 判断是否超过int上限
                if (sum > (Integer.MAX_VALUE - (array[i] - '0')) / 10) {
                    // 别忘了判断符号
                    return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                sum = sum * 10 + (array[i] - '0');
            } else if (sum > 0) {
                // 后面的值无效了，直接return了
                return sign * sum;
            }
        }
        return sign * sum;
    }

}
