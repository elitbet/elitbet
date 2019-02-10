package org.elitbet.core.model.dto;

import org.elitbet.core.model.OutcomePeriod;
import org.elitbet.core.model.OutcomeType;

public class OddDTO {

    private OutcomeType type;
    private OutcomePeriod period;
    private double parameter;
    private double odds;

    public OddDTO() {
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

    public double getParameter() {
        return parameter;
    }

    public void setParameter(double parameter) {
        this.parameter = parameter;
    }

    public double getOdds() {
        return odds;
    }

    public void setOdds(double odds) {
        this.odds = odds;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OddDTO{");
        sb.append("type=").append(type);
        sb.append(", period=").append(period);
        sb.append(", parameter=").append(parameter);
        sb.append(", odds=").append(odds);
        sb.append('}');
        return sb.toString();
    }
}
