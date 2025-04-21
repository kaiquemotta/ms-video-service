package com.ms.video.adapter.input.rest.dto;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ConfirmUploadRequestTest {

    private static Validator validator;

    @BeforeAll
    static void setupValidatorInstance() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldNotHaveViolationsWhenAllFieldsAreValid() {
        ConfirmUploadRequest request = new ConfirmUploadRequest("video.mp4", "Meu Vídeo", 15);
        Set<ConstraintViolation<ConfirmUploadRequest>> violations = validator.validate(request);

        assertThat(violations).isEmpty();
    }

    @Test
    void shouldFailWhenKeyIsBlank() {
        ConfirmUploadRequest request = new ConfirmUploadRequest(" ", "Título válido", 10);
        Set<ConstraintViolation<ConfirmUploadRequest>> violations = validator.validate(request);

        assertThat(violations).extracting(ConstraintViolation::getPropertyPath)
                .anySatisfy(path -> assertThat(path.toString()).isEqualTo("key"));
    }

    @Test
    void shouldFailWhenTitleIsBlank() {
        ConfirmUploadRequest request = new ConfirmUploadRequest("video.mp4", "", 10);
        Set<ConstraintViolation<ConfirmUploadRequest>> violations = validator.validate(request);

        assertThat(violations).extracting(ConstraintViolation::getPropertyPath)
                .anySatisfy(path -> assertThat(path.toString()).isEqualTo("title"));
    }

    @Test
    void shouldFailWhenSecondsPartitionIsNull() {
        ConfirmUploadRequest request = new ConfirmUploadRequest("video.mp4", "Título", null);
        Set<ConstraintViolation<ConfirmUploadRequest>> violations = validator.validate(request);

        assertThat(violations).extracting(ConstraintViolation::getPropertyPath)
                .anySatisfy(path -> assertThat(path.toString()).isEqualTo("secondsPartition"));
    }
}
