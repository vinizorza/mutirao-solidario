package com.vfl.mutirao_solidario.service;

import com.vfl.mutirao_solidario.controller.dto.EventRequest;
import com.vfl.mutirao_solidario.controller.dto.EventResponse;
import com.vfl.mutirao_solidario.controller.dto.EventUpdate;
import com.vfl.mutirao_solidario.enums.Status;
import com.vfl.mutirao_solidario.model.Event;
import com.vfl.mutirao_solidario.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public void create(EventRequest event) {
        eventRepository.save(Event.builder()
                .organizerId(event.organizerId())
                .title(event.title())
                .description(event.description())
                .minVolunteers(event.minVolunteers())
                .maxVolunteers(event.maxVolunteers())
                .status(Status.ORGANIZING)
                .build());
    }

    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(event -> new EventResponse(
                        event.getId(),
                        event.getOrganizerId(),
                        event.getTitle(),
                        event.getDescription(),
                        event.getLocation(),
                        event.getMinVolunteers(),
                        event.getMaxVolunteers(),
                        event.getStatus()))
                .collect(Collectors.toList());
    }


    public void delete(Long id) {
        eventRepository.deleteById(id);
    }

    public void update(EventUpdate event) {
        eventRepository.save(Event.builder()
                .id(event.id())
                .organizerId(event.organizerId())
                .title(event.title())
                .description(event.description())
                .minVolunteers(event.minVolunteers())
                .maxVolunteers(event.maxVolunteers())
                .status(event.status())
                .build());
    }
}
