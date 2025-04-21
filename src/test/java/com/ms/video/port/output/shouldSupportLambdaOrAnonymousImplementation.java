package com.ms.video.port.output;


import com.ms.video.domain.model.Video;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class VideoEventPublisherTest {

    @Test
    void shouldSupportLambdaOrAnonymousImplementation() {
        VideoEventPublisher publisher = video -> {
            System.out.println("Evento enviado para vídeo: " + video.getId());
        };

        Video video = new Video("Título", "url", "user", 10);
        assertThatCode(() -> publisher.sendVideoUploadedEvent(video))
                .doesNotThrowAnyException();
    }
}
