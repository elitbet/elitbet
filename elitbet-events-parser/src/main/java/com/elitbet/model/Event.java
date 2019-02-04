package com.elitbet.model;

public class Event {

    private String id;
    private String tournament;
    private String date;
    private String startTime;
    private String status;

    public Event() {
    }

    public Event(String id, String tournament, String date, String startTime, String status) {
        this.id = id;
        this.tournament = tournament;
        this.date = date;
        this.startTime = startTime;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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