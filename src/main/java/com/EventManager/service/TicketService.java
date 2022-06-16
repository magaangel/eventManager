package com.EventManager.service;

import com.EventManager.model.Ticket;
import com.EventManager.repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    @Autowired
    private TicketRepo ticketRepo;

    public boolean sellTicket(long ticketId, int quantity) {
        boolean success = false;
        Ticket ticket = ticketRepo.findById(ticketId).orElse(null);

        if(ticket != null) {
            ticket.setTicketAble(ticket.getTicketAble() - quantity);
            ticketRepo.save(ticket);
            success = true;
        }

        return success;
    }

}
