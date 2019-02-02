package com.elitbet.model.odds;

public abstract class Odd {

    String bookmaker;

    Odd(String bookmaker){
        this.bookmaker = bookmaker;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Odd{");
        sb.append("bookmaker='").append(bookmaker).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
