package org.elitbet.core.service;

import org.elitbet.core.model.Event;
import org.elitbet.core.model.dto.EventDTO;
import org.elitbet.core.model.dto.OddDTO;
import org.elitbet.core.model.dto.OutcomeDTO;
import org.elitbet.core.model.dto.StatisticDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    OutcomeService outcomeService;

    @Override
    public Object getEventStatistic(Integer eventID) {
        return null;
    }

    @Override
    public List<Event> getLines() {
        return null;
    }

    @Override
    public Event getEventByID(Integer eventID) {
        return null;
    }

    @Override
    public void updateEvents(List<EventDTO> events) {
        updateStatistics(events);
        updateOutcomes(events);
        events.forEach(System.out::println);
    }

    @Override
    public void updateEvent(EventDTO eventDTO) {
    }

    @Override
    public void updateStatistics(List<EventDTO> eventDTOS) {
        List<StatisticDTO> statisticDTOS = new LinkedList<>();
        for(EventDTO eventDTO:eventDTOS){
            StatisticDTO statisticDTO = new StatisticDTO(eventDTO);
            statisticDTOS.add(statisticDTO);
        }
        // TODO: 10.02.19 send patch in statistic service
        System.out.println(statisticDTOS);

    }

    @Override
    public void updateOutcomes(List<EventDTO> eventDTOS) {
        //List<OutcomeDTO> outcomeDTOS = new LinkedList<>();
        for(EventDTO eventDTO:eventDTOS){
            OddDTO[] oddDTOS = eventDTO.getOdds();
            Integer eventID = eventDTO.getEventID();
            for(OddDTO oddDTO:oddDTOS){
                OutcomeDTO outcomeDTO = new OutcomeDTO(eventID, oddDTO);
                outcomeService.updateOutcome(outcomeDTO);
                //outcomeDTOS.add(outcomeDTO);
            }
        }
        // TODO: 10.02.19 send patch to outcome service
        //System.out.println(outcomeDTOS);
    }
}
