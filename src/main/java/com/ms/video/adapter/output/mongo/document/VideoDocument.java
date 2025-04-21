package com.ms.video.adapter.output.mongo.document;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "videos")
public class VideoDocument {

    @Id
    private String id;

    private String title;
    private String url;
    private String urlZip;
    private Integer secondsPartition;
    private String clientId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected VideoDocument() {
    }

    public VideoDocument(String title, String url, String clientId, String status, Integer secondsPartition,String urlZip, LocalDateTime createdAt) {
        this.title = title;
        this.url = url;
        this.clientId = clientId;
        this.status = status;
        this.secondsPartition = secondsPartition;
        this.urlZip = urlZip;
        this.createdAt = createdAt;
        this.updatedAt = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getUrlZip() {
        return urlZip;
    }

    public void setUrlZip(String urlZip) {
        this.urlZip = urlZip;
    }

    public Integer getSecondsPartition() {
        return secondsPartition;
    }

    public void setSecondsPartition(Integer secondsPartition) {
        this.secondsPartition = secondsPartition;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
