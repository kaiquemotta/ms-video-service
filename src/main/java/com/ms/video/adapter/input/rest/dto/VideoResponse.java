package com.ms.video.adapter.input.rest.dto;

import com.ms.video.domain.model.Video;

import java.time.LocalDateTime;

public record VideoResponse(
        String id,
        String title,
        String url,
        String status,
        LocalDateTime createdAt
) {
    public static VideoResponse from(Video video) {
        return new VideoResponse(
                video.getId(),
                video.getTitle(),
                video.getUrl(),
                video.getStatus(),
                video.getCreatedAt()
        );
    }
}
