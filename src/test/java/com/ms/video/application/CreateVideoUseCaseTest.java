package com.ms.video.application;

import com.ms.video.domain.exception.VideoPersistenceException;
import com.ms.video.domain.exception.VideoPublishException;
import com.ms.video.domain.exception.VideoUploadException;
import com.ms.video.domain.model.Video;
import com.ms.video.port.output.SqsPublisher;
import com.ms.video.port.output.VideoRepository;
import com.ms.video.port.output.VideoStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateVideoUseCaseTest {

    private VideoRepository videoRepository;
    private SqsPublisher sqsPublisher;
    private VideoStorage videoStorage;
    private CreateVideoUseCase useCase;

    @BeforeEach
    void setUp() {
        videoRepository = mock(VideoRepository.class);
        sqsPublisher = mock(SqsPublisher.class);
        videoStorage = mock(VideoStorage.class);
        useCase = new CreateVideoUseCase(videoRepository, sqsPublisher, videoStorage);
    }

    @Test
    void shouldCreateVideoSuccessfully() {
        MultipartFile mockFile = mock(MultipartFile.class);
        String uploadedUrl = "https://s3.amazonaws.com/video.mp4";
        String savedId = "123";

        when(videoStorage.upload(mockFile)).thenReturn(uploadedUrl);
        when(videoRepository.save(any())).thenReturn(savedId);

        Video video = useCase.createVideo("Título", "client-id", 15, mockFile);

        assertThat(video.getTitle()).isEqualTo("Título");
        assertThat(video.getUrl()).isEqualTo(uploadedUrl);
        assertThat(video.getClientId()).isEqualTo("client-id");
        assertThat(video.getId()).isEqualTo(savedId);

        verify(videoStorage).upload(mockFile);
        verify(videoRepository).save(any(Video.class));
        verify(sqsPublisher).publish(any(Video.class));
    }

    @Test
    void shouldThrowVideoUploadExceptionWhenUploadFails() {
        MultipartFile mockFile = mock(MultipartFile.class);
        when(videoStorage.upload(mockFile)).thenThrow(new RuntimeException("S3 down"));

        assertThatThrownBy(() -> useCase.createVideo("Título", "client-id", 15, mockFile))
                .isInstanceOf(VideoUploadException.class)
                .hasMessageContaining("Falha ao enviar vídeo para o S3");

        verify(videoStorage).upload(mockFile);
        verifyNoInteractions(videoRepository, sqsPublisher);
    }

    @Test
    void shouldThrowVideoPersistenceExceptionWhenSaveFails() {
        MultipartFile mockFile = mock(MultipartFile.class);
        when(videoStorage.upload(mockFile)).thenReturn("url");
        when(videoRepository.save(any())).thenThrow(new RuntimeException("MongoDB down"));

        assertThatThrownBy(() -> useCase.createVideo("Título", "client-id", 15, mockFile))
                .isInstanceOf(VideoPersistenceException.class)
                .hasMessageContaining("Falha ao salvar o vídeo");

        verify(videoStorage).upload(mockFile);
        verify(videoRepository).save(any(Video.class));
        verifyNoMoreInteractions(sqsPublisher);
    }

    @Test
    void shouldThrowVideoPublishExceptionWhenPublishFails() {
        MultipartFile mockFile = mock(MultipartFile.class);
        when(videoStorage.upload(mockFile)).thenReturn("url");
        when(videoRepository.save(any())).thenReturn("123");
        doThrow(new RuntimeException("SQS off")).when(sqsPublisher).publish(any());

        assertThatThrownBy(() -> useCase.createVideo("Título", "client-id", 15, mockFile))
                .isInstanceOf(VideoPublishException.class)
                .hasMessageContaining("Falha ao enviar o vídeo para o SQS");

        verify(videoStorage).upload(mockFile);
        verify(videoRepository).save(any(Video.class));
        verify(sqsPublisher).publish(any(Video.class));
    }
}
