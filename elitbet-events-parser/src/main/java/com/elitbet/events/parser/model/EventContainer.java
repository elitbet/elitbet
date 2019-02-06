package com.elitbet.events.parser.model;

import java.time.LocalTime;
import java.util.Collection;

public class EventContainer {

    private LocalTime update;
    private EventType eventType;
    private Collection<Event> events;

    public EventContainer() {
    }

    public EventContainer(LocalTime update, EventType eventType, Collection<Event> events) {
        this.update = update;
        this.eventType = eventType;
        this.events = events;
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

    public Collection<Event> getEvents() {
        return events;
    }

    public void setEvents(Collection<Event> events) {
        this.events = events;
    }
}
