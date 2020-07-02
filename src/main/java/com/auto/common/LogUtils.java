package com.auto.common;

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
        System.out.println(String.format(content, params));
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
