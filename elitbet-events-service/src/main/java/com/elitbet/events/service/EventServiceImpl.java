package com.elitbet.events.service;

import com.elitbet.events.model.Event;
import com.elitbet.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository eventRepository;

    @Override
    public void updateEventList(Event[] eventList) {

    }
}
