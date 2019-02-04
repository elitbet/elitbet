package com.elitbet;

import com.elitbet.service.FootballOddsService;
import com.elitbet.service.FootballService;
import com.elitbet.service.FootballStatisticService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplicationBuilder app = new SpringApplicationBuilder(Application.class).web(WebApplicationType.NONE);
        ApplicationContext context = app.run(args);
//        FootballService footballStatisticService = context.getBean(FootballStatisticService.class);
//        footballStatisticService.run();
        FootballService footballOddsService = context.getBean(FootballOddsService.class);
        footballOddsService.run();
    }
}