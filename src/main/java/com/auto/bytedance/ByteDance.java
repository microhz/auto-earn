package com.auto.bytedance;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auto.common.ChromeSupport;
import com.auto.common.Consumer;
import com.auto.common.ImageDownload;
import com.auto.common.LogUtils;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : jihai
 * @date : 2020/6/23
 * @description :
 */
public class ByteDance extends ChromeSupport {

    private static final String TOUTIAO_PAGE = "https://www.toutiao.com/";
    private static final String CURRENT_ROBOT_TEST_IMAGE = "robot-test";
    private static final String MANAGE_PAGE_URL = "https://mp.toutiao.com/profile_v3/xigua/content-analysis";
    //    private static final String USER_NAME = "13018937935";
    private  static String userName;
    private static String password;

    public static void setAccount(String accountName) {
        String name = null;
        if (accountName.equals("zn")) {
            name = "bytedance-zn";

        } else if (accountName.equals("mpc")) {
            name = "bytedance-mpc";
        } else {
            throw new RuntimeException();
        }
        password = JSON.parseObject(secretKey.get(name).toString()).getString("password");
        userName = JSON.parseObject(secretKey.get(name).toString()).getString("userName");
    }

    public void publicMicroImageInfo(List<String> imageNameList, String content, boolean shutdownBrower) throws IOException, InterruptedException {
        publisHomePageMicro(() -> {
            uploadImage(imageNameList, ChromeSupport.INS_PATH);
        }, content, shutdownBrower);
    }

    public static void setUserName(String userName) {
        ByteDance.userName = userName;
    }

    public void setPassword(String pwd) {
        ByteDance.password = pwd;
    }

