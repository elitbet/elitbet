package org.elitbet.statistic.service.calculators;

import org.elitbet.statistic.model.Statistic;
import org.elitbet.statistic.service.Calculator;

public class HomeWin_FullTime_Calculator implements Calculator {
    @Override
    public boolean calculate(Statistic statistic) throws NullPointerException{
        Integer homeFullTimeGoals = statistic.getHomeFullTimeGoals();
        Integer awayFullTimeGoals = statistic.getAwayFullTimeGoals();
        return homeFullTimeGoals>awayFullTimeGoals;
    }
}
