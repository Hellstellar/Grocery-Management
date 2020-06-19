package com.hellstellar.recieveorderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hellstellar.recieveorderservice.models.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {

}
