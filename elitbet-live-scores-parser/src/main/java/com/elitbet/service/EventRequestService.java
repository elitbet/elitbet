package com.elitbet.service;

import com.elitbet.model.EventRequest;
import com.elitbet.model.Priority;
import com.elitbet.repository.EventRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventRequestService {

    @Autowired
    EventRequestRepository eventRequestRepository;

    public List<EventRequest> getEventsWithLowPriority(){
        return eventRequestRepository.findAllByPriority(Priority.LOW);
    }

    public List<EventRequest> getEventsWithHighPriority(){
        return eventRequestRepository.findAllByPriority(Priority.HIGH);
    }

}
