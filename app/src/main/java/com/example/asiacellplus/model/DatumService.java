package com.example.asiacellplus.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DatumService implements Serializable {



    @SerializedName("item")
    @Expose
    private String item;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("sub_data")
    @Expose
    private String subData;
    @SerializedName("sub_no")
    @Expose
    private String subNo;
    @SerializedName("unsub_data")
    @Expose
    private String unsubData;
    @SerializedName("unsub_no")
    @Expose
    private String unsubNo;
    @SerializedName("custom")
    @Expose
    private String custom;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("youtube_url")
    @Expose
    private String youtubeUrl;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getSubData() {
        return subData;
    }

    public void setSubData(String subData) {
        this.subData = subData;
    }

    public String getSubNo() {
        return subNo;
    }

    public void setSubNo(String subNo) {
        this.subNo = subNo;
    }

    public String getUnsubData() {
        return unsubData;
    }

    public void setUnsubData(String unsubData) {
        this.unsubData = unsubData;
    }

    public String getUnsubNo() {
        return unsubNo;
    }

    public void setUnsubNo(String unsubNo) {
        this.unsubNo = unsubNo;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }
}
