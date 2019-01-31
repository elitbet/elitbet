package com.elitbet.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashscoreLoader {

    @Autowired
    private ChromeOptions options;


    public void run(){

        WebDriver driver = new ChromeDriver(options);;

        String url = "https://www.flashscore.com/";
        driver.get(url);
        List<WebElement> soccerFixtures = driver.findElements(By.className("soccer"));

        for(WebElement fixture:soccerFixtures){
            System.out.println(fixture.getText());
        }

    }

}
