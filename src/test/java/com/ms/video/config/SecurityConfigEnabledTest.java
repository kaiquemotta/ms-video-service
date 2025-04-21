package com.ms.video.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource(properties = {
        "security.jwt.enabled=true"
})
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
