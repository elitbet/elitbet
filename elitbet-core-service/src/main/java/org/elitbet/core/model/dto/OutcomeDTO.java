package org.elitbet.core.model.dto;

import org.elitbet.core.model.OutcomePeriod;
import org.elitbet.core.model.OutcomeStatus;
import org.elitbet.core.model.OutcomeType;

public class OutcomeDTO {

    private Integer eventID;
    private OutcomeType type;
    private OutcomePeriod period;
    private OutcomeStatus status;
    private double parameters;
    private double odds;

    public OutcomeDTO() {
    }

    public OutcomeDTO(Integer eventID, OddDTO oddDTO){
        this.eventID = eventID;
        this.type = oddDTO.getType();
        this.period = oddDTO.getPeriod();
        this.parameters = oddDTO.getParameter();
        this.odds = oddDTO.getOdds();
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public OutcomeType getType() {
        return type;
    }

    public void setType(OutcomeType type) {
        this.type = type;
    }

    public OutcomePeriod getPeriod() {
        return period;
    }

    public void setPeriod(OutcomePeriod period) {
        this.period = period;
    }

    public OutcomeStatus getStatus() {
        return status;
    }

    public void setStatus(OutcomeStatus status) {
        this.status = status;
    }

    public double getParameters() {
        return parameters;
    }

    public void setParameters(double parameters) {
        this.parameters = parameters;
    }

    public double getOdds() {
        return odds;
    }

    public void setOdds(double odds) {
        this.odds = odds;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OutcomeDTO{");
        sb.append("eventID=").append(eventID);
        sb.append(", type=").append(type);
        sb.append(", period=").append(period);
        sb.append(", status=").append(status);
        sb.append(", parameters=").append(parameters);
        sb.append(", odds=").append(odds);
        sb.append('}');
        return sb.toString();
    }
}
