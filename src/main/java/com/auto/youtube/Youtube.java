package com.auto.youtube;

import com.auto.common.ChromeSupport;
import com.auto.common.LogUtils;
import com.github.kiulian.downloader.OnYoutubeDownloadListener;
import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.YoutubeException;
import com.github.kiulian.downloader.model.YoutubeVideo;
import com.github.kiulian.downloader.model.formats.AudioFormat;
import com.github.kiulian.downloader.model.formats.AudioVideoFormat;
import com.github.kiulian.downloader.model.formats.Format;
import com.github.kiulian.downloader.model.formats.VideoFormat;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : jihai
 * @date : 2020/6/24
 * @description :
 */
public class Youtube extends ChromeSupport {

    public void downloadVideo(String videoId) throws IOException, YoutubeException {
        downloadVideo(videoId, "", true);
    }

    public void downloadVideo(String videoId, String fileName) throws IOException, YoutubeException {
        downloadVideo(videoId, fileName, true);
    }

    public void downloadVideo(String videoId, String fileName, boolean sync) throws IOException, YoutubeException {
        // init downloader
        LogUtils.print("start to download");
        YoutubeDownloader downloader = new YoutubeDownloader();
        YoutubeVideo video = downloader.getVideo(videoId);

        printFormat(video);
        FormatQuantityGet formatQuantityGet = new AudioVideoQuantityGet();
        List<Format> formatList = formatQuantityGet.getHigh(video.formats());

        for (Format fmt : formatList) {
            if (StringUtils.isBlank(fileName)) {
                video.download(fmt, new File(YOUTUBE_PATH_ORIGIN));
            } else {
                video.download(fmt, new File(YOUTUBE_PATH_ORIGIN), fileName);
            }
        }
        LogUtils.print("finished..");

    }


    public void downloadVideo(String videoId, String fileName, int piexl) throws IOException, YoutubeException {
        LogUtils.print("download direct pixel %s", piexl);
        if (piexl <= 720) {
            downloadVideo(videoId, fileName);
            return ;
        }

        YoutubeDownloader youtubeDownloader = new YoutubeDownloader();
        YoutubeVideo video = youtubeDownloader.getVideo(videoId);
        // 分别下载并合并
        VideoFormat videoFormat = getFormat(video, piexl);
        LogUtils.print("开始下载清晰度为 %s 的视频 %s", piexl, fileName);
        video.download(videoFormat, new File(YOUTUBE_PATH_ORIGIN), fileName);
    }

    private VideoFormat getFormat(YoutubeVideo video, int piexl) {
        for (Format format : video.formats()) {
            if (format instanceof VideoFormat) {
                VideoFormat vf = (VideoFormat) format;

                if (vf.qualityLabel().contains(String.valueOf(piexl))) {
                    return vf;
                }
            }
        }
        throw new RuntimeException("找不到指定分辨率");
    }

    public void downloadAudio(String videoId, String fileName) throws IOException, YoutubeException {
        YoutubeDownloader youtubeDownloader = new YoutubeDownloader();
        YoutubeVideo video = youtubeDownloader.getVideo(videoId);
        // 分别下载并合并
        AudioFormat audioFormat = null;
        for (Format format : video.formats()) {
            if (format instanceof AudioFormat) {
                AudioFormat af = (AudioFormat) format;
                if (af.audioQuality().name().equals("medium")) {
                    audioFormat = af;
                    break;
                }
            }
        }

        LogUtils.print("开始下载 %s 的音频 %s", fileName, audioFormat);
        video.download(audioFormat, new File(YOUTUBE_PATH_ORIGIN), fileName);
    }

    public void mergeAudioAndVideo(String fileName, String audioName) {

    }

    interface FormatQuantityGet {
        <T extends Format>  List<T> getHigh(List<T> originList);
    }

