package com.elitbet.live.scores.parser.service;

import com.elitbet.live.scores.parser.model.Event;
import com.elitbet.live.scores.parser.model.EventContainer;
import com.elitbet.live.scores.parser.model.EventType;
import com.elitbet.live.scores.parser.model.Request;
import com.elitbet.live.scores.parser.model.odds.Odd;
import com.elitbet.live.scores.parser.model.statistic.Statistic;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
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
                    LocalTime update = LocalTime.now();
                    List<Event> eventList = createResponse(lowPriorityRequests, 2);
                    EventContainer container = new EventContainer(update, EventType.FOOTBALL_MATCH, eventList);
                    sendEventContainer(container);
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
                    LocalTime update = LocalTime.now();
                    List<Event> eventList = createResponse(highPriorityRequests, 10);
                    EventContainer container = new EventContainer(update, EventType.FOOTBALL_MATCH, eventList);
                    sendEventContainer(container);
                }
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Runnable> runnableList = new ArrayList<>(Arrays.asList(runnable1,runnable2));
        for(Runnable runnable:runnableList){
            executorService.execute(runnable);
        }

    }

    private List<Event> createResponse(List<Request> requests, int threadNumber){

        ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);
        List<Callable<String>> creators = new ArrayList<>();
        Queue<Request> requestQueue = new ConcurrentLinkedQueue<>(requests);
        List<Event> eventList = Collections.synchronizedList(new LinkedList<>());
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
                        Event event = new Event(eventId, statistic, odds, update);
                        eventList.add(event);
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

        return eventList;
    }

    private void sendEventContainer(EventContainer container){
        ClientHttpRequestFactory requestFactory = getClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        HttpEntity<EventContainer> httpEntity = new HttpEntity<>(container);
        String eventsResource = "http://localhost:8081/events";
        try {
            URI eventsResourceURL = new URI(eventsResource);
            restTemplate.put(eventsResourceURL,httpEntity);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(1000);
        return clientHttpRequestFactory;
    }

}