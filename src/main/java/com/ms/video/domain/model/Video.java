package com.ms.video.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Video {
    private final String id;
    private final String title;
    private final String url;
    private final String clientId;
    private final String status;
    private final LocalDateTime createdAt;

    public Video(String title, String url, String clientId) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.url = url;
        this.clientId = clientId;
        this.status = "RECEIVED";
        this.createdAt = LocalDateTime.now();
    }

    public Video(String id, String title, String url, String clientId, String status, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.clientId = clientId;
        this.status = status;
        this.createdAt = createdAt;
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
}
