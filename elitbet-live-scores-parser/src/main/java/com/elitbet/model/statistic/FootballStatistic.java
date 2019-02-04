package com.elitbet.model.statistic;

public class FootballStatistic extends Statistic {

    private String country;
    private String tournament;
    private String date;
    private String startTime;
    private String status;
    private String homeTeamName;
    private String awayTeamName;
    private int homeTeamGoals;
    private int awayTeamGoals;
    private int homeTeamFirstHalfGoals;
    private int awayTeamFirstHalfGoals;

    public FootballStatistic() {}

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(int homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(int awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FootballStatistic{");
        sb.append(", country='").append(country).append('\'');
        sb.append(", tournament='").append(tournament).append('\'');
        sb.append(", date='").append(date).append('\'');
        sb.append(", startTime='").append(startTime).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", homeTeamName='").append(homeTeamName).append('\'');
        sb.append(", awayTeamName='").append(awayTeamName).append('\'');
        sb.append(", homeTeamGoals=").append(homeTeamGoals);
        sb.append(", awayTeamGoals=").append(awayTeamGoals);
        sb.append(", homeTeamFirstHalfGoals=").append(homeTeamFirstHalfGoals);
        sb.append(", awayTeamFirstHalfGoals=").append(awayTeamFirstHalfGoals);
        sb.append('}');
        return sb.toString();
    }

}