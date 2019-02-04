package com.elitbet;

import com.elitbet.service.ResponseService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplicationBuilder app = new SpringApplicationBuilder(Application.class).web(WebApplicationType.NONE);
        ApplicationContext context = app.run(args);
        ResponseService responseService = context.getBean(ResponseService.class);
        responseService.run();
    }
}