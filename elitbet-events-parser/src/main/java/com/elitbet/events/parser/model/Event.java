package com.elitbet.events.parser.model;

public class Event {

    private String eventId;
    private String tournament;
    private String date;
    private String startTime;
    private String status;

    public Event() {
    }

    public Event(String eventId, String tournament, String date, String startTime, String status) {
        this.eventId = eventId;
        this.tournament = tournament;
        this.date = date;
        this.startTime = startTime;
        this.status = status;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}