package com.vfl.mutirao_solidario.controller;

import com.vfl.mutirao_solidario.controller.dto.EventRequest;
import com.vfl.mutirao_solidario.controller.dto.EventResponse;
import com.vfl.mutirao_solidario.controller.dto.EventUpdate;
import com.vfl.mutirao_solidario.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/event")
@AllArgsConstructor
public class EventController {

    final EventService eventService;

    @PostMapping(value = "/create")
    public ResponseEntity<Void> create(@RequestBody EventRequest event){

        eventService.create(event);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/update")
    public EventResponse update(@RequestBody EventUpdate event){
        return null;
    }

    @GetMapping
    public List<EventResponse> getAllEvents(){
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        return null;
    }

}
