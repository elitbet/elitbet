package com.elitbet.model;

import org.openqa.selenium.WebElement;

public class StatisticWrapper {

    private WebElement tournament;
    private WebElement date;

    public StatisticWrapper(WebElement tournament, WebElement date) {
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
