package com.feidi.template.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class bannerBean implements Parcelable {

    /**
     * uid : rc-upload-1588006041317-6
     * status : done
     * name : timg.jpg
     * url : https://47.57.31.10:8443/odm/file/2020/04/28/998b0a4a1ffe4cfabec324c113dff515.jpg
     */

    private String uid;
    private String status;
    private String name;
    private String url;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uid);
        dest.writeString(this.status);
        dest.writeString(this.name);
        dest.writeString(this.url);
    }

    public bannerBean() {
    }

    protected bannerBean(Parcel in) {
        this.uid = in.readString();
        this.status = in.readString();
        this.name = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<bannerBean> CREATOR = new Parcelable.Creator<bannerBean>() {
        @Override
        public bannerBean createFromParcel(Parcel source) {
            return new bannerBean(source);
        }

        @Override
        public bannerBean[] newArray(int size) {
            return new bannerBean[size];
        }
    };
}
