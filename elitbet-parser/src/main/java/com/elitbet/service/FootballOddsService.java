package com.elitbet.service;

import com.elitbet.model.OddsContainer;
import com.elitbet.model.odds.Odd;
import com.elitbet.model.odds.Odd_1x2;
import com.elitbet.model.odds.Odd_OverUnder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FootballOddsService extends FootballService {

    private List<Odd> loadOddList(WebDriver driver){
        List<Odd> oddList = new LinkedList<>();
        oddList.addAll(load_1x2_FullTime_Odds(driver));
        oddList.addAll(load_1x2_1stHalf_Odds(driver));
        oddList.addAll(load_1x2_2ndHalf_Odds(driver));
        oddList.addAll(load_OverUnder_FullTime_Odds(driver));
        oddList.addAll(load_OverUnder_FirstHalf_Odds(driver));
        oddList.addAll(load_OverUnder_SecondHalf_Odds(driver));
        return oddList;
    }

    private List<Odd> load_1x2_FullTime_Odds(WebDriver driver){
        try {
            clickElement(driver, By.id("bookmark-1x2"));
            clickElement(driver, By.id("bookmark-1x2-ft"));
            WebElement block = loadElement(driver, By.id("block-1x2-ft"));
            return loadOdds_1x2(block, "1x2_FullTime");
        } catch (Exception ignored) {
            return new ArrayList<>();
        }
    }

    private List<Odd> load_1x2_1stHalf_Odds(WebDriver driver) {
        try {
            clickElement(driver, By.id("bookmark-1x2"));
            clickElement(driver, By.id("bookmark-1x2-1hf"));
            WebElement block = loadElement(driver, By.id("block-1x2-1hf"));
            return loadOdds_1x2(block, "1x2_FirstHalf");
        } catch (Exception ignored) {
            return new ArrayList<>();
        }
    }

    private List<Odd> load_1x2_2ndHalf_Odds(WebDriver driver){
        try {
            clickElement(driver, By.id("bookmark-1x2"));
            clickElement(driver, By.id("bookmark-1x2-2hf"));
            WebElement block = loadElement(driver, By.id("block-1x2-2hf"));
            return loadOdds_1x2(block, "1x2_SecondHalf");
        } catch (Exception ignored) {
            return new ArrayList<>();
        }
    }

    private List<Odd> load_OverUnder_FullTime_Odds(WebDriver driver){
        try {
            clickElement(driver, By.id("bookmark-under-over"));
            clickElement(driver, By.id("bookmark-under-over-ft"));
            WebElement block = loadElement(driver, By.id("block-under-over-ft"));
            return loadOdds_OverUnder(block, "OverUnder_FullTime");
        } catch (Exception ignored) {
            return new ArrayList<>();
        }
    }

    private List<Odd> load_OverUnder_FirstHalf_Odds(WebDriver driver){
        try {
            clickElement(driver, By.id("bookmark-under-over"));
            clickElement(driver, By.id("bookmark-under-over-1hf"));
            WebElement block = loadElement(driver, By.id("block-under-over-1hf"));
            return loadOdds_OverUnder(block, "OverUnder_FirstHalf");
        } catch (Exception ignored) {
            return new ArrayList<>();
        }
    }

    private List<Odd> load_OverUnder_SecondHalf_Odds(WebDriver driver){
        try {
            clickElement(driver, By.id("bookmark-under-over"));
            clickElement(driver, By.id("bookmark-under-over-2hf"));
            WebElement block = loadElement(driver, By.id("block-under-over-2hf"));
            return loadOdds_OverUnder(block, "OverUnder_SecondHalf");
        } catch (Exception ignored) {
            return new ArrayList<>();
        }
    }

    private List<Odd> loadOdds_1x2(WebElement block, String description){
        List<Odd> oddList = new LinkedList<>();
        List<WebElement> oddTable;
        try {
            oddTable = oddTable(block);
        } catch (Exception e){
            return oddList;
        }
        for(WebElement oddRaw:oddTable){
            try {
                String bookmaker = bookmaker(oddRaw);
                double[] oddValues = oddValues(oddRaw, 3);
                double firstWin = oddValues[0];
                double draw = oddValues[1];
                double secondWin = oddValues[2];
                Odd odd = new Odd_1x2(bookmaker, description, firstWin, draw, secondWin);
                oddList.add(odd);
            } catch (Exception ignored) {
            }
        }
        return oddList;
    }

    private List<Odd> loadOdds_OverUnder(WebElement block, String description) {
        List<Odd> oddList = new LinkedList<>();
        List<WebElement> oddTable;
        try {
            oddTable = oddTable(block);
        } catch (Exception e){
            return oddList;
        }
        for(WebElement oddRaw:oddTable){
            try {
                String bookmaker = bookmaker(oddRaw);
                double total = total_OverUnder(oddRaw);
                double[] oddValues = oddValues(oddRaw, 2);
                double over = oddValues[0];
                double under = oddValues[1];
                Odd odd = new Odd_OverUnder(bookmaker, description, total, over, under);
                oddList.add(odd);
            } catch (Exception ignored) {
            }
        }
        return oddList;
    }

    private List<WebElement> oddTable(WebElement block){
        return block.findElements(By.xpath("table/tbody/tr"));
    }

    private String bookmaker(WebElement oddRaw) throws Exception{
        return oddRaw.findElement(By.className("elink")).getAttribute("title");
    }

    private double total_OverUnder(WebElement oddRaw) throws Exception{
        List<WebElement> rawElements = oddRaw.findElements(By.tagName("td"));
        WebElement totalElement = rawElements.get(1);
        String totalString = totalElement.getText();
        return Double.parseDouble(totalString);
    }

    private double[] oddValues(WebElement oddRaw, int count) throws Exception{
        double[] oddValues = new double[count];
        List<WebElement> odds = oddRaw.findElements(By.className("odds-wrap"));
        for(int i=0;i<count;i++){
            oddValues[i] = Double.parseDouble(odds.get(i).getText());
        }
        return oddValues;
    }

    @Override
    public void loadElements(WebDriver driver) {
        loadEventIds(driver);
    }

    private void loadEventIds(WebDriver driver){
        List<WebElement> fixtures = driver.findElements(By.xpath("//div/table/tbody/tr"));
        //Queue<String> eventIds = new ConcurrentLinkedQueue<>();
        List<String> eventIds1 = new ArrayList<>();
        for(WebElement fixture:fixtures){
            String id = fixture.getAttribute("id");
            //eventIds.add(id);
            eventIds1.add(id);
        }
        runMatchExecutorService(new ConcurrentLinkedQueue<>(eventIds1.subList(0,100)));
        //runMatchExecutorService(eventIds);
    }

    private void runMatchExecutorService(Queue<String> eventIds) {
        int threadNumber = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);
        List<Callable<Void>> creators = new ArrayList<>();
        for(int i=0;i<threadNumber;i++){
            creators.add(() -> {
                WebDriver driver = new ChromeDriver(options);
                while(true){
                    String eventId = eventIds.poll();
                    if(eventId!=null){
                        String windowUrl = basicUrl + "match/" + eventId.replace("g_1_","");
                        driver.get(windowUrl);
                        try {
                            clickElement(driver, By.id("a-match-odds-comparison"));
                            List<Odd> oddList = loadOddList(driver);
                            OddsContainer container = new OddsContainer();
                            container.setId(eventId);
                            container.setOdds(oddList);
                            urls.add(container.toString());
                        } catch (Exception ignored) {}
                    }else {
                        break;
                    }
                }
                driver.close();
                return null;
            });
        }
        try {
            executorService.invokeAll(creators);
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}