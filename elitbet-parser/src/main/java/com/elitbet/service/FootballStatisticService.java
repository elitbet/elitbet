package com.elitbet.service;

import com.elitbet.model.FootballStatistic;
import com.elitbet.model.StatisticWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FootballStatisticService extends FootballService {

    private final int THREADS_COUNT = 100;

    private Queue<StatisticWrapper> statisticWrappers = new ConcurrentLinkedQueue<>();

    @Override
    public void loadElements(WebDriver driver) {
        loadTournaments(driver);
    }

    private void loadTournaments(WebDriver driver){
        WebElement date = driver.findElement(By.xpath("//span[2]/span/a"));
        List<WebElement> tournaments = driver.findElements(By.xpath("//div/table"));
        for(WebElement tournament:tournaments){
            statisticWrappers.add(new StatisticWrapper(tournament,date));
        }
    }

    protected void runElementExecutorService(){
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNT);
        for(int i=0;i<THREADS_COUNT;i++){
            executorService.execute(() -> {
                while(true) {
                    StatisticWrapper statisticWrapper = statisticWrappers.poll();
                    if (statisticWrapper != null) {
                        WebElement date = statisticWrapper.getDate();
                        WebElement tournamentElement = statisticWrapper.getTournament();
                        WebElement country = tournamentElement.findElement(By.className("country_part"));
                        WebElement tournament = tournamentElement.findElement(By.className("tournament_part"));
                        List<WebElement> eventElements = tournamentElement.findElements(By.xpath("tbody/tr"));
                        for (WebElement event : eventElements) {
                            FootballStatistic statistic = getStatistic(date, tournament, country, event);
                            urls.add(statistic.toURL());
                            System.out.println("operation: + " +"size: " + urls.size());
                        }
                    } else {
                        try {
                            Thread.sleep(THREAD_SLEEP_MILLIS);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    @Override
    protected boolean queueIsEmpty() {
        return statisticWrappers.isEmpty();
    }

    private FootballStatistic getStatistic(WebElement date, WebElement tournament, WebElement country, WebElement event){

        FootballStatistic statistic = new FootballStatistic();
        statistic.setId(event.getAttribute("id"));
        statistic.setDate(date.getText());
        statistic.setTournament(tournament.getText());
        statistic.setCountry(country.getText().replace(":",""));
        statistic.setStartTime(event.findElement(By.cssSelector("td.time.cell_ad")).getText());
        statistic.setStatus(event.findElement(By.cssSelector("td.timer.cell_aa")).getText());
        statistic.setHomeTeamName(event.findElement(By.cssSelector("td.team-home.cell_ab")).getText());
        statistic.setAwayTeamName(event.findElement(By.cssSelector("td.team-away.cell_ac")).getText());

        int[] score = parseScore(event.findElement(By.cssSelector("td.score.cell_sa")).getText());
        statistic.setHomeTeamGoals(score[0]);
        statistic.setAwayTeamGoals(score[1]);

        int[] firstHalfScore = parseScore(event.findElement(By.cssSelector("td.part-top.cell_sb")).getText());
        statistic.setHomeTeamFirstHalfGoals(firstHalfScore[0]);
        statistic.setAwayTeamFirstHalfGoals(firstHalfScore[1]);

        return statistic;
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