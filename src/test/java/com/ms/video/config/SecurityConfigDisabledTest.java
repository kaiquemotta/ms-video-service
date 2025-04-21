package com.ms.video.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.TestPropertySource;
import org.springframework.context.annotation.Import;
import org.springframework.security.web.SecurityFilterChain;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig
@Import({SecurityConfig.class, JwtSecurityProperties.class})
@TestPropertySource(properties = "security.jwt.enabled=false")
class SecurityConfigDisabledTest {

    @Autowired
    private SecurityFilterChain filterChain;

    @Autowired
    private JwtSecurityProperties props;

    @Test
    void shouldDisableSecurityWhenJwtIsDisabled() {
        assertThat(props.isEnabled()).isFalse();
        assertThat(filterChain).isNotNull(); // valida que o contexto carrega
    }
}
