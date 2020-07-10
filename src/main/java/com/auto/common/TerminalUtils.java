package com.auto.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author : jihai
 * @date : 2020/7/9
 * @description :
 */
public class TerminalUtils {
    public static void asyncPrint(InputStream inputStream) {
        new Thread(() -> {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String len;
                while ((len = bufferedReader.readLine()) != null) {
                    LogUtils.print(Thread.currentThread().getName() + ":" + len);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void syncPrint(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String len;
            while ((len = bufferedReader.readLine()) != null) {
                LogUtils.print(Thread.currentThread().getName() + ":" + len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
