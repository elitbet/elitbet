package com.elitbet.model.odds;

public class Odd_1x2 extends Odd{
    private double firstWin;
    private double draw;
    private double secondWin;

    public Odd_1x2(String bookmaker, double firstWin, double draw, double secondWin) {
        super(bookmaker);
        this.firstWin = firstWin;
        this.draw = draw;
        this.secondWin = secondWin;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Odd_1x2{");
        sb.append("firstWin='").append(firstWin).append('\'');
        sb.append(", draw='").append(draw).append('\'');
        sb.append(", secondWin='").append(secondWin).append('\'');
        sb.append(", bookmaker='").append(bookmaker).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
