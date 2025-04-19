package com.ms.video.adapter.input.rest.exception;


import com.ms.video.domain.exception.VideoUploadException;
import com.ms.video.domain.exception.VideoPersistenceException;
import com.ms.video.domain.exception.VideoPublishException;
import jakarta.servlet.http.HttpServletRequest; // ✅ esse aqui!
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VideoUploadException.class)
    public ResponseEntity<?> handleUpload(VideoUploadException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Falha ao fazer upload do vídeo", ex);
    }

    @ExceptionHandler(VideoPersistenceException.class)
    public ResponseEntity<?> handlePersistence(VideoPersistenceException ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao salvar vídeo", ex);
    }

    @ExceptionHandler(VideoPublishException.class)
    public ResponseEntity<?> handlePublish(VideoPublishException ex) {
        return buildResponse(HttpStatus.BAD_GATEWAY, "Erro ao enviar vídeo para processamento", ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro inesperado", ex);
    }

    private ResponseEntity<?> buildResponse(HttpStatus status, String title, Exception ex) {
        return ResponseEntity.status(status).body(Map.of(
                "type", "error" + status.name().toLowerCase().replace(" ", "-"),
                "title", title,
                "status", status.value(),
                "detail", ex.getMessage(),
                "timestamp", Instant.now(),
                "path", getCurrentRequestPath()
        ));
    }

    private String getCurrentRequestPath() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(ServletRequestAttributes.class::isInstance)
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest)
                .map(HttpServletRequest::getRequestURI)
                .orElse("N/A");
    }
}
