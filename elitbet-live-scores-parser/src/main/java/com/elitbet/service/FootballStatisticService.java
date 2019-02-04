package com.elitbet.service;

import com.elitbet.model.statistic.FootballStatistic;
import com.elitbet.model.statistic.Statistic;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

@Service
class FootballStatisticService {

    private Statistic loadStatistic(WebDriver driver){

        WebElement fletf = driver.findElement(By.className("fletf"));
        String tournament = fletf.getText();

        WebElement utime = driver.findElement(By.id("utime"));
        String startTime = utime.getText();

        WebElement infoStatus = driver.findElement(By.className("info-status"));
        String status = infoStatus.getText();

        return new Statistic(tournament, startTime, status);
    }

    Statistic loadFootballStatistic(WebDriver driver){

        Statistic statistic = loadStatistic(driver);

        WebElement nameHome = driver.findElement(By.className("tname-home"));
        String teamHomeName = nameHome.getText();

        WebElement nameAway = driver.findElement(By.className("tname-away"));
        String teamAwayName = nameAway.getText();

        WebElement p1_home = driver.findElement(By.className("p1_home"));
        WebElement p1_away = driver.findElement(By.className("p1_away"));
        WebElement p2_home = driver.findElement(By.className("p2_home"));
        WebElement p2_away = driver.findElement(By.className("p2_away"));

        int homeTeamFirstHalfGoals = Integer.valueOf(p1_home.getText());
        int awayTeamFirstHalfGoals = Integer.valueOf(p1_away.getText());
        int homeTeamSecondHalfGoals = Integer.valueOf(p2_home.getText());
        int awayTeamSecondHalfGoals = Integer.valueOf(p2_away.getText());

        int homeTeamFullTimeGoals = homeTeamFirstHalfGoals + homeTeamSecondHalfGoals;
        int awayTeamFullTimeGoals = awayTeamFirstHalfGoals + awayTeamSecondHalfGoals;

        return new FootballStatistic
                (statistic, teamHomeName, teamAwayName, homeTeamFullTimeGoals,
                        awayTeamFullTimeGoals, homeTeamFirstHalfGoals, awayTeamFirstHalfGoals,
                        homeTeamSecondHalfGoals, awayTeamSecondHalfGoals);
    }

}
