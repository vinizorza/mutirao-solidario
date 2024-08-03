package com.vfl.mutirao_solidario.controller.dto;

import com.vfl.mutirao_solidario.model.User;

import java.time.LocalDateTime;

public record EventRequest(
        User organizer,
        String title,
        String description,
        Double latitude,
        Double longitude,
        Integer minVolunteers,
        Integer maxVolunteers,
        LocalDateTime date) {
}
