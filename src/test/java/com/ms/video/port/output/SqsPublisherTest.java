package com.ms.video.port.output;

import com.ms.video.domain.model.Video;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class SqsPublisherTest {

    @Test
    void shouldSupportLambdaOrAnonymousImplementation() {
        SqsPublisher publisher = video -> {
            System.out.println("Enviando vídeo " + video.getTitle());
        };

        Video video = new Video("Título", "url", "user", 10);

        assertThatCode(() -> publisher.publish(video)).doesNotThrowAnyException();
    }
}
