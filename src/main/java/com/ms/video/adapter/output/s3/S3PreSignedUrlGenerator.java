package com.ms.video.adapter.output.s3;

import com.ms.video.port.output.PreSignedUrlGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.time.Duration;

@Component
public class S3PreSignedUrlGenerator implements PreSignedUrlGenerator {

    private final S3Presigner presigner;
    private final String bucketName;

    public S3PreSignedUrlGenerator(
            @Value("${aws.s3.bucket}") String bucketName,
            @Value("${aws.region}") String region
    ) {
        this.bucketName = bucketName;
        this.presigner = S3Presigner.builder()
                .region(Region.of(region))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    @Override
    public String generateUploadUrl(String key) {
        var putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        var preSignedRequest = presigner.presignPutObject(builder -> builder
                .signatureDuration(Duration.ofMinutes(10))
                .putObjectRequest(putObjectRequest)
        );

        return preSignedRequest.url().toString();
    }
}