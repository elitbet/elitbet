package com.elitbet.live.scores.parser.model.statistic;

public class Statistic {

    protected String tournament;
    protected String startTime;
    protected String status;

    public Statistic() {
    }

    public Statistic(String tournament, String startTime, String status) {
        this.tournament = tournament;
        this.startTime = startTime;
        this.status = status;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
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