package com.ms.video.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig
@Import(JwtConfig.class)
@TestPropertySource(properties = "spring.security.oauth2.resourceserver.jwt.secret-key=test-secret")
class JwtConfigTest {

    @Autowired
    private JwtDecoder jwtDecoder;

    @Test
    void shouldCreateJwtDecoderBean() {
        assertThat(jwtDecoder).isNotNull();
    }
}
