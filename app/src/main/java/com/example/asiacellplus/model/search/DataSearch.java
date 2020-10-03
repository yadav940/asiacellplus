
package com.example.asiacellplus.model.search;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataSearch {

    @SerializedName("item")
    @Expose
    private List<Item> item = null;
    @SerializedName("melody")
    @Expose
    private List<Melody> melody = null;
    @SerializedName("services")
    @Expose
    private List<Service> services = null;

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    public List<Melody> getMelody() {
        return melody;
    }

    public void setMelody(List<Melody> melody) {
        this.melody = melody;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

}
