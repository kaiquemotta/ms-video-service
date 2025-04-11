package com.ms.video.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Video {

    private final String id;
    private final String title;
    private final String url;
    private final LocalDateTime createdAt;

    public Video(String title, String url) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.url = url;
        this.createdAt = LocalDateTime.now();
    }

    public Video(String id, String title, String url, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}