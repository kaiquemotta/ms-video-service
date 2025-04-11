package com.ms.video.adapter.input.rest.dto;

import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateVideoRequest(
        @NotBlank String title,
        @NotNull MultipartFile file
) {}