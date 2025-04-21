package com.ms.video.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class VideoTest {

    @Test
    void shouldCreateVideoWithDefaultConstructor() {
        String title = "Vídeo de teste";
        String url = "https://s3.com/video.mp4";
        String clientId = "client-123";
        int secondsPartition = 15;

        Video video = new Video(title, url, clientId, secondsPartition);

        assertThat(video.getId()).isNotNull();
        assertThat(video.getTitle()).isEqualTo(title);
        assertThat(video.getUrl()).isEqualTo(url);
        assertThat(video.getClientId()).isEqualTo(clientId);
        assertThat(video.getSecondsPartition()).isEqualTo(secondsPartition);
        assertThat(video.getStatus()).isEqualTo("RECEIVED");
        assertThat(video.getCreatedAt()).isNotNull();
        assertThat(video.getUpdatedAt()).isNull();
        assertThat(video.getUrlZip()).isNull();
    }

    @Test
    void shouldCreateVideoWithAllFields() {
        String id = "video-id";
        String title = "Video Completo";
        String url = "https://example.com/video.mp4";
        String clientId = "client-456";
        String status = "COMPLETED";
        Integer secondsPartition = 30;
        String urlZip = "https://example.com/zip.zip";
        LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
        LocalDateTime updatedAt = LocalDateTime.now();

        Video video = new Video(id, title, url, clientId, status, secondsPartition, urlZip, createdAt, updatedAt);

        assertThat(video.getId()).isEqualTo(id);
        assertThat(video.getTitle()).isEqualTo(title);
        assertThat(video.getUrl()).isEqualTo(url);
        assertThat(video.getClientId()).isEqualTo(clientId);
        assertThat(video.getStatus()).isEqualTo(status);
        assertThat(video.getSecondsPartition()).isEqualTo(secondsPartition);
        assertThat(video.getUrlZip()).isEqualTo(urlZip);
        assertThat(video.getCreatedAt()).isEqualTo(createdAt);
        assertThat(video.getUpdatedAt()).isEqualTo(updatedAt);
    }

    @Test
    void shouldUpdateIdUrlZipAndUpdatedAt() {
        Video video = new Video("t", "url", "c", 1);

        video.setId("new-id");
        video.setUrlZip("https://s3.com/zipped.zip");
        LocalDateTime updated = LocalDateTime.now();
        video.setUpdatedAt(updated);

        assertThat(video.getId()).isEqualTo("new-id");
        assertThat(video.getUrlZip()).isEqualTo("https://s3.com/zipped.zip");
        assertThat(video.getUpdatedAt()).isEqualTo(updated);
    }
}
