package com.ms.video.port.output;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class S3PreSignedUrlGeneratorTest {

    @Test
    void shouldImplementPreSignedUrlGeneratorInterface() {
        PreSignedUrlGenerator generator = key -> "https://mock-s3.com/" + key;

        String key = "video.mp4";
        String result = generator.generateUploadUrl(key);

        assertThat(result).isEqualTo("https://mock-s3.com/video.mp4");
    }
}