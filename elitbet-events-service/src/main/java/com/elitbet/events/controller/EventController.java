package com.elitbet.events.controller;

import com.elitbet.events.model.dto.EventDTO;
import com.elitbet.events.model.dto.LiveScoresParserRequest;
import com.elitbet.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
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
}
