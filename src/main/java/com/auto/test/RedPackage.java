package com.auto.test;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/10/14
 * @description :
 */
public class RedPackage {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getPackageList(20, 6));
        }
    }

    private static List<Integer> getPackageList(int amount, int number) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0;i < number - 1;i ++) {
            int e = 0;
            while (true) {
                e = RandomUtils.nextInt(0, amount - (number - i) + 1);
                if (e > 0) break;
            }
            amount -= e;
            list.add(e);
        }
        list.add(amount);


        // shuff
        Collections.shuffle(list);
        return list;
    }
}
