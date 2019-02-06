package com.elitbet.wagers.repositories;

import com.elitbet.wagers.model.entities.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutcomeRepository extends JpaRepository<Outcome,Long> {

}
