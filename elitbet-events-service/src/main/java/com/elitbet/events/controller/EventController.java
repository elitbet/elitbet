package com.elitbet.events.controller;

import com.elitbet.events.model.Event;
import com.elitbet.events.model.dto.EventDTO;
import com.elitbet.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {
    @Autowired
    EventService eventService;

    @PutMapping
    public void updateEvents(@RequestBody EventDTO event){
        System.out.println(event);
    }

}
