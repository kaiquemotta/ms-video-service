package com.ms.video.application;

import com.ms.video.domain.model.Video;
import com.ms.video.port.output.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ListVideosByClientUseCaseTest {

    private VideoRepository videoRepository;
    private ListVideosByClientUseCase useCase;

    @BeforeEach
    void setup() {
        videoRepository = mock(VideoRepository.class);
        useCase = new ListVideosByClientUseCase(videoRepository);
    }

    @Test
    void shouldReturnVideosFromRepositoryByClientId() {
        String clientId = "client-123";
        List<Video> mockVideos = List.of(
                new Video("Título 1", "url1", clientId, 10),
                new Video("Título 2", "url2", clientId, 15)
        );
        when(videoRepository.findByClientId(clientId)).thenReturn(mockVideos);
        List<Video> result = useCase.listByClientId(clientId);
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getClientId()).isEqualTo(clientId);
        verify(videoRepository, times(1)).findByClientId(clientId);
    }
}
