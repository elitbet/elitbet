package com.elitbet.model.odds;

public class Odd_1x2 extends Odd{

    private double firstWin;
    private double draw;
    private double secondWin;

    public Odd_1x2(String bookmaker, String description, double firstWin, double draw, double secondWin) {
        super(bookmaker, description);
        this.firstWin = firstWin;
        this.draw = draw;
        this.secondWin = secondWin;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Odd_1x2{");
        sb.append("firstWin=").append(firstWin);
        sb.append(", draw=").append(draw);
        sb.append(", secondWin=").append(secondWin);
        sb.append(", bookmaker='").append(bookmaker).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
