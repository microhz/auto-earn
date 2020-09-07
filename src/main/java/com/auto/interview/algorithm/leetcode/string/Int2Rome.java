package com.auto.interview.algorithm.leetcode.string;

/**
 * @author : jihai
 * @date : 2020/9/6
 * @description :
 */
public class Int2Rome {

    public static void main(String[] args) {
        Int2Rome int2Rome = new Int2Rome();
        System.out.println(int2Rome.intToRoman2(1994));;
    }

    public String intToRoman2(int num) {
        int[] array = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbol = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            while (num > array[i]) {
                sb.append(symbol[i]);
                num -= array[i];
                i ++;
            }
            i ++;
            if (num - array[i] >= 0) {
                sb.append(symbol[i]);
                num -= array[i];
                if (num < array[i]) i ++;
            } else {
                i ++;
            }
        }
        return sb.toString();
    }

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            String nextStr = null;
            int n = 0;
            if (num / 1000 > 0) {
                n = num / 1000;
                nextStr = "M";
                num %= 1000;
            } else if (num / 900 > 0) {
                n = num / 900;
                nextStr = "CM";
                num %= 900;
            } else if (num / 500 > 0) {
                n = num / 500;
                nextStr = "D";
                num %= 500;
            } else if (num / 400 > 0) {
                n = num / 400;
                nextStr = "CD";
                num %= 400;
            } else if (num / 100 > 0) {
                n = num / 100;
                nextStr = "C";
                num %= 100;
            } else if (num / 90 > 0) {
                n = num / 90;
                nextStr = "XC";
                num %= 90;
            } else if (num / 50 > 0) {
                n = num / 50;
                nextStr = "L";
                num %= 50;
            } else if (num / 40 > 0) {
                n = num / 40;
                nextStr = "XL";
                num %= 40;
            } else if (num / 10 > 0) {
                n = num / 10;
                nextStr = "X";
                num %= 10;
            } else if (num / 9 > 0) {
                n = num / 9;
                nextStr = "IX";
                num %= 9;
            } else if (num / 5 > 0) {
                n = num / 5;
                nextStr = "V";
                num %= 5;
            } else if (num / 4 > 0) {
                n = num / 4;
                nextStr = "IV";
                num %= 4;
            } else if (num / 1 > 0) {
                n = num / 1;
                nextStr = "I";
                num %= 1;
            }
            for (int i = 0;i < n;i ++) {
                sb.append(nextStr);
            }
        }
        return sb.toString();
    }
}
