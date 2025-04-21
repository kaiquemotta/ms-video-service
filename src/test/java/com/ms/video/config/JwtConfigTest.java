package com.ms.video.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        "spring.security.oauth2.resourceserver.jwt.secret-key=test-secret"
})
class JwtConfigTest {

    @Autowired
    private JwtDecoder jwtDecoder;

    @Test
    void shouldCreateJwtDecoderBean() {
        assertThat(jwtDecoder).isNotNull();
    }
}
