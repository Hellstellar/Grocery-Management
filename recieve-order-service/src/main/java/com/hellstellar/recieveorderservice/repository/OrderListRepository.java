package com.hellstellar.recieveorderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hellstellar.recieveorderservice.models.OrderList;


public interface OrderListRepository extends JpaRepository<OrderList, Integer>  {

}
