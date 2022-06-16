package com.EventManager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private Boolean creator;
    @ElementCollection
    private List<Event> createdEvents;
    @ElementCollection
    private List<Event> attendanceEvents;

}
