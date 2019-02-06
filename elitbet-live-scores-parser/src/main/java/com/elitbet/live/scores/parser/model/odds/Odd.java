package com.elitbet.live.scores.parser.model.odds;

public class Odd {

    private OutcomeType outcomeType;
    private OutcomePeriod outcomePeriod;
    private String bookmaker;
    private double odds;
    private double parameter;

    Odd(OutcomeType outcomeType, OutcomePeriod outcomePeriod, String bookmaker, double odds) {
        this.outcomeType = outcomeType;
        this.outcomePeriod = outcomePeriod;
        this.bookmaker = bookmaker;
        this.odds = odds;
    }

    Odd(OutcomeType outcomeType, OutcomePeriod outcomePeriod, String bookmaker, double odds, double parameter) {
        this.outcomeType = outcomeType;
        this.outcomePeriod = outcomePeriod;
        this.bookmaker = bookmaker;
        this.odds = odds;
        this.parameter = parameter;
    }

    public OutcomeType getOutcomeType() {
        return outcomeType;
    }

    public OutcomePeriod getOutcomePeriod() {
        return outcomePeriod;
    }

    public String getBookmaker() {
        return bookmaker;
    }

    public double getOdds() {
        return odds;
    }

    public double getParameter() {
        return parameter;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Odd{");
        sb.append("outcomeType=").append(outcomeType);
        sb.append(", outcomePeriod=").append(outcomePeriod);
        sb.append(", bookmaker='").append(bookmaker).append('\'');
        sb.append(", odds=").append(odds);
        sb.append(", parameter=").append(parameter);
        sb.append('}');
        return sb.toString();
    }
}
