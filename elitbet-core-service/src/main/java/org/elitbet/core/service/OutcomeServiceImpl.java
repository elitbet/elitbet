package org.elitbet.core.service;

import org.elitbet.core.model.Outcome;
import org.elitbet.core.model.OutcomePeriod;
import org.elitbet.core.model.OutcomeType;
import org.elitbet.core.model.dto.OutcomeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutcomeServiceImpl implements OutcomeService {
    @Override
    public void updateOutcome(OutcomeDTO outcomeDTO) {
        System.out.println("Updated: " + outcomeDTO);
    }

    @Override
    public Outcome getOutcome(Integer outcomeID) {
        return null;
    }

    @Override
    public List<Outcome> getEventOutcomesInLine(Integer eventID) {
        return null;
    }

    @Override
    public List<Outcome> getEventOutcomes(Integer eventID) {
        return null;
    }

    @Override
    public Outcome getOutcome(Integer eventID, OutcomeType type, OutcomePeriod period) {
        return null;
    }
}
