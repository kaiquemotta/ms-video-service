package com.ms.video.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.amazon.awssdk.services.s3.S3Client;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        "aws.region=us-east-1"
})
class AwsS3ConfigTest {

    @Autowired
    private S3Client s3Client;

    @Test
    void shouldCreateS3ClientBean() {
        assertThat(s3Client).isNotNull();
        assertThat(s3Client.serviceName()).isEqualTo("s3");
    }
}
