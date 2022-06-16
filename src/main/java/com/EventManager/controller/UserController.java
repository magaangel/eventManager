package com.EventManager.controller;

import com.EventManager.model.User;
import com.EventManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventmanager/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity createUser(@RequestBody User user){
        if(userService != null){
            try{
                if(user.getEmail().equalsIgnoreCase(userService.findUserByEmail(user.getEmail()).getEmail())){
                    String msg = "Email found in database";
                    return ResponseEntity.badRequest().body(msg);
                }
            }catch(Exception e){
                e.getStackTrace();
            }
        }
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") long id){
        return new ResponseEntity<User>(userService.findUserById(id), HttpStatus.OK);
    }

}
