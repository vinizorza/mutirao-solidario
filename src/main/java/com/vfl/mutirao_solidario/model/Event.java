package com.vfl.mutirao_solidario.model;

import com.vfl.mutirao_solidario.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ORGANIZER_ID", nullable = false)
    private Long organizerId;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "LOCATION", nullable = false)
    private String location;

    @Column(name = "MIN_VOLUNTEERS", nullable = false)
    private Integer minVolunteers;

    @Column(name = "MAX_VOLUNTEERS", nullable = false)
    private Integer maxVolunteers;

    @Column(name = "STATUS")
    private Status status;
}
