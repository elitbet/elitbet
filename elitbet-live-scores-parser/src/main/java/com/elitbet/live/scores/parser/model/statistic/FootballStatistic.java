package com.elitbet.live.scores.parser.model.statistic;

public class FootballStatistic extends Statistic {

    private String homeTeamName;
    private String awayTeamName;
    private int homeTeamFullTimeGoals;
    private int awayTeamFullTimeGoals;
    private int homeTeamFirstHalfGoals;
    private int awayTeamFirstHalfGoals;
    private int homeTeamSecondHalfGoals;
    private int awayTeamSecondHalfGoals;


    public FootballStatistic(Statistic statistic, String homeTeamName, String awayTeamName,
                             int homeTeamFullTimeGoals, int awayTeamFullTimeGoals, int homeTeamFirstHalfGoals,
                             int awayTeamFirstHalfGoals, int homeTeamSecondHalfGoals, int awayTeamSecondHalfGoals) {

        this.tournament = statistic.tournament;
        this.status = statistic.status;
        this.startTime = statistic.startTime;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeTeamFullTimeGoals = homeTeamFullTimeGoals;
        this.awayTeamFullTimeGoals = awayTeamFullTimeGoals;
        this.homeTeamFirstHalfGoals = homeTeamFirstHalfGoals;
        this.awayTeamFirstHalfGoals = awayTeamFirstHalfGoals;
        this.homeTeamSecondHalfGoals = homeTeamSecondHalfGoals;
        this.awayTeamSecondHalfGoals = awayTeamSecondHalfGoals;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public int getHomeTeamFullTimeGoals() {
        return homeTeamFullTimeGoals;
    }

    public void setHomeTeamFullTimeGoals(int homeTeamFullTimeGoals) {
        this.homeTeamFullTimeGoals = homeTeamFullTimeGoals;
    }

    public int getAwayTeamFullTimeGoals() {
        return awayTeamFullTimeGoals;
    }

    public void setAwayTeamFullTimeGoals(int awayTeamFullTimeGoals) {
        this.awayTeamFullTimeGoals = awayTeamFullTimeGoals;
    }

    public int getHomeTeamFirstHalfGoals() {
        return homeTeamFirstHalfGoals;
    }

    public void setHomeTeamFirstHalfGoals(int homeTeamFirstHalfGoals) {
        this.homeTeamFirstHalfGoals = homeTeamFirstHalfGoals;
    }

    public int getAwayTeamFirstHalfGoals() {
        return awayTeamFirstHalfGoals;
    }

    public void setAwayTeamFirstHalfGoals(int awayTeamFirstHalfGoals) {
        this.awayTeamFirstHalfGoals = awayTeamFirstHalfGoals;
    }

    public int getHomeTeamSecondHalfGoals() {
        return homeTeamSecondHalfGoals;
    }

    public void setHomeTeamSecondHalfGoals(int homeTeamSecondHalfGoals) {
        this.homeTeamSecondHalfGoals = homeTeamSecondHalfGoals;
    }

    public int getAwayTeamSecondHalfGoals() {
        return awayTeamSecondHalfGoals;
    }

    public void setAwayTeamSecondHalfGoals(int awayTeamSecondHalfGoals) {
        this.awayTeamSecondHalfGoals = awayTeamSecondHalfGoals;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FootballStatistic{");
        sb.append("homeTeamName='").append(homeTeamName).append('\'');
        sb.append(", awayTeamName='").append(awayTeamName).append('\'');
        sb.append(", homeTeamFullTimeGoals=").append(homeTeamFullTimeGoals);
        sb.append(", awayTeamFullTimeGoals=").append(awayTeamFullTimeGoals);
        sb.append(", homeTeamFirstHalfGoals=").append(homeTeamFirstHalfGoals);
        sb.append(", awayTeamFirstHalfGoals=").append(awayTeamFirstHalfGoals);
        sb.append(", homeTeamSecondHalfGoals=").append(homeTeamSecondHalfGoals);
        sb.append(", awayTeamSecondHalfGoals=").append(awayTeamSecondHalfGoals);
        sb.append(", tournament='").append(tournament).append('\'');
        sb.append(", startTime='").append(startTime).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}