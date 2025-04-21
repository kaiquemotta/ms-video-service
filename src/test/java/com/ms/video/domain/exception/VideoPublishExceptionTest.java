package com.ms.video.domain.exception;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VideoPublishExceptionTest {

    @Test
    void shouldCreateExceptionWithMessageAndCause() {
        Throwable cause = new RuntimeException("Erro na fila");
        VideoPublishException exception = new VideoPublishException("Falha ao publicar vídeo", cause);

        assertThat(exception)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Falha ao publicar vídeo")
                .hasCause(cause);
    }
}
