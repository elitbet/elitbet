package com.elitbet.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TOURNAMENT")
public class Tournament {
    @Id
    @Column(name = "TOURNAMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tournamentId;

    @Column(name="DESCRIPTION")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tournament")
    private List<Event> eventList = new ArrayList<>();

    public Tournament() {
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public void addEvent(Event event){
        eventList.add(event);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament that = (Tournament) o;
        return Objects.equals(tournamentId, that.tournamentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tournamentId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tournament{");
        sb.append("tournamentId=").append(tournamentId);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}