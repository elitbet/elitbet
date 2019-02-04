package com.elitbet.bets.api.controller;

import com.elitbet.bets.api.model.Wager;
import com.elitbet.bets.api.services.WagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/bets")
public class BetsController {

    @Autowired
    WagerService wagerService;

    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public List<Wager> testBet(){
        return wagerService.getAll();
    }
}
