package com.vfl.mutirao_solidario.model;

import com.vfl.mutirao_solidario.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name = "ORGANIZER_ID")
    private User organizer;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", nullable = false, length = 10000)
    private String description;

    @Column(name = "LATITUDE", nullable = false)
    private Double latitude;

    @Column(name = "LONGITUDE", nullable = false)
    private Double longitude;

    @Column(name = "MIN_VOLUNTEERS", nullable = false)
    private Integer minVolunteers;

    @Column(name = "MAX_VOLUNTEERS", nullable = false)
    private Integer maxVolunteers;

    @Column(name = "STATUS")
    private Status status;

    @Column(name = "DATE", nullable = false)
    private LocalDateTime date;
}
