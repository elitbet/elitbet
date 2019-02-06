package com.elitbet.live.scores.parser.model.odds;

import java.util.List;

public abstract class OddContainer {

    protected String bookmaker;

    public OddContainer(String bookmaker) {
        this.bookmaker = bookmaker;
    }

    public abstract List<Odd> toOdds();
}
