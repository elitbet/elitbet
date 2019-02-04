package com.elitbet.service;

import com.elitbet.model.statistic.FootballStatistic;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

@Service
public class FootballStatisticService {

    // TODO: 04.02.19  statistic in window

    public FootballStatistic loadStatistic(WebDriver driver){
        return new FootballStatistic();
    }

}
