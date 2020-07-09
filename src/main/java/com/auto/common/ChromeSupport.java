package com.auto.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/6/23
 * @description :
 */
public abstract class ChromeSupport {

    protected static final String YOUTUBE_PATH_PREPARE_PUBLISH = "/Users/mapeichuan/Downloads/auto-earn-dowload/video/preparePublish/";;
    protected static final String BATCH_UPDATE_PATH = "/Users/mapeichuan/Downloads/auto-earn-dowload/batch/";

    protected static final String homeDir = System.getProperty("user.home");

    protected static JSONObject secretKey;

    static {
        /**
         * 底层socket代理
         */
        LogUtils.print("proxy mode");
        PingSupport.setProxyConfig();

        String json;
        try {
            json = FileUtils.readFileToString(new File(homeDir + "/secret.json"), Charset.defaultCharset());
        } catch (IOException e) {
            LogUtils.print("找不到密码配置文件");
            throw new RuntimeException(e);
        }
        secretKey = JSON.parseObject(json);
    }

    /**
     * 根据网络环境的等待时间
     */
    public static final long PAEG_LOADING_WAITING_TIME = 4000;

    public static final String INS_PATH = "/Users/mapeichuan/Downloads/auto-earn-dowload/image/";

    public static final String YOUTUBE_PATH_ORIGIN = "/Users/mapeichuan/Downloads/auto-earn-dowload/video/origin/";

    public static final long SHORT_WAITING_LOADING = 1000;

    public WebDriver webDriver;

    public static void clearImage() {
        File imageDir =new File(INS_PATH);
        File[] files = imageDir.listFiles();
        if (files.length > 0) {
            for (File file : files) {
                try {
                    LogUtils.print("删除文件 %s", file.getName());
                    file.delete();
                } catch (Exception e) {
                    LogUtils.errorPrint(e, "删除文件错误");
                }
            }
        }
    }

    public static void clearBatchDir() {
        File file = new File(BATCH_UPDATE_PATH);
        File[] files = file.listFiles();
        if (files.length == 0) {
            return ;
        }
        for (File f : files) {
            f.delete();
        }
    }

    public void init() throws IOException {
        System.setProperty("webdriver.chrome.driver", "/Users/mapeichuan/github/chromedriver");
        ChromeDriverService chromeDriverService = new ChromeDriverService.Builder().usingPort(8080).build();
        chromeDriverService.start();
        webDriver = new ChromeDriver(chromeDriverService);
    }

    /**
     * 默认等待
     * @param url
     */
    protected void openPage(String url) {
        LogUtils.print("跳转页面 " + url);
        webDriver.get(url);
        waitLongLoading();
    }

    protected void openPageAndWaitLoading(String url, By by, String pageName) {
        LogUtils.print("跳转页面 " + url);
        webDriver.get(url);
        checkPageStatus(by, pageName);
    }

    protected void openPageAndWaitLoading(String url, List<By> byList, String pageName) {
        LogUtils.print("跳转页面 " + url);
        webDriver.get(url);
        byList.forEach(by -> checkPageStatus(by, pageName));
    }

    protected void openPage(String url,Integer waitCost) {
        webDriver.get(url);
        if (waitCost != null) {
            sleep(waitCost);
        } else {
            waitLongLoading();
        }
    }

    public void closeDriver() {
        try {
            webDriver.close();
        } catch (RuntimeException e) {
            LogUtils.errorPrint(e ," 关闭浏览器失败");
        }
    }

    public void navigatePageAndWaitRefresh(String url) {
        webDriver.navigate().to(url);
        waitLongLoading();
    }


    public void waitLongLoading() {
        LogUtils.print("will waiting page loading %s", PAEG_LOADING_WAITING_TIME);
        sleep(PAEG_LOADING_WAITING_TIME);
    }

    protected void sleep(long costTime) {
        try {
            LogUtils.print("will sleep %s", costTime);
            Thread.sleep(costTime);
        } catch (InterruptedException e) {
            LogUtils.print("{} wait is interruptted {}", Thread.currentThread().getName(), costTime);
        }
    }

    protected void shortWait() {
        try {
            LogUtils.print("will waiting short wait %s", SHORT_WAITING_LOADING);
            Thread.sleep(SHORT_WAITING_LOADING);
        } catch (InterruptedException e) {
            e.printStackTrace();
            LogUtils.errorPrint(e, "short wait error");
        }
    }

    protected void checkPageStatus(By by, String pageName) {
        try {
            WebElement element = webDriver.findElement(by);
            // 有元素了再等待1s
            sleep(1000);
            return ;
        } catch (NoSuchElementException e) {
            LogUtils.print(pageName + "仍在加载中....");
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            checkPageStatus(by, pageName);
        }
    }

}
