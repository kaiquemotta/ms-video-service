package com.ms.video.adapter.output.mongo;

import com.ms.video.adapter.output.mongo.document.VideoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SpringDataVideoRepository extends MongoRepository<VideoDocument, String> {

    List<VideoDocument> findByClientId(String clientId);

}