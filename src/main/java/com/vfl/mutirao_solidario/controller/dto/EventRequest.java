package com.vfl.mutirao_solidario.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

import static com.vfl.mutirao_solidario.config.Constants.FIELD_REQUIRED;

public record EventRequest(
        @NotNull(message = FIELD_REQUIRED)
        Long organizerId,
        @NotBlank(message = FIELD_REQUIRED)
        String title,
        @NotBlank(message = FIELD_REQUIRED)
        String description,
        @NotNull(message = FIELD_REQUIRED)
        Double latitude,
        @NotNull(message = FIELD_REQUIRED)
        Double longitude,
        @NotNull(message = FIELD_REQUIRED)
        Integer minVolunteers,
        @NotNull(message = FIELD_REQUIRED)
        Integer maxVolunteers,
        @NotNull(message = FIELD_REQUIRED)
        LocalDateTime date) {
}
