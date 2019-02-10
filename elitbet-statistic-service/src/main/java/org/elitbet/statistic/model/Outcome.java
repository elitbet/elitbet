package org.elitbet.statistic.model;

import java.util.Objects;

public class Outcome {

    private OutcomeType type;
    private OutcomePeriod period;
    private OutcomeStatus status;

    public Outcome() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outcome outcome = (Outcome) o;
        return type == outcome.type &&
                period == outcome.period;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, period);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Outcome{");
        sb.append("type=").append(type);
        sb.append(", period=").append(period);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
