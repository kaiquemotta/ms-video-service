package com.ms.video.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VideoUploadedEventTest {

    @Test
    void shouldCreateWithAllFieldsUsingConstructor() {
        String videoId = "vid-123";
        String userId = "user-456";
        String s3Key = "folder/video.mp4";

        VideoUploadedEvent event = new VideoUploadedEvent(videoId, userId, s3Key);

        assertThat(event.getVideoId()).isEqualTo(videoId);
        assertThat(event.getUserId()).isEqualTo(userId);
        assertThat(event.getS3Key()).isEqualTo(s3Key);
    }

    @Test
    void shouldSetAndGetFieldsUsingSetters() {
        VideoUploadedEvent event = new VideoUploadedEvent();

        event.setVideoId("v123");
        event.setUserId("u456");
        event.setS3Key("videos/uploaded.mp4");

        assertThat(event.getVideoId()).isEqualTo("v123");
        assertThat(event.getUserId()).isEqualTo("u456");
        assertThat(event.getS3Key()).isEqualTo("videos/uploaded.mp4");
    }
}
