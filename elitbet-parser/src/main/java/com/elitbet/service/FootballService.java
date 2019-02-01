package com.elitbet.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class FootballService {

    @Autowired
    protected ChromeOptions options;

    private final int COUNT_DEFAULT = 10;
    final String basicUrl = "https://www.flashscore.com/";
    final int THREAD_SLEEP_MILLIS = 1000;
    private int tomorrowCount = COUNT_DEFAULT;
    private int yesterdayCount = COUNT_DEFAULT;

    public void run(){
        runExecutorService();
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

    protected abstract void runExecutorService();

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