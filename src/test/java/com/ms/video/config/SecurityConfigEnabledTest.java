package com.ms.video.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.TestPropertySource;
import org.springframework.context.annotation.Import;
import org.springframework.security.web.SecurityFilterChain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringJUnitConfig
@Import({SecurityConfig.class, JwtSecurityProperties.class})
@TestPropertySource(properties = "security.jwt.enabled=true")
class SecurityConfigEnabledTest {

    @Autowired
    private SecurityFilterChain filterChain;

    @Autowired
    private JwtSecurityProperties props;

    @Test
    void shouldEnableSecurityWhenJwtIsEnabled() {
        assertThat(props.isEnabled()).isTrue();
        assertThat(filterChain).isNotNull();
    }
}
