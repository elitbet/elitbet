package com.elitbet.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FootballOddsService extends FootballService {

    // TODO: 01.02.19 set in properties
    private final int THREADS_COUNT = 5;

    private Queue<String> eventIdList = new ConcurrentLinkedQueue<>();


    @Override
    protected void runExecutorService() {
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
//                        WebElement oddsWindow = driver.findElement(By.id("a-match-odds-comparison"));
//                        oddsWindow.click();
//                        List<WebElement> bookmakers = driver.findElements(By.xpath("//tbody/tr[1]/td[1]/div[1]/a"));
//                        bookmakers.forEach(bookmaker -> System.out.println(bookmaker.getText()));
                        try {
                            String firstWin = driver.findElement(By.className("0_1")).getText();
                            String draw = driver.findElement(By.className("o_0")).getText();
                            String secondWin = driver.findElement(By.className("o_2")).getText();
                            System.out.println("1:" + firstWin + ";X:" +  draw + ";2:" +  secondWin);
                        }catch (Exception ignored){}

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
//            System.out.println("--------------------");
//            System.out.println(fixture.getText());
//            fixture.click();
//            System.out.println(driver.getTitle());
        }
    }
}
