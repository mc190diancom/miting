package com.feidi.template.mvp.model;

import android.widget.ImageView;

public class LiveChatRoomDataBean {

    private String imgAdr;

    private String liveChatTitle;

    private String liveChatContent;

    private int status;//是否接待中....

    public String getImgAdr() {
        return imgAdr;
    }

    public void setImgAdr(String imgAdr) {
        this.imgAdr = imgAdr;
    }

    public String getLiveChatTitle() {
        return liveChatTitle;
    }

    public void setLiveChatTitle(String liveChatTitle) {
        this.liveChatTitle = liveChatTitle;
    }

    public String getLiveChatContent() {
        return liveChatContent;
    }

    public void setLiveChatContent(String liveChatContent) {
        this.liveChatContent = liveChatContent;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LiveChatRoomDataBean() {
    }

    public LiveChatRoomDataBean(String imgAdr, String liveChatTitle, String liveChatContent, int status) {
        this.imgAdr = imgAdr;
        this.liveChatTitle = liveChatTitle;
        this.liveChatContent = liveChatContent;
        this.status = status;
    }
}
