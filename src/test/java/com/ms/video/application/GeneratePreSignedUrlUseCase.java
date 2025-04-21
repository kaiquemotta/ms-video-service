package com.ms.video.application;

import com.ms.video.port.output.PreSignedUrlGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class GeneratePreSignedUrlUseCaseTest {

    private PreSignedUrlGenerator generator;
    private GeneratePreSignedUrlUseCase useCase;

    @BeforeEach
    void setup() {
        generator = mock(PreSignedUrlGenerator.class);
        useCase = new GeneratePreSignedUrlUseCase(generator);
    }

    @Test
    void shouldGeneratePreSignedUrlWithRandomKey() {
        String originalFilename = "video.mp4";
        String expectedUrl = "https://s3.amazonaws.com/bucket/uuid_video.mp4";
        when(generator.generateUploadUrl(anyString())).thenReturn(expectedUrl);
        GeneratePreSignedUrlUseCase.UploadUrlResponse response = useCase.execute(originalFilename);
        assertThat(response).isNotNull();
        assertThat(response.key()).endsWith("_" + originalFilename);
        assertThat(response.uploadUrl()).isEqualTo(expectedUrl);
        verify(generator, times(1)).generateUploadUrl(response.key());
    }
}
