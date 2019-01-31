package com.elitbet;

import com.elitbet.service.FlashscoreLoader;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplicationBuilder app = new SpringApplicationBuilder(Application.class).web(WebApplicationType.NONE);
        ApplicationContext context = app.run(args);
        FlashscoreLoader flashscoreLoader = context.getBean(FlashscoreLoader.class);
        flashscoreLoader.run();
    }
}