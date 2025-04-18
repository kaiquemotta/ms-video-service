package com.ms.video.domain.exception;

public class VideoPublishException extends RuntimeException {
    public VideoPublishException(String message, Throwable cause) {
        super(message, cause);
    }
}