package com.ms.video.adapter.output.mongo;

import com.ms.video.adapter.output.mongo.document.VideoDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SpringDataVideoRepositoryTest {

    private SpringDataVideoRepository repository;

    @BeforeEach
    void setup() {
        repository = mock(SpringDataVideoRepository.class);
    }

    @Test
    void shouldReturnListWhenClientIdMatches() {
        VideoDocument mockDoc = new VideoDocument(
                "Título",
                "https://video.mp4",
                "client-123",
                "RECEIVED",
                10,
                "",
                LocalDateTime.now()
        );
        mockDoc.setId("abc123");

        when(repository.findByClientId("client-123"))
                .thenReturn(List.of(mockDoc));

        List<VideoDocument> result = repository.findByClientId("client-123");
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Título");
        verify(repository, times(1)).findByClientId("client-123");
    }
}
