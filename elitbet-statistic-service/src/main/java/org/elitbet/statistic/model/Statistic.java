package org.elitbet.statistic.model;

import java.util.Objects;

public class Statistic {

    private Integer id;
    private String homeName;
    private String awayName;
    private Integer homeFullTimeGoals;
    private Integer awayFullTimeGoals;
    private Integer homeFirstHalfGoals;
    private Integer awayFirstHalfGoals;
    private Integer homeSecondHalfGoals;
    private Integer awaySecondHalfGoals;

    public Statistic() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getHomeFullTimeGoals() {
        return homeFullTimeGoals;
    }

    public void setHomeFullTimeGoals(Integer homeFullTimeGoals) {
        this.homeFullTimeGoals = homeFullTimeGoals;
    }

    public Integer getAwayFullTimeGoals() {
        return awayFullTimeGoals;
    }

    public void setAwayFullTimeGoals(Integer awayFullTimeGoals) {
        this.awayFullTimeGoals = awayFullTimeGoals;
    }

    public Integer getHomeFirstHalfGoals() {
        return homeFirstHalfGoals;
    }

    public void setHomeFirstHalfGoals(Integer homeFirstHalfGoals) {
        this.homeFirstHalfGoals = homeFirstHalfGoals;
    }

    public Integer getAwayFirstHalfGoals() {
        return awayFirstHalfGoals;
    }

    public void setAwayFirstHalfGoals(Integer awayFirstHalfGoals) {
        this.awayFirstHalfGoals = awayFirstHalfGoals;
    }

    public Integer getHomeSecondHalfGoals() {
        return homeSecondHalfGoals;
    }

    public void setHomeSecondHalfGoals(Integer homeSecondHalfGoals) {
        this.homeSecondHalfGoals = homeSecondHalfGoals;
    }

    public Integer getAwaySecondHalfGoals() {
        return awaySecondHalfGoals;
    }

    public void setAwaySecondHalfGoals(Integer awaySecondHalfGoals) {
        this.awaySecondHalfGoals = awaySecondHalfGoals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistic statistic = (Statistic) o;
        return Objects.equals(id, statistic.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Statistic{");
        sb.append("id=").append(id);
        sb.append(", homeName='").append(homeName).append('\'');
        sb.append(", awayName='").append(awayName).append('\'');
        sb.append(", homeFullTimeGoals=").append(homeFullTimeGoals);
        sb.append(", awayFullTimeGoals=").append(awayFullTimeGoals);
        sb.append(", homeFirstHalfGoals=").append(homeFirstHalfGoals);
        sb.append(", awayFirstHalfGoals=").append(awayFirstHalfGoals);
        sb.append(", homeSecondHalfGoals=").append(homeSecondHalfGoals);
        sb.append(", awaySecondHalfGoals=").append(awaySecondHalfGoals);
        sb.append('}');
        return sb.toString();
    }
}
