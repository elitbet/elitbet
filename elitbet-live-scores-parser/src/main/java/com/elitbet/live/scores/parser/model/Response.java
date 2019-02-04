package com.elitbet.live.scores.parser.model;

import com.elitbet.live.scores.parser.model.statistic.Statistic;
import com.elitbet.live.scores.parser.model.odds.Odd;

import java.time.LocalTime;
import java.util.List;

public class Response {

    private String eventId;
    private Statistic statistic;
    private List<Odd> oddList;
    private LocalTime update;

    public Response() {
    }

    public Response(String eventId, Statistic statistic, List<Odd> oddList, LocalTime update) {
        this.eventId = eventId;
        this.statistic = statistic;
        this.oddList = oddList;
        this.update = update;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }

    public List<Odd> getOddList() {
        return oddList;
    }

    public void setOddList(List<Odd> oddList) {
        this.oddList = oddList;
    }

    public String toUrl(){
        return toString();
    }

    public LocalTime getUpdate() {
        return update;
    }

    public void setUpdate(LocalTime update) {
        this.update = update;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Response{");
        sb.append("eventId='").append(eventId).append('\'');
        sb.append(", statistic=").append(statistic);
        sb.append(", oddList=").append(oddList);
        sb.append(", update=").append(update);
        sb.append('}');
        return sb.toString();
    }
}
