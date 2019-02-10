package org.elitbet.statistic.service;

import org.elitbet.statistic.model.Statistic;

public interface Calculator {

    boolean calculate(Statistic statistic) throws NullPointerException;

}
