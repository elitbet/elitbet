package com.elitbet.live.scores.parser.model.odds;

public abstract class Odd {

    String bookmaker;
    String description;

    Odd(String bookmaker, String description) {
        this.bookmaker = bookmaker;
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Odd{");
        sb.append("bookmaker='").append(bookmaker).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
