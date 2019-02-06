package com.elitbet.live.scores.parser.model.odds;

import java.util.Arrays;
import java.util.List;

public class OddContainer_1x2 extends OddContainer {

    private double firstWin;
    private double draw;
    private double secondWin;
    private OutcomePeriod period;

    public OddContainer_1x2(String bookmaker, double firstWin, double draw, double secondWin) {
        super(bookmaker);
        this.firstWin = firstWin;
        this.draw = draw;
        this.secondWin = secondWin;
    }

    public OddContainer_1x2(String bookmaker, double firstWin, double draw, double secondWin, OutcomePeriod period) {
        super(bookmaker);
        this.firstWin = firstWin;
        this.draw = draw;
        this.secondWin = secondWin;
        this.period = period;
    }

    @Override
    public List<Odd> toOdds() {
        Odd firstWinOdd = new Odd(OutcomeType.HOME_WIN, period, bookmaker, firstWin);
        Odd drawOdd = new Odd(OutcomeType.DRAW, period, bookmaker, draw);
        Odd secondWinOdd = new Odd(OutcomeType.AWAY_WIN, period, bookmaker, secondWin);
        return Arrays.asList(firstWinOdd, drawOdd, secondWinOdd);
    }
}
