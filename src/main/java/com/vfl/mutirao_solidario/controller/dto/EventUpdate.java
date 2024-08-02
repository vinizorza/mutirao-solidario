package com.vfl.mutirao_solidario.controller.dto;

public record EventUpdate(Long id, Long organizerId, String title, String description, String location, String minVolunteers, String maxVolunteers) {
}
