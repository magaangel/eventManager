package com.EventManager.repository;

import com.EventManager.model.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepo extends CrudRepository <Ticket, Long> {

    public Optional<Ticket> findById(Long email);
}
