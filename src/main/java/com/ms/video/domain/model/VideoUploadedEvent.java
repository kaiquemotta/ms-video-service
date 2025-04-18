package com.ms.video.domain.model;

public class VideoUploadedEvent {
    private String videoId;
    private String userId;
    private String s3Key;

    public VideoUploadedEvent() {
    }

    public VideoUploadedEvent(String videoId, String userId, String s3Key) {
        this.videoId = videoId;
        this.userId = userId;
        this.s3Key = s3Key;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getS3Key() {
        return s3Key;
    }

    public void setS3Key(String s3Key) {
        this.s3Key = s3Key;
    }
}