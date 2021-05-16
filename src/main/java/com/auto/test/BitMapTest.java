package com.auto.test;

import sun.jvm.hotspot.utilities.BitMap;

/**
 * @author : jihai
 * @date : 2021/3/8
 * @description :
 */
public class BitMapTest {

    public static void main(String[] args) {
        BitMap day1 = new BitMap(1024);
        day1.atPut(4, true);
        day1.atPut(1023, false);

        System.out.println(day1.at(4));
        System.out.println(day1.at(5));
        System.out.println(day1.at(1023));
    }
}
