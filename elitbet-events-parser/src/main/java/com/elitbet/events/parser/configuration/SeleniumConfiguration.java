package com.elitbet.events.parser.configuration;

import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfiguration {

    @Bean
    ChromeOptions chromeOptions(){
        System.setProperty("webdriver.chrome.driver", "elitbet-events-parser/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        return options;
    }
}
