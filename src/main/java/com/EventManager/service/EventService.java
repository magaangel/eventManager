package com.EventManager.service;

import com.EventManager.model.Event;
import com.EventManager.model.OrderBuy;
import com.EventManager.model.Ticket;
import com.EventManager.repository.EventRepo;
import com.EventManager.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private OrderBuyService orderBuyService;


    public Event createEvent(Long idUser, Event event){

        eventRepo.save(event);
        userService.addCreatedEventToUser(idUser, event.getId());

        return event;
    }

    public Event findEventById(long id){
        Optional<Event> op = eventRepo.findById(id);
        return op.orElse(null);
    }

    public List<Event> findAllEvents(){
        return (List<Event>) eventRepo.findAll();
    }

    public Event updateEvent(Event event){
        return eventRepo.save(event);
    }

    public Event addTicket(Long id, Ticket ticket){
        Event event = this.findEventById(id);
        event.getTickets().add(ticket);
        this.updateEvent(event);
        return event;
    }

    public Event modifyEvent(Event event){
        Event eventSaved = eventRepo.findById(event.getId()).orElse(null);
        if(eventSaved != null){
            eventSaved.setTittle(event.getTittle());
            eventSaved.setDescription(event.getDescription());
            eventSaved.setDate(event.getDate());
            eventSaved.setTime(event.getTime());
            eventSaved.setDurationHours(event.getDurationHours());
            eventSaved.setModality(event.getModality());
            eventSaved.setAddress(event.getAddress());
            eventSaved.setTickets(event.getTickets());
            this.updateEvent(eventSaved);
            return eventSaved;
        }else{
            return null;
        }
    }

    public Event deleteEvent(Long idEvent){
        Event event = eventRepo.findById(idEvent).orElse(null);
        eventRepo.delete(event);
        return event;
    }

    public OrderBuy buyTicketEvent(OrderBuy orderBuy){
        orderBuyService.createOrder(orderBuy);

        for (long ticketId : orderBuy.getTicketsIds()) {
            ticketService.sellTicket(ticketId, 1);
        }

        userService.addAssistEventToUser(orderBuy.getIdBuyer(), orderBuy.getIdEvent());

        return orderBuy;
    }


}
