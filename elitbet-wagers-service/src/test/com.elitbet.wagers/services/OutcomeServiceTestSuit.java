package com.elitbet.wagers.services;


import com.elitbet.wagers.model.entities.Outcome;
import com.elitbet.wagers.repositories.OutcomeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.InvalidParameterException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.powermock.api.mockito.PowerMockito.when;


@TestConfiguration
class OutcomeServiceTestSuitConfiguration{
    @Bean
    public OutcomeService outcomeService(){
        return new OutcomeService();
    }
}

@RunWith(PowerMockRunner.class)
@ContextConfiguration(classes = OutcomeServiceTestSuitConfiguration.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PowerMockIgnore("javax.management.*")
public class OutcomeServiceTestSuit {

    @Autowired
    OutcomeService outcomeService;

    @MockBean
    OutcomeRepository outcomeRepository;

    @Test(expected = InvalidParameterException.class)
    public void getByIdBadNullOutcomeId(){
        outcomeService.getById(null);
    }

    @Test(expected = InvalidParameterException.class)
    public void getByIdNegativeOutcomeId(){
        outcomeService.getById(-1L);
    }

    @Test(expected = NoSuchElementException.class)
    public void getByIdNotFound(){
        when(outcomeRepository.findById(1L)).thenReturn(Optional.empty());

        outcomeService.getById(1L);
    }

    @Test
    public void getByIdGood(){
        when(outcomeRepository.findById(1L)).thenReturn(Optional.of(new Outcome()));

        outcomeService.getById(1L);

        verify(outcomeRepository,times(1)).findById(1L);
    }

}
