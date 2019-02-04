package com.elitbet.bets.api.services;

import com.elitbet.bets.api.model.Wager;
import com.elitbet.bets.api.repositories.WagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WagerService {
    @Autowired
    WagerRepository wagerRepository;

    public List<Wager> getAll(){
        return wagerRepository.findAll();
    }
}
