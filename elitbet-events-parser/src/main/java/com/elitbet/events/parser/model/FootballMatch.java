package com.elitbet.events.parser.model;

public class FootballMatch extends Event {

    private String homeTeamName;
    private String awayTeamName;

    public FootballMatch(String id, String tournament, String date, String startTime, String status, String homeTeamName, String awayTeamName) {
        super(id, tournament, date, startTime, status);
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
    }

    public FootballMatch() {
        eventType = EventType.FOOTBALL_MATCH;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FootballMatch{");
        sb.append("homeTeamName='").append(homeTeamName).append('\'');
        sb.append(", awayTeamName='").append(awayTeamName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
