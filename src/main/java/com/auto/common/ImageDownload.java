package com.auto.common;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : jihai
 * @date : 2020/6/20
 * @description :
 */
public class ImageDownload {

    private static final String IMAGE_DOWNLOAD_FILE = "img.text";
    private static final String MAIN_DOMAIN_ADDRESS = "https://www.instagram.com/";

    public void executeFromConfig() {
        try {
            if (! testDomain(MAIN_DOMAIN_ADDRESS)) {
                LogUtils.print("can't acccess");
                return ;
            }
            List<Image> imageList = getImageConfigList();
            dowloadImage(imageList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Image> getImageConfigList() {
        ClassPathResource classPathResource = new ClassPathResource(IMAGE_DOWNLOAD_FILE);
        try {
            File file = classPathResource.getFile();
            String content = FileUtils.readFileToString(file, Charset.defaultCharset());
            List<Object> list = JSON.parseObject(content, List.class);
            return list.stream().map(e -> {
                try {
                    return JSON.parseObject(e.toString(), Image.class);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }).filter(e -> Objects.nonNull(e)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Lists.newArrayList();
    }

    private void dowloadImage(List<Image> imageList) {
        if (CollectionUtils.isEmpty(imageList)) {
            return ;
        }

        imageList.forEach(e -> {
            try {
                dowloadImage(e.getUrl(), e.getFileName());
            } catch (Exception ex) {
                LogUtils.errorPrint(ex, "downloadImage");
            }
        });
    }

    /**
     * 测试域名是否通常
     * @param domain
     * @return
     */
    private boolean testDomain(String domain) {
        try {
            URL url = new URL(domain);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String content;
            StringBuffer page = new StringBuffer();
            try {
                while ((content = bufferedReader.readLine()) != null) {
                    page.append(content);
                }
            } catch (IOException e) {
                LogUtils.errorPrint(e, "domain can't connected");
                return false;
            }
        } catch (IOException e) {
            LogUtils.errorPrint(e, "test domain error");
            return false;
        }
        return true;
    }

    /**
     * 下载图片
     * @param imageUrl
     */
    public void dowloadImage(String imageUrl, String imageName) {
        this.dowloadImage(ChromeSupport.INS_PATH, imageUrl, imageName);
    }

    public void dowloadImage(String batchUpdatePath, String imageUrl, String imageName) {
        try {
            LogUtils.print("waiting download name : %s, url : %s", imageName, imageUrl);
            URL url = new URL(imageUrl);
            FileUtils.copyURLToFile(url, new File(batchUpdatePath + imageName + ".jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
