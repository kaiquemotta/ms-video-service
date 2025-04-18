package com.ms.video.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Video {
    private String id;
    private final String title;
    private final String url;
    private final String clientId;
    private final String status;
    private final Integer secondsPartition;
    private String urlZip;
    private final LocalDateTime createdAt;
    private  LocalDateTime updatedAt;

    public Video(String title, String url, String clientId, Integer secondsPartition) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.url = url;
        this.clientId = clientId;
        this.status = "RECEIVED";
        this.secondsPartition = secondsPartition;
        this.createdAt = LocalDateTime.now();
    }

    public Video(String id, String title, String url, String clientId, String status, Integer secondsPartition, String urlZip, LocalDateTime createdAt, LocalDateTime updatedAt) {        this.id = id;
        this.title = title;
        this.url = url;
        this.clientId = clientId;
        this.status = status;
        this.secondsPartition = secondsPartition;
        this.urlZip = urlZip;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public String getClientId() {
        return clientId;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Integer getSecondsPartition() {
        return secondsPartition;
    }

    public String getUrlZip() {
        return urlZip;
    }

    public void setUrlZip(String urlZip) {
        this.urlZip = urlZip;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
