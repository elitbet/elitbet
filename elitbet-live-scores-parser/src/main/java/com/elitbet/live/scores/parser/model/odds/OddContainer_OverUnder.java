package com.elitbet.live.scores.parser.model.odds;

import java.util.Arrays;
import java.util.List;

public class OddContainer_OverUnder extends OddContainer {

    private double total;
    private double over;
    private double under;
    private OutcomePeriod period;

    public OddContainer_OverUnder(String bookmaker, double total, double over, double under, OutcomePeriod period) {
        super(bookmaker);
        this.total = total;
        this.over = over;
        this.under = under;
        this.period = period;
    }

    @Override
    public List<Odd> toOdds() {
        Odd totalOver = new Odd(OutcomeType.TOTAL_OVER, period, bookmaker, over, total);
        Odd totalUnder = new Odd(OutcomeType.TOTAL_UNDER, period, bookmaker, under, total);
        return Arrays.asList(totalOver, totalUnder);
    }

}
