package com.ms.video.adapter.input.rest;

import com.ms.video.adapter.input.rest.dto.CreateVideoResponse;
import com.ms.video.application.CreateVideoUseCase;
import com.ms.video.config.JwtSecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private final CreateVideoUseCase createVideoUseCase;
    private final JwtSecurityProperties jwtSecurityProperties;

    public VideoController(CreateVideoUseCase createVideoUseCase,
                           JwtSecurityProperties jwtSecurityProperties) {
        this.createVideoUseCase = createVideoUseCase;
        this.jwtSecurityProperties = jwtSecurityProperties;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<CreateVideoResponse> create(
            @RequestParam("title") String title,
            @RequestParam("file") MultipartFile file
    ) {
        String clientId = resolveClientId();
        var created = createVideoUseCase.createVideo(title, clientId, file);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateVideoResponse(created.getId(), created.getUrl()));
    }

    private String resolveClientId() {
        if (jwtSecurityProperties.isEnabled()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwt = (Jwt) authentication.getPrincipal();
            return jwt.getClaim("sub"); // ou "client_id", dependendo do conteúdo do JWT
        }
        return "dev-client-id";
    }

}
