package com.elitbet.wagers.services;

import com.elitbet.wagers.model.entities.Outcome;
import com.elitbet.wagers.model.entities.User;
import com.elitbet.wagers.model.entities.Wager;
import com.elitbet.wagers.model.entities.WagerStatus;
import com.elitbet.wagers.repositories.WagerRepository;
import com.elitbet.wagers.util.SecurityUtil;
import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.mockito.PowerMockito.*;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.logging.Logger;

@TestConfiguration
class WagerServiceTestContextConfiguration {
    @Bean
    public WagerService wagerService() {
        return new WagerService();
    }
}


@RunWith(PowerMockRunner.class)
@ContextConfiguration(classes = WagerServiceTestContextConfiguration.class)
//@SpringBootTest(classes = WagerServiceTestContextConfiguration.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PrepareForTest(SecurityUtil.class)
@PowerMockIgnore("javax.management.*")
public class WagerServiceTestSuit {
    private final static Logger logger = Logger.getLogger(WagerServiceTestSuit.class.getName());

    @Autowired
    WagerService wagerService = new WagerService();

    @MockBean
    WagerRepository wagerRepository;

    @MockBean
    UserService userService;

    @MockBean
    OutcomeService outcomeService;

    @Test
    public void getAllForCurrentUser(){
        PageRequest of = PageRequest.of(0,20,new Sort(Sort.Direction.DESC,"wagerId"));
        List<Wager> expected = Arrays.asList(new Wager(null,null, WagerStatus.NEW,14.2,10,0,new Date(0)), new Wager(null,null, WagerStatus.NEW,5.2,100,0,new Date(0)));
        Page<Wager> wagerPage = Mockito.mock(Page.class);
        mockStatic(SecurityUtil.class);
        when(SecurityUtil.getCurrentUsername()).thenReturn("romanchi");
        when(wagerPage.getContent()).thenReturn(expected);
        when(wagerRepository.findAllByUser_Username(SecurityUtil.getCurrentUsername(), of)).thenReturn(wagerPage);


        List<Wager> actual = wagerService.getAllForCurrentUser(of);
        logger.info(String.valueOf(actual.size()));
        assertEquals(expected,actual);
    }

    @Test(expected = InvalidParameterException.class)
    public void saveWagerBadId(){
        Wager wager = new Wager();
        wager.setWagerId(4);

        wagerService.saveWager(wager);
    }

    @Test(expected = InvalidParameterException.class)
    public void saveWagerBadOutcome(){
        Wager wager = new Wager();
        wager.setOutcome(null);

        wagerService.saveWager(wager);
    }

    @Test(/*expected = InvalidParameterException.class*/)
    public void saveWagerBadBetValue(){
        Wager wager = new Wager();
        Outcome outcome = new Outcome();
        outcome.setOutcomeId(1L);
        wager.setOutcome(outcome);
        mockStatic(SecurityUtil.class);
        when(SecurityUtil.getCurrentUsername()).thenReturn("romanchi");
        when(outcomeService.getById(1L)).thenReturn(outcome);
        when(userService.getByUsername(SecurityUtil.getCurrentUsername())).thenReturn(new User());
        try {
            wagerService.saveWager(wager);
            fail();
        }catch (InvalidParameterException ex){
            Assert.assertEquals("Bad bet value",ex.getMessage());
        }
    }

    @Test
    public void saveWagerBadOddsValue(){
        Wager wager = new Wager();
        Outcome outcome = new Outcome();
        outcome.setOutcomeId(1L);
        outcome.setOdds(123);
        wager.setOutcome(outcome);
        wager.setBetValue(12);
        mockStatic(SecurityUtil.class);
        when(SecurityUtil.getCurrentUsername()).thenReturn("romanchi");
        when(outcomeService.getById(1L)).thenReturn(outcome);
        when(userService.getByUsername(SecurityUtil.getCurrentUsername())).thenReturn(new User());
        try {
            wagerService.saveWager(wager);
            fail("Exception not thrown");
        }catch (InvalidParameterException ex){
            Assert.assertEquals("Bad odds value",ex.getMessage());
        }
    }

    @Test
    public void saveWagerDifferentOddsValues(){
        Wager wager = new Wager();
        Outcome outcome = new Outcome();
        outcome.setOutcomeId(1L);
        outcome.setOdds(123);
        wager.setOutcome(outcome);
        wager.setBetValue(12);
        wager.setOdds(122);
        mockStatic(SecurityUtil.class);
        when(SecurityUtil.getCurrentUsername()).thenReturn("romanchi");
        when(outcomeService.getById(1L)).thenReturn(outcome);
        when(userService.getByUsername(SecurityUtil.getCurrentUsername())).thenReturn(new User());
        try {
            wagerService.saveWager(wager);
            fail("Exception not thrown");
        }catch (InvalidParameterException ex){
            Assert.assertEquals("Bad odds value",ex.getMessage());
        }
    }

    @Test
    public void saveWagerGood(){
        Wager wager = new Wager();
        Outcome outcome = new Outcome();
        outcome.setOutcomeId(1L);
        outcome.setOdds(123);
        wager.setOutcome(outcome);
        wager.setBetValue(12);
        wager.setOdds(123);
        mockStatic(SecurityUtil.class);
        when(SecurityUtil.getCurrentUsername()).thenReturn("romanchi");
        when(outcomeService.getById(1L)).thenReturn(outcome);
        when(userService.getByUsername(SecurityUtil.getCurrentUsername())).thenReturn(new User());
        when(wagerRepository.save(wager)).thenReturn(wager);
        Wager actual = wagerService.saveWager(wager);

        assertEquals(WagerStatus.NEW, actual.getWagerStatus());
    }

