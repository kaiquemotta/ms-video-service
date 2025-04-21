package com.ms.video.config;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AwsPropertiesTest {

    @Test
    void shouldSetAndGetAllProperties() {
        AwsProperties props = new AwsProperties();

        props.setRegion("us-east-1");
        props.setAccessKey("test-access-key");
        props.setSecretKey("test-secret-key");

        assertThat(props.getRegion()).isEqualTo("us-east-1");
        assertThat(props.getAccessKey()).isEqualTo("test-access-key");
        assertThat(props.getSecretKey()).isEqualTo("test-secret-key");
    }
}
