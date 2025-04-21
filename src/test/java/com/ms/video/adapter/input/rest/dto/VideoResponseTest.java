package com.ms.video.adapter.input.rest.dto;

import com.ms.video.domain.model.Video;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class VideoResponseTest {

    @Test
    void shouldMapVideoToVideoResponseCorrectly() {
        String id = "123";
        String title = "Vídeo Teste";
        String url = "https://bucket.s3/video.mp4";
        String clientId = "client-001";
        String status = "COMPLETED";
        Integer secondsPartition = 10;
        String urlZip = "https://bucket.s3/video.zip";
        LocalDateTime createdAt = LocalDateTime.of(2024, 1, 1, 12, 0);
        LocalDateTime updatedAt = LocalDateTime.of(2024, 1, 2, 12, 0);

        Video video = new Video(id, title, url, clientId, status, secondsPartition, urlZip, createdAt, updatedAt);

        VideoResponse response = VideoResponse.from(video);

        assertThat(response.id()).isEqualTo(id);
        assertThat(response.title()).isEqualTo(title);
        assertThat(response.url()).isEqualTo(url);
        assertThat(response.urlZip()).isEqualTo(urlZip);
        assertThat(response.status()).isEqualTo(status);
        assertThat(response.secondsPartition()).isEqualTo("10");
        assertThat(response.createdAt()).isEqualTo(createdAt);
        assertThat(response.updatedAt()).isEqualTo(updatedAt);
    }

    @Test
    void shouldReturnDashWhenUrlZipIsNull() {
        Video video = new Video(
                "id-456",
                "Vídeo sem zip",
                "https://bucket.s3/video2.mp4",
                "client-002",
                "PROCESSING",
                20,
                null,
                LocalDateTime.of(2024, 2, 1, 10, 0),
                LocalDateTime.of(2024, 2, 1, 11, 0)
        );

        VideoResponse response = VideoResponse.from(video);
        assertThat(response.urlZip()).isEqualTo("-");
    }
}
