package com.elitbet.service;

import com.elitbet.model.statistic.FootballStatistic;
import com.elitbet.model.statistic.Statistic;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class FootballStatisticService {

    private Statistic loadStatistic(WebDriver driver){

        WebElement fleft = driver.findElement(By.className("fleft"));
        String tournament = fleft.getText();

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

        int homeTeamFirstHalfGoals = 0, awayTeamFirstHalfGoals = 0;
        try {
            WebElement p1_home = driver.findElement(By.className("p1_home"));
            WebElement p1_away = driver.findElement(By.className("p1_away"));
            homeTeamFirstHalfGoals = Integer.valueOf(p1_home.getText());
            awayTeamFirstHalfGoals = Integer.valueOf(p1_away.getText());
        } catch (Exception ignored) {}

        int homeTeamSecondHalfGoals = 0, awayTeamSecondHalfGoals = 0;
        try {
            WebElement p2_home = driver.findElement(By.className("p2_home"));
            WebElement p2_away = driver.findElement(By.className("p2_away"));
            homeTeamSecondHalfGoals = Integer.valueOf(p2_home.getText());
            awayTeamSecondHalfGoals = Integer.valueOf(p2_away.getText());
        } catch (Exception ignored) {}


        int homeTeamFullTimeGoals = 0, awayTeamFullTimeGoals = 0;
        try {
            List<WebElement> scoreboard = driver.findElements(By.className("scoreboard"));
            System.out.println("Scoreboard size: " + scoreboard.size());
            homeTeamFullTimeGoals = Integer.parseInt(scoreboard.get(0).getText());
            awayTeamFullTimeGoals = Integer.parseInt(scoreboard.get(1).getText());
        } catch (Exception ignored) {}

        return new FootballStatistic
                (statistic, teamHomeName, teamAwayName, homeTeamFullTimeGoals,
                        awayTeamFullTimeGoals, homeTeamFirstHalfGoals, awayTeamFirstHalfGoals,
                        homeTeamSecondHalfGoals, awayTeamSecondHalfGoals);
    }

}
