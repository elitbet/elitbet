package com.elitbet.live.scores.parser.model;

import java.time.LocalTime;
import java.util.List;

public class EventContainer {

    private LocalTime update;
    private EventType eventType;
    private List<Event> eventList;

    public EventContainer(LocalTime update, EventType eventType, List<Event> eventList) {
        this.update = update;
        this.eventType = eventType;
        this.eventList = eventList;
    }

    public LocalTime getUpdate() {
        return update;
    }

    public void setUpdate(LocalTime update) {
        this.update = update;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

}
