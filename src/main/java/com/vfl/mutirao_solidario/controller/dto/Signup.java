package com.vfl.mutirao_solidario.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static com.vfl.mutirao_solidario.config.Constants.FIELD_INVALID;
import static com.vfl.mutirao_solidario.config.Constants.FIELD_REQUIRED;

public record Signup(
        @NotBlank(message = FIELD_REQUIRED)
        String name,
        @NotBlank(message = FIELD_REQUIRED)
        @Email(message = FIELD_INVALID)
        String email,
        @NotBlank(message = FIELD_REQUIRED)
        @Pattern(regexp = "^.{8,30}$", message = FIELD_INVALID)
        String password,
        @NotBlank(message = FIELD_REQUIRED)
        @Pattern(regexp = "^\\d{8,12}$", message = FIELD_INVALID)
        String phoneNumber
) {
}
