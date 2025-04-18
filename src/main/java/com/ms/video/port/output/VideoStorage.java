package com.ms.video.port.output;


import org.springframework.web.multipart.MultipartFile;

public interface VideoStorage {
    String upload(MultipartFile file);
}