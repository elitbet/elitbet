package com.elitbet.bets.api.model.dto;

import com.elitbet.bets.api.model.entities.Outcome;
import com.elitbet.bets.api.model.entities.OutcomeStatus;
import com.elitbet.bets.api.model.entities.Wager;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class OutcomeDTO {

    private long outcomeId;


    private OutcomeStatus outcomeStatus;


    private List<WagerDTO> wagerDTOS;

    public OutcomeDTO() {
    }

    public OutcomeDTO(Outcome outcome) {
        this.outcomeId = outcome.getOutcomeId();
        this.outcomeStatus = outcome.getOutcomeStatus();
        wagerDTOS = outcome.getWagerList().stream().map(WagerDTO::new).collect(Collectors.toList());
    }

    public long getOutcomeId() {
        return outcomeId;
    }

    public void setOutcomeId(long outcomeId) {
        this.outcomeId = outcomeId;
    }

    public OutcomeStatus getOutcomeStatus() {
        return outcomeStatus;
    }

    public void setOutcomeStatus(OutcomeStatus outcomeStatus) {
        this.outcomeStatus = outcomeStatus;
    }

    public List<WagerDTO> getWagerDTOS() {
        return wagerDTOS;
    }

    public void setWagerDTOS(List<WagerDTO> wagerDTOS) {
        this.wagerDTOS = wagerDTOS;
    }
}
