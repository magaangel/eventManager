package com.EventManager.repository;

import com.EventManager.model.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EventRepo extends CrudRepository<Event, Long> {

    public Optional<Event> findById(Long id);

}
