package com.vfl.mutirao_solidario.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import static com.vfl.mutirao_solidario.config.Constants.FIELD_INVALID;
import static com.vfl.mutirao_solidario.config.Constants.FIELD_REQUIRED;

public record Signin(
        @NotBlank(message = FIELD_REQUIRED)
        @Email(message = FIELD_INVALID)
        String email,
        @NotBlank(message = FIELD_REQUIRED)
        String password
) {
}
