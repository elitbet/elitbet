package com.elitbet.configuration;

import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfiguration {

    @Bean
    ChromeOptions chromeOptions(){
        System.setProperty("webdriver.chrome.driver", "elitbet-live-scores-parser/chromedriver");
        System.out.println(System.getProperty("webdriver.chrome.driver"));
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        return options;
    }
}
