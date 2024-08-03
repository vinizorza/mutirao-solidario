package com.vfl.mutirao_solidario.service;

import com.vfl.mutirao_solidario.controller.dto.RegistrationRequest;
import com.vfl.mutirao_solidario.controller.dto.RegistrationResponse;
import com.vfl.mutirao_solidario.model.Event;
import com.vfl.mutirao_solidario.model.Registration;
import com.vfl.mutirao_solidario.model.User;
import com.vfl.mutirao_solidario.repository.RegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final AuthenticationService authenticationService;
    public void joinEvent(RegistrationRequest registration) {
        authenticationService.validateUser(registration.userId());

        registrationRepository.save(Registration.builder()
                .event(Event.builder().id(registration.eventId()).build())
                .user(User.builder().id(registration.userId()).build())
                .build());
    }

    public void delete(Long id) {
        registrationRepository.deleteById(id);
    }

    public List<RegistrationResponse> getRegistrationByUserId(Long userId) {
        return registrationRepository.findByUserId(userId).stream()
                .map(registration -> new RegistrationResponse(
                    registration.getId(),
                    registration.getUser().getId(),
                    registration.getEvent().getId()
                )).collect(Collectors.toList());
    }

    public List<RegistrationResponse> getRegistrationByEventId(Long eventId) {
        return registrationRepository.findByEventId(eventId).stream()
                .map(registration -> new RegistrationResponse(
                    registration.getId(),
                    registration.getUser().getId(),
                    registration.getEvent().getId()
                )).collect(Collectors.toList());
    }
}
