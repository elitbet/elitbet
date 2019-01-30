package com.elitbet.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "EVENT")
public class Event {

    @Id
    @Column(name = "EVENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_TYPE_ID",referencedColumnName = "EVENT_TYPE_ID")
    private EventType eventType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_STATUS_ID",referencedColumnName = "EVENT_STATUS_ID")
    private EventStatus eventStatus;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STATISTIC_ID",referencedColumnName = "STATISTIC_ID")
    private Statistic statistic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TOURNAMENT_ID", referencedColumnName = "TOURNAMENT_ID")
    private Tournament tournament;

    @Column(name = "FLASHSCORE_ID")
    private String flashscoreId;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name = "START_DATETIME")
    private Date startDateTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    private List<Outcome> outcomeList = new ArrayList<>();

    public Event() {
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
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

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public String getFlashscoreId() {
        return flashscoreId;
    }

    public void setFlashscoreId(String flashscoreId) {
        this.flashscoreId = flashscoreId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
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
        sb.append(", statistic=").append(statistic);
        sb.append(", tournament=").append(tournament);
        sb.append(", flashscoreId='").append(flashscoreId).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", startDateTime=").append(startDateTime);
        sb.append('}');
        return sb.toString();
    }
}