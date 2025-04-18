package com.ms.video.port.output;

import com.ms.video.domain.model.Video;

import java.util.List;

public interface VideoRepository {

    String save(Video video);

    List<Video> findByClientId(String clientId);

}