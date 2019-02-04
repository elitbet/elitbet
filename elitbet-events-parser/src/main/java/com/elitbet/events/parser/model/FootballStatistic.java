package com.elitbet.events.parser.model;

public class FootballStatistic {

    private String id;
    private String country;
    private String tournament;
    private String date;
    private String startTime;
    private String status;
    private String homeTeamName;
    private String awayTeamName;

    public FootballStatistic() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FootballStatistic{");
        sb.append("id='").append(id).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", tournament='").append(tournament).append('\'');
        sb.append(", date='").append(date).append('\'');
        sb.append(", startTime='").append(startTime).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", homeTeamName='").append(homeTeamName).append('\'');
        sb.append(", awayTeamName='").append(awayTeamName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String toURL(){
        return toString();
    }
}
