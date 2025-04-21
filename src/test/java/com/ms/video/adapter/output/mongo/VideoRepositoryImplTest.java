package com.ms.video.adapter.output.mongo;

import com.ms.video.adapter.output.mongo.document.VideoDocument;
import com.ms.video.domain.model.Video;
import com.ms.video.port.output.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class VideoRepositoryImplTest {

    private SpringDataVideoRepository springDataVideoRepository;
    private VideoRepository videoRepository;

    @BeforeEach
    void setup() {
        springDataVideoRepository = mock(SpringDataVideoRepository.class);
        videoRepository = new VideoRepositoryImpl(springDataVideoRepository);
    }

    @Test
    void shouldReturnVideosByClientId() {
        VideoDocument doc = new VideoDocument("Título", "url", "client-123", "RECEIVED", 10,"", LocalDateTime.now());
        doc.setId("abc123");

        when(springDataVideoRepository.findByClientId("client-123"))
                .thenReturn(List.of(doc));

        List<Video> videos = videoRepository.findByClientId("client-123");

        assertThat(videos).hasSize(1);
        assertThat(videos.get(0).getTitle()).isEqualTo("Título");
        assertThat(videos.get(0).getId()).isEqualTo("abc123");
        verify(springDataVideoRepository, times(1)).findByClientId("client-123");
    }
}
