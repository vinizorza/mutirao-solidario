package com.vfl.mutirao_solidario.controller.dto;

import com.vfl.mutirao_solidario.enums.Status;

import java.time.LocalDateTime;

public record EventUpdate(
        Long organizerId,
        String title,
        String description,
        String location,
        Integer minVolunteers,
        Integer maxVolunteers,
        Status status ,
        LocalDateTime date) {
}
