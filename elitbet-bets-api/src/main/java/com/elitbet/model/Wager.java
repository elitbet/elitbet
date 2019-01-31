package com.elitbet.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "wager")
public class Wager {
    @Id
    @Column(name = "wager_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wagerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "outcome_id")
    private Outcome outcome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private User user;

    @Column(name = "wager_status")
    private WagerStatus wagerStatus;

    @Column(name = "odds")
    private double odds;

    @Column(name = "bet_value")
    private double betValue;

    public Wager() {
    }

    public long getWagerId() {
        return wagerId;
    }

    public void setWagerId(long wagerId) {
        this.wagerId = wagerId;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WagerStatus getWagerStatus() {
        return wagerStatus;
    }

    public void setWagerStatus(WagerStatus wagerStatus) {
        this.wagerStatus = wagerStatus;
    }

    public double getOdds() {
        return odds;
    }

    public void setOdds(double odds) {
        this.odds = odds;
    }

    public double getBetValue() {
        return betValue;
    }

    public void setBetValue(double betValue) {
        this.betValue = betValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wager wager = (Wager) o;
        return wagerId == wager.wagerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wagerId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Wager{");
        sb.append("wagerId=").append(wagerId);
        sb.append(", outcome=").append(outcome);
        sb.append(", user=").append(user);
        sb.append(", wagerStatus=").append(wagerStatus);
        sb.append(", odds=").append(odds);
        sb.append(", betValue=").append(betValue);
        sb.append('}');
        return sb.toString();
    }
}