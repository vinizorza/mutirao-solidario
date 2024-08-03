package com.vfl.mutirao_solidario.controller.dto;

import java.time.LocalDateTime;

public record EventRequest(
        Long organizerId,
        String title,
        String description,
        Double latitude,
        Double longitude,
        Integer minVolunteers,
        Integer maxVolunteers,
        LocalDateTime date) {
}
