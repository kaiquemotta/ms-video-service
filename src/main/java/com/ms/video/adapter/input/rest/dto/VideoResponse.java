package com.ms.video.adapter.input.rest.dto;

import com.ms.video.domain.model.Video;

import java.time.LocalDateTime;
import java.util.Objects;

public record VideoResponse(
        String id,
        String title,
        String url,
        String urlZip,
        String status,
        String secondsPartition,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
    public static VideoResponse from(Video video) {
        return new VideoResponse(
                video.getId(),
                video.getTitle(),
                video.getUrl(),
                Objects.isNull(video.getUrlZip()) ? "-" : video.getUrlZip(),
                video.getStatus(),
                String.valueOf(video.getSecondsPartition()),
                video.getCreatedAt(),
                video.getUpdatedAt()
        );
    }
}
