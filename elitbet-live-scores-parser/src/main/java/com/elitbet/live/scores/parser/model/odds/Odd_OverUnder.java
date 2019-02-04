package com.elitbet.live.scores.parser.model.odds;

public class Odd_OverUnder extends Odd {

    private double total;
    private double over;
    private double under;

    public Odd_OverUnder(String bookmaker, String description, double total, double over, double under) {
        super(bookmaker, description);
        this.total = total;
        this.over = over;
        this.under = under;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Odd_OverUnder{");
        sb.append("total=").append(total);
        sb.append(", over=").append(over);
        sb.append(", under=").append(under);
        sb.append(", bookmaker='").append(bookmaker).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
