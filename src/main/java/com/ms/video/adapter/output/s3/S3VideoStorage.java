package com.ms.video.adapter.output.s3;

import com.ms.video.port.output.VideoStorage;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;

import java.net.URL;
import java.util.UUID;

@Component
public class S3VideoStorage implements VideoStorage {

    private final S3Client s3Client;
    private final String bucketName = "video-storage-kaique"; // ou @Value("${s3.bucket-name}")

    public S3VideoStorage(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public String upload(MultipartFile file) {
        try {
            String key = UUID.randomUUID() + "_" + file.getOriginalFilename();

            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .build(),
                    RequestBody.fromBytes(file.getBytes())
            );

            return "https://" + bucketName + ".s3.amazonaws.com/" + key;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar para o S3", e);
        }
    }
}
