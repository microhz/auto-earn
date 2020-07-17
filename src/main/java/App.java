import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auto.bytedance.ByteDance;
import com.auto.common.ChromeSupport;
import com.auto.common.LogUtils;
import com.auto.instagram.InstagramClient;
import com.auto.twitter.Twitter;
import com.auto.youtube.Youtube;
import com.github.kiulian.downloader.YoutubeException;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.List;

/**
 * @author : jihai
 * @date : 2020/6/19
 * @description : 专门用来测试各种工具
 */
public class App {


    // TODO ins 视频下载，自动翻页还没做
    // TODO 真正自动化， 无需干预，部署到服务器上
    // TODO 自动打开运营后台查看
    // TODO  大视频文件 SSL peer shut down incorrectly 问题
    // TODO 单个视频页面多视频问题
    // TODO 视频素材卖钱
    // TODO 木马病毒
    // TODO 刷单赚钱?
    // TODO 搭建wifi进行嗅探
    // TODO 养粉丝卖钱
    // 视频批量
    public static void main(String[] args) {
        try {
            ChromeSupport.clearImage();
            //https://www.instagram.com/p/CBh26p6pvBq/?utm_source=ig_web_button_share_sheet
            ByteDance.setAccount("zn");
            // 下载instagram图片
//            downloadInstagram("carmaterial");

//            downloadInstagramImageDetail("https://www.instagram.com/p/CCEKsz9okM_/", "tail");

//            // 上传头条, 图片不需要前缀
//            updateToutaioImage(Lists.newArrayList("tail"), "#兰博基尼 野牛出圈");

            autoDownloadAndUpload();


//            testDT();

//            // 下载youtube视频
//            downloadYoutube("PkkV1vLHUvQ", "冲绳", 2160);
//            PingSupport.ping("www.baidu.com");
//
//            // 上传头条视频 TODO 下载进度条
//            updateToutiaoVideo("印度6x6", "奔跑在印度街头的奔驰6x6，周围的车都是弟弟", true);
//            https://www.instagram.com/p/CB8suMbB4EW/?utm_source=ig_web_button_share_sheet

//            downLoadInstagramVideo("https://www.instagram.com/p/CCEFvdWHSre/?utm_source=ig_web_button_share_sheet", "v12");
//
//            updateToutiaoVideo("v12-0", "这兰博基尼自动钥匙如何，感觉太酷炫了", true);

//            downloadTwitterImage("thesupercar_sqd,1275698368728178688", "2");

//            updateToutaioImage("2", "#BMW 这尾巴气势逼人啊");

//             batchUpdate(5, "");

        } catch (Throwable e) {
            LogUtils.errorPrint(e, "系统异常");
        }

    }

    private static void testDT() {

        Integer[] arara= new Integer[10];
        System.out.println(arara.length);
    }

    /**
     * 根据配置文件
     */
    private static void autoDownloadAndUpload() {
        try {
            ChromeSupport.clearBatchDir();

            InstagramClient instagramClient = new InstagramClient();
            instagramClient.batchDownloadFromConfig();

            ByteDance byteDance = new ByteDance();
            byteDance.uploadFromConfig();

            LogUtils.print("上传完毕");
        } catch (Exception e) {
            LogUtils.errorPrint(e, "批量上传失败");
        }
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
        InstagramClient instagramClient = new InstagramClient();
        instagramClient.downloadDetail(imageDetailUrl.split("/")[4], fileName);
    }

    private static void downLoadInstagramVideo(String videlPageUrl, String fileName) {
        InstagramClient instagramClient = new InstagramClient();
        instagramClient.downloadVideo(videlPageUrl.split("/")[4], fileName);

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
        InstagramClient instagramClient = new InstagramClient();
        instagramClient.downloadUserPage(userPage);
    }
}
