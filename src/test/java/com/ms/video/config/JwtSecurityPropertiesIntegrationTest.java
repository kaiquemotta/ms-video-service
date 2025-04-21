package com.ms.video.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig
@EnableConfigurationProperties(JwtSecurityProperties.class)
@TestPropertySource(properties = {
        "security.jwt.enabled=true"
})
class JwtSecurityPropertiesIntegrationTest {

    @Autowired
    private JwtSecurityProperties properties;

    @Test
    void shouldBindEnabledPropertyFromYaml() {
        assertThat(properties.isEnabled()).isTrue();
    }
}