package com.ms.video.port.output;

import com.ms.video.domain.model.Video;

public interface SqsPublisher {
    void publish(Video video);
}