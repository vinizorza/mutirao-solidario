package com.vfl.mutirao_solidario.service;

import com.vfl.mutirao_solidario.controller.dto.EventRequest;
import com.vfl.mutirao_solidario.model.Event;
import com.vfl.mutirao_solidario.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public void create(EventRequest event) {
        eventRepository.save(Event.builder()
                .title(event.title())
                .description(event.description())
                .minVolunteers(event.minVolunteers())
                .maxVolunteers(event.maxVolunteers())
                .build());
    }
}
