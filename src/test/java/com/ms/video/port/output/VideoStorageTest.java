package com.ms.video.port.output;



import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class VideoStorageTest {

    @Test
    void shouldSimulateUploadUsingFakeImplementation() {
        MultipartFile file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("video.mp4");
        VideoStorage storage = f -> "https://fake-bucket.com/" + f.getOriginalFilename();
        String result = storage.upload(file);
        assertThat(result).isEqualTo("https://fake-bucket.com/video.mp4");
    }
}
