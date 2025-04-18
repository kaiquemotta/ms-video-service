package com.ms.video.adapter.input.rest;

import com.ms.video.application.GeneratePreSignedUrlUseCase;
import com.ms.video.application.GeneratePreSignedUrlUseCase.UploadUrlResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/videos/upload-url")
public class PreSignedVideoController {

    private final GeneratePreSignedUrlUseCase useCase;

    public PreSignedVideoController(GeneratePreSignedUrlUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public UploadUrlResponse generateUrl(@RequestParam("filename") String filename) {
        return useCase.execute(filename);
    }
}