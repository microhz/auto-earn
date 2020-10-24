package com.auto.test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author : jihai
 * @date : 2020/8/24
 * @description :
 */
public class Draft {

    public static void main(String[] args) {
        Draft draft = new Draft();
        draft.threeSum(new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0});
    }

    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if (num == null || num.length == 0) return list;
        Arrays.sort(num);
        for (int i = 0;i < num.length - 2;i ++) {
            int cur = num[i];
            if (i - 1 >= 0 && num[i - 1] == num[i]) {
                i ++;
                continue;
            }
            int left = i + 1, right = num.length - 1;
            while (left < right) {
                if (left - 1 > i && num[left - 1] == num[left]) {
                    left ++;
                    continue;
                }
                if (right + 1 <= num.length - 1 && num[right] == num[right + 1]){
                    right --;
                    continue;
                }
                if (num[left] + num[right] + cur < 0) {
                    left ++;
                } else if ((num[left] + num[right] + cur > 0)) {
                    right --;
                } else {
                    ArrayList<Integer> tList = new ArrayList<>();
                    tList.add(num[i]);
                    tList.add(num[left]);
                    tList.add(num[right]);
                    list.add(tList);
                    left ++;
                    right --;
                }

            }
        }
        return list;
    }
}
