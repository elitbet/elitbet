package com.elitbet.bets.api.repositories;

import com.elitbet.bets.api.model.Wager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WagerRepository extends JpaRepository<Wager, Long> {
}
