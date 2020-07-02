package com.auto.pojo;

/**
 * @author : jihai
 * @date : 2020/7/1
 * @description :
 */
public class UploadBO {

    public static final Integer IMAGE = 0;
    public static final Integer VIDEO = 1;
    /**
     * type 0-图片，1-视频
     */
    private Integer type;

    /**
     * url
     */
    private String name;

    /**
     * 输入内容
     */
    private String content;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
