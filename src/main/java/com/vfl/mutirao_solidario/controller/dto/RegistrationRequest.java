package com.vfl.mutirao_solidario.controller.dto;

import jakarta.validation.constraints.NotNull;

import static com.vfl.mutirao_solidario.config.Constants.FIELD_REQUIRED;

public record RegistrationRequest(
        @NotNull(message = FIELD_REQUIRED)
        Long eventId,
        @NotNull(message = FIELD_REQUIRED)
        Long userId) {
}
