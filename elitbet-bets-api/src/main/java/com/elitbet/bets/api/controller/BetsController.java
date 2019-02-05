package com.elitbet.bets.api.controller;

import com.elitbet.bets.api.model.dto.WagerDTO;
import com.elitbet.bets.api.model.entities.Wager;
import com.elitbet.bets.api.services.WagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/bets")
public class BetsController {

    @Autowired
    WagerService wagerService;

    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public List<WagerDTO> testBet(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        wagerService.getAllByUsername(userDetails.getUsername());
        return wagerService.getAllByUsername(userDetails.getUsername()).stream().map(WagerDTO::new).collect(Collectors.toList());
    }
}
