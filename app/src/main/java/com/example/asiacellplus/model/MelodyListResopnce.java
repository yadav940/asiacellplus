package com.example.asiacellplus.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MelodyListResopnce {



    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<DatumMelody> data = null;
    @SerializedName("message")
    @Expose
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DatumMelody> getData() {
        return data;
    }

    public void setData(List<DatumMelody> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
