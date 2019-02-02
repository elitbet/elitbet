package com.elitbet.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class FootballService {

    @Autowired
    protected ChromeOptions options;

    // TODO: 01.02.19 properties
    private final int THREADS_COUNT = 100;

    static Queue<String> urls = new ConcurrentLinkedQueue<>();

    private final int COUNT_DEFAULT = 10;
    final String basicUrl = "https://www.flashscore.com/";
    final int THREAD_SLEEP_MILLIS = 1000;
    private int tomorrowCount = COUNT_DEFAULT;
    private int yesterdayCount = COUNT_DEFAULT;

    public void run(){
        runElementExecutorService();
        runUrlExecutorService();
        while (true){
            if(queueIsEmpty()){
                parseFootballToday();
            }
            try {
                Thread.sleep(THREAD_SLEEP_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void runUrlExecutorService(){
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNT);
        for(int i=0;i<THREADS_COUNT;i++){
            executorService.execute(()->{
                while(true){
                    HttpURLConnection connection;
                    try {
                        String urlContext = urls.poll();
                        if(urlContext!=null){
                            System.out.println("operation: - " +"size: " + urls.size());
                            URL url = new URL("http://localhost:8081/parser?" + urlContext);
                        } else {
                            try {
                                Thread.sleep(THREAD_SLEEP_MILLIS);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    protected abstract void runElementExecutorService();

    protected abstract boolean queueIsEmpty();

    public abstract void loadElements(WebDriver driver);

    private void parseFootballToday(){
        WebDriver driver = new ChromeDriver(options);
        driver.get(basicUrl);
        loadElements(driver);
        if(tomorrowCount-- < 0){
            parseFootballTomorrow(driver);
            tomorrowCount = COUNT_DEFAULT;
        }else if(yesterdayCount-- < 0){
            parseFootballYesterday(driver);
            yesterdayCount = COUNT_DEFAULT;
        }
    }

    private void parseFootballYesterday(WebDriver driver){
        WebElement yesterday = driver.findElement(By.className("yesterday"));
        yesterday.click();
        loadElements(driver);
    }

    private void parseFootballTomorrow(WebDriver driver){
        WebElement tomorrow = driver.findElement(By.className("tomorrow"));
        tomorrow.click();
        loadElements(driver);
    }
}