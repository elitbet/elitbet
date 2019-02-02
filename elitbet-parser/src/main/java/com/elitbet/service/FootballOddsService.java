package com.elitbet.service;

import com.elitbet.model.OddsContainer;
import com.elitbet.model.odds.Odd;
import com.elitbet.model.odds.Odd_1x2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FootballOddsService extends FootballService {

    // TODO: 01.02.19 set in properties
    private final int THREADS_COUNT = 5;

    private Queue<String> eventIdQueue = new ConcurrentLinkedQueue<>();

    @Override
    protected void runElementExecutorService() {
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNT);
        for(int i=0;i<THREADS_COUNT;i++){
            executorService.execute(() -> {
                WebDriver driver = new ChromeDriver(options);
                while(true) {
                    String id = eventIdQueue.poll();
                    if (id != null) {
                        String windowUrl = basicUrl + "match/" + id.replace("g_1_","");
                        driver.get(windowUrl);

                        WebElement oddsWindow;
                        try {
                            WebDriverWait wait = new WebDriverWait(driver,10);
                            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("a-match-odds-comparison")));
                            oddsWindow = driver.findElement(By.id("a-match-odds-comparison"));
                            oddsWindow.click();

                            List<Odd> oddList = new LinkedList<>();
                            oddList.addAll(load_1x2_FullTime_Odds(driver));
                            oddList.addAll(load_1x2_1stHalf_Odds(driver));
                            oddList.addAll(load_1x2_2ndHalf_Odds(driver));

                            OddsContainer container = new OddsContainer();
                            container.setId(id);
                            container.setOdds(oddList);
                            System.out.println(container);
                            urls.add(container.toString());
                        } catch (Exception ignored) {}
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

    private List<Odd> load_1x2_FullTime_Odds(WebDriver driver){

        WebElement button;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bookmark-1x2-ft")));
            button = driver.findElement(By.id("bookmark-1x2-ft"));
            button.click();
        } catch (Exception e){
            return new ArrayList<>();
        }


        WebElement block;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("block-1x2-ft")));
            block = driver.findElement(By.id("block-1x2-ft"));
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return loadOdds(block);
    }

    private List<Odd> load_1x2_1stHalf_Odds(WebDriver driver) {

        WebElement button;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bookmark-1x2-1hf")));
            button = driver.findElement(By.id("bookmark-1x2-1hf"));
            button.click();
        } catch (Exception e){
            return new ArrayList<>();
        }

        WebElement block;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("block-1x2-1hf")));
            block = driver.findElement(By.id("block-1x2-1hf"));
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return loadOdds(block);
    }

    private List<Odd> load_1x2_2ndHalf_Odds(WebDriver driver){

        WebElement button;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bookmark-1x2-2hf")));
            button = driver.findElement(By.id("bookmark-1x2-2hf"));
            button.click();
        } catch (Exception e){
            return new ArrayList<>();
        }

        WebElement block;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("block-1x2-2hf")));
            block = driver.findElement(By.id("block-1x2-2hf"));
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return loadOdds(block);
    }

    private List<Odd> loadOdds(WebElement block){

        List<Odd> oddList = new LinkedList<>();

        List<WebElement> oddTable;
        try {
            oddTable = block.findElements(By.xpath("table/tbody/tr"));
        } catch (Exception e){
            return oddList;
        }

        for(WebElement oddRaw:oddTable){

            Odd_1x2 odd;

            String bookmaker;
            try {
                bookmaker = oddRaw.findElement(By.className("elink")).getAttribute("title");
            } catch (Exception ignored){
                break;
            }

            try {
                List<WebElement> odds = oddRaw.findElements(By.className("odds-wrap"));
                double firstWin = Double.parseDouble(odds.get(0).getText());
                double draw = Double.parseDouble(odds.get(1).getText());
                double secondWin = Double.parseDouble(odds.get(2).getText());
                odd = new Odd_1x2(bookmaker,firstWin,draw,secondWin);
            } catch (Exception e){
                break;
            }

            oddList.add(odd);
        }

        return oddList;
    }



    @Override
    protected boolean queueIsEmpty() {
        return eventIdQueue.isEmpty();
    }

    @Override
    public void loadElements(WebDriver driver) {
        loadOdds(driver);
    }

    private void loadOdds(WebDriver driver){
        List<WebElement> fixtures = driver.findElements(By.xpath("//div/table/tbody/tr"));
        for(WebElement fixture:fixtures){
            String id = fixture.getAttribute("id");
            eventIdQueue.add(id);
        }
    }
}
