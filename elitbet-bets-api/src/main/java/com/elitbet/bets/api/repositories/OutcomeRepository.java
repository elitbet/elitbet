package com.elitbet.bets.api.repositories;

import com.elitbet.bets.api.model.entities.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutcomeRepository extends JpaRepository<Outcome,Long> {

}
