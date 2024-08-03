package com.vfl.mutirao_solidario.controller.dto;

import com.vfl.mutirao_solidario.enums.Status;
import com.vfl.mutirao_solidario.model.User;

import java.time.LocalDateTime;

public record EventResponse (
        Long id,
        User organizer,
        String title,
        String description,
        Double latitude,
        Double longitude,
        Integer minVolunteers,
        Integer maxVolunteers,
        Status status,
        LocalDateTime date){
}
