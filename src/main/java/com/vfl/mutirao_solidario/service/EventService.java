package com.vfl.mutirao_solidario.service;

import com.vfl.mutirao_solidario.controller.dto.EventRequest;
import com.vfl.mutirao_solidario.controller.dto.EventResponse;
import com.vfl.mutirao_solidario.controller.dto.EventUpdate;
import com.vfl.mutirao_solidario.controller.dto.UserResponse;
import com.vfl.mutirao_solidario.enums.Status;
import com.vfl.mutirao_solidario.model.Event;
import com.vfl.mutirao_solidario.model.User;
import com.vfl.mutirao_solidario.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    private final AuthenticationService authenticationService;

    public void create(EventRequest event) {

        authenticationService.validateUser(event.organizerId());

        eventRepository.save(Event.builder()
                .organizer(User.builder().id(event.organizerId()).build())
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

    public List<EventResponse> getEventsByFilter(Double latitude,
                                                 Double longitude,
                                                 Long radius,
                                                 LocalDateTime dateFrom,
                                                 LocalDateTime dateTo,
                                                 Long userId,
                                                 List<Status> status) {

        List<Event> events = eventRepository.findEvents(userId, dateFrom , dateTo, status);

        events = events.stream().filter(e -> radius == null ||
                        distance(latitude, longitude, e.getLatitude(), e.getLongitude()) <= radius)
                .toList();

        return events.stream().map(event -> new EventResponse(
                        event.getId(),
                        new UserResponse(event.getOrganizer().getId(),
                                event.getOrganizer().getName(),
                                event.getOrganizer().getEmail(),
                                event.getOrganizer().getPhoneNumber()),
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

        authenticationService.validateUser(event.organizerId());

        eventRepository.save(Event.builder()
                .id(id)
                .organizer(User.builder().id(event.organizerId()).build())
                .title(event.title())
                .description(event.description())
                .minVolunteers(event.minVolunteers())
                .maxVolunteers(event.maxVolunteers())
                .status(event.status())
                .date(event.date())
                .latitude(event.latitude())
                .longitude(event.longitude())
                .build());
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) +
                Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;

        return (dist);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
}
