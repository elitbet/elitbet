package org.elitbet.core.service;

import org.elitbet.core.model.Event;
import org.elitbet.core.model.dto.EventDTO;

import java.util.List;

public interface EventService {

    Object getEventStatistic(Integer eventID);
    List<Event> getLines();
    Event getEventByID(Integer eventID);
    void updateEvents(List<EventDTO> events);
    void updateEvent(EventDTO eventDTO);
    void updateStatistics(List<EventDTO> eventDTOS);
    void updateOutcomes(List<EventDTO> eventDTOS);

}
