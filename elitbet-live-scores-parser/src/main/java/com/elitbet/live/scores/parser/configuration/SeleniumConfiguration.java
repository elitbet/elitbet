package com.elitbet.live.scores.parser.configuration;

import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfiguration {

    @Bean
    ChromeOptions chromeOptions(){
        System.setProperty("webdriver.chrome.driver", "elitbet-live-scores-parser/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);//todo change to true
        return options;
    }
}
