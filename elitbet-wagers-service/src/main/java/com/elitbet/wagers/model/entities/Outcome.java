package com.elitbet.wagers.model.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "outcome")
public class Outcome {
    @Id
    @Column(name="outcome_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long outcomeId;

    @Column(name = "outcome_status")
    @Enumerated(EnumType.STRING)
    private OutcomeStatus outcomeStatus;

    @OneToMany(mappedBy = "outcome")
    private List<Wager> wagerList = new ArrayList<>();

    @Column(name="outcome_type")
    @Enumerated(EnumType.STRING)
    private OutcomeType outcomeType;

    @Column(name="odds")
    private double odds;

    @Column(name="is_displayed_in_lines")
    private boolean isDisplayedInLines;

    @Column(name="outcome_period")
    @Enumerated(EnumType.STRING)
    private OutcomePeriod outcomePeriod;

    public Outcome() {
    }

    public Long getOutcomeId() {
        return outcomeId;
    }

    public void setOutcomeId(Long outcomeId) {
        this.outcomeId = outcomeId;
    }

    public OutcomeStatus getOutcomeStatus() {
        return outcomeStatus;
    }

    public void setOutcomeStatus(OutcomeStatus outcomeStatus) {
        this.outcomeStatus = outcomeStatus;
    }

    public List<Wager> getWagerList() {
        return wagerList;
    }

    public void setWagerList(List<Wager> wagerList) {
        this.wagerList = wagerList;
    }

    public OutcomeType getOutcomeType() {
        return outcomeType;
    }

    public void setOutcomeType(OutcomeType outcomeType) {
        this.outcomeType = outcomeType;
    }

    public double getOdds() {
        return odds;
    }

    public void setOdds(double odds) {
        this.odds = odds;
    }

    public boolean isDisplayedInLines() {
        return isDisplayedInLines;
    }

    public void setDisplayedInLines(boolean displayedInLines) {
        isDisplayedInLines = displayedInLines;
    }

    public OutcomePeriod getOutcomePeriod() {
        return outcomePeriod;
    }

    public void setOutcomePeriod(OutcomePeriod outcomePeriod) {
        this.outcomePeriod = outcomePeriod;
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
        sb.append(", outcomeStatus=").append(outcomeStatus);
        sb.append(", wagerList=").append(wagerList);
        sb.append('}');
        return sb.toString();
    }
}