package com.vfl.mutirao_solidario.controller;

import com.vfl.mutirao_solidario.controller.dto.EventRequest;
import com.vfl.mutirao_solidario.controller.dto.EventResponse;
import com.vfl.mutirao_solidario.controller.dto.EventUpdate;
import com.vfl.mutirao_solidario.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/event")
public class EventController {

//    List events by location, date
//	- Create event
//	- Update event
//	- List event by user
    @Autowired
    EventService eventService;

    @PostMapping(value = "/create")
    public ResponseEntity<EventResponse> create(@RequestBody EventRequest event){
        eventService.create(event);
        return null;
    }

    @PutMapping(value = "/update")
    public EventResponse update(@RequestBody EventUpdate event){
        return null;
    }

    @GetMapping
    public List<EventResponse> getAllEvents(){
        return null;
    }

    @DeleteMapping("/id")
    public ResponseEntity delete(@PathVariable("id") Long id){
        return null;
    }

}