    class AudioVideoQuantityGet implements FormatQuantityGet {
        @Override
        public <T extends Format> List<T> getHigh(List<T> originList) {
            List<T> collect = originList.stream().filter(e -> e instanceof AudioVideoFormat).collect(Collectors.toList());
            Collections.sort(collect, (o1, o2) -> {
                AudioVideoFormat audioVideo1 = (AudioVideoFormat) o1;
                AudioVideoFormat audioVideo2 = (AudioVideoFormat) o2;
                Integer quantity1 = Integer.valueOf(audioVideo1.qualityLabel().replace("p", ""));
                Integer quantity2 = Integer.valueOf(audioVideo2.qualityLabel().replace("p", ""));
                if (quantity1 > quantity2) {
                    return -1;
                } else if (quantity1.equals(quantity2)){
                    return 0;
                }
                return 1;
            });
            return Lists.newArrayList(collect.get(0));
        }
    }
    // low medium high
    class AudioQuantityGet implements FormatQuantityGet {
        @Override
        public <T extends Format> List<T> getHigh(List<T> originList) {
            List<T> collect = originList.stream().filter(e -> e instanceof AudioFormat).collect(Collectors.toList());
            Collections.sort(collect, (o1, o2) -> {
                AudioFormat audio1 = (AudioFormat) o1;
                AudioFormat audio2 = (AudioFormat) o2;
                String quantity1 = audio1.audioQuality().name();
                String quantity2 = audio2.audioQuality().name();
                if (quantity1.equals(quantity2)) {
                    return 0;
                } else if (quantity2.contains("medium")) {
                    if (quantity1.contains("low")) {
                        return 1;
                    }
                } else if (quantity2.contains("high")) {
                    if (quantity1.contains("low") || quantity1.contains("medium")) {
                        return 1;
                    }
                }
                return -1;
            });
            return Lists.newArrayList(collect.get(0));
        }
    }
    class VideoQuantityGet implements FormatQuantityGet {
        @Override
        public <T extends Format> List<T> getHigh(List<T> originList) {
            List<T> collect = originList.stream().filter(e -> e instanceof VideoFormat).collect(Collectors.toList());
            Collections.sort(collect, (o1, o2) -> {
                VideoFormat audioVideo1 = (VideoFormat) o1;
                VideoFormat audioVideo2 = (VideoFormat) o2;
                Integer quantity1 = Integer.valueOf(audioVideo1.qualityLabel().replace("p", ""));
                Integer quantity2 = Integer.valueOf(audioVideo2.qualityLabel().replace("p", ""));
                if (quantity1 > quantity2) {
                    return -1;
                } else if (quantity1.equals(quantity2)){
                    return 0;
                }
                return 1;
            });
            VideoFormat videoFormat = (VideoFormat) collect.get(0);
            String highDesc = videoFormat.qualityLabel();
            return originList.stream().filter(e -> ((VideoFormat) e).qualityLabel().equals(highDesc)).collect(Collectors.toList());
        }
    }

    /**
     * get highest quantity
     * @param video
     * @return
     */
    private void printFormat(YoutubeVideo video) {
        List<Format> formats = video.formats();
        List<String> audioVideoFormatDesc = Lists.newArrayList();
        List<String> videoFormatDesc = Lists.newArrayList();
        List<String> audioFormatDesc = Lists.newArrayList();

        AudioVideoFormat highFormat = null;
        for (Format format : formats) {
            if (format instanceof AudioVideoFormat) {
                AudioVideoFormat audioVideoFormat = (AudioVideoFormat) format;
                String quantity = audioVideoFormat.qualityLabel();
                audioVideoFormatDesc.add(quantity);
            } else if (format instanceof VideoFormat) {
                String str = ((VideoFormat) format).qualityLabel();
                videoFormatDesc.add(str);
            } else if (format instanceof AudioFormat) {
                String name = ((AudioFormat) format).audioQuality().name();
                audioFormatDesc.add(name);
            }
        }
        LogUtils.print("解析到可以下载到完整格式:%s", audioVideoFormatDesc);
        LogUtils.print("解析到可以下载到单纯视频格式:%s", videoFormatDesc);
        LogUtils.print("解析到可以下载到单纯音频格式:%s", audioFormatDesc);
    }

    private void asyncDownload(YoutubeVideo video, List<VideoFormat> videoFormats, File outputDir) throws IOException, YoutubeException {
        video.downloadAsync(videoFormats.get(0), outputDir, new OnYoutubeDownloadListener() {
            @Override
            public void onDownloading(int progress) {
                System.out.printf("Downloaded %d%%\n", progress);
            }

            @Override
            public void onFinished(File file) {
                System.out.println("Finished file: " + file);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error: " + throwable.getLocalizedMessage());
            }
        });
    }
}
