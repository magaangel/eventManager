package com.EventManager.controller;

import com.EventManager.model.Event;
import com.EventManager.model.OrderBuy;
import com.EventManager.model.User;
import com.EventManager.service.EventService;
import com.EventManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventmanager/api/v1")
public class EventController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;


    @PostMapping("/create-event/{idUser}")
    public ResponseEntity createEvent(@PathVariable("idUser") long id, @RequestBody Event event){
        User user = userService.findUserById(id);
        if(user.getCreator()){
            return ResponseEntity.ok(eventService.createEvent(id, event));
        }else{
            String msg = "User isn't a creator";
            return ResponseEntity.badRequest().body(msg);
        }
    }

    @PutMapping("/buy-ticket")
    public ResponseEntity<OrderBuy> buyTickets(@RequestBody OrderBuy orderBuy){
        return new ResponseEntity<OrderBuy>(eventService.buyTicketEvent(orderBuy), HttpStatus.OK);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<Event> findEventById(@PathVariable("id") long id){
        return new ResponseEntity<Event>(eventService.findEventById(id), HttpStatus.OK);
    }

    @GetMapping("/event")
    public ResponseEntity<List<Event>> findAllEvents(){
        return new ResponseEntity<List<Event>>(eventService.findAllEvents(), HttpStatus.OK);
    }

}
