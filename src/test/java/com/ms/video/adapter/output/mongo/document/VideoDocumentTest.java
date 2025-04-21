package com.ms.video.adapter.output.mongo.document;


import com.ms.video.adapter.output.mongo.document.VideoDocument;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class VideoDocumentTest {

    @Test
    void shouldCreateVideoDocumentAndSetAllFields() {
        LocalDateTime now = LocalDateTime.now();
        VideoDocument doc = new VideoDocument(
                "Título",
                "https://video.mp4",
                "client-123",
                "RECEIVED",
                15,
                null,
                now
        );
        doc.setId("abc-123");
        doc.setUrlZip("https://video-zip.zip");
        doc.setUpdatedAt(now.plusHours(1));
        assertThat(doc.getId()).isEqualTo("abc-123");
        assertThat(doc.getTitle()).isEqualTo("Título");
        assertThat(doc.getUrl()).isEqualTo("https://video.mp4");
        assertThat(doc.getClientId()).isEqualTo("client-123");
        assertThat(doc.getStatus()).isEqualTo("RECEIVED");
        assertThat(doc.getSecondsPartition()).isEqualTo(15);
        assertThat(doc.getCreatedAt()).isEqualTo(now);
        assertThat(doc.getUrlZip()).isEqualTo("https://video-zip.zip");
        assertThat(doc.getUpdatedAt()).isEqualTo(now.plusHours(1));
    }
}
