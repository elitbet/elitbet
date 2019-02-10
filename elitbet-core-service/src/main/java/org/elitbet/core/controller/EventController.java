package org.elitbet.core.controller;

import org.elitbet.core.model.Event;
import org.elitbet.core.model.dto.EventDTO;
import org.elitbet.core.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController("/events")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping
    public ResponseEntity<List<Event>> getLine(){
        List<Event> lines = eventService.getLines();
        return ResponseEntity.ok(lines);
    }

    @PostMapping
    public ResponseEntity<?> updateEvents(@RequestBody EventDTO[] events){
        eventService.updateEvents(Arrays.asList(events));
        return ResponseEntity.ok().body("Updated");
    }

}
