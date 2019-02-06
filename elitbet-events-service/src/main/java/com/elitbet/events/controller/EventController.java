package com.elitbet.events.controller;

import com.elitbet.events.model.Event;
import com.elitbet.events.model.dto.EventDTO;
import com.elitbet.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/events")
public class EventController {
    private final static Logger logger = Logger.getLogger(EventController.class.getName());

    @Autowired
    EventService eventService;

    @PatchMapping
    public void updateEvents(@RequestBody EventDTO[] event){
        logger.info(String.valueOf(event.length));
    }
}
