package com.example.asiacellplus.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class DatumBanner {



    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("hyper_link")
    @Expose
    private String hyperLink;
    @SerializedName("lang_id")
    @Expose
    private String langId;
    @SerializedName("youtube_url")
    @Expose
    private String youtubeUrl;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHyperLink() {
        return hyperLink;
    }

    public void setHyperLink(String hyperLink) {
        this.hyperLink = hyperLink;
    }

    public String getLangId() {
        return langId;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }
}
