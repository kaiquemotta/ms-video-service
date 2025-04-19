package com.ms.video.adapter.input.rest.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConfirmUploadRequest(
        @NotBlank String key,
        @NotBlank String title,
        @NotNull Integer secondsPartition
) {}