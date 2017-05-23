package com.qanzone.mypreciousgift.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by 10243 on 2017/5/6.
 */

public class NetVideoData extends BmobObject {
    /**
     * 直播源的url
     */
    private String videoUrl;
    /**
     * 高清视频源
     */
    private String videoHdUrl;
    /**
     * 直播源的名称
     */
    private String videoName;
    /**
     * 直播源的类型
     * == 1 电视直播
     * == 2 轮播台
     */
    private int videoType;
    /**
     * 轮播视频的类型 保留字段 暂未定义
     */
    private int lunboType;

    //### get、set方法 ###
    public String getVideoHdUrl() {
        return videoHdUrl;
    }

    public void setVideoHdUrl(String videoHdUrl) {
        this.videoHdUrl = videoHdUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getVideoName() {
        return videoName;
    }

    public int getVideoType() {
        return videoType;
    }

    public int getLunboType() {
        return lunboType;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public void setVideoType(int videoType) {
        this.videoType = videoType;
    }

    public void setLunboType(int lunboType) {
        this.lunboType = lunboType;
    }

}
