package com.elitbet.service;

import com.elitbet.model.EventRequest;
import com.elitbet.model.EventResponse;
import com.elitbet.model.odds.Odd;
import com.elitbet.model.statistic.FootballStatistic;
import com.elitbet.model.statistic.Statistic;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class EventResponseService implements SeleniumInterface {

    @Autowired
    private EventRequestService requestService;

    @Autowired
    private FootballOddsService footballOddsService;

    @Autowired
    private FootballStatisticService footballStatisticService;

    @Autowired
    private ChromeOptions options;

    public void run(){
        while(true){

        }
    }

    public void createResponse(List<EventRequest> requests, int threadNumber){
        ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);
        List<Callable<Void>> creators = new ArrayList<>();
        Queue<EventRequest> requestQueue = new ConcurrentLinkedQueue<>(requests);
        List<String> urls = Collections.synchronizedList(new LinkedList<>());
        for(int i=0;i<threadNumber;i++){
            creators.add(() -> {
                WebDriver driver = new ChromeDriver(options);
                while(true){
                    EventRequest request = requestQueue.poll();
                    String eventId = request.getEventId();
                    if(eventId!=null){
                        String windowUrl = "https://www.flashscore.com/match/" + eventId.replace("g_1_","");
                        driver.get(windowUrl);
                        try {
                            LocalTime update = LocalTime.now();
                            clickElement(driver, By.id("a-match-odds-comparison"));
                            Statistic statistic = footballStatisticService.loadFootballStatistic(driver);
                            List<Odd> odds = footballOddsService.loadOdds(driver, request);
                            EventResponse response = new EventResponse(eventId, statistic, odds, update);
                            urls.add(response.toUrl());
                        } catch (Exception ignored) {}
                    }else {
                        break;
                    }
                }
                driver.close();
                return null;
            });
        }
        try {
            executorService.invokeAll(creators);
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}