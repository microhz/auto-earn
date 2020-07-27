package com.auto.instagram;

import com.alibaba.fastjson.JSON;
import com.auto.common.ChromeSupport;
import com.auto.common.ImageDownload;
import com.auto.common.LogUtils;
import com.auto.pojo.ImageVideoBO;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author : jihai
 * @date : 2020/6/20
 * @description :
 */
public class InstagramClient extends ChromeSupport {

    private static String USER_NAME;
    private static String PASSWORD;

    static {
        PASSWORD = JSON.parseObject(secretKey.get("ins").toString()).getString("password");
        USER_NAME = JSON.parseObject(secretKey.get("ins").toString()).getString("userName");
    }

    @Override
    public void init() throws IOException {
        try {
            super.init();
            openPageAndWaitLoading("https://www.instagram.com/accounts/login/?next=/login/", By.xpath("//button"), "登陆页面");
            login(USER_NAME, PASSWORD);
        } catch (Exception e) {
            LogUtils.errorPrint(e, "login failure");
        }
    }

    public void downloadUserPage(String userPageUrl) {
        try {
            init();
            dowloadUserPageImageList(userPageUrl);
            closeDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dowloadUserPageImageList(String userPage) throws InterruptedException {
        navigatePageAndWaitRefresh("https://www.instagram.com/" + userPage + "/");
        waitLongLoading();
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        try {
            webDriver.manage().timeouts().setScriptTimeout(3, TimeUnit.SECONDS);
            javascriptExecutor.executeAsyncScript("var q=document.documentElement.scrollTop=10000");
        } catch (Throwable e) {
            System.out.println("执行js 强制结束");
        }
        // wait js executed
        sleep(4000);

        List<WebElement> imageList = webDriver.findElements(By.xpath("//img")).stream().
                filter(e -> e.getAttribute("srcset") != null &&
                        StringUtils.isNotBlank(e.getAttribute("srcset")))
                .collect(Collectors.toList());

        ImageDownload imageDownload = new ImageDownload();

        System.out.println("size " + imageList.size());
        int i = 0;
        for (WebElement webElement : imageList) {
            String imageUrl = webElement.getAttribute("srcset");
            String[] split = imageUrl.split(",");
            String maxImageUrl = split[split.length - 1].split(" ")[0];
            if (! maxImageUrl.contains("http")) {
                continue;
            }
            System.out.println(maxImageUrl);
            imageDownload.dowloadImage(maxImageUrl, "ins-" + userPage + "-" + (i ++));
        }
    }

    private void login(String username, String password) {
        WebElement usernameEl = webDriver.findElement(By.name("username"));
        WebElement passwordEl = webDriver.findElement(By.name("password"));

        usernameEl.sendKeys(username);
        passwordEl.sendKeys(password);

        WebElement submit = webDriver.findElement(By.xpath("//button"));
        submit.submit();
        super.checkPageStatus(By.className("coreSpriteKeyhole"), "ins登陆成功页面");
        LogUtils.print("ins 登陆结束");
    }

    public void downloadVideo(String videoUrl, String fileName) {
        try {
            init();
            openPage("https://www.instagram.com/p/" + videoUrl + "/");
            List<WebElement> videoElList = getVideoElList();
            LogUtils.print("一共" + videoElList.size() + "个视频需要下载");
            if (videoElList.size() == 1) {
                FileUtils.copyURLToFile(new URL(videoElList.get(0).getAttribute("src")), new File(YOUTUBE_PATH_ORIGIN + fileName +".mp4"));
            } else {
                for (int i = 0; i < videoElList.size(); i++) {
                    FileUtils.copyURLToFile(new URL(videoElList.get(i).getAttribute("src")), new File(YOUTUBE_PATH_ORIGIN + fileName + "-" + i +".mp4"));
                }
            }
            webDriver.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<WebElement> getVideoElList() {
        List<WebElement> elements = webDriver.findElements(By.xpath("//video[@type='video/mp4']"));
        if (elements.size() == 0) {
            sleep(500);
            return getVideoElList();
        }
        return elements;
    }

    public void downloadDetail(String imageDetailUrl, String fileName) {
        try {
            init();
            openPageAndWaitLoading("https://www.instagram.com/p/" + imageDetailUrl + "/", By.xpath("//img[@class='FFVAD']"), "图片详情页面");
            Set<String> imageSet = getImageDetail();
            LogUtils.print("获取到封面图片 %s", imageSet);
            WebElement nextEl;
            while (true) {
                try {
                    nextEl = webDriver.findElement(By.xpath("//div[@class='    coreSpriteRightChevron  ']"));
                    LogUtils.print("next page");
                } catch (NoSuchElementException e) {
                    break;
                }
                nextEl.click();
                sleep(1000);
                imageSet.addAll(getImageDetail());
                LogUtils.print("获取到下一页图片 %s", imageSet);
            }

            imageSet.removeIf(e -> StringUtils.isBlank(e));

            LogUtils.print("一共" + imageSet.size() + "个图片需要下载");
            if (imageSet.size() == 0) {
                return ;
            }

            ImageDownload imageDownload = new ImageDownload();
            List<String> downUrlList = Lists.newArrayList(imageSet);
            if (imageSet.size() == 1) {
                imageDownload.dowloadImage(downUrlList.get(0), fileName);
            } else {
                for (int i = 0; i < imageSet.size(); i++) {
                    imageDownload.dowloadImage(downUrlList.get(i), fileName + "-" + i);
                }
            }
            webDriver.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Set<String> getImageDetail() {
        HashSet<String> set = Sets.newHashSet(webDriver.findElements(By.xpath("//article//div[@role='button']//img")).
                stream().filter(e -> StringUtils.isBlank(e.getAttribute("data-testid"))).map(e -> e.getAttribute("src")).collect(Collectors.toList()));
        if (set.stream().filter(e -> StringUtils.isNotBlank(e) && !e.contains("null")).count() == 0) {
            LogUtils.print("详情页没捕捉到图片，等待一下继续捕捉");
            sleep(1000);
            return getImageDetail();
        }
        return set;
    }

    private void filterNotMaxSize(Set<WebElement> imageSet) {
        int max = 0;
        for (WebElement e : imageSet){
            String sizes = e.getAttribute("sizes");
            Integer size = Integer.valueOf(sizes.replace("px", ""));
            max = size > max ? size : max;
        }
        String maxSize = max + "px";
        imageSet.removeIf(e -> !e.getAttribute("sizes").equals(maxSize));
    }

    public void batchDownloadFromConfig() throws IOException {

        List<ImageVideoBO> imageVideoBOList = convert2ImageVideo(FileUtils.readFileToString(new ClassPathResource("img.text").getFile(), Charset.defaultCharset()));
        List<ImageVideoBO> videoBOList = convert2ImageVideo(FileUtils.readFileToString(new ClassPathResource("video.text").getFile(), Charset.defaultCharset()));
        init();

        for (int i = 0; i < imageVideoBOList.size(); i++) {
            ImageVideoBO imageVideoBO = imageVideoBOList.get(i);
            String imageDetailUrl = imageVideoBO.getUrl();
            openPageAndWaitLoading(imageDetailUrl, By.xpath("//img[@class='FFVAD']"), "图片详情页面");
            Set<String> imageSet = getImageDetail();
            LogUtils.print("获取到封面图片 %s", imageSet);
            WebElement nextEl;
            while (true) {
                try {
                    nextEl = webDriver.findElement(By.xpath("//div[@class='    coreSpriteRightChevron  ']"));
                    LogUtils.print("next page");
                } catch (NoSuchElementException e) {
                    break;
                }
                nextEl.click();
                sleep(1000);
                imageSet.addAll(getImageDetail());
                LogUtils.print("获取到下一页图片 %s", imageSet);
            }

            imageSet.removeIf(e -> StringUtils.isBlank(e));

            LogUtils.print("一共" + imageSet.size() + "个图片需要下载");
            if (imageSet.size() == 0) {
                return ;
            }

            ImageDownload imageDownload = new ImageDownload();
            List<String> downUrlList = Lists.newArrayList(imageSet);
            if (imageVideoBO.getContent().length() > 30) {
                throw new RuntimeException(imageVideoBO.getContent().length() + " 标题太长");
            }
            if (imageSet.size() == 1) {
                imageDownload.dowloadImage(BATCH_UPDATE_PATH, downUrlList.get(0), imageVideoBO.getContent());
            } else {
                for (int j = 0; j < imageSet.size(); j++) {
                    imageDownload.dowloadImage(BATCH_UPDATE_PATH, downUrlList.get(j), imageVideoBO.getContent() + "-" + j);
                }
            }
        }

        for (int i = 0; i < videoBOList.size(); i++) {
            try {
                ImageVideoBO imageVideoBO = videoBOList.get(i);
                openPage(imageVideoBO.getUrl());
                List<WebElement> videoElList = getVideoElList();
                LogUtils.print("一共" + videoElList.size() + "个视频需要下载");
                if (videoElList.size() == 1) {
                    FileUtils.copyURLToFile(new URL(videoElList.get(0).getAttribute("src")), new File(BATCH_UPDATE_PATH + imageVideoBO.getContent() +".mp4"));
                } else {
                    /*for (int j = 0; j < videoElList.size(); j++) {
                        FileUtils.copyURLToFile(new URL(videoElList.get(i).getAttribute("src")), new File(BATCH_UPDATE_PATH + imageVideoBO.getContent() + "-" + j +".mp4"));
                    }*/
                    // 只取一个
                    FileUtils.copyURLToFile(new URL(videoElList.get(0).getAttribute("src")), new File(BATCH_UPDATE_PATH + imageVideoBO.getContent()  +".mp4"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        closeDriver();
    }

    private List<ImageVideoBO> convert2ImageVideo(String readFileToString) {
        String[] split = readFileToString.split("\n");

        if (split.length == 0 || StringUtils.isBlank(readFileToString.replace(" ", ""))) {
            return Lists.newArrayList();
        }
        List<ImageVideoBO> imageVideoBOList = Lists.newArrayList();
        for (String s : split) {
            ImageVideoBO imageVideoBO = new ImageVideoBO();
            String[] content = s.split("@");
            if (content.length == 2) {
                imageVideoBO.setUrl(content[0]);
                imageVideoBO.setContent(content[1]);
            } else {
                // 可能是从手机来的
                content = s.split(" ");
                imageVideoBO.setUrl(content[0]);
                imageVideoBO.setContent(content[content.length - 1]);
            }

            imageVideoBOList.add(imageVideoBO);
        }
        return imageVideoBOList;
    }

}
