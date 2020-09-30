package com.example.asiacellplus.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DatumMelody implements Serializable {



    @SerializedName("item")
    @Expose
    private String item;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("audio")
    @Expose
    private String audio;
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
    @SerializedName("tone_number")
    @Expose
    private String toneNumber;
    @SerializedName("tone_code")
    @Expose
    private String toneCode;
    @SerializedName("tone_mobile")
    @Expose
    private String toneMobile;

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

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
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

    public String getToneNumber() {
        return toneNumber;
    }

    public void setToneNumber(String toneNumber) {
        this.toneNumber = toneNumber;
    }

    public String getToneCode() {
        return toneCode;
    }

    public void setToneCode(String toneCode) {
        this.toneCode = toneCode;
    }

    public String getToneMobile() {
        return toneMobile;
    }

    public void setToneMobile(String toneMobile) {
        this.toneMobile = toneMobile;
    }

}
