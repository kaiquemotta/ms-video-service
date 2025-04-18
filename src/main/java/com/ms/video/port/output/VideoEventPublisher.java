package com.ms.video.port.output;

import com.ms.video.domain.model.Video;

public interface VideoEventPublisher {
    void sendVideoUploadedEvent(Video video);
}