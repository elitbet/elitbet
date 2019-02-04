package com.elitbet.repository;

import com.elitbet.model.EventRequest;
import com.elitbet.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRequestRepository extends JpaRepository<EventRequest,String> {
    List<EventRequest> findAllByPriority(Priority priority);
}