package com.ms.video.port.output;

import com.ms.video.domain.model.Video;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class VideoRepositoryTest {

    @Test
    void shouldAllowSimpleImplementation() {
        VideoRepository repo = new VideoRepository() {
            @Override
            public String save(Video video) {
                return "fake-id";
            }

            @Override
            public List<Video> findByClientId(String clientId) {
                return List.of(new Video("Video Teste", "url", clientId, 15));
            }
        };

        String savedId = repo.save(new Video("Teste", "url", "client-1", 10));
        List<Video> videos = repo.findByClientId("client-1");

        assertThat(savedId).isEqualTo("fake-id");
        assertThat(videos).hasSize(1);
        assertThat(videos.get(0).getClientId()).isEqualTo("client-1");
    }
}
