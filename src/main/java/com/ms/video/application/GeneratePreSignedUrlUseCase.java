package com.ms.video.application;


import com.ms.video.port.output.PreSignedUrlGenerator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GeneratePreSignedUrlUseCase {

    private final PreSignedUrlGenerator generator;

    public GeneratePreSignedUrlUseCase(PreSignedUrlGenerator generator) {
        this.generator = generator;
    }

    public UploadUrlResponse execute(String originalFilename) {
        String key = UUID.randomUUID() + "_" + originalFilename;
        String url = generator.generateUploadUrl(key);
        return new UploadUrlResponse(key, url);
    }

    public record UploadUrlResponse(String key, String uploadUrl) {
    }
}
