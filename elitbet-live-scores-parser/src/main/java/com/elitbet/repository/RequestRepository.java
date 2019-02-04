package com.elitbet.repository;

import com.elitbet.model.Request;
import com.elitbet.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request,String> {
    List<Request> findAllByPriority(Priority priority);
}