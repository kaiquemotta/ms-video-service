package com.ms.video.domain.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VideoUploadExceptionTest {

    @Test
    void shouldCreateExceptionWithMessageAndCause() {
        Throwable cause = new RuntimeException("Erro ao enviar para S3");
        VideoUploadException exception = new VideoUploadException("Falha no upload do vídeo", cause);

        assertThat(exception)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Falha no upload do vídeo")
                .hasCause(cause);
    }
}
