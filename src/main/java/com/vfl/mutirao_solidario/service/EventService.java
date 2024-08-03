package com.vfl.mutirao_solidario.service;

import com.vfl.mutirao_solidario.controller.dto.EventRequest;
import com.vfl.mutirao_solidario.controller.dto.EventResponse;
import com.vfl.mutirao_solidario.controller.dto.EventUpdate;
import com.vfl.mutirao_solidario.enums.Status;
import com.vfl.mutirao_solidario.model.Event;
import com.vfl.mutirao_solidario.model.User;
import com.vfl.mutirao_solidario.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    private final AuthenticationService authenticationService;

    public void create(EventRequest event) {

        authenticationService.validateUser(event.organizer().getId());

        eventRepository.save(Event.builder()
                .organizer(User.builder().id(event.organizer().getId()).build())
                .title(event.title())
                .description(event.description())
                .minVolunteers(event.minVolunteers())
                .maxVolunteers(event.maxVolunteers())
                .latitude(event.latitude())
                .longitude(event.longitude())
                .status(Status.ORGANIZING)
                .date(event.date())
                .build());
    }

    public List<EventResponse> getAllEvents(Double latitude, Double longitude, Long radius, LocalDate dateFrom, LocalDate dateTo) {
        return eventRepository.findAll().stream()
                .map(event -> new EventResponse(
                        event.getId(),
                        event.getOrganizer(),
                        event.getTitle(),
                        event.getDescription(),
                        event.getLatitude(),
                        event.getLongitude(),
                        event.getMinVolunteers(),
                        event.getMaxVolunteers(),
                        event.getStatus(),
                        event.getDate()))
                .collect(Collectors.toList());
    }


    public void delete(Long id) {
        eventRepository.deleteById(id);
    }

    public void update(EventUpdate event, Long id) {
//        eventRepository.save(Event.builder()
//                .id(id)
//                .organizerId(event.organizerId())
//                .title(event.title())
//                .description(event.description())
//                .minVolunteers(event.minVolunteers())
//                .maxVolunteers(event.maxVolunteers())
//                .status(event.status())
//                .date(event.date())
//                .build());
    }
}
