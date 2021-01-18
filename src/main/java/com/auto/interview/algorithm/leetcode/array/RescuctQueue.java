package com.auto.interview.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author : jihai
 * @date : 2020/11/16
 * @description :
 *
 * 406. 根据身高重建队列
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 *
 * 注意：
 * 总人数少于1100人。
 *
 * 示例
 *
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class RescuctQueue {

    public static void main(String[] args) {
        RescuctQueue rescuctQueue = new RescuctQueue();
        System.out.println("[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]".replace("[", "{").replace("]", "}"));
//        rescuctQueue.reconstructQueue(new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}});


//        System.out.println(monotoneIncreasingDigits(747131058));

        firstUniqChar("leetcode");
    }

    public static int firstUniqChar(String s) {
        if (s == null) return -1;
        int[] ans = new int[26];
        for (char c : s.toCharArray()) {
            ans[c - 'a'] ++;
        }
        int t = -1;
        for (int i = 0;i < ans.length;i ++) {
            if (ans[i] == 1) {
                t = i;
                break;
            }
        }
        System.out.println(t);
        for (int i = 0;i < s.length();i ++) {
            if (s.charAt(i) - 'a' == t) {
                return i;
            }
        }
        return -1;
    }

    public static int monotoneIncreasingDigits(int N) {
        if (N < 10) return N;
        int i = 0;
        char[] arr = String.valueOf(N).toCharArray();
        while (i + 1 < arr.length && arr[i] <= arr[i + 1]) {
            i ++;
        }
        if (i + 1 < arr.length) {
            arr[i] = (char) ((Integer.valueOf(arr[i] - '0') - 1) + Integer.valueOf('0'));
            i ++;
            while (i < arr.length) {
                arr[i] = '9';
                i ++;
            }
            // System.out.println(new String(arr));
            return monotoneIncreasingDigits(Integer.valueOf(new String(arr)));
        }
        return Integer.valueOf(new String(arr));
    }

    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0) return new int[0][0];
        Arrays.sort(people, (p1, p2) -> {
            return p1[0] == p2[0] ? p1[1] - p2[1] : p2[0] - p1[0];
        });

        List<int[]> list = new ArrayList<>();
        for (int[] i : people) {
            list.add(i[1], i);
        }
        return list.toArray(new int[list.size()][2]);
    }

}
