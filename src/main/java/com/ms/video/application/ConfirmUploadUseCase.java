package com.ms.video.application;

import com.ms.video.domain.model.Video;
import com.ms.video.port.output.SqsPublisher;
import com.ms.video.port.output.VideoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConfirmUploadUseCase {

    private final VideoRepository videoRepository;
    private final SqsPublisher sqsPublisher;
    private final String bucket;

    public ConfirmUploadUseCase(VideoRepository videoRepository, SqsPublisher sqsPublisher,
                                @Value("${aws.s3.bucket}") String bucket) {
        this.videoRepository = videoRepository;
        this.sqsPublisher = sqsPublisher;
        this.bucket = bucket;
    }

    public Video execute(String title, String key, String clientId) {
        String id = UUID.randomUUID().toString();
        String url = "https://" + bucket + ".s3.amazonaws.com/" + key;

        Video video = new Video(title, url,clientId);
        videoRepository.save(video);
        sqsPublisher.publish(video);

        return video;
    }
}
