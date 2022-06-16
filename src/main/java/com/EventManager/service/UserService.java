package com.EventManager.service;

import com.EventManager.model.Event;
import com.EventManager.model.User;
import com.EventManager.repository.EventRepo;
import com.EventManager.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EventRepo eventRepo;

    public User createUser(User user){
        try{
            if(user.getEmail().equalsIgnoreCase(userRepo.findByEmail(user.getEmail()).getEmail())){
                return null;
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return userRepo.save(user);
    }

    public User findUserByEmail(String email){
        return userRepo.findByEmail(email);
    }

    public User findUserById(long id){
        return userRepo.findById(id).orElse(null);
    }

    public List<User> findAllUser(){
        return (List<User>) userRepo.findAll();
    }

    public User updateUser(User user){
        return userRepo.save(user);
    }

    public User deleteUser(String email){
        User user = userRepo.findByEmail(email);
        userRepo.delete(user);
        return user;
    }

    public void addAssistEventToUser(long idBuyer, long idEvent) {
        User user = userRepo.findById(idBuyer).orElse(null);
        Event event = eventRepo.findById(idEvent).orElse(null);
        if ( user != null && event != null) {
            user.getAttendanceEvents().add(event);
            this.updateUser(user);
        }
    }

    public void addCreatedEventToUser(long idBuyer, long idEvent) {
        User user = userRepo.findById(idBuyer).orElse(null);
        Event event = eventRepo.findById(idEvent).orElse(null);
        if ( user != null && event != null) {
            user.getCreatedEvents().add(event);
            this.updateUser(user);
        }
    }
}
