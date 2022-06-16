package com.EventManager.service;

import com.EventManager.model.OrderBuy;
import com.EventManager.repository.OrderBuyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderBuyService {
    @Autowired
    private OrderBuyRepo orderBuyRepo;

    public OrderBuy createOrder(OrderBuy orderBuy){
        return orderBuyRepo.save(orderBuy);
    }

}
