package com.elitbet.events.parser.service;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class FootballService {

    @Autowired
    protected ChromeOptions options;

    protected Queue<String> urls = new ConcurrentLinkedQueue<>();

    final String basicUrl = "https://www.flashscore.com/";

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
        // TODO: 04.02.19 set wait for tommorrow window
        driver.get(basicUrl);
        parseFootballTomorrow(driver);
//        loadElements(driver);
//        for(int i=0;i<6;i++){
//            parseFootballTomorrow(driver);
//        }
        driver.close();
    }

    private void clickElement(WebDriver driver, By by) throws Exception {
        WebElement button = loadElement(driver, by);
        button.click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        System.out.println("wait before click button");
        wait.until(ExpectedConditions.attributeToBe(By.id("preload"),"style","display: none;"));
        System.out.println("loaded after clckking button");
        System.out.println("Clicked " + by.toString());
    }

    WebElement loadElement(WebDriver driver, By by) throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, 10);
        System.out.println("before wait");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".table-main>.soccer"),1));
        System.out.println("after watt");
        WebElement element = driver.findElement(by);
        System.out.println("Loading " + by.toString());
        return element;
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