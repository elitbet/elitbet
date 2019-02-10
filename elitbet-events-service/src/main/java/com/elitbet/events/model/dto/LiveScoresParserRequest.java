package com.elitbet.events.model.dto;

import java.time.LocalTime;
import java.util.Arrays;

public class LiveScoresParserRequest {

    private LocalTime update;

    private EventDTO[] events;

    public LiveScoresParserRequest() {
    }

    public LocalTime getUpdate() {
        return update;
    }

    public void setUpdate(LocalTime update) {
        this.update = update;
    }

    public EventDTO[] getEvents() {
        return events;
    }

    public void setEvents(EventDTO[] events) {
        this.events = events;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LiveScoresParserRequest{");
        sb.append("update=").append(update);
        sb.append(", events=").append(Arrays.toString(events));
        sb.append('}');
        return sb.toString();
    }
}
