package com.elitbet.events.controller;

import com.elitbet.events.model.Event;
import com.elitbet.events.model.dto.LiveScoresParserRequest;
import com.elitbet.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/events")
public class EventController {

    private final static Logger logger = Logger.getLogger(EventController.class.getName());

    @Autowired
    EventService eventService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void test(){
        logger.info("ok");
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.PATCH, consumes = "application/json")
    public void updateEvents(@RequestBody LiveScoresParserRequest request){
        logger.info(String.valueOf(request.getEvents().length));
        logger.info(request.toString());
    }

    @GetMapping()
    public ResponseEntity<List<Event>> getAllEvents(){
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }
}