    @Test(expected = InvalidParameterException.class)
    public void getByIdBadId(){
        wagerService.getById(-1);
    }

    @Test(expected = NoSuchElementException.class)
    public void getByIdNotFound(){
        when(wagerRepository.findById(1L)).thenReturn(Optional.empty());
        wagerService.getById(1);
    }

    @Test
    public void getByIdGood(){
        Wager wager = new Wager();
        when(wagerRepository.findById(1L)).thenReturn(Optional.of(new Wager()));

        wagerService.getById(1);

        verify(wagerRepository,times(1)).findById(1L);
    }

    @Test(expected = AccessDeniedException.class)
    public void getByIdForUserAccessDenied(){
        long id = 1;
        Wager wager = new Wager();
        wager.setWagerId(id);
        User user = new User();
        user.setUsername("mykola");
        wager.setUser(user);
        mockStatic(SecurityUtil.class);
        when(SecurityUtil.getCurrentUsername()).thenReturn("romanchi");
        when(wagerRepository.findById(id)).thenReturn(Optional.of(wager));

        wagerService.getByIdForCurrentUser(id);
    }

    @Test
    public void getByIdForUserGood(){
        long id = 1;
        Wager wager = new Wager();
        wager.setWagerId(id);
        User user = new User();
        user.setUsername("romanchi");
        wager.setUser(user);
        mockStatic(SecurityUtil.class);
        when(SecurityUtil.getCurrentUsername()).thenReturn("romanchi");
        when(wagerRepository.findById(id)).thenReturn(Optional.of(wager));

        Wager actual = wagerService.getByIdForCurrentUser(id);

        verify(wagerRepository,times(1)).findById(id);
        Assert.assertEquals("romanchi", actual.getUser().getUsername());
    }

    @Test
    public void deleteById(){
        long id = 1;
        Wager wager = new Wager();
        wager.setWagerId(id);
        User user = new User();
        user.setUsername("romanchi");
        wager.setUser(user);
        mockStatic(SecurityUtil.class);
        when(SecurityUtil.getCurrentUsername()).thenReturn("romanchi");
        when(wagerRepository.findById(id)).thenReturn(Optional.of(wager));
        doNothing().when(wagerRepository).delete(wager);

        wagerService.deleteById(1);

        verify(wagerRepository,times(1)).delete(wager);

    }

    @Test
    public void updateWagerPassed(){
        wagerService = spy(wagerService);
        Wager wager = new Wager();
        wager.setWagerId(1);
        wager.setWagerStatus(WagerStatus.PASSED);

        Wager wagerDatabase = new Wager();
        wagerDatabase.setWagerId(1);
        wagerDatabase.setOdds(14.3);
        wagerDatabase.setBetValue(10);

        doReturn(wagerDatabase).when(wagerService).getById(wager.getWagerId());;

        when(wagerRepository.save(wagerDatabase)).thenReturn(wagerDatabase);

        Wager actual = wagerService.updateWager(wager);
        assertEquals(Double.compare(143.0, actual.getPayout()),0);
    }

    @Test
    public void updateWagerNotPassed(){
        wagerService = spy(wagerService);
        Wager wager = new Wager();
        wager.setWagerId(1);
        wager.setWagerStatus(WagerStatus.NOT_PASSED);

        Wager wagerDatabase = new Wager();
        wagerDatabase.setWagerId(1);
        wagerDatabase.setOdds(14.3);
        wagerDatabase.setBetValue(10);

        doReturn(wagerDatabase).when(wagerService).getById(wager.getWagerId());;

        when(wagerRepository.save(wagerDatabase)).thenReturn(wagerDatabase);

        Wager actual = wagerService.updateWager(wager);
        assertEquals(Double.compare(0, actual.getPayout()),0);
    }

    @Test
    public void updateWagerNew(){
        wagerService = spy(wagerService);
        Wager wager = new Wager();
        wager.setWagerId(1);
        wager.setWagerStatus(WagerStatus.NEW);

        Wager wagerDatabase = new Wager();
        wagerDatabase.setWagerId(1);
        wagerDatabase.setOdds(14.3);
        wagerDatabase.setBetValue(10);

        doReturn(wagerDatabase).when(wagerService).getById(wager.getWagerId());;

        when(wagerRepository.save(wagerDatabase)).thenReturn(wagerDatabase);

        Wager actual = wagerService.updateWager(wager);
        assertEquals(Double.compare(0, actual.getPayout()),0);
    }

    @Test
    public void updateWagerReturned(){
        wagerService = spy(wagerService);
        Wager wager = new Wager();
        wager.setWagerId(1);
        wager.setWagerStatus(WagerStatus.RETURNED);

        Wager wagerDatabase = new Wager();
        wagerDatabase.setWagerId(1);
        wagerDatabase.setOdds(14.3);
        wagerDatabase.setBetValue(10);

        doReturn(wagerDatabase).when(wagerService).getById(wager.getWagerId());;

        when(wagerRepository.save(wagerDatabase)).thenReturn(wagerDatabase);

        Wager actual = wagerService.updateWager(wager);
        assertEquals(Double.compare(10, actual.getPayout()),0);
    }

}
