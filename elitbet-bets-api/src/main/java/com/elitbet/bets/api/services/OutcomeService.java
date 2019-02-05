package com.elitbet.bets.api.services;

import com.elitbet.bets.api.model.entities.Outcome;
import com.elitbet.bets.api.repositories.OutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.NoSuchElementException;

@Service
public class OutcomeService {
    @Autowired
    OutcomeRepository outcomeRepository;

    public Outcome getById(Long outcomeId) {
        if(outcomeId == null || outcomeId <=0){
            throw new InvalidParameterException("Bad outcome id");
        }
        return outcomeRepository.findById(outcomeId).orElseThrow(()-> new NoSuchElementException("No outcome found by id " + outcomeId));
    }
}
