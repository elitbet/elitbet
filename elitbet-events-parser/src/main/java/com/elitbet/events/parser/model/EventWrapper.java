package com.elitbet.events.parser.model;

import org.openqa.selenium.WebElement;

import java.time.LocalTime;

public class EventWrapper {

    private WebElement tournament;
    private WebElement date;
    private WebElement country;
    private WebElement event;
    private LocalTime update;

    public EventWrapper(WebElement tournament, WebElement date, WebElement country, WebElement event, LocalTime update) {
        this.tournament = tournament;
        this.date = date;
        this.country = country;
        this.event = event;
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

    public WebElement getCountry() {
        return country;
    }

    public void setCountry(WebElement country) {
        this.country = country;
    }

    public WebElement getEvent() {
        return event;
    }

    public void setEvent(WebElement event) {
        this.event = event;
    }

    public LocalTime getUpdate() {
        return update;
    }

    public void setUpdate(LocalTime update) {
        this.update = update;
    }
}
