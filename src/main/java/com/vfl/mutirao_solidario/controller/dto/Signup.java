package com.vfl.mutirao_solidario.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record Signup(
        @NotBlank(message = "is required.")
        String name,
        @NotBlank(message = "is required.")
        @Email(message = "is invalid")
        String email,
        @NotBlank(message = "is required.")
        String password,
        @NotBlank(message = "is required.")
        @Pattern(regexp = "^\\d{1,5}$", message = "Phone number is invalid.")
        String phoneNumber
) {
}
