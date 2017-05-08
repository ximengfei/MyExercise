package com.qanzone.mypreciousgift.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by admin on 2017/5/8.
 * 意见反馈的数据bean
 */

public class Advice extends BmobObject {

    /**
     * 反馈的内容
     */
    private String content;
    /**
     * 反馈人的联系方式
     */
    private String contact;

    public String getContent() {
        return content;
    }

    public String getContact() {
        return contact;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

//    public Advice(String content, String contact) {
//        this.content = content;
//        this.contact = contact;
//    }
//
//    public Advice() {
//    }
}
