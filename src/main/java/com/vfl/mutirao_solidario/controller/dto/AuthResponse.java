package com.vfl.mutirao_solidario.controller.dto;

import lombok.Builder;

@Builder
public record AuthResponse(
        String name,
        String token
) {
}
