package com.ms.video.port.output;

public interface PreSignedUrlGenerator {
    String generateUploadUrl(String key);
}