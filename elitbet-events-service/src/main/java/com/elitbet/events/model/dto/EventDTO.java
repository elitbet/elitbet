package com.elitbet.events.model.dto;

import com.elitbet.events.model.Event;
import com.elitbet.events.model.EventType;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventDTO {

    private String eventId;

    private EventType eventType;

    private String tournament;

    private String date;

    private LocalTime startTime;

    private String status;

    private String homeTeam;

    private String awayTeam;

    public EventDTO() {
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EventDTO{");
        sb.append("eventId='").append(eventId).append('\'');
        sb.append(", eventType=").append(eventType);
        sb.append(", tournament='").append(tournament).append('\'');
        sb.append(", date=").append(date);
        sb.append(", startTime=").append(startTime);
        sb.append(", status='").append(status).append('\'');
        sb.append(", homeTeam='").append(homeTeam).append('\'');
        sb.append(", awayTeam='").append(awayTeam).append('\'');
        sb.append('}');
        return sb.toString();
    }

    Event toEntity(){
        Event event = new Event();
        event.setEventId(this.eventId);
        event.setEventType(this.eventType);
        return event;
    }
}
