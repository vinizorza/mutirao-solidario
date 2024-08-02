package com.vfl.mutirao_solidario.model;

import jakarta.persistence.Entity;

import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Registration {

    private Long id;

    private Long eventId;

    private Long userId;
}
