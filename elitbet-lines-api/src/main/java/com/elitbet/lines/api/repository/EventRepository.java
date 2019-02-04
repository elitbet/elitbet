package com.elitbet.lines.api.repository;

import com.elitbet.lines.api.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
}
