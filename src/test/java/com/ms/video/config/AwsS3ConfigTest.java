package com.ms.video.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import software.amazon.awssdk.services.s3.S3Client;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig
@Import(AwsS3Config.class)
@TestPropertySource(properties = "aws.region=us-east-1")
class AwsS3ConfigTest {

    @Autowired
    private S3Client s3Client;

    @Test
    void shouldCreateS3ClientBean() {
        assertThat(s3Client).isNotNull();
        assertThat(s3Client.serviceName()).isEqualTo("s3");
    }
}
