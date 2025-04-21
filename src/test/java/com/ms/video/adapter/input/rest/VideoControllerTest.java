package com.ms.video.adapter.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.video.adapter.input.rest.dto.ConfirmUploadRequest;
import com.ms.video.application.ConfirmUploadUseCase;
import com.ms.video.application.CreateVideoUseCase;
import com.ms.video.application.ListVideosByClientUseCase;
import com.ms.video.config.JwtSecurityProperties;
import com.ms.video.domain.model.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = VideoController.class)
@ActiveProfiles("test")
class VideoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateVideoUseCase createVideoUseCase;

    @MockBean
    private ListVideosByClientUseCase listVideosByClientUseCase;

    @MockBean
    private ConfirmUploadUseCase confirmUploadUseCase;

    @MockBean
    private JwtSecurityProperties jwtSecurityProperties;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldReturnMongoUriOnDebug() throws Exception {
        mockMvc.perform(get("/videos/debug")
                        .with(jwt().jwt(jwt -> jwt.claim("sub", "dev-client-id"))))
                .andExpect(status().isOk());
    }

    @Test
    void shouldListVideosByClientId() throws Exception {
        when(jwtSecurityProperties.isEnabled()).thenReturn(true); // simula ambiente real com JWT

        Video video = new Video("Título", "https://video.mp4", "dev-client-id", 10);
        when(listVideosByClientUseCase.listByClientId("dev-client-id")).thenReturn(List.of(video));

        mockMvc.perform(get("/videos")
                        .with(jwt().jwt(jwt -> jwt.claim("sub", "dev-client-id"))))
                .andExpect(status().isOk());
    }
    @Test
    void shouldCreateVideo() throws Exception {
        when(jwtSecurityProperties.isEnabled()).thenReturn(true); // simula ambiente real com JWT

        MockMultipartFile file = new MockMultipartFile("file", "video.mp4", "video/mp4", "dummy content".getBytes());
        Video created = new Video("Título", "https://video.mp4", "dev-client-id", 10);
        when(createVideoUseCase.createVideo("Título", "dev-client-id", 10, file)).thenReturn(created);

        mockMvc.perform(multipart("/videos")
                        .file(file)
                        .param("title", "Título")
                        .param("secondsPartition", "10")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .with(jwt().jwt(jwt -> jwt.claim("sub", "dev-client-id")))
                        .with(csrf())) // ✅ CSRF válido
                .andExpect(status().isCreated());
    }
    @Test
    void shouldConfirmUpload() throws Exception {
        ConfirmUploadRequest request = new ConfirmUploadRequest("video.mp4", "Título", 10);
        Video video = new Video("Título", "https://video.mp4", "dev-client-id", 10);

        when(jwtSecurityProperties.isEnabled()).thenReturn(true);
        when(confirmUploadUseCase.execute("Título", "video.mp4", "dev-client-id", 10)).thenReturn(video);

        mockMvc.perform(post("/videos/confirm-upload")
                        .with(jwt().jwt(jwt -> jwt.claim("sub", "dev-client-id")))
                        .with(csrf()) // ✅ simula token CSRF válido
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }
}
