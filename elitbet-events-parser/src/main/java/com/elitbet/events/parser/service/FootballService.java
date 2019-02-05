package com.elitbet.events.parser.service;

import com.elitbet.events.parser.model.Event;
import com.elitbet.events.parser.model.EventContainer;
import com.elitbet.events.parser.model.EventType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class FootballService {

    @Autowired
    protected ChromeOptions options;

    List<Event> eventList;

    public void run(){
        while (true){
            eventList = Collections.synchronizedList(new LinkedList<>());
            LocalTime update = LocalTime.now();
            parseFootballToday();
            EventType eventType = EventType.FOOTBALL_MATCH;
            EventContainer container = new EventContainer(update, eventType, eventList);
            sendUrlContainer(container);
        }
    }

    private void sendUrlContainer(EventContainer container){
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

    public abstract void loadElements(WebDriver driver);

    private void parseFootballToday(){
        WebDriver driver = new ChromeDriver(options);
        String basicUrl = "https://www.flashscore.com/";
        driver.get(basicUrl);
        loadElements(driver);
        for(int i=0;i<7;i++){
            parseFootballTomorrow(driver);
        }
        driver.close();
    }

    private void clickElement(WebDriver driver, By by) throws Exception {
        WebElement button = loadElement(driver, by);
        button.click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.attributeToBe(By.id("preload"),"style","display: none;"));
    }

    WebElement loadElement(WebDriver driver, By by) throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".table-main>.soccer"),1));
        return driver.findElement(by);
    }

    private void parseFootballTomorrow(WebDriver driver){
        try {
            clickElement(driver, By.className("tomorrow"));
            loadElements(driver);
        } catch (Exception e) {
            System.out.println("Tomorrow page not loaded");
        }
    }
}