package com.ms.video.domain.exception;

public class VideoPersistenceException extends RuntimeException {
    public VideoPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
