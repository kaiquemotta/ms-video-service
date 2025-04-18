package com.ms.video.application;

import com.ms.video.domain.model.Video;
import com.ms.video.port.output.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListVideosByClientUseCase {

    private final VideoRepository videoRepository;

    public ListVideosByClientUseCase(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public List<Video> listByClientId(String clientId) {
        return videoRepository.findByClientId(clientId);
    }
}
