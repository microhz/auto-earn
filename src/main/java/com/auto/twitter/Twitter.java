package com.auto.twitter;

import com.auto.common.ChromeSupport;
import com.auto.common.ImageDownload;
import com.auto.common.LogUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : jihai
 * @date : 2020/6/28
 * @description :
 */
public class Twitter extends ChromeSupport {

    public void downloadImage(String url, String fileName) {
        this.downloadImage(url, fileName, true);
    }

    /**
     *
     * @param urlId 格式,分割
     * @param fileName
     * @param shutdownBrower
     */
    public void downloadImage(String urlId, String fileName, boolean shutdownBrower) {
        try {
            init();
            openPage("https://twitter.com/" + urlId);
            List<WebElement> imageListEl = webDriver.findElements(By.xpath("//div[@data-testid='tweetPhoto']//img"));
            ImageDownload imageDownload = new ImageDownload();

            List<String> imageDownloadUrlList = imageListEl.stream().map(e -> {
                String imageUrl = e.getAttribute("src");
                String replace = imageUrl.replace("small", "large");
                return replace;
            }).distinct().collect(Collectors.toList());
            if (imageDownloadUrlList.size() == 1) {
                imageDownload.dowloadImage(imageDownloadUrlList.get(0), fileName);
            } else {
                for (int i = 0; i < imageDownloadUrlList.size(); i++) {
                    imageDownload.dowloadImage(imageDownloadUrlList.get(i), fileName + "-" + i);
                }
            }
        } catch (Exception e) {
            LogUtils.errorPrint(e, "下载图片失败");
        } finally {
            if (webDriver != null && shutdownBrower) {
                webDriver.close();
            }
        }
    }
}
