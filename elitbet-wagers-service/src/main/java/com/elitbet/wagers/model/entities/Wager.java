package com.elitbet.wagers.model.entities;

import javax.persistence.*;
import java.util.Date;
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
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "wager_status")
    @Enumerated(EnumType.STRING)
    private WagerStatus wagerStatus;

    @Column(name = "odds")
    private double odds;

    @Column(name = "bet")
    private double betValue;

    @Column(name = "payout")
    private double payout;

    @Column(name = "time")
    private Date time;

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


    public double getPayout() {
        return payout;
    }

    public void setPayout(double payout) {
        this.payout = payout;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
        return "Wager{" +
                "wagerId=" + wagerId +
                ", outcome=" + outcome +
                ", user=" + user +
                ", wagerStatus=" + wagerStatus +
                ", odds=" + odds +
                ", betValue=" + betValue +
                ", payout=" + payout +
                ", time=" + time +
                '}';
    }
}