package com.auto;

import com.auto.bytedance.ByteDance;
import com.auto.common.ChromeSupport;
import com.auto.common.LogUtils;
import com.auto.instagram.Instagram;
import com.auto.twitter.Twitter;
import com.auto.youtube.Youtube;
import com.github.kiulian.downloader.YoutubeException;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/6/19
 * @description : 专门用来测试各种工具
 */
public class App {

    // TODO 头条滑动验证码AI识别破解
    // TODO ins视频分片拼接问题
    // 视频批量
    public static void main(String[] args) {
        try {
            ChromeSupport.clearImage();
            ByteDance.setAccount("jihai");
            autoDownloadAndUpload();
        } catch (Throwable e) {
            LogUtils.errorPrint(e, "系统异常");
        }
    }
    /**
     * 根据配置文件
     */
    private static void autoDownloadAndUpload() {
        ChromeSupport.clearBatchDir();
        ByteDance byteDance = new ByteDance();
        Instagram instagram = new Instagram();
        try {

            instagram.batchDownloadFromConfig();

            byteDance.uploadFromConfig();

            LogUtils.print("上传完毕");
        } catch (Exception e) {
            LogUtils.errorPrint(e, "批量上传失败");
        } finally {
            byteDance.closeDriver();
        }
    }

    private static void downloadYoutube(String url, String fileName, int pixel, String dir) throws Exception {
        String originDir = ChromeSupport.YOUTUBE_PATH_ORIGIN;
        ChromeSupport.YOUTUBE_PATH_ORIGIN = ChromeSupport.YOUTUBE_PATH_ORIGIN + dir + "/";
        createDir(ChromeSupport.YOUTUBE_PATH_ORIGIN);
        downloadYoutube(url, fileName, pixel);
        ChromeSupport.YOUTUBE_PATH_ORIGIN = originDir;
    }

    private static void createDir(String dir) {
        File file = new File(dir);
        if (file.exists()) {
            return ;
        }
        file.mkdir();
    }




    private static void downloadYoutube(String videoId, String fileName, int quantity) throws Exception {
        Youtube youtube = new Youtube();
        String videoName = fileName + "-video";
        youtube.downloadVideo(videoId, videoName, quantity);
        if (quantity <= 720) {
            return ;
        }
        // 下载音频，合并
        String audioName = fileName + "-audio";
        youtube.downloadAudio(videoId, audioName);
        youtube.mergeAudioAndVideo(videoName, audioName);
        youtube.clearRandomFile(videoName, audioName);
    }

    private static List<String> getList(String s, int start, int end) {
        List<String> list = Lists.newArrayList();
        for (int i = start; i <= end; i ++) {
            list.add(s + i);
        }
        return list;
    }


    private static void downloadTwitterImage(String urlId, String fileName) {
        downloadTwitterImage(urlId, fileName, true);
    }

    private static void downloadTwitterImage(String urlId, String fileName, boolean shutdownBrower) {
        Twitter twitter = new Twitter();
        twitter.downloadImage(urlId, fileName, shutdownBrower);
    }

    private static void downloadInstagramImageDetail(String imageDetailUrl, String fileName) {
        Instagram instagram = new Instagram();
        instagram.downloadDetail(imageDetailUrl.split("/")[4], fileName);
    }

    private static void downLoadInstagramVideo(String videlPageUrl, String fileName) {
        Instagram instagram = new Instagram();
        instagram.downloadVideo(videlPageUrl.split("/")[4], fileName);

    }

    private static void updateToutiaoVideo(String videoName, String title, boolean shutdownBrower) {
        try {
            ByteDance byteDance = new ByteDance();
            byteDance.updateVideoContent(videoName, title, shutdownBrower);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void downloadYoutube(String videoId, String fileName) {
        try {
            Youtube youtube = new Youtube();
            youtube.downloadVideo(videoId, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (YoutubeException e) {
            e.printStackTrace();
        }
    }


    /**
     * 自动填充视频标题
     * @param videoId
     */
    private static void downloadYoutube(String videoId) {
        try {
            Youtube youtube = new Youtube();
            youtube.downloadVideo(videoId, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (YoutubeException e) {
            e.printStackTrace();
        }
    }

    private static void updateToutaioImage(String imageName, String content) {
        try {
            ByteDance byteDance = new ByteDance();
            byteDance.publicMicroImageInfo(Lists.newArrayList(imageName), content, true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void updateToutaioImage(List<String> imageNameList, String content) {
        try {
            ByteDance byteDance = new ByteDance();
            byteDance.publicMicroImageInfo(imageNameList, content, true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void downloadInstagram(String userPage) {
        Instagram instagram = new Instagram();
        instagram.downloadUserPage(userPage);
    }
}

