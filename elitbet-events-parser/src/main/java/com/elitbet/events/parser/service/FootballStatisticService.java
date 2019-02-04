package com.elitbet.events.parser.service;

import com.elitbet.events.parser.model.EventWrapper;
import com.elitbet.events.parser.model.FootballStatistic;
import com.elitbet.events.parser.model.TournamentWrapper;
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

    private void loadTournaments(WebDriver driver) {

        WebElement date;
        try {
            date = loadDateElement(driver);
        } catch (Exception e) {
            System.out.println("Today web element not loaded");
            return;
        }

        List<WebElement> tournaments;
        try {
            tournaments = loadTournamentElements(driver);
        } catch (Exception e){
            System.out.println("Problem loading tournaments");
            return;
        }

        List<TournamentWrapper> tournamentWrappers;
        try {
            tournamentWrappers = loadTournamentWrappers(tournaments, date);
        } catch (Exception e){
            System.out.println("Problem loading wrappers");
            return;
        }

        Queue<TournamentWrapper> tournamentWrappersQueue = new ConcurrentLinkedQueue<>(tournamentWrappers);

        System.out.println("Well done");

        try {
            runTournamentExecutorService(tournamentWrappersQueue);
        } catch (Exception e){
            System.out.println("Error in executor service");
            e.printStackTrace();
        }
    }

    private WebElement loadDateElement(WebDriver driver) throws Exception {
        WebElement date = loadElement(driver, By.className("today"));
        System.out.println(date.getText());
        return date;
    }

    private List<WebElement> loadTournamentElements(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".table-main>.soccer"),1));

        List<WebElement> tournaments = driver.findElements(By.className("soccer"));
        System.out.println(tournaments.size());
        return tournaments;
    }

    private List<TournamentWrapper> loadTournamentWrappers(List<WebElement> tournamentElements, WebElement dateElement) throws Exception{
        LocalTime now = LocalTime.now();
        List<TournamentWrapper> tournamentWrappers = new LinkedList<>();
        for (WebElement tournament : tournamentElements) {
            TournamentWrapper wrapper = new TournamentWrapper(tournament, dateElement, now);
            tournamentWrappers.add(wrapper);
        }
        return tournamentWrappers;
    }

    private void runTournamentExecutorService(Queue<TournamentWrapper> tournamentWrappers){
        int threadNumber = 5;
        System.out.println("Tournament wrappers: " + tournamentWrappers.size());
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
                        System.out.println("Event Elements: " + eventElements.size());
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
            System.out.println("Event Wrappers: " + eventWrappers.size());
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