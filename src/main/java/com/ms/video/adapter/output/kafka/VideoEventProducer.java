package com.ms.video.adapter.output.kafka;

import com.ms.video.domain.model.Video;
import com.ms.video.port.output.VideoEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class VideoEventProducer implements VideoEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public VideoEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendVideoUploadedEvent(Video video) {
        kafkaTemplate.send("video-uploaded", video.getId(), video);
    }
}