package com.EventManager.repository;

import com.EventManager.model.OrderBuy;
import org.springframework.data.repository.CrudRepository;

public interface OrderBuyRepo extends CrudRepository<OrderBuy, Long> {

    public OrderBuy findById(long id);
}
