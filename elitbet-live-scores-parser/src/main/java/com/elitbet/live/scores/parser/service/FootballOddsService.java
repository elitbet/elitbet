package com.elitbet.live.scores.parser.service;

import com.elitbet.live.scores.parser.model.odds.OutcomePeriod;
import com.elitbet.live.scores.parser.model.Request;
import com.elitbet.live.scores.parser.model.odds.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
class FootballOddsService implements SeleniumInterface {

    List<Odd> loadOdds(WebDriver driver, Request request) throws Exception {

        clickElement(driver, By.id("a-match-odds-comparison"));

        List<Odd> oddList = new LinkedList<>();
        if(request.isLoad_1x2_FullTime()){
            oddList.addAll(load_1x2_FullTime_Odds(driver));
        }
        if(request.isLoad_1x2_FirstHalf()){
            oddList.addAll(load_1x2_1stHalf_Odds(driver));
        }
        if(request.isLoad_1x2_SecondHalf()){
            oddList.addAll(load_1x2_2ndHalf_Odds(driver));
        }
        if(request.isLoad_OverUnder_FullTime()){
            oddList.addAll(load_OverUnder_FullTime_Odds(driver));
        }
        if(request.isLoad_OverUnder_FirstHalf()){
            oddList.addAll(load_OverUnder_FirstHalf_Odds(driver));
        }
        if(request.isLoad_OverUnder_SecondHalf()){
            oddList.addAll(load_OverUnder_SecondHalf_Odds(driver));
        }
        return oddList;
    }

    private List<Odd> load_1x2_FullTime_Odds(WebDriver driver){
        try {
            clickElement(driver, By.id("bookmark-1x2"));
            clickElement(driver, By.id("bookmark-1x2-ft"));
            WebElement block = loadElement(driver, By.id("block-1x2-ft"));
            return loadOdds_1x2(block, OutcomePeriod.FULL_TIME);
        } catch (Exception ignored) {
            return new ArrayList<>();
        }
    }

    private List<Odd> load_1x2_1stHalf_Odds(WebDriver driver) {
        try {
            clickElement(driver, By.id("bookmark-1x2"));
            clickElement(driver, By.id("bookmark-1x2-1hf"));
            WebElement block = loadElement(driver, By.id("block-1x2-1hf"));
            return loadOdds_1x2(block, OutcomePeriod.FIRST_HALF);
        } catch (Exception ignored) {
            return new ArrayList<>();
        }
    }

    private List<Odd> load_1x2_2ndHalf_Odds(WebDriver driver){
        try {
            clickElement(driver, By.id("bookmark-1x2"));
            clickElement(driver, By.id("bookmark-1x2-2hf"));
            WebElement block = loadElement(driver, By.id("block-1x2-2hf"));
            return loadOdds_1x2(block, OutcomePeriod.SECOND_HALF);
        } catch (Exception ignored) {
            return new ArrayList<>();
        }
    }

    private List<Odd> load_OverUnder_FullTime_Odds(WebDriver driver){
        try {
            clickElement(driver, By.id("bookmark-under-over"));
            clickElement(driver, By.id("bookmark-under-over-ft"));
            WebElement block = loadElement(driver, By.id("block-under-over-ft"));
            return loadOdds_OverUnder(block, OutcomePeriod.FULL_TIME);
        } catch (Exception ignored) {
            return new ArrayList<>();
        }
    }

    private List<Odd> load_OverUnder_FirstHalf_Odds(WebDriver driver){
        try {
            clickElement(driver, By.id("bookmark-under-over"));
            clickElement(driver, By.id("bookmark-under-over-1hf"));
            WebElement block = loadElement(driver, By.id("block-under-over-1hf"));
            return loadOdds_OverUnder(block, OutcomePeriod.FIRST_HALF);
        } catch (Exception ignored) {
            return new ArrayList<>();
        }
    }

    private List<Odd> load_OverUnder_SecondHalf_Odds(WebDriver driver){
        try {
            clickElement(driver, By.id("bookmark-under-over"));
            clickElement(driver, By.id("bookmark-under-over-2hf"));
            WebElement block = loadElement(driver, By.id("block-under-over-2hf"));
            return loadOdds_OverUnder(block, OutcomePeriod.SECOND_HALF);
        } catch (Exception ignored) {
            return new ArrayList<>();
        }
    }

    private List<Odd> loadOdds_1x2(WebElement block, OutcomePeriod period){
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
                OddContainer container = new OddContainer_1x2(bookmaker,firstWin,draw,secondWin,period);
                oddList.addAll(container.toOdds());
            } catch (Exception ignored) {
            }
        }
        return oddList;
    }

    private List<Odd> loadOdds_OverUnder(WebElement block, OutcomePeriod period) {
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
                OddContainer container = new OddContainer_OverUnder(bookmaker, total, over, under, period);
                oddList.addAll(container.toOdds());
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

}