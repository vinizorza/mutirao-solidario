package com.vfl.mutirao_solidario.controller;

import com.vfl.mutirao_solidario.controller.dto.RegistrationRequest;
import com.vfl.mutirao_solidario.controller.dto.RegistrationResponse;
import com.vfl.mutirao_solidario.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/registration")
@AllArgsConstructor
@Validated
public class RegistrationController {

    private final RegistrationService registrationService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> joinEvent(@Valid @RequestBody RegistrationRequest registration){
        registrationService.joinEvent(registration);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        registrationService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/byUserId/{userId}")
    public List<RegistrationResponse> getRegistrationByUserId(@PathVariable("userId") Long userId){
        return registrationService.getRegistrationByUserId(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/byEventId/{eventId}")
    public List<RegistrationResponse> getRegistrationByEventId(@PathVariable("eventId") Long eventId){
        return registrationService.getRegistrationByEventId(eventId);
    }

}
