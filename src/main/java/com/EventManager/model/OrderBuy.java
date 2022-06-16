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
public class OrderBuy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long idEvent;
    private long idBuyer;
    @ElementCollection
    private List<Integer> ticketsIds;
    private long totalPrice;

}
