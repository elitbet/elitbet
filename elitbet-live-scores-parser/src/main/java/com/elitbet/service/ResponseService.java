package com.elitbet.service;

import com.elitbet.model.Request;
import com.elitbet.model.Response;
import com.elitbet.model.odds.Odd;
import com.elitbet.model.statistic.Statistic;
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
public class ResponseService implements SeleniumInterface {

    @Autowired
    private RequestService requestService;
    @Autowired
    private FootballOddsService footballOddsService;
    @Autowired
    private FootballStatisticService footballStatisticService;
    @Autowired
    private ChromeOptions options;

    public void run(){

        Runnable runnable1 = () -> {
            while(true){
                List<Request> lowPriorityRequests = requestService.getEventsWithLowPriority();
                List<String> urls = createResponse(lowPriorityRequests, 1);
                sendResponse(urls);
            }
        };

        Runnable runnable2 = () -> {
            while (true){
                List<Request> highPriorityRequests = requestService.getEventsWithHighPriority();
                List<String> urls = createResponse(highPriorityRequests, 5);
                sendResponse(urls);
            }
        };

        runnable1.run();
        runnable2.run();
    }

    private List<String> createResponse(List<Request> requests, int threadNumber){
        ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);
        List<Callable<Void>> creators = new ArrayList<>();
        Queue<Request> requestQueue = new ConcurrentLinkedQueue<>(requests);
        List<String> urls = Collections.synchronizedList(new LinkedList<>());
        for(int i=0;i<threadNumber;i++){
            creators.add(() -> {
                WebDriver driver = new ChromeDriver(options);
                while(true){
                    Request request = requestQueue.poll();
                    String eventId = request.getEventId();
                    if(eventId!=null){
                        String windowUrl = "https://www.flashscore.com/match/" + eventId.replace("g_1_","");
                        driver.get(windowUrl);
                        try {
                            LocalTime update = LocalTime.now();
                            Statistic statistic = footballStatisticService.loadFootballStatistic(driver);
                            List<Odd> odds = footballOddsService.loadOdds(driver, request);
                            Response response = new Response(eventId, statistic, odds, update);
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

        return urls;
    }

    private void sendResponse(List<String> urls){
        System.out.println("SENDING " + urls.size() + " ITEMS");
    }

}