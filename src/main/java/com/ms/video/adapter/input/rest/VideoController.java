package com.ms.video.adapter.input.rest;

import com.ms.video.adapter.input.rest.dto.CreateVideoResponse;
import com.ms.video.application.CreateVideoUseCase;
import com.ms.video.domain.model.Video;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private final CreateVideoUseCase createVideoUseCase;

    public VideoController(CreateVideoUseCase createVideoUseCase) {
        this.createVideoUseCase = createVideoUseCase;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<CreateVideoResponse> create(
            @RequestParam("title") String title,
            @RequestParam("file") MultipartFile file
    ) {
        Video created = createVideoUseCase.createVideo(title, file);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreateVideoResponse(created.getId(), created.getUrl()));
    }
}
