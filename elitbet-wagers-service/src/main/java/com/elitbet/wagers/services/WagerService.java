package com.elitbet.wagers.services;

import com.elitbet.wagers.model.entities.*;
import com.elitbet.wagers.repositories.WagerRepository;
import com.elitbet.wagers.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @author Roman Malyarchuk
 */
@Service
public class WagerService {

    private final static Logger logger = Logger.getLogger(WagerService.class.getName());

    @Autowired
    WagerRepository wagerRepository;
    @Autowired
    UserService userService;
    @Autowired
    OutcomeService outcomeService;

    public List<Wager> getAllForCurrentUser(PageRequest of){
        return wagerRepository
                .findAllByUser_Username(SecurityUtil.getCurrentUsername(), of).getContent();
    }

    public Wager saveWager(Wager wager) {
        if(wager.getWagerId() > 0){
            throw new InvalidParameterException("Wager id must be null. To update wager use PATCH");
        }
        if(wager.getOutcome()==null){
            throw new InvalidParameterException("Bad outcome");
        }
        Long outcomeId = wager.getOutcome().getOutcomeId();
        Outcome outcome = outcomeService.getById(outcomeId);
        wager.setOutcome(outcome);

        User user = userService.getByUsername(SecurityUtil.getCurrentUsername());

        wager.setUser(user);
        if(wager.getBetValue() <= 0){
            throw new InvalidParameterException("Bad bet value");
        }
        if(wager.getOdds() <= 0){
            throw new InvalidParameterException("Bad odds value");
        }
        if(Double.compare(wager.getOdds(),outcome.getOdds())!=0){
            throw new InvalidParameterException("Bad odds value");
        }
        wager.setPayout(0.0);
        wager.setWagerStatus(WagerStatus.NEW);
        wager.setTime(new Date());
        return wagerRepository.save(wager);
    }


    public Wager updateWager(Wager wager){
        Wager wagerDatabase = getById(wager.getWagerId());
        wagerDatabase.setWagerStatus(wager.getWagerStatus());

        //CALCULATE PAYOUT

        double payout = 0.0;
        double odds = wagerDatabase.getOdds();
        double betValue = wagerDatabase.getBetValue();

        switch (wager.getWagerStatus()){
            case PASSED:{
                payout = odds*betValue;
                break;
            }
            case NOT_PASSED:{
                payout = 0;
                break;
            }
            case NEW:{
                return wagerDatabase;
            }
            case RETURNED:{
                payout = betValue;
                break;
            }
        }

        wagerDatabase.setPayout(payout);

        return wagerRepository.save(wagerDatabase);
    }

    public Wager getById(long id){
        if(id <= 0){
            throw new InvalidParameterException("Wager id must be positive long");
        }

        Wager wager = wagerRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("No wager found by id " + id));
        return wager;
    }

    public Wager getByIdForCurrentUser(long id){
        Wager wager = getById(id);
        if(!Objects.equals(wager.getUser().getUsername(),SecurityUtil.getCurrentUsername())){
            throw new AccessDeniedException("Invalid wager id for current user");
        }

        return wager;
    }

    public void deleteById(long id) {

        Wager wager = getByIdForCurrentUser(id);

        wagerRepository.delete(wager);

    }
}
