package com.example.asiacellplus.model.helper;

public class SearchHelperModel {

    /*public enum ServiceType { MELODY,SERVICE }*/
    private String imageUrl;
    private String title;
    private String description;
    private ServiceType serviceType;

    public SearchHelperModel(String imageUrl, String title, String description, ServiceType serviceType) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.serviceType = serviceType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ServiceType getType() {
        return serviceType;
    }
}
