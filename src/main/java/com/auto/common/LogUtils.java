package com.auto.common;

import com.alibaba.fastjson.JSONObject;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * @author : jihai
 * @date : 2020/6/20
 * @description :
 */
public class LogUtils {

    public static void print(String content, Object ... params) {
        if (content.contains("%s")) {
            System.out.println(String.format(content, params));
        } else if (content.contains("{}")) {
            StringBuffer sb = new StringBuffer();
            String[] split = content.split("\\{\\}");
            for (int i = 0; i < params.length; i++) {
                sb.append(split[i]).append(params[i]);
            }
            if (split.length > params.length) {
                sb.append(split[params.length]);
            }
            System.out.println(sb);
        } else {
            System.out.println(content);
        }
    }

    public static void errorPrint(Throwable e, String errorMsg) {
        System.out.println(String.format("%s error stack %s", errorMsg, e.getMessage()));
        e.printStackTrace();
    }

    public void printSupportSSL() {
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, null, null);

            SSLSocketFactory factory = (SSLSocketFactory) context.getSocketFactory();
            SSLSocket socket = (SSLSocket) factory.createSocket();

            String[] protocols = socket.getSupportedProtocols();

            System.out.println("Supported Protocols: " + protocols.length);
            for (int i = 0; i < protocols.length; i++) {
                System.out.println(" " + protocols[i]);
            }

            protocols = socket.getEnabledProtocols();

            System.out.println("Enabled Protocols: " + protocols.length);
            for (int i = 0; i < protocols.length; i++) {
                System.out.println(" " + protocols[i]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
