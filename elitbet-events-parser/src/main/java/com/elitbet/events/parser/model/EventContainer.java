package com.elitbet.events.parser.model;

import java.time.LocalTime;
import java.util.Collection;

public class EventContainer {

    private LocalTime update;
    private Collection<Event> events;

    public EventContainer(LocalTime update, Collection<Event> events) {
        this.update = update;
        this.events = events;
    }

    public LocalTime getUpdate() {
        return update;
    }

    public void setUpdate(LocalTime update) {
        this.update = update;
    }

    public Collection<Event> getEvents() {
        return events;
    }

    public void setEvents(Collection<Event> events) {
        this.events = events;
    }

}
