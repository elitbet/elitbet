package com.elitbet.wagers.repositories;

import com.elitbet.wagers.model.entities.Wager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WagerRepository extends JpaRepository<Wager, Long> {
    Page<Wager> findAllByUser_Username(String username, Pageable pageable);
}
