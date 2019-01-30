package com.elitbet.domain;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity(name= "FOOTBALL_MATCH_STATISTIC")
@Table(name="FOOTBALL_MATCH_STATISTIC")
@PrimaryKeyJoinColumn(name = "STATISTIC_ID")
public class FootballMatchStatistic extends Statistic {

    @Column(name="HOME_NAME")
    private String homeName;

    @Column(name="AWAY_NAME")
    private String awayName;

    @Column(name="HOME_GOALS")
    private int homeGoals;

    @Column(name="AWAY_GOALS")
    private int awayGoals;

    public FootballMatchStatistic() {
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getAwayName() {
        return awayName;
    }

    public void setAwayName(String awayName) {
        this.awayName = awayName;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    @Override
    public String names(){
        return homeName + " : " + awayName;
    }

    @Override
    public Map<String, String> getDataMap() {
        Map<String,String> dataMap = new LinkedHashMap<>();
        dataMap.put("home_name",homeName);
        dataMap.put("away_name",awayName);
        dataMap.put("home_goals", String.valueOf(homeGoals));
        dataMap.put("away_goals", String.valueOf(awayGoals));
        return dataMap;
    }
}