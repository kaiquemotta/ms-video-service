
package com.ms.video.adapter.input.rest.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConfirmUploadResponseTest {

    @Test
    void shouldCreateResponseCorrectly() {
        String id = "abc123";
        String url = "https://bucket.s3.amazonaws.com/video.mp4";
        String status = "COMPLETED";

        ConfirmUploadResponse response = new ConfirmUploadResponse(id, url, status);

        assertThat(response.id()).isEqualTo(id);
        assertThat(response.url()).isEqualTo(url);
        assertThat(response.status()).isEqualTo(status);
    }

    @Test
    void shouldRespectEqualityAndHashCode() {
        ConfirmUploadResponse r1 = new ConfirmUploadResponse("1", "url", "READY");
        ConfirmUploadResponse r2 = new ConfirmUploadResponse("1", "url", "READY");

        assertThat(r1).isEqualTo(r2);
        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
    }
}
