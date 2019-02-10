package org.elitbet.core.service;

import org.elitbet.core.model.Outcome;
import org.elitbet.core.model.OutcomePeriod;
import org.elitbet.core.model.OutcomeType;
import org.elitbet.core.model.dto.OutcomeDTO;

import java.util.List;

public interface OutcomeService {

    void updateOutcome(OutcomeDTO outcomeDTO);
    Outcome getOutcome(Integer outcomeID);
    List<Outcome> getEventOutcomesInLine(Integer eventID);
    List<Outcome> getEventOutcomes(Integer eventID);
    Outcome getOutcome(Integer eventID, OutcomeType type, OutcomePeriod period);

}
