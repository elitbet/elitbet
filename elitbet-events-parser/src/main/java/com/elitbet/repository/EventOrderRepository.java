package com.elitbet.repository;

import com.elitbet.model.EventOrder;
import com.elitbet.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventOrderRepository extends JpaRepository<EventOrder,String> {
    List<EventOrder> findAllByPriority(Priority priority);
}