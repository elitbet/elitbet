package com.elitbet.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class FootballService {

    @Autowired
    protected ChromeOptions options;

    protected Queue<String> urls = new ConcurrentLinkedQueue<>();

    private final int COUNT_DEFAULT = 10;
    final String basicUrl = "https://www.flashscore.com/";

    private int tomorrowCount = COUNT_DEFAULT;
    private int yesterdayCount = COUNT_DEFAULT;

    public void run(){
        while (true){
            parseFootballToday();
            sendUrlList();
        }
    }

    private void sendUrlList(){
        System.out.println("SENDING: " + urls.size() + " ITEMS");
        urls = new LinkedBlockingQueue<>();
    }

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
        driver.close();
    }

    void clickElement(WebDriver driver, By by) throws Exception {
        WebElement button = loadElement(driver, by);
        button.click();
    }

    WebElement loadElement(WebDriver driver, By by) throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by);
    }

    private void parseFootballYesterday(WebDriver driver){
        try {
            clickElement(driver, By.className("yesterday"));
            loadElements(driver);
        } catch (Exception e) {
            System.out.println("Yesterday page not loaded");
        }
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