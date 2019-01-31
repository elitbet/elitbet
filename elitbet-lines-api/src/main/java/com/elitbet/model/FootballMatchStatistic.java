package com.elitbet.model;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity()
@Table(name="football_match_statistic")
@PrimaryKeyJoinColumn(name = "statistic_id")
public class FootballMatchStatistic extends Statistic {

    @Column(name="home_name")
    private String homeName;

    @Column(name="away_name")
    private String awayName;

    @Column(name="home_goals")
    private int homeGoals;

    @Column(name="away_goals")
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