package com.elitbet.bets.api.repositories;

import com.elitbet.bets.api.model.entities.Wager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WagerRepository extends JpaRepository<Wager, Long> {
    List<Wager> findAllByUser_Username(String username);
}
