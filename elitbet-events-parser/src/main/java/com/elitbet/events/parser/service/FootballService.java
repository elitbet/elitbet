package com.elitbet.events.parser.service;

import com.elitbet.events.parser.model.Event;
import com.elitbet.events.parser.model.EventContainer;
import com.google.gson.Gson;
import okhttp3.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.io.IOException;
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
            EventContainer container = new EventContainer(update, eventList);
            System.out.println("Before Loading");
            sendUrlContainer(container);
        }
    }

    private void sendUrlContainer(EventContainer container){

        Gson gson = new Gson();
        String jsonBody = gson.toJson(container);

        System.out.println("----JSONBODY---");
        System.out.println(jsonBody);

        String url = "http://localhost:8081/events";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonBody);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                String pageJson = response.body().string();
                System.out.println(pageJson);
            } else {
                System.out.println("Response error");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

//        ClientHttpRequestFactory requestFactory = getClientHttpRequestFactory();
//        RestTemplate restTemplate = new RestTemplate(requestFactory);
//        HttpEntity<EventContainer> httpEntity = new HttpEntity<>(container);
//        String eventsResource = "http://localhost:8081/events";
//        try {
//            URI eventsResourceURL = new URI(eventsResource);
//            restTemplate.put(eventsResourceURL,httpEntity);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
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
//        for(int i=0;i<7;i++){
//            parseFootballTomorrow(driver);
//        }
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