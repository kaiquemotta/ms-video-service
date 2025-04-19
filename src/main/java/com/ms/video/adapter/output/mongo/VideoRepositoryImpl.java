package com.ms.video.adapter.output.mongo;

import com.ms.video.adapter.output.mongo.document.VideoDocument;
import com.ms.video.domain.model.Video;
import com.ms.video.port.output.VideoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VideoRepositoryImpl implements VideoRepository {
    private static final Logger log = LoggerFactory.getLogger(VideoRepositoryImpl.class);

    private final SpringDataVideoRepository mongoRepository;

    public VideoRepositoryImpl(SpringDataVideoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    public String save(Video video) {
        VideoDocument doc = new VideoDocument(
                video.getTitle(),
                video.getUrl(),
                video.getClientId(),
                video.getStatus(),
                video.getSecondsPartition(),
                video.getCreatedAt()
        );

        VideoDocument saved = mongoRepository.save(doc);
        return saved.getId();
    }

    @Override
    public List<Video> findByClientId(String clientId) {
        log.info("Buscando vídeos para o clientId: {}", clientId);

        List<Video> videos = mongoRepository.findByClientId(clientId).stream()
                .map(doc -> new Video(
                        doc.getId(),
                        doc.getTitle(),
                        doc.getUrl(),
                        doc.getClientId(),
                        doc.getStatus(),
                        doc.getSecondsPartition(),
                        doc.getUrlZip(),
                        doc.getCreatedAt(),
                        doc.getUpdatedAt()
                )).toList();

        log.info("Encontrados {} vídeos para o clientId {}", videos.size(), clientId);
        return videos;
    }
}