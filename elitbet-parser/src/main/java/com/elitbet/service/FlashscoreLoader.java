package com.elitbet.service;

import com.elitbet.model.Fixture;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FlashscoreLoader {

    @Autowired
    private ChromeOptions options;

    private static Queue<WebElement> tournamentElements;

    public void run(){

        WebDriver driver = new ChromeDriver(options);;

        String url = "https://www.flashscore.com/";
        driver.get(url);

        WebElement date = driver.findElement(By.xpath("//span[2]/span/a"));
        tournamentElements = new ConcurrentLinkedQueue<>(driver.findElements(By.xpath("//div/table")));

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i=0;i<5;i++){
            executorService.execute(() -> {
                WebElement tournamentElement = tournamentElements.poll();
                if(tournamentElement!=null){
                    WebElement country = tournamentElement.findElement(By.className("country_part"));
                    WebElement tournament = tournamentElement.findElement(By.className("tournament_part"));
                    List<WebElement> eventElements = tournamentElement.findElements(By.xpath("tbody/tr"));
                    for(WebElement event:eventElements) {
                        String fixtureUrl = fixtureUrl(date, tournament, country, event);
                        System.out.println(fixtureUrl);
                    }
                }
            });
        }
    }

    public String fixtureUrl(WebElement date, WebElement tournament, WebElement country, WebElement event){

        Fixture fixture = new Fixture();
        fixture.setId(event.getAttribute("id"));
        fixture.setDate(date.getText());
        fixture.setTournament(tournament.getText());
        fixture.setCountry(country.getText().replace(":",""));
        fixture.setStartTime(event.findElement(By.cssSelector("td.time.cell_ad")).getText());
        fixture.setStatus(event.findElement(By.cssSelector("td.timer.cell_aa")).getText());
        fixture.setHomeTeamName(event.findElement(By.cssSelector("td.team-home.cell_ab")).getText());
        fixture.setAwayTeamName(event.findElement(By.cssSelector("td.team-away.cell_ac")).getText());

        int[] score = parseScore(event.findElement(By.cssSelector("td.score.cell_sa")).getText());
        fixture.setHomeTeamGoals(score[0]);
        fixture.setAwayTeamGoals(score[1]);

        int[] firstHalfScore = parseScore(event.findElement(By.cssSelector("td.part-top.cell_sb")).getText());
        fixture.setHomeTeamFirstHalfGoals(firstHalfScore[0]);
        fixture.setAwayTeamFirstHalfGoals(firstHalfScore[1]);

        return fixture.toURL();
    }

    private int[] parseScore(String scoreString){
        int indexLeft = scoreString.indexOf("(");
        int indexRight = scoreString.indexOf(")");
        if(indexLeft>0){
            scoreString = scoreString.substring(indexLeft+1, indexRight-1);
        }
        int[] score = new int[2];
        try {
            String[] scoreArray = scoreString.split("-");
            score[0] = Integer.valueOf(scoreArray[0].trim());
            score[1] = Integer.valueOf(scoreArray[1].trim());
        } catch (Exception e){
            score[0] = 0;
            score[1] = 0;
        }
        return score;
    }

}
