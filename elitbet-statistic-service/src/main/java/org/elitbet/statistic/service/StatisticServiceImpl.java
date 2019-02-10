package org.elitbet.statistic.service;

import org.elitbet.statistic.model.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Override
    public Statistic createStatistic(EventType eventType, Statistic statistic) {
        statistic.setId(15);
        return statistic;
    }

    @Override
    public Statistic getStatistic(Integer statisticID) {
        Statistic statistic = new Statistic();
        statistic.setId(statisticID);
        statistic.setHomeName("Dynamo");
        statistic.setAwayName("Metalist");
        return statistic;
    }

    @Override
    public Statistic updateStatistic(Statistic statistic) {
        return statistic;
    }

    @Override
    public Set<Outcome> getOutcomes(Integer statisticID, OutcomePeriod period) {
        Outcome outcome = new Outcome();
        outcome.setType(OutcomeType.DRAW);
        outcome.setPeriod(OutcomePeriod.FULL_TIME);
        outcome.setStatus(OutcomeStatus.NO_STATUS);
        return new HashSet<>(Collections.singletonList(outcome));
    }
}
