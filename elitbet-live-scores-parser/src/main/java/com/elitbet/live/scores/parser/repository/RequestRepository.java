package com.elitbet.live.scores.parser.repository;

import com.elitbet.live.scores.parser.model.Request;
import com.elitbet.live.scores.parser.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request,String> {
    List<Request> findAllByPriority(Priority priority);
}