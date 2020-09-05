package com.auto.interview.algorithm.leetcode.string;

import com.auto.interview.algorithm.leetcode.utils.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : jihai
 * @date : 2020/9/3
 * @description :
 *
 * 13. 罗马数字转整数
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 *
 *
 *
 * 示例 1:
 *
 * 输入: "III"
 * 输出: 3
 * 示例 2:
 *
 * 输入: "IV"
 * 输出: 4
 * 示例 3:
 *
 * 输入: "IX"
 * 输出: 9
 * 示例 4:
 *
 * 输入: "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 * 示例 5:
 *
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 *
 * 提示：
 *
 * 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
 * IC 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
 * 关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。
 */
public class RomeNumber {

    public static void main(String[] args) {
        RomeNumber romeNumber = new RomeNumber();

        Assert.assertTrue(romeNumber.romanToInt("III") == 3);
        Assert.assertTrue(romeNumber.romanToInt("IV") == 4);
        Assert.assertTrue(romeNumber.romanToInt("IX") == 9);
        Assert.assertTrue(romeNumber.romanToInt("LVIII") == 58);
        Assert.assertTrue(romeNumber.romanToInt("MCMXCIV") == 1994);

    }

    // 优选转换成的减位的表示
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        char[] array = s.toCharArray();
        int sum = 0;
        for (int i = 0;i < array.length;i ++) {
            int temp = sum;
            if (i < array.length - 1) {
                if (array[i] == 'I') {
                    if (array[i + 1] == 'V') {
                        sum += 4;
                        i ++;
                    } else if (array[i + 1] == 'X') {
                        sum += 9;
                        i ++;
                    }
                } else if (array[i] == 'X') {
                    if (array[i + 1] == 'L') {
                        sum += 40;
                        i ++;
                    } else if (array[i + 1] == 'C') {
                        sum += 90;
                        i ++;
                    }
                } else if (array[i] == 'C') {
                    if (array[i + 1] == 'D') {
                        sum += 400;
                        i ++;
                    } else if (array[i + 1] == 'M') {
                        sum += 900;
                        i ++;
                    }
                }
            }
            if (sum > temp) continue;
            sum += map.get(array[i]);
        }
        return sum;
    }
}
