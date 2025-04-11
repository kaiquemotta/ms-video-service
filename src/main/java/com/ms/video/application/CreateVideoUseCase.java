package com.ms.video.application;


import com.ms.video.domain.model.Video;
import com.ms.video.port.output.SqsPublisher;
import com.ms.video.port.output.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CreateVideoUseCase {

    private final VideoRepository videoRepository;
    private final SqsPublisher sqsPublisher;

    public CreateVideoUseCase(VideoRepository videoRepository, SqsPublisher sqsPublisher) {
        this.videoRepository = videoRepository;
        this.sqsPublisher = sqsPublisher;
    }

    public void execute(String title, MultipartFile file) {
        // TODO: salvar arquivo e gerar URL (ex: em disco local, S3, etc.)
        String generatedUrl = "https://videos.local/" + file.getOriginalFilename();
        Video video = new Video(title, generatedUrl);
        videoRepository.save(video);
        sqsPublisher.publish(video);
    }
}