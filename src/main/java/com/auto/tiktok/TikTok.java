package com.auto.tiktok;

import com.auto.common.ChromeSupport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author : jihai
 * @date : 2020/7/20
 * @description : tiktok download
 */
public class TikTok extends ChromeSupport {
    public static void main(String[] args) {
        downloadUrl("https://aweme.snssdk.com/aweme/v1/playwm/?video_id=v0200f210000bs9recanhoe2itovjmc0&ratio=720p&line=0");
    }

    private static void downloadUrl(String url) {
        try {
            URL u = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)u.openConnection();

            byte[] content = new byte[4096];
            File file = new File(ChromeSupport.YOUTUBE_PATH_ORIGIN + "test.mp4");

            BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            int count = 0;
            while ((count = bufferedInputStream.read()) != -1) {
                System.out.println("写入");
                bufferedOutputStream.write(content, 0, count);
            }

            bufferedInputStream.close();
            bufferedOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
