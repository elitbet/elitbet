package org.elitbet.statistic.service;

import org.elitbet.statistic.model.Outcome;
import org.elitbet.statistic.model.OutcomePeriod;
import org.elitbet.statistic.model.Statistic;

import java.util.Set;

public interface StatisticService {

    Statistic createStatistic(Statistic statistic);
    Statistic getStatistic(Integer statisticID);
    Statistic updateStatistic(Statistic statistic);
    Set<Outcome> getOutcomes(Integer statisticID, OutcomePeriod period);

}
