package com.vfl.mutirao_solidario.controller.dto;

import com.vfl.mutirao_solidario.enums.Status;

import java.time.LocalDateTime;

public record EventResponse (
        Long id,
        UserResponse organizer,
        String title,
        String description,
        Double latitude,
        Double longitude,
        Integer minVolunteers,
        Integer maxVolunteers,
        Status status,
        LocalDateTime date){
}
