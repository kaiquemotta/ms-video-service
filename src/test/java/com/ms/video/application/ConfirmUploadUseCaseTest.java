package com.ms.video.application;

import com.ms.video.domain.model.Video;
import com.ms.video.port.output.SqsPublisher;
import com.ms.video.port.output.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class ConfirmUploadUseCaseTest {

    private VideoRepository videoRepository;
    private SqsPublisher sqsPublisher;
    private ConfirmUploadUseCase useCase;

    @BeforeEach
    void setUp() {
        videoRepository = mock(VideoRepository.class);
        sqsPublisher = mock(SqsPublisher.class);
        useCase = new ConfirmUploadUseCase(videoRepository, sqsPublisher, "my-bucket");
    }

    @Test
    void shouldCreateVideoWithCorrectDataAndPublishToSqs() {
        String title = "Título do Vídeo";
        String key = "video.mp4";
        String clientId = "client-123";
        int secondsPartition = 10;

        Video video = useCase.execute(title, key, clientId, secondsPartition);

        assertThat(video).isNotNull();
        assertThat(video.getTitle()).isEqualTo(title);
        assertThat(video.getClientId()).isEqualTo(clientId);
        assertThat(video.getUrl()).isEqualTo("https://my-bucket.s3.amazonaws.com/video.mp4");
        assertThat(video.getSecondsPartition()).isEqualTo(secondsPartition);
        assertThat(video.getStatus()).isEqualTo("RECEIVED");

        verify(videoRepository, times(1)).save(video);
        verify(sqsPublisher, times(1)).publish(video);
    }
}
