package org.elitbet.core.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @Column(name = "event_id")
    private String eventId;

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(name = "event_status")
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @Column(name = "START_TIME")
    private Date startTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    private List<Outcome> outcomeList = new ArrayList<>();

    public Event() {
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

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public List<Outcome> getOutcomeList() {
        return outcomeList;
    }

    public void setOutcomeList(List<Outcome> outcomeList) {
        this.outcomeList = outcomeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(eventId, event.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("eventId=").append(eventId);
        sb.append(", eventType=").append(eventType);
        sb.append(", eventStatus=").append(eventStatus);
        sb.append(", tournament=").append(tournament);
        sb.append(", startTime=").append(startTime);
        sb.append('}');
        return sb.toString();
    }
}