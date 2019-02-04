package com.elitbet.service;

import com.elitbet.model.FootballStatistic;
import com.elitbet.model.EventWrapper;
import com.elitbet.model.TournamentWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.*;

@Service
public class FootballStatisticService extends FootballService {

    @Override
    public void loadElements(WebDriver driver) {
        loadTournaments(driver);
    }

    private void loadTournaments(WebDriver driver){
        WebElement date;
        try {
            date = loadElement(driver, By.className("today"));
        } catch (Exception e) {
            System.out.println("Today web element not loaded");
            return;
        }
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div/table"),50));
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        List<WebElement> tournaments = driver.findElements(By.xpath("//div/table"));
        System.out.println(tournaments.size());
        LocalTime now = LocalTime.now();
        List<TournamentWrapper> tournamentWrappers = new LinkedList<>();
        for(WebElement tournament:tournaments){
            TournamentWrapper wrapper = new TournamentWrapper(tournament, date, now);
            tournamentWrappers.add(wrapper);
            System.out.println(date.getText());
        }
        Queue<TournamentWrapper> tournamentWrappersQueue = new ConcurrentLinkedQueue<>(tournamentWrappers);
        runTournamentExecutorService(tournamentWrappersQueue);
    }

    private void runTournamentExecutorService(Queue<TournamentWrapper> tournamentWrappers){
        int threadNumber = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Callable<Void>> creators = new ArrayList<>();
        Queue<EventWrapper> eventWrappers = new ConcurrentLinkedQueue<>();
        for(int i=0;i<threadNumber;i++){
            creators.add(() -> {
                while (true){
                    TournamentWrapper tournamentWrapper = tournamentWrappers.poll();
                    if(tournamentWrapper!=null){
                        WebElement date = tournamentWrapper.getDate();
                        WebElement tournamentElement = tournamentWrapper.getTournament();
                        WebElement country = tournamentElement.findElement(By.className("country_part"));
                        WebElement tournament = tournamentElement.findElement(By.className("tournament_part"));
                        List<WebElement> eventElements = tournamentElement.findElements(By.xpath("tbody/tr"));
                        LocalTime update = tournamentWrapper.getUpdate();
                        for(WebElement eventElement:eventElements){
                            eventWrappers.add(new EventWrapper(tournament,date, country, eventElement, update));
                        }
                    } else {
                        break;
                    }
                }
                return null;
            });
        }
        try {
            executorService.invokeAll(creators);
            runEventExecutorService(eventWrappers);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void runEventExecutorService(Queue<EventWrapper> eventWrappers){
        ExecutorService executorService = Executors.newFixedThreadPool(eventWrappers.size());
        List<Callable<Void>> creators = new ArrayList<>();
        for (EventWrapper wrapper : eventWrappers) {
            creators.add(() -> {
                FootballStatistic statistic = getStatistic(wrapper);
                urls.add(statistic.toURL());
                return null;
            });
        }
        try {
            executorService.invokeAll(creators);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private FootballStatistic getStatistic(EventWrapper wrapper){

        FootballStatistic statistic = new FootballStatistic();
        statistic.setId(wrapper.getEvent().getAttribute("id"));
        statistic.setDate(wrapper.getDate().getText());
        statistic.setTournament(wrapper.getTournament().getText());
        statistic.setCountry(wrapper.getCountry().getText().replace(":",""));
        statistic.setStartTime(wrapper.getEvent().findElement(By.cssSelector("td.time.cell_ad")).getText());
        statistic.setStatus(wrapper.getEvent().findElement(By.cssSelector("td.timer.cell_aa")).getText());
        statistic.setHomeTeamName(wrapper.getEvent().findElement(By.cssSelector("td.team-home.cell_ab")).getText());
        statistic.setAwayTeamName(wrapper.getEvent().findElement(By.cssSelector("td.team-away.cell_ac")).getText());

        System.out.println("ID= " + statistic.getId() + " DATE = " + statistic.getDate() +
                " HOME = " + statistic.getHomeTeamName() + " AWAY = " + statistic.getAwayTeamName());

        return statistic;
    }

}