package com.elitbet.model;

import java.util.ArrayList;
import java.util.List;

public class FootballOdds {

    private String id;
    private List<BookmakerOdd> firstWinOdds = new ArrayList<>();
    private List<BookmakerOdd> drawOdds = new ArrayList<>();
    private List<BookmakerOdd> secondWinOdds = new ArrayList<>();

    class BookmakerOdd{
        String bookmaker;
        double odd;

        public BookmakerOdd(String bookmaker, double odd) {
            this.bookmaker = bookmaker;
            this.odd = odd;
        }
    }

    public FootballOdds() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<BookmakerOdd> getFirstWinOdds() {
        return firstWinOdds;
    }

    public void setFirstWinOdds(List<BookmakerOdd> firstWinOdds) {
        this.firstWinOdds = firstWinOdds;
    }

    public List<BookmakerOdd> getDrawOdds() {
        return drawOdds;
    }

    public void setDrawOdds(List<BookmakerOdd> drawOdds) {
        this.drawOdds = drawOdds;
    }

    public List<BookmakerOdd> getSecondWinOdds() {
        return secondWinOdds;
    }

    public void setSecondWinOdds(List<BookmakerOdd> secondWinOdds) {
        this.secondWinOdds = secondWinOdds;
    }
}