package com.vfl.mutirao_solidario.controller.dto;

public record EventRequest(
        Long organizerId,
        String title,
        String description,
        String location,
        Integer minVolunteers,
        Integer maxVolunteers) {
}
