package com.elitbet.wagers.model.dto;

import com.elitbet.wagers.model.entities.Outcome;
import com.elitbet.wagers.model.entities.Wager;
import com.elitbet.wagers.model.entities.WagerStatus;

import java.util.Date;

public class WagerDTO {
    private long wagerId;

    private long outcomeId;

    private UserPublicDTO userDTO;

    private WagerStatus wagerStatus;

    private double odds;

    private double betValue;

    private double payout;

    private long time;

    public WagerDTO() {

    }

    public WagerDTO(Wager wager) {
        this.wagerId = wager.getWagerId();
        this.outcomeId = wager.getOutcome().getOutcomeId();
        this.userDTO = new UserPublicDTO(wager.getUser());
        this.wagerStatus = wager.getWagerStatus();
        this.odds = wager.getOdds();
        this.betValue = wager.getBetValue();
        this.payout = wager.getPayout();
        this.time = wager.getTime().getTime();
    }

    public long getWagerId() {
        return wagerId;
    }

    public void setWagerId(long wagerId) {
        this.wagerId = wagerId;
    }

    public long getOutcomeId() {
        return outcomeId;
    }

    public void setOutcomeId(long outcomeId) {
        this.outcomeId = outcomeId;
    }

    public UserPublicDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Wager toEntity(){
        Wager wager = new Wager();
        wager.setWagerId(wagerId);
        Outcome outcome = new Outcome();
        outcome.setOutcomeId(outcomeId);
        wager.setOutcome(outcome);
        wager.setUser(null);
        wager.setWagerStatus(wagerStatus);
        wager.setOdds(odds);
        wager.setBetValue(betValue);
        wager.setPayout(payout);
        wager.setTime(new Date(time));
        return wager;
    }

}
