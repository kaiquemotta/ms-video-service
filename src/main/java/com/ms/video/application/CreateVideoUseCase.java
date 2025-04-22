package com.ms.video.application;

import com.ms.video.domain.exception.VideoPersistenceException;
import com.ms.video.domain.exception.VideoPublishException;
import com.ms.video.domain.exception.VideoUploadException;
import com.ms.video.domain.model.Video;
import com.ms.video.port.output.SqsPublisher;
import com.ms.video.port.output.VideoEventPublisher;
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
    private final SqsPublisher videoEventPublisher;
    private final VideoStorage videoStorage;

    public CreateVideoUseCase(VideoRepository videoRepository, SqsPublisher videoEventPublisher, VideoStorage videoStorage) {
        this.videoRepository = videoRepository;
        this.videoEventPublisher = videoEventPublisher;
        this.videoStorage = videoStorage;
    }

    public Video createVideo(String title, String clientId, Integer secondPartition,MultipartFile file) {
        String s3Url = uploadFileToS3(file);
        Video video = new Video(title, s3Url, clientId,secondPartition);
        saveVideo(video);
        publishToQueue(video);
        return video;
    }

    private String uploadFileToS3(MultipartFile file) {
        try {
            return videoStorage.upload(file);
        } catch (Exception e) {
            log.error("    --- Erro ao fazer upload do vídeo", e);
            throw new VideoUploadException("Falha ao enviar vídeo para o S3", e);
        }
    }

    private void saveVideo(Video video) {
        try {
            String documentId = videoRepository.save(video);
            video.setId(documentId);
        } catch (Exception e) {
            log.error("Erro ao salvar vídeo no repositório", e);
            throw new VideoPersistenceException("Falha ao salvar o vídeo", e);
        }
    }

    private void publishToQueue(Video video) {
        try {
            videoEventPublisher.publish(video);
        } catch (Exception e) {
            log.error("Erro ao publicar vídeo no SQS", e);
            throw new VideoPublishException("Falha ao enviar o vídeo para o SQS", e);
        }
    }


//    private void publishToQueue(Video video) {
//        try {
//            videoEventPublisher.sendVideoUploadedEvent(video);
//        } catch (Exception e) {
//            log.error("Erro ao publicar vídeo no Kafka", e);
//            throw new VideoPublishException("Falha ao enviar o vídeo para o Kafka", e);
//        }
//    }
}
