package com.ms.video.adapter.input.rest;

import com.ms.video.adapter.input.rest.dto.ConfirmUploadRequest;
import com.ms.video.adapter.input.rest.dto.ConfirmUploadResponse;
import com.ms.video.adapter.input.rest.dto.CreateVideoResponse;
import com.ms.video.adapter.input.rest.dto.VideoResponse;
import com.ms.video.application.ConfirmUploadUseCase;
import com.ms.video.application.CreateVideoUseCase;
import com.ms.video.application.ListVideosByClientUseCase;
import com.ms.video.config.JwtSecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private final CreateVideoUseCase createVideoUseCase;

    private final ListVideosByClientUseCase listVideosByClientUseCase;

    private final ConfirmUploadUseCase confirmUploadUseCase;

    private final JwtSecurityProperties jwtSecurityProperties;

    public VideoController(CreateVideoUseCase createVideoUseCase,
                           JwtSecurityProperties jwtSecurityProperties, ListVideosByClientUseCase listVideosByClientUseCase, ConfirmUploadUseCase confirmUploadUseCase) {
        this.createVideoUseCase = createVideoUseCase;
        this.jwtSecurityProperties = jwtSecurityProperties;
        this.listVideosByClientUseCase = listVideosByClientUseCase;
        this.confirmUploadUseCase = confirmUploadUseCase;
    }
    @GetMapping("/debug")
    public ResponseEntity<String> debug() {
        return ResponseEntity.ok(System.getenv("MONGO_URI"));
    }

    @GetMapping
    public ResponseEntity<List<VideoResponse>> listByClient() {
        String clientId = extractClientIdFromToken();
        var videos = listVideosByClientUseCase.listByClientId(clientId)
                .stream()
                .map(VideoResponse::from)
                .toList();
        return ResponseEntity.ok(videos);
    }


    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<CreateVideoResponse> create(
            @RequestParam("title") String title,
            @RequestParam("secondsPartition") Integer secondsPartition,
            @RequestParam("file") MultipartFile file
    ) {
        String clientId = resolveClientId();
        var created = createVideoUseCase.createVideo(title, clientId,secondsPartition, file);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateVideoResponse(created.getId(), created.getUrl()));
    }

    @PostMapping("/confirm-upload")
    public ResponseEntity<ConfirmUploadResponse> confirm(@RequestBody ConfirmUploadRequest request) {
        String clientId = resolveClientId();
        System.out.println("NOVA VERSAO");
        var video = confirmUploadUseCase.execute(request.title(), request.key(), clientId,request.secondsPartition());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ConfirmUploadResponse(video.getId(), video.getUrl(), video.getStatus()));
    }

    private String resolveClientId() {
        if (jwtSecurityProperties.isEnabled()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwt = (Jwt) authentication.getPrincipal();
            return jwt.getClaim("sub");
        }
        return "dev-client-id";
    }

    private String extractClientIdFromToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof Jwt jwt) {
            return jwt.getClaim("sub");
        }
        return "dev-client-id";
    }


}
