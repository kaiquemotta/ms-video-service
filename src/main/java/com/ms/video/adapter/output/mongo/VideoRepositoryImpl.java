package com.ms.video.adapter.output.mongo;

import com.ms.video.adapter.output.mongo.document.VideoDocument;
import com.ms.video.domain.model.Video;
import com.ms.video.port.output.VideoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VideoRepositoryImpl implements VideoRepository {

    private final SpringDataVideoRepository mongoRepository;

    public VideoRepositoryImpl(SpringDataVideoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    public void save(Video video) {
        VideoDocument doc = new VideoDocument(
                video.getTitle(),
                video.getUrl(),
                video.getClientId(),
                video.getStatus(),
                video.getCreatedAt()
        );
        mongoRepository.save(doc);
    }

    @Override
    public List<Video> findByClientId(String clientId) {
        return mongoRepository.findByClientId(clientId).stream()
                .map(doc -> new Video(
                        doc.getId(),
                        doc.getTitle(),
                        doc.getUrl(),
                        doc.getClientId(),
                        doc.getStatus(),
                        doc.getCreatedAt()
                )).toList();
    }
}