package com.elitbet.model;

import org.openqa.selenium.WebElement;

import java.time.LocalTime;

public class TournamentWrapper {

    private WebElement tournament;
    private WebElement date;
    private LocalTime update;

    public TournamentWrapper(WebElement tournament, WebElement date, LocalTime update) {
        this.tournament = tournament;
        this.date = date;
        this.update = update;
    }

    public WebElement getTournament() {
        return tournament;
    }

    public void setTournament(WebElement tournament) {
        this.tournament = tournament;
    }

    public WebElement getDate() {
        return date;
    }

    public void setDate(WebElement date) {
        this.date = date;
    }

    public LocalTime getUpdate() {
        return update;
    }

    public void setUpdate(LocalTime update) {
        this.update = update;
    }
}
