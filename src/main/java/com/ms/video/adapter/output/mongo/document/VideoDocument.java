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
    private String clientId;
    private String status;
    private LocalDateTime createdAt;

    protected VideoDocument() {}

    public VideoDocument(String title, String url, String clientId, String status, LocalDateTime createdAt) {
        this.title = title;
        this.url = url;
        this.clientId = clientId;
        this.status = status;
        this.createdAt = createdAt;
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
}
