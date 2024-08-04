package com.vfl.mutirao_solidario.service;

import com.vfl.mutirao_solidario.controller.dto.EventRequest;
import com.vfl.mutirao_solidario.controller.dto.EventResponse;
import com.vfl.mutirao_solidario.controller.dto.EventUpdate;
import com.vfl.mutirao_solidario.enums.Status;
import com.vfl.mutirao_solidario.model.Event;
import com.vfl.mutirao_solidario.model.User;
import com.vfl.mutirao_solidario.repository.EventRepository;
import com.vfl.mutirao_solidario.repository.RegistrationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private RegistrationRepository registrationRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private EventService eventService;

    @Captor
    private ArgumentCaptor<Event> eventCaptor;

    private static EventRequest getEventRequest(Long organizerId) {
        return new EventRequest(
                organizerId,
                "Limpeza margem do rio pinheiros",
                "Retirar o lixo jogado na margem do rio",
                1236.0,
                12235.2,
                10,
                50,
                LocalDateTime.now()
        );
    }

    private static Event getEvent(Long eventId, Long organizerId, Status status, LocalDateTime date, Double latitude, Double longitude){
        return Event.builder()
                .id(eventId)
                .organizer(User.builder().id(organizerId).build())
                .status(status)
                .date(date)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }

    @Test
    void createEventWithSucess() {

        EventRequest eventRequest = getEventRequest(3L);

        doNothing().when(authenticationService).validateUser(eventRequest.organizerId());

        eventService.create(eventRequest);

        verify(authenticationService, times(1)).validateUser(eventRequest.organizerId());
        verify(eventRepository,times(1)).save(eventCaptor.capture());

        Event savedEvent = eventCaptor.getValue();
        assertThat(savedEvent.getOrganizer().getId()).isEqualTo(3L);
        assertThat(savedEvent.getStatus()).isEqualTo(Status.ORGANIZING);
        assertThat(assertThat(savedEvent.getDescription())).isNotNull();
    }

    @Test
    public void createEventThrowsExceptionWhenUserNotHavePermission() {

        EventRequest eventRequest = getEventRequest(2L);

        doThrow(new RuntimeException("User does not have the permission")).when(authenticationService).validateUser(eventRequest.organizerId());

        assertThatThrownBy(() -> eventService.create(eventRequest))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("User does not have the permission");

        verify(authenticationService, times(1)).validateUser(eventRequest.organizerId());
        verifyNoInteractions(eventRepository);
    }

    @Test
    void getEventsByFilter() {

        List<Event> eventList = Arrays.asList(
                getEvent(1L, 1L, Status.CONFIRMED, LocalDateTime.of(2024,8,20,15,30), -19.918780765956157, -43.94689818810624),
                getEvent(2L, 1L, Status.CONFIRMED, LocalDateTime.of(2024,8,10,15,30), -18.78900340181768, -44.42081879334378),
                getEvent(3L, 2L, Status.ORGANIZING, LocalDateTime.of(2024,8,15,15,30), -2.509562271953361, -44.235443044838455));

        when(eventRepository.findEvents(null, null, null, Arrays.asList(Status.CONFIRMED, Status.ORGANIZING))).thenReturn(eventList);

        List<EventResponse> responses = eventService.getEventsByFilter(-19.943576, -43.967696, 150L, null, null,null,Arrays.asList(Status.CONFIRMED, Status.ORGANIZING));

        assertThat(responses).hasSize(2);

        verify(eventRepository,times(1)).findEvents(null, null, null, Arrays.asList(Status.CONFIRMED, Status.ORGANIZING));
    }


    @Test
    void deleteEvent() {
        Long eventId = 2L;

        eventService.delete(eventId);

        verify(eventRepository, times(1)).deleteById(eventId);
    }

    @Test
    void updateSucessfully() {
        EventUpdate eventUpdate = new EventUpdate(
                1L,
                "Limpeza margem do rio pinheiros",
                "Retirar o lixo jogado na margem do rio",
                10,
                60,
                Status.CONFIRMED,
                LocalDateTime.now(),
                12235.2,
                1000.2
        );

        doNothing().when(authenticationService).validateUser(eventUpdate.organizerId());

        eventService.update(eventUpdate, 1L);

        verify(authenticationService, times(1)).validateUser(eventUpdate.organizerId());
        verify(eventRepository,times(1)).save(eventCaptor.capture());

        Event savedEvent = eventCaptor.getValue();
        assertThat(savedEvent.getId()).isEqualTo(1L);
        assertThat(savedEvent.getStatus()).isEqualTo(Status.CONFIRMED);
        assertThat(assertThat(savedEvent.getDescription())).isNotNull();
    }
}