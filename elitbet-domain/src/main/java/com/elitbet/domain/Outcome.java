package com.elitbet.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "OUTCOME")
public class Outcome {
    @Id
    @Column(name="OUTCOME_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long outcomeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OUTCOME_TYPE_ID",referencedColumnName = "OUTCOME_TYPE_ID")
    private OutcomeType outcomeType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OUTCOME_STATUS_ID",referencedColumnName = "OUTCOME_STATUS_ID")
    private OutcomeStatus outcomeStatus;

    @Column(name = "PARAMETERS")
    private String parameters;

    @Column(name="ODDS")
    private double odds;

    public Outcome() {
    }

    public Long getOutcomeId() {
        return outcomeId;
    }

    public void setOutcomeId(Long outcomeId) {
        this.outcomeId = outcomeId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public OutcomeType getOutcomeType() {
        return outcomeType;
    }

    public void setOutcomeType(OutcomeType outcomeType) {
        this.outcomeType = outcomeType;
    }

    public OutcomeStatus getOutcomeStatus() {
        return outcomeStatus;
    }

    public void setOutcomeStatus(OutcomeStatus outcomeStatus) {
        this.outcomeStatus = outcomeStatus;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public double getOdds() {
        return odds;
    }

    public void setOdds(double odds) {
        this.odds = odds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outcome outcome = (Outcome) o;
        return Objects.equals(outcomeId, outcome.outcomeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(outcomeId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Outcome{");
        sb.append("outcomeId=").append(outcomeId);
        sb.append(", event=").append(event);
        sb.append(", outcomeType=").append(outcomeType);
        sb.append(", outcomeStatus=").append(outcomeStatus);
        sb.append(", parameters='").append(parameters).append('\'');
        sb.append(", odds=").append(odds);
        sb.append('}');
        return sb.toString();
    }
}