package com.elitbet.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class FootballStatistic {

    private String id;
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
        sb.append("id='").append(id).append('\'');
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

    public String toURL() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.
                    append("access_token=").
                    append(URLEncoder.encode("1", "UTF-8")).
                    append("&start_timestamp=").
                    append(URLEncoder.encode(String.valueOf(date), "UTF-8")).
                    append("&parameters=home_name:").
                    append(URLEncoder.encode(homeTeamName, "UTF-8")).
                    append(";away_name:").
                    append(URLEncoder.encode(awayTeamName, "UTF-8")).
                    append(";home_goals:").
                    append(URLEncoder.encode(String.valueOf(homeTeamGoals), "UTF-8")).
                    append(";away_goals:").
                    append(URLEncoder.encode(String.valueOf(awayTeamGoals), "UTF-8")).
                    append("&tournament=").
                    append(URLEncoder.encode(tournament, "UTF-8")).
                    append("&status=").
                    append(URLEncoder.encode(status,"UTF-8")).
                    append("&id=").
                    append(URLEncoder.encode(id,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
