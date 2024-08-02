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

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<Void> create(@RequestBody EventRequest event){
        eventService.create(event);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public ResponseEntity<Void> update(@RequestBody EventUpdate event){
        eventService.update(event);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<EventResponse> getAllEvents(){
        return eventService.getAllEvents();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        eventService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
