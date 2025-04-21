package com.ms.video.domain.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VideoPersistenceExceptionTest {

    @Test
    void shouldCreateExceptionWithMessageAndCause() {
        Throwable cause = new RuntimeException("Erro original");
        VideoPersistenceException exception = new VideoPersistenceException("Falha ao persistir vídeo", cause);

        assertThat(exception)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Falha ao persistir vídeo")
                .hasCause(cause);
    }
}
