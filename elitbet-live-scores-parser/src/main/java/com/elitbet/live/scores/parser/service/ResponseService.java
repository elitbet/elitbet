package com.elitbet.live.scores.parser.service;

import com.elitbet.live.scores.parser.model.Request;
import com.elitbet.live.scores.parser.model.Response;
import com.elitbet.live.scores.parser.model.odds.Odd;
import com.elitbet.live.scores.parser.model.statistic.Statistic;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.*;

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
                if(lowPriorityRequests.isEmpty()){
                    try {
                        System.out.println("No requests with low priority");
                        Thread.sleep(60_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    List<String> urls = createResponse(lowPriorityRequests, 2);
                    sendResponse(urls);
                }
            }
        };

        Runnable runnable2 = () -> {
            while (true){
                List<Request> highPriorityRequests = requestService.getEventsWithHighPriority();
                if(highPriorityRequests.isEmpty()){
                    try {
                        System.out.println("No requests with high priority");
                        Thread.sleep(60_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    List<String> urls = createResponse(highPriorityRequests, 10);
                    sendResponse(urls);
                }
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Runnable> runnableList = new ArrayList<>(Arrays.asList(runnable1,runnable2));
        for(Runnable runnable:runnableList){
            executorService.execute(runnable);
        }

    }

    private List<String> createResponse(List<Request> requests, int threadNumber){

        ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);
        List<Callable<String>> creators = new ArrayList<>();
        Queue<Request> requestQueue = new ConcurrentLinkedQueue<>(requests);
        List<String> urls = Collections.synchronizedList(new LinkedList<>());

        Callable<String> callable = () -> {
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
            return "TASK COMPLETED";
        };

        for(int i=0;i<threadNumber;i++){
            creators.add(callable);
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
        urls.forEach(System.out::println);
    }

}