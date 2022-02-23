package com.cs.logger.repository;

import com.cs.logger.domain.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer> {
}
