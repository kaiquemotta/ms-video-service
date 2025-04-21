package com.ms.video.adapter.input.rest.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateVideoResponseTest {

    @Test
    void shouldCreateResponseCorrectly() {
        String id = "abc123";
        String url = "https://bucket.s3.amazonaws.com/video.mp4";

        CreateVideoResponse response = new CreateVideoResponse(id, url);

        assertThat(response.id()).isEqualTo(id);
        assertThat(response.url()).isEqualTo(url);
    }

    @Test
    void shouldRespectEqualityAndHashCode() {
        CreateVideoResponse r1 = new CreateVideoResponse("1", "url");
        CreateVideoResponse r2 = new CreateVideoResponse("1", "url");

        assertThat(r1).isEqualTo(r2);
        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
    }
}
