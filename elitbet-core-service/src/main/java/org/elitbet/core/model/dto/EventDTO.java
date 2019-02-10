package org.elitbet.core.model.dto;

import org.elitbet.core.model.EventType;

import java.util.Arrays;

public class EventDTO {

    private Integer eventID;
    private EventType eventType;
    private String tournament;
    private String startTime;
    private String status;
    private Object statistic;
    private OddDTO[] odds;

    public EventDTO() {
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
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

    public Object getStatistic() {
        return statistic;
    }

    public void setStatistic(Object statistic) {
        this.statistic = statistic;
    }

    public OddDTO[] getOdds() {
        return odds;
    }

    public void setOdds(OddDTO[] odds) {
        this.odds = odds;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EventDTO{");
        sb.append("eventID=").append(eventID);
        sb.append(", eventType=").append(eventType);
        sb.append(", tournament='").append(tournament).append('\'');
        sb.append(", startTime='").append(startTime).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", statistic=").append(statistic);
        sb.append(", odds=").append(Arrays.toString(odds));
        sb.append('}');
        return sb.toString();
    }
}
