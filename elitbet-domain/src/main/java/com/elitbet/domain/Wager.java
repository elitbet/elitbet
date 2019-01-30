package com.elitbet.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "WAGER")
public class Wager {
    @Id
    @Column(name = "WAGER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wagerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OUTCOME_ID", referencedColumnName = "OUTCOME_ID")
    private Outcome outcome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @OneToOne
    @JoinColumn(name = "WAGER_STATUS_ID",referencedColumnName = "WAGER_STATUS_ID")
    private WagerStatus wagerStatus;

    @Column(name = "ODDS")
    private double odds;

    @Column(name = "BET_VALUE")
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
        sb.append(", client=").append(client);
        sb.append(", wagerStatus=").append(wagerStatus);
        sb.append(", odds=").append(odds);
        sb.append(", betValue=").append(betValue);
        sb.append('}');
        return sb.toString();
    }
}