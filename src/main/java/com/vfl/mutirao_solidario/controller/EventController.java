package com.vfl.mutirao_solidario.controller;

import com.vfl.mutirao_solidario.controller.dto.EventRequest;
import com.vfl.mutirao_solidario.controller.dto.EventResponse;
import com.vfl.mutirao_solidario.controller.dto.EventUpdate;
import com.vfl.mutirao_solidario.enums.Status;
import com.vfl.mutirao_solidario.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/event")
@AllArgsConstructor
public class EventController {

    final EventService eventService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody EventRequest event) {
        eventService.create(event);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody EventUpdate event, @PathVariable("id") Long id){
        eventService.update(event, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<EventResponse> getEventsByFilter(@RequestParam (required = true) Double latitude,
                                            @RequestParam (required = true) Double longitude,
                                            @RequestParam (defaultValue = "100") Long radius,
                                            @RequestParam (required = false) LocalDateTime dateFrom,
                                            @RequestParam (required = false) LocalDateTime dateTo,
                                            @RequestParam (required = false) Long organizerId,
                                            @RequestParam (required = false) List<Status> status){
        return eventService.getEventsByFilter(latitude, longitude, radius, dateFrom, dateTo, organizerId, status);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        eventService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
