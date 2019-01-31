package com.elitbet.model;

import org.openqa.selenium.WebElement;

public class TournamentWrapper {

    private WebElement tournament;
    private WebElement date;

    public TournamentWrapper(WebElement tournament, WebElement date) {
        this.tournament = tournament;
        this.date = date;
    }

    public WebElement getTournament() {
        return tournament;
    }

    public WebElement getDate() {
        return date;
    }
}
