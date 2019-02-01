package com.elitbet.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FootballOddsService extends FootballService {

    // TODO: 01.02.19 set in properties
    private final int THREADS_COUNT = 1;

    private Queue<String> eventIdList = new ConcurrentLinkedQueue<>();


    @Override
    protected void runElementExecutorService() {
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNT);
        for(int i=0;i<THREADS_COUNT;i++){
            executorService.execute(() -> {
                WebDriver driver = new ChromeDriver(options);
                while(true) {
                    String id = eventIdList.poll();
                    if (id != null) {
                        String windowUrl = basicUrl + "match/" + id.replace("g_1_","");
                        System.out.println(windowUrl);
                        driver.get(windowUrl);

                        WebDriverWait wait = new WebDriverWait(driver,10);
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("a-match-odds-comparison")));
//                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("block-1x2-ft")));
//                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("block-1x2-1hf")));
//                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("block-1x2-2hf")));
                        WebElement oddsWindow = driver.findElement(By.id("a-match-odds-comparison"));
                        oddsWindow.click();

                        load_1x2_FullTime_Odds(driver);
                        load_1x2_1stHalf_Odds(driver);
                        load_1x2_2ndHalf_Odds(driver);
                    } else {
                        try {
                            Thread.sleep(THREAD_SLEEP_MILLIS);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
        }
    }

    private void load_1x2_FullTime_Odds(WebDriver driver){

        System.out.println("1x2_FullTime");

        WebElement button;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bookmark-1x2-ft")));
            button = driver.findElement(By.id("bookmark-1x2-ft"));
            button.click();
        } catch (Exception e){
            System.out.println("Can`t find button");
            return;
        }


        WebElement block;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("block-1x2-ft")));
            block = driver.findElement(By.id("block-1x2-ft"));
        } catch (Exception e) {
            System.out.println("Can`t find block-1x2-ft");
            return;
        }

        printOdds(driver,block);
    }

    private void load_1x2_1stHalf_Odds(WebDriver driver) {

        WebElement button;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bookmark-1x2-1hf")));
            button = driver.findElement(By.id("bookmark-1x2-1hf"));
            button.click();
        } catch (Exception e){
            System.out.println("Can`t find button");
            return;
        }

        System.out.println("1x2_1ndHalf");

        WebElement block;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("block-1x2-1hf")));
            block = driver.findElement(By.id("block-1x2-1hf"));
        } catch (Exception e) {
            System.out.println("Can`t find block-1x2-1hf");
            return;
        }

        printOdds(driver,block);
    }

    private void load_1x2_2ndHalf_Odds(WebDriver driver){

        System.out.println("1x2_2ndHalf");

        WebElement button;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bookmark-1x2-2hf")));
            button = driver.findElement(By.id("bookmark-1x2-2hf"));
            button.click();
        } catch (Exception e){
            System.out.println("Can`t find button");
            return;
        }

        WebElement block;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("block-1x2-2hf")));
            block = driver.findElement(By.id("block-1x2-2hf"));
        } catch (Exception e) {
            System.out.println("Can`t find block-1x2-2hf");
            return;
        }

        printOdds(driver,block);
    }

    private void printOdds(WebDriver driver, WebElement block){
        List<WebElement> bookmakers;
        try {
//            WebDriverWait wait = new WebDriverWait(driver, 10);
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("table/tbody/tr")));
            bookmakers = block.findElements(By.xpath("table/tbody/tr"));
        } catch (Exception e){
            System.out.println("Can`t find table");
            return;
        }

        for(WebElement bookmaker:bookmakers){
            try {
                String bookmakerName = bookmaker.findElement(By.className("elink")).getAttribute("title");
                System.out.print("Bookmaker: " + bookmakerName);
            } catch (Exception e){
                System.out.println("Bookmaker not present ");
            }

            try {
//                WebDriverWait wait1 = new WebDriverWait(driver, 10);
//                wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("odds-wrap")));
                List<WebElement> odds = bookmaker.findElements(By.className("odds-wrap"));
                String firstWin = odds.get(0).getText();
                String draw = odds.get(1).getText();
                String secondWin = odds.get(2).getText();
                System.out.println(" 1:" + firstWin + " X:" + draw + " 2:" + secondWin);
            } catch (Exception e){
                System.out.println(" Odds not present");
            }
        }
    }

    @Override
    protected boolean queueIsEmpty() {
        return eventIdList.isEmpty();
    }

    @Override
    public void loadElements(WebDriver driver) {
        loadOdds(driver);
    }

    public void loadOdds(WebDriver driver){
        List<WebElement> fixtures = driver.findElements(By.xpath("//div/table/tbody/tr"));
        for(WebElement fixture:fixtures){
            String id = fixture.getAttribute("id");
            eventIdList.add(id);
        }
    }
}
