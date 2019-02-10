package org.elitbet.statistic.controller;

import org.elitbet.statistic.model.Outcome;
import org.elitbet.statistic.model.OutcomePeriod;
import org.elitbet.statistic.model.Statistic;
import org.elitbet.statistic.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

@Controller("/statistic/")
public class MainController {

    @Autowired
    StatisticService statisticService;

    @PostMapping
    public ResponseEntity<Statistic> createStatistic(@RequestBody Statistic statistic) throws URISyntaxException {
        statistic = statisticService.createStatistic(statistic);
        URI uri = new URI("localhost:8081/statistic/" + statistic.getId());
        return ResponseEntity.ok(statistic);
    }

    @PatchMapping
    public ResponseEntity<Statistic> updateStatistic(@RequestBody Statistic statistic){
        statistic = statisticService.updateStatistic(statistic);
        return ResponseEntity.ok(statistic);
    }

    @GetMapping("{statisticId}")
    public ResponseEntity<Statistic> getStatistic(@PathVariable Integer statisticId){
        Statistic statistic = statisticService.getStatistic(statisticId);
        return ResponseEntity.ok(statistic);
    }

    @GetMapping("{statisticId}/outcomes")
    public ResponseEntity<Set<Outcome>> getOutcomes(@PathVariable Integer statisticId,
                                                    @RequestParam(value = "period") OutcomePeriod period){
        Set<Outcome> outcomes = statisticService.getOutcomes(statisticId, period);
        return ResponseEntity.ok(outcomes);
    }

}
