package com.ms.video.application;

import com.ms.video.domain.exception.VideoPersistenceException;
import com.ms.video.domain.exception.VideoPublishException;
import com.ms.video.domain.exception.VideoUploadException;
import com.ms.video.domain.model.Video;
import com.ms.video.port.output.SqsPublisher;
import com.ms.video.port.output.VideoRepository;
import com.ms.video.port.output.VideoStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CreateVideoUseCase {

    private static final Logger log = LoggerFactory.getLogger(CreateVideoUseCase.class);

    private final VideoRepository videoRepository;
    private final SqsPublisher sqsPublisher;
    private final VideoStorage videoStorage;

    public CreateVideoUseCase(VideoRepository videoRepository, SqsPublisher sqsPublisher, VideoStorage videoStorage) {
        this.videoRepository = videoRepository;
        this.sqsPublisher = sqsPublisher;
        this.videoStorage = videoStorage;
    }

    public Video createVideo(String title,String clientId, MultipartFile file) {
        String s3Url = videoStorage.upload(file);
        Video video = new Video(title, s3Url);
        videoRepository.save(video);
        sqsPublisher.publish(video);
        return video;
    }

    private String uploadFileToS3(MultipartFile file) {
        try {
            return videoStorage.upload(file);
        } catch (Exception e) {
            log.error("Erro ao fazer upload do vídeo", e);
            throw new VideoUploadException("Falha ao enviar vídeo para o S3", e);
        }
    }

    private void saveVideo(Video video) {
        try {
            videoRepository.save(video);
        } catch (Exception e) {
            log.error("Erro ao salvar vídeo no repositório", e);
            throw new VideoPersistenceException("Falha ao salvar o vídeo", e);
        }
    }

    private void publishToQueue(Video video) {
        try {
            sqsPublisher.publish(video);
        } catch (Exception e) {
            log.error("Erro ao publicar vídeo na fila", e);
            throw new VideoPublishException("Falha ao enviar o vídeo para a fila", e);
        }
    }
}