    public void publisHomePageMicro(Consumer consumer, String content, boolean shutdownBrower) {
        try {
            init();
            openToutiao();
            login(userName, password);
            consumer.consume();
            updateTextContent(content);
            publish();
            if (shutdownBrower) {
                LogUtils.print("will auto close driver");
                closeDriver();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void publish() {
        WebElement publicEl = webDriver.findElement(By.xpath("//a[text()='发布']"));
        publicEl.click();
        LogUtils.print("published");
        shortWait();
    }

    private void updateTextContent(String content) {
        WebElement inputEl = webDriver.findElement(By.xpath("//textarea[@class='title-input']"));
        inputEl.sendKeys(content);
    }

    private void uploadImage(List<String> imageNameList, String path) {
        WebElement updateEl = webDriver.findElement(By.ById.id("fileElem"));
        // 打开进度条
        WebElement statusEl = webDriver.findElement(By.xpath("//span[@class='show-image-uploader show-uploader']"));
        statusEl.click();

        int size = imageNameList.size();

        int uploadCount = 0;
        for (String e : imageNameList) {
            try {
                LogUtils.print("upload image %s", e);
                String fileName = path + e + ".jpeg";
                File file = new File(fileName);
                if (file.exists()) {
                    if (uploadCount >= 9) {
                        LogUtils.print("最多上传9张,跳出");
                        break;
                    }
                    LogUtils.print("单张图片 %s", e);
                    updateEl.sendKeys(path + e + ".jpeg");
                    checkUploadedSize(++ uploadCount);
                } else {
                    // 多图片
                    int i = 0;
                    while (true) {
                        if (uploadCount >= 9) {
                            LogUtils.print("最多上传9张,跳出");
                            break;
                        }
                        String subFileName = path + e + "-" + i + ".jpeg";
                        File f = new File(subFileName);
                        if (f.exists()) {
                            LogUtils.print("上传子图片 %s", subFileName);
                            updateEl.sendKeys(subFileName);
                            checkUploadedSize(++ uploadCount);
                            i ++;
                            continue;
                        }
                        if (i > 0) {
                            size += (i - 1);
                        }
                        break;
                    }
                }
            } catch (Exception ex) {
                LogUtils.errorPrint(ex, "上传图片" + e + " 失败，但是继续上传");
            }
        }
        if (uploadCount == 0) {
            throw new RuntimeException("no image can upload");
        }
//        checkPageStatus(By.xpath("//p[text()='共 " + size + " 张，还能上传 9 张']"), "上传图片完成");
    }

    private void checkUploadedSize(int size) {
        WebElement element = webDriver.findElement(By.xpath("//p[@class='uploader-meta']"));
        String text = element.getText();
        if (text.contains("共 " + size + " 张")) {
            sleep(1000);
            return ;
        }
        LogUtils.print("还没上传成功，等待一下");
        sleep(200);
        checkUploadedSize(size);
    }

    private void checkImageUploadStatusSuccess(int size) {
        // 共 1 张
        int costTime = 0;
        while (true) {
            WebElement statusEl = webDriver.findElement(By.xpath("//p[@class='uploader-meta']"));
            if (statusEl.getText().contains("共 " + size + " 张")) {
                break;
            }
            shortWait();
            costTime += SHORT_WAITING_LOADING;
            LogUtils.print("耗时" + costTime + "ms, 仍在上传中，请等待");
        }
    }

    private void updateVideo(String videoName, String dirPath) {
        WebElement publicVideoEl = webDriver.findElement(By.xpath("//li[text()='发视频']"));
        publicVideoEl.click();
        // 展示上传状态
        WebElement showStatusEl = webDriver.findElement(By.xpath("//span[@class='show-video-uploader show-uploader']"));
        showStatusEl.click();

        WebElement updateEl = webDriver.findElement(By.ById.id("fileElem"));

        String path;
        if (StringUtils.isBlank(dirPath)) {
            String filePath = YOUTUBE_PATH_PREPARE_PUBLISH + videoName + ".mp4";
            File file = FileUtils.getFile(filePath);
            if (file == null || !file.exists()) {
                LogUtils.print("在编辑后的列表未找到，直接找原始文件 " + videoName);
                path = YOUTUBE_PATH_ORIGIN + videoName + ".mp4";
            } else {
                path = filePath;
            }
        } else {
            path = dirPath + videoName + ".mp4";
        }
        updateEl.sendKeys(path);
        LogUtils.print("uploading video %s to toutiao", videoName);
        checkUploadStatusSuccess();
    }

    private void checkUploadStatusSuccess() {
        int costTime = 0;
        while (true) {
            try {
                WebElement statusEl = webDriver.findElement(By.xpath("//span[@class='upload-status']"));
                if (statusEl.getText().equals("上传成功")) {
                    LogUtils.print("上传成功..");
                    break;
                }
            } catch (NoSuchElementException e) {}
            LogUtils.print("还没上传完成, 已等待 " + costTime + "s");
            sleep(1000);
            costTime += 1;
        }
    }


    public void login(String username, String pwd) {
        WebElement loginButtonEl = webDriver.findElement(By.xpath("//button[@class='login-button']"));
        loginButtonEl.click();
        LogUtils.print("click to login");
        sleep(300);

        // 密码登陆
        WebElement keyPwdPageEl = webDriver.findElement(By.xpath("//img[@class='login-type-icon']"));
        keyPwdPageEl.click();

        sleep(200);

        WebElement usernameEl = webDriver.findElement(By.ById.id("user-name"));
        WebElement passwordEl = webDriver.findElement(By.ById.id("password"));

        usernameEl.sendKeys(username);
        passwordEl.sendKeys(pwd);

        WebElement submitEl = webDriver.findElement(By.ById.id("bytedance-login-submit"));
        submitEl.click();

        // 获取验证码并破解
        super.shortWait();

        while (!killRobotTest()) {
            LogUtils.print("kill robot will retry");
            continue;
        }

        LogUtils.print("login success");
    }

    /**
     * 处理滑动验证码破解
     */
    public boolean killRobotTest() {
        try {
            ImageDownload imageDownload = new ImageDownload();
            WebElement img = getRobotTestImage();
            String imgUrl = img.getAttribute("src");
            imageDownload.dowloadImage(imgUrl, CURRENT_ROBOT_TEST_IMAGE);

            double sliteRate = getSlideRate(CURRENT_ROBOT_TEST_IMAGE);
            moveButton(sliteRate);
        } catch (Throwable e) {
            return true;
        }
        return false;
    }

    private WebElement getRobotTestImage() {
        return webDriver.findElement(By.xpath("//img[@draggable='false']"));
    }

    private void moveButton(double sliteRate) {
        WebElement moveButtonEl = webDriver.findElement(By.xpath("//div[@class='secsdk-captcha-drag-icon sc-cSHVUG lbvrEu']"));
        Actions moveAction = new Actions(webDriver);
        moveAction.clickAndHold(moveButtonEl);

        // 随机滑动步数
        int targetMoveCount = (int) (280 * sliteRate);

        for (Integer count : getRandomStep(targetMoveCount)) {
            moveAction.moveByOffset(count, 0);
            moveAction.perform();
        }
        moveAction.release(moveButtonEl).perform();
        waitLongLoading();
    }

    public List<Integer> getRandomStep(int targetMoveCount) {
        List<Integer> list = Lists.newArrayList();
        while (targetMoveCount > 0) {
            int count = RandomUtils.nextInt(0, targetMoveCount + 1);
            list.add(count);
            targetMoveCount -= count;
        }
        return list;
    }

    private double getSlideRate(String imageName) {
        try {
            BufferedImage image = ImageIO.read(new File(ChromeSupport.INS_PATH + imageName + ".jpeg"));
            int width = image.getWidth();
            int height = image.getHeight();

            List<Integer> widthEdgeList = Lists.newArrayList();

            for (int i = 0;i < width;i ++) {
                for (int j = 0; j < height; j++) {
                    int rgb = image.getRGB(i, j);
                    int redV = (rgb & 0xff0000) >> 16;
                    int greenV = (rgb & 0xff00) >> 8;
                    int blueV = (rgb & 0xff);
                    if (redV > 230 && greenV > 230 && blueV > 230) {
                        widthEdgeList.add(i);
                    }
                }
            }

            // 统计最多的witdh
            Map<Integer, List<Integer>> map = widthEdgeList.stream().collect(Collectors.groupingBy(Integer::intValue));
            ArrayList<List<Integer>> lists = Lists.newArrayList(map.values());

            Collections.sort(lists, (o1, o2) -> {
                if (o1.size() > o2.size()) {
                    return -1;
                } else if (o1.size() < o2.size()) {
                    return 1;
                }
                return 0;
            });
            int leftEdge = lists.get(0).get(0);
            int rightEdge = lists.get(1).get(0);


            int slidePixel = (rightEdge + leftEdge) / 2;
            // 滑动比例
            double rate = Double.valueOf(slidePixel) / Double.valueOf(width);
            // 柔性调整
            return zoomRate(rate);
        } catch (IOException e) {
            LogUtils.errorPrint(e, "get kill robot rate error");
        }
        return 0;
    }

    /**
     * 发现会根据50%的偏移量需要增量
     * @param rate
     * @return
     */
    private double zoomRate(double rate) {
        double originRate = rate;
        if (rate < 0.45) {
            rate -= 0.02;
        }
        if (rate >= 0.45 && rate < 0.6) {
            return rate;
        } else if (rate >= 0.6 && rate < 0.67) {
            rate += 0.02;
        } else if (rate >= 0.67 && rate < 0.75){
            rate += 0.02;
        } else if (rate >= 0.75 && rate < 0.8){
            rate += 0.05;
        } else if (rate > 0.8) {
            rate += 0.07;
        } else if (rate > 0.9) {
            rate += 0.1;
        }
        LogUtils.print("kill robot slide rate %s, zoom rate %s", originRate, rate);
        return rate;

    }

    public void openToutiao() throws InterruptedException {
        openPage(TOUTIAO_PAGE, 0);
    }

    public void updateVideoContent(String videoName, String title, boolean isShutBrower) {
        publisHomePageMicro(() -> {
            updateVideo(videoName, null);
        }, title, isShutBrower);
    }

    public void openManage(String account) {
        try {
            setAccount(account);
            init();
            openToutiao();
            login(userName, password);
            openMangePage();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void openMangePage() {
        navigatePageAndWaitRefresh(MANAGE_PAGE_URL);
    }

    public void uploadFromConfig() throws IOException, InterruptedException {
        init();
        openToutiao();
        login(userName, password);

        File file = FileUtils.getFile(ChromeSupport.BATCH_UPDATE_PATH);
        String[] list = file.list();
        if (list.length == 0) {
            LogUtils.print("没有可以上传的文件");
        }

        ArrayList<String> fileNameList = Lists.newArrayList(list);

        List<String> imageList = fileNameList.stream().filter(e -> e.contains("jpeg")).collect(Collectors.toList());
        List<String> videoList = fileNameList.stream().filter(e -> e.contains("mp4")).collect(Collectors.toList());

        if (imageList.size() != 0) {
            batchUpdateImage(imageList);
        }

        if (videoList.size() != 0) {
            batchUpdateVideo(videoList);
        }
        closeDriver();
    }

    private void batchUpdateVideo(List<String> videoList) {
        LogUtils.print("开始批量上传视频 %s", videoList);
        for (String videoFile : videoList) {
            // 视频只取第一个
            String[] split = videoFile.split("-");
            String fileName = split[0];
            if (split.length > 1) {
                LogUtils.print(split[0] + "存在多个子视频， 只上传一个");
                fileName = fileName + "-0";
            }

            updateVideoContent(ChromeSupport.BATCH_UPDATE_PATH, fileName.replace(".mp4", ""));
            updateTextContent(fileName.replace(".mp4", ""));
            publish();
        }
    }

    private void batchUpdateImage(List<String> imageList) {
        LogUtils.print("开始批量上传图片 %s", imageList);
        ArrayListMultimap<String, String> fileSubListMap = ArrayListMultimap.create();

        for (String image : imageList) {
            String[] split = image.split("-");
            fileSubListMap.put(split[0].replace(".jpeg", ""), image.replace(".jpeg", ""));
        }
        LogUtils.print("需要上传的图片" + fileSubListMap);

        for (Map.Entry<String, Collection<String>> entry : fileSubListMap.asMap().entrySet()) {
            uploadImage(Lists.newArrayList(entry.getValue()), ChromeSupport.BATCH_UPDATE_PATH);
            updateTextContent(entry.getKey());
            publish();

            LogUtils.print("上传成功头条  %s", entry.getKey());
            sleep(1000);
        }
    }

    private void updateVideoContent(String batchUpdatePath, String fileName) {
        updateVideo(fileName, batchUpdatePath);
    }
}
