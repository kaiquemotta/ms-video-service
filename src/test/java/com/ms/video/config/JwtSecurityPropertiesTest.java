package com.ms.video.config;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JwtSecurityPropertiesTest {

    @Test
    void shouldSetAndGetEnabledProperty() {
        JwtSecurityProperties props = new JwtSecurityProperties();
        props.setEnabled(true);
        assertThat(props.isEnabled()).isTrue();

        props.setEnabled(false);
        assertThat(props.isEnabled()).isFalse();
    }
}
