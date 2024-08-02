package com.vfl.mutirao_solidario.controller;

import com.vfl.mutirao_solidario.controller.dto.RegistrationRequest;
import com.vfl.mutirao_solidario.controller.dto.RegistrationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/registration")
public class RegistrationController {

    @PostMapping
    public ResponseEntity<RegistrationResponse> joinEvent(@RequestBody RegistrationRequest registration){
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        return null;
    }



}
