package com.elitbet.authorization.repositories;

import com.elitbet.authorization.model.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User repository for CRUD operations.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
