package com.ms.video.adapter.input.rest;

import com.ms.video.application.GeneratePreSignedUrlUseCase;
import com.ms.video.application.GeneratePreSignedUrlUseCase.UploadUrlResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PreSignedVideoController.class)
class PreSignedVideoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GeneratePreSignedUrlUseCase useCase;

    @Test
    void shouldReturn401WhenGeneratingUrl() throws Exception {
        String filename = "video.mp4";
        UploadUrlResponse mockResponse = new UploadUrlResponse("https://mock-url", "mock-key");
        when(useCase.execute(filename)).thenReturn(mockResponse);

        mockMvc.perform(get("/videos/upload-url")
                        .param("filename", filename)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}
