package com.ms.video.adapter.output.mongo;

import com.ms.video.adapter.output.mongo.document.VideoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataVideoRepository extends MongoRepository<VideoDocument, String> {
}