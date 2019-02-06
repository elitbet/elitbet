package com.elitbet.events.parser;

import com.elitbet.events.parser.service.FootballService;
import com.elitbet.events.parser.service.FootballStatisticService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EventsParserApplication {
    public static void main(String[] args) {
        SpringApplicationBuilder app = new SpringApplicationBuilder(EventsParserApplication.class).web(WebApplicationType.NONE);
        ApplicationContext context = app.run(args);
        FootballService footballStatisticService = context.getBean(FootballStatisticService.class);
        footballStatisticService.run();
    }
}