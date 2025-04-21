package com.ms.video.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = {
        "security.jwt.enabled=true"
})
class JwtSecurityPropertiesIntegrationTest {

    @Autowired
    private JwtSecurityProperties props;

    @Test
    void shouldBindEnabledPropertyFromYaml() {
        assertThat(props.isEnabled()).isTrue();
    }
}
