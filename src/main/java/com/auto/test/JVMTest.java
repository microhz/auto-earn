package com.auto.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/11/14
 * @description :
 */
public class JVMTest {

    public static void main(String[] args) throws InterruptedException {
        List<Byte[]> ret = new ArrayList<>();
        int size = 0;
        while (true) {
            Byte[] data = new Byte[1024 * 1024];
            ret.add(data);

            Thread.sleep(500);
            System.out.println("增加数据 ： " + ++ size + " MB");
            // to process ....
            data = null;

        }
    }
}
