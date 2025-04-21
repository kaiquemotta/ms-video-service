package com.ms.video.adapter.input.rest.dto;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CreateVideoRequestTest {

    private static Validator validator;

    @BeforeAll
    static void setupValidatorInstance() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldNotHaveViolationsWhenAllFieldsAreValid() {
        MockMultipartFile file = new MockMultipartFile("file", "video.mp4", "video/mp4", "dummy content".getBytes());
        CreateVideoRequest request = new CreateVideoRequest("Meu vídeo", file);

        Set<ConstraintViolation<CreateVideoRequest>> violations = validator.validate(request);
        assertThat(violations).isEmpty();
    }

    @Test
    void shouldFailWhenTitleIsBlank() {
        MockMultipartFile file = new MockMultipartFile("file", "video.mp4", "video/mp4", "dummy content".getBytes());
        CreateVideoRequest request = new CreateVideoRequest("  ", file);

        Set<ConstraintViolation<CreateVideoRequest>> violations = validator.validate(request);
        assertThat(violations)
                .extracting(ConstraintViolation::getPropertyPath)
                .anySatisfy(path -> assertThat(path.toString()).isEqualTo("title"));
    }

    @Test
    void shouldFailWhenFileIsNull() {
        CreateVideoRequest request = new CreateVideoRequest("Título válido", null);

        Set<ConstraintViolation<CreateVideoRequest>> violations = validator.validate(request);
        assertThat(violations)
                .extracting(ConstraintViolation::getPropertyPath)
                .anySatisfy(path -> assertThat(path.toString()).isEqualTo("file"));
    }
}
