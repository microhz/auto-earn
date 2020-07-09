package com.auto.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.*;

/**
 * @author : jihai
 * @date : 2020/6/20
 * @description : ping 测试
 */
public class PingSupport {

    public static boolean getPingResult(String url) {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("PING " + url);
            return getPingResult(process);
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean ping(String url) {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("PING " + url);
            boolean isOK = getPingResult(process);
            TerminalUtils.asyncPrint(process.getErrorStream());
            TerminalUtils.asyncPrint(process.getInputStream());
            return isOK;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean getPingResult(Process process) {
        try {
            ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));
            Future<Boolean> submit = executor.submit(() -> {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                int i = 0;
                StringBuffer content = new StringBuffer();
                while (i < 5) {
                    i ++;
                    String str = bufferedReader.readLine();
                    LogUtils.print(str);
                    content.append(str);
                }
                if (content.toString().contains("timeout")) {
                    return false;
                }
                return true;
            });

            // 等待秒
            try {
                return submit.get(8, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                LogUtils.errorPrint(e, "connected timeout");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void setProxyConfig() {

        String proxyHost = "127.0.0.1";
        String proxyPort = "1081";

        System.setProperty("http.proxyHost", proxyHost);
        System.setProperty("http.proxyPort", proxyPort);

        System.setProperty("https.proxyHost", proxyHost);
        System.setProperty("https.proxyPort", proxyPort);
    }

}
